package com.example.wood121.viewdemos.frame_part.bean;

import java.io.Serializable;

/**
 * Created by wood121 on 2017/12/28.
 */

public class Tab0208Bean implements Serializable {

    private String id;
    private String typeName;
    private String agencyId;
    private String name;
    private String cardId;
    private String address;
    private String money_standard;
    private String money_count;
    private String money;
    private String account;
    private String date;
    private String bank_name;
    private String back_up;
    private String ws;

    public Tab0208Bean() {
    }

    public Tab0208Bean(String id, String typeName, String agencyId, String name, String cardId, String address, String money_standard, String money_count, String money, String account, String date, String bank_name, String back_up, String ws) {
        this.id = id;
        this.typeName = typeName;
        this.agencyId = agencyId;
        this.name = name;
        this.cardId = cardId;
        this.address = address;
        this.money_standard = money_standard;
        this.money_count = money_count;
        this.money = money;
        this.account = account;
        this.date = date;
        this.bank_name = bank_name;
        this.back_up = back_up;
        this.ws = ws;
    }

    @Override
    public String toString() {
        return "Tab0208Bean{" +
                "id='" + id + '\'' +
                ", typeName='" + typeName + '\'' +
                ", agencyId='" + agencyId + '\'' +
                ", name='" + name + '\'' +
                ", cardId='" + cardId + '\'' +
                ", address='" + address + '\'' +
                ", money_standard='" + money_standard + '\'' +
                ", money_count='" + money_count + '\'' +
                ", money='" + money + '\'' +
                ", account='" + account + '\'' +
                ", date='" + date + '\'' +
                ", bank_name='" + bank_name + '\'' +
                ", back_up='" + back_up + '\'' +
                ", ws='" + ws + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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

    public String getMoney_standard() {
        return money_standard;
    }

    public void setMoney_standard(String money_standard) {
        this.money_standard = money_standard;
    }

    public String getMoney_count() {
        return money_count;
    }

    public void setMoney_count(String money_count) {
        this.money_count = money_count;
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

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBack_up() {
        return back_up;
    }

    public void setBack_up(String back_up) {
        this.back_up = back_up;
    }

    public String getWs() {
        return ws;
    }

    public void setWs(String ws) {
        this.ws = ws;
    }
}
