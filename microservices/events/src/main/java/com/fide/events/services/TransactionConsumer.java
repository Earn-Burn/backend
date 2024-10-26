//package com.fide.events.services;
//
//import com.example.client.models.Transaction;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.apache.kafka.common.errors.WakeupException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.support.Acknowledgment;
//import org.springframework.stereotype.Service;
//
//@EnableKafka
//@Service
//public class TransactionConsumer {
//
//    private static final Logger logger = LoggerFactory.getLogger(TransactionConsumer.class);
//
//    @KafkaListener(topics = "TransactionTopic", groupId = "transaction-group")
//    public void consumeTransaction(ConsumerRecord<String, Transaction> record, Acknowledgment ack) {
//        try {
//            Transaction transaction = record.value();
//            logger.info("Transaction reçue: {}", transaction);
//
//            // Vérifiez les conditions et ajoutez des points si nécessaire
//            if (verifyConditions(transaction)) {
//                addLoyaltyPoints(transaction.getClientId());
//            }
//
//            // Accuser réception à Kafka que le message a été traité
//            ack.acknowledge();
//
//        } catch (WakeupException e) {
//            // Erreur lors de l'arrêt du consommateur Kafka
//            logger.error("Erreur lors de la réception de la transaction", e);
//        } catch (Exception e) {
//            logger.error("Erreur générale", e);
//        }
//    }
//
//    private boolean verifyConditions(Transaction transaction) {
//        return transaction.getAmount() > 100;  // Exemple : montant minimum pour ajouter des points
//    }
//
//    private void addLoyaltyPoints(String clientId) {
//        logger.info("Points ajoutés pour le client: {}", clientId);
//    }
//}
