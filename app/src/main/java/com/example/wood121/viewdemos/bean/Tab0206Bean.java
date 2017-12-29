package com.example.wood121.viewdemos.bean;

import java.io.Serializable;

/**
 * Created by wood121 on 2017/12/28.
 */

public class Tab0206Bean implements Serializable {

    private String id;
    private String agencyId;
    private String name;
    private String cardId;
    private String address;
    private String money;
    private String account;
    private String date;

    public Tab0206Bean() {
    }

    public Tab0206Bean(String id, String agencyId, String name, String cardId, String address, String money, String account, String date) {
        this.id = id;
        this.agencyId = agencyId;
        this.name = name;
        this.cardId = cardId;
        this.address = address;
        this.money = money;
        this.account = account;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Tab0206Bean{" +
                "id='" + id + '\'' +
                ", agencyId='" + agencyId + '\'' +
                ", name='" + name + '\'' +
                ", cardId='" + cardId + '\'' +
                ", address='" + address + '\'' +
                ", money='" + money + '\'' +
                ", account='" + account + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
