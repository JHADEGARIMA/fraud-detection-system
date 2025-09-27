package com.elite.transaction_service.repository;

import com.elite.transaction_service.model.FraudAlert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FraudAlertRepository extends JpaRepository<FraudAlert, Long> {
}
