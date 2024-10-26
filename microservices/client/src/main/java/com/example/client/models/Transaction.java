//package com.example.client.models;
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//
//public class Transaction {
//    @JsonProperty("transactionId")
//    private String transactionId;
//
//    @JsonProperty("clientId")
//    private String clientId;
//
//    @JsonProperty("amount")
//    private double amount;
//
//    // Constructeurs
//    public Transaction(String transactionId, String clientId, double amount) {
//        this.transactionId = transactionId;
//        this.clientId = clientId;
//        this.amount = amount;
//    }
//
//    // Getters et Setters
//    public String getTransactionId() {
//        return transactionId;
//    }
//
//    public void setTransactionId(String transactionId) {
//        this.transactionId = transactionId;
//    }
//
//    public String getClientId() {
//        return clientId;
//    }
//
//    public void setClientId(String clientId) {
//        this.clientId = clientId;
//    }
//
//    public double getAmount() {
//        return amount;
//    }
//
//    public void setAmount(double amount) {
//        this.amount = amount;
//    }
//
//    @Override
//    public String toString() {
//        return "Transaction{" +
//                "transactionId='" + transactionId + '\'' +
//                ", clientId='" + clientId + '\'' +
//                ", amount=" + amount +
//                '}';
//    }
//}
