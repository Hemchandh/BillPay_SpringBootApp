package com.tcs.paybills.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Filter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;

@Entity
@Table(name = "biller")
public class Biller {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "billName", nullable = false)
    private String billName;

    @Column(name = "billAmount", nullable = false)
    private String billAmount;

    @Column(name = "mobileNumber", nullable = false)
    private String mobileNumber;

    @Column(name = "recurringTimePeriod", nullable = false)
    private String recurringTimePeriod;

    @Column(name = "address", nullable = false)
    private String address;
    private static String authToken ="zzz";
    public static String getAuthToken() {
        return authToken.trim();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public String getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(String billAmount) {
        this.billAmount = billAmount;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getRecurringTimePeriod() {
        return recurringTimePeriod;
    }

    public void setRecurringTimePeriod(String recurringTimePeriod) {
        this.recurringTimePeriod = recurringTimePeriod;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
