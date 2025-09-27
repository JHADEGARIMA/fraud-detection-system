package com.elite.transaction_service.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "fraud_alert")
public class FraudAlert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long transaction_id;
    private double amount;

    private LocalDateTime timestamp;

    private String reason;

    public FraudAlert() {
    }

    public FraudAlert(long transaction_id, double amount, LocalDateTime timestamp, String reason) {
        this.transaction_id = transaction_id;
        this.amount = amount;
        this.timestamp = timestamp;
        this.reason = reason;
    }
}
