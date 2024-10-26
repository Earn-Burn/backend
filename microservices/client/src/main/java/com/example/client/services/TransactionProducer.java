//package com.example.client.services;
//
//import com.example.client.models.Transaction;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class TransactionProducer {
//
//    @Autowired
//    private KafkaTemplate<String, Transaction> kafkaTemplate;
//
//    private static final String TOPIC = "TransactionTopic";
//
//    public void sendTransaction(Transaction transaction) {
//        try {
//            kafkaTemplate.send(TOPIC, transaction);
//            System.out.println("Transaction envoyée: " + transaction);
//        } catch (Exception e) {
//            System.err.println("Erreur lors de l'envoi de la transaction: " + e.getMessage());
//        }
//    }
//}
