//package com.example.client.controllers;
//
//import com.example.client.models.Transaction;
//import com.example.client.services.TransactionProducer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/clients")
//public class ClientController {
//
//    @Autowired
//    private TransactionProducer transactionProducer;
//
//    @PostMapping("/simulateTransaction")
//    public String simulateTransaction(@RequestBody Transaction transaction) {
//        if (transaction.getAmount() <= 0) {
//            return "Le montant doit être supérieur à zéro.";
//        }
//        transactionProducer.sendTransaction(transaction);
//        return "Transaction simulée et envoyée au topic Kafka.";
//    }
//}
