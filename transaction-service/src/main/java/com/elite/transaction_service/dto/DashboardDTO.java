package com.elite.transaction_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DashboardDTO {


    private String year;
    private int todayTransaction;
    private int weekTransaction;
    private int monthTransaction;

    private List<String> suspeciousFrauds;
    private int confirmedFraud;
    private int falsePositive;
    private int fraudDetectionAccuracy;

    private List<RealTimeAlertDTO> realTimeAlerts;


}
