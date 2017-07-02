package com.example.shubham.parkingsystem;

import java.util.Date;
//import java.util.Time;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by archi on 20-06-2017.
 */

public class User {
    //public String vehicleNo;
    public String Name;
    public String address;
    public String mobile;
    public Date timestart;
    public Date timeend;

    public User() {

    }

    public User(String Name, String address, String mobile, Date timestart, Date timeend) {
        //this.vehicleNo=vehicleNo;
        this.Name = Name;
        this.address = address;
        this.mobile = mobile;
        this.timestart = timestart;
        this.timeend = timeend;
    }
}
