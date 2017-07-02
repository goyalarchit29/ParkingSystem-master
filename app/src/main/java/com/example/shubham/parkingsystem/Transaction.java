package com.example.shubham.parkingsystem;

/**
 * Created by shubham on 17-06-2017.
 */

public class Transaction {
    private Double cost;
    private String dealerName;
    private String dealerNumber;

    public Transaction(Double cost,String dealerName,String dealerNumber){
        this.cost=cost;
        this.dealerName=dealerName;
        this.dealerNumber=dealerNumber;
    }

    public Double getCost() {
        return cost;
    }

    public String getDealerName() {
        return dealerName;
    }

    public String getDealerNumber() {
        return dealerNumber;
    }
}
