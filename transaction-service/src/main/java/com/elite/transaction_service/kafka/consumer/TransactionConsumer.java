package com.elite.transaction_service.kafka.consumer;

import com.elite.transaction_service.model.FraudAlert;
import com.elite.transaction_service.model.Transaction;
import com.elite.transaction_service.repository.FraudAlertRepository;
import com.elite.transaction_service.service.EmailService;
import com.elite.transaction_service.service.TransactionService;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class TransactionConsumer {

    @Autowired
    private EmailService emailService;

    private final FraudAlertRepository repository;

    public TransactionConsumer(FraudAlertRepository repository) {
        this.repository = repository;
    }


    @KafkaListener(topics = "${kafka.topic.name:transaction-topic}", groupId = "{kafka.groupId = fraud-detector-group}")
    public void consume(Transaction transaction) {
        System.out.println("Received Transaction" + transaction);
        if(transaction.getAmount() >= 10000){
            System.out.println("🚨 FRAUD DETECTED! Transaction ID: " + transaction.getId() +
                    ", Amount: " + transaction.getAmount());

            FraudAlert fraud = new FraudAlert(transaction.getId(), transaction.getAmount(), transaction.getTimestamp(),"Transaction amount exceeds ₹10,000");
            repository.save(fraud);

            String to = "garimajhade@gmail.com";
            String subject = "Fraud Alert - Transaction ID: " + transaction.getId();
            String body = "A fraudulent transaction of ₹" + transaction.getAmount() +
            " was detected.\nTransaction ID: " + transaction.getId() +
                    "\nTime: " + transaction.getTimestamp();
           // emailService.sendFraudMailAlert(to, subject, body);
        }else {
            System.out.println("✅ Transaction is normal.");
        }

    }
}
