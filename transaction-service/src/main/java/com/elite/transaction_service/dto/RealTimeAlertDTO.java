package com.elite.transaction_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RealTimeAlertDTO {

    private String transactionID;
    private String customerId;
    private String amount;
    private String location;
    private String ipAddress;
    private String riskScore;
    private String status;
    private boolean highRiskFlag;




}
