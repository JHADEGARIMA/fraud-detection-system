package com.elite.transaction_service.service;

import com.elite.transaction_service.config.Constants;
import com.elite.transaction_service.model.Transaction;
import com.elite.transaction_service.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

@Service
public class TransactionService {


    @Autowired
    private SMSService smsService;

    @Autowired
    private TwilioService  twilioService;

    private final TransactionRepository repository;
    private final KafkaTemplate<String, Transaction> kafkaTemplate;

    @Value("${kafka.topic.name:transaction-topic}")
    private String topicName;

    public TransactionService(TransactionRepository transactionRepository, KafkaTemplate<String, Transaction> kafkaTemplate) {
        this.repository = transactionRepository;
        this.kafkaTemplate = kafkaTemplate;

    }
    public Transaction processTransaction(Transaction transaction) {
        transaction.setTimestamp(LocalDateTime.now());

        transaction.set_fraud(transaction.getAmount() >= 10000);
        Transaction saved = repository.save(transaction);
        // Send to Kafka
        kafkaTemplate.send(Constants.TRANSACTION_TOPIC_NAME, transaction);

        if(transaction.is_fraud()){

              //  String message = "⚠️ Fraud Alert! Transaction of ₹" + transaction.getAmount() + " is suspicious.";
          //    smsService.sendSMS("917440546383", message); // put your number
                String message = "🚨 FRAUD DETECTED! Txn ID: " + saved.getId() + ", Amt: ₹" + saved.getAmount();
                twilioService.sendSMS("+918506857525", message); // Use your verified number
        }

        return saved;
    }
}
