package com.example.shubham.parkingsystem;

/**
 * Created by archi on 27-06-2017.
 */

public class Dealer {
    private Double cost;
    private String CusName;
    private String CusNumber;

    public Dealer(Double cost,String CusName,String CusNumber){
        this.cost=cost;
        this.CusName=CusName;
        this.CusNumber=CusNumber;
    }

    public Double getCost() {
        return cost;
    }

    public String getCusName() {
        return CusName;
    }

    public String getCusNumber() {
        return CusNumber;
    }

}
