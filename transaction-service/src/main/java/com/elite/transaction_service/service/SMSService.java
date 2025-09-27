package com.elite.transaction_service.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SMSService {

    @Value("${sms.api.key}")
    private String apiKey;

    private final RestTemplate  restTemplate;

    public SMSService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendSMS(String phoneNumber, String message) {
        String url = "https://www.fast2sms.com/dev/bulkV2?" +
                "authorization=" + apiKey +
                "&message=" + message +
                "&language=english&route=q&numbers=" + phoneNumber;
        restTemplate.getForObject(url, String.class);
    }
}
