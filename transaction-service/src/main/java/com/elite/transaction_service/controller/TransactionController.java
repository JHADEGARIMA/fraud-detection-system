package com.elite.transaction_service.controller;

import com.elite.transaction_service.dto.AdminDashboardDTO;
import com.elite.transaction_service.model.Transaction;
import com.elite.transaction_service.repository.TransactionRepository;
import com.elite.transaction_service.service.TransactionService;
import org.hibernate.query.QueryParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {



    @Autowired
    private TransactionService service;


    @GetMapping("/admin-dashboard")
    public ResponseEntity<AdminDashboardDTO> getDashboardDetails(@RequestParam String adminId){

        return null;
    }

    @PostMapping("/create")
    public ResponseEntity<Transaction>  createTransaction(@RequestBody Transaction transaction) {
        System.out.println("Hi doddd");
        return ResponseEntity.ok(service.processTransaction(transaction));
    }

}
