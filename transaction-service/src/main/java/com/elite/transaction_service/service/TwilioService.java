package com.elite.transaction_service.service;

import com.elite.transaction_service.config.TwilioConfig;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class TwilioService {
    private final TwilioConfig twilioConfig;

    public TwilioService(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
    }


    @PostConstruct
    public void initTwilio() {
        Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
    }

    public void sendSMS(String to, String body) {
        Message.creator(
                new PhoneNumber(to), // Receiver's number
                new PhoneNumber(twilioConfig.getFromNumber()), // Twilio trial number
                body
        ).create();

        System.out.println("✅ SMS sent to " + to);
    }
}
