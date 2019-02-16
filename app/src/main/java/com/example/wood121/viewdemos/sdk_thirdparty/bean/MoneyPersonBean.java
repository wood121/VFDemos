package com.example.wood121.viewdemos.sdk_thirdparty.bean;

import java.io.Serializable;

/**
 * Created by wood121 on 2017/12/27.
 */

public class MoneyPersonBean implements Serializable {

    private String id;
    private String agencyId;
    private String name;
    private String cardId;
    private String isCardId;
    private String account;
    private String isAccount;

    public MoneyPersonBean() {

    }

    public MoneyPersonBean(String id, String agencyId, String name, String cardId, String isCardId, String account, String isAccount) {
        this.id = id;
        this.agencyId = agencyId;
        this.name = name;
        this.cardId = cardId;
        this.isCardId = isCardId;
        this.account = account;
        this.isAccount = isAccount;
    }

    @Override
    public String toString() {
        return "MoneyPersonBean{" +
                "id=" + id +
                ", agencyId='" + agencyId + '\'' +
                ", name='" + name + '\'' +
                ", cardId='" + cardId + '\'' +
                ", isCardId=" + isCardId +
                ", account='" + account + '\'' +
                ", isAccount=" + isAccount +
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

    public String getIsCardId() {
        return isCardId;
    }

    public void setIsCardId(String isCardId) {
        this.isCardId = isCardId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getIsAccount() {
        return isAccount;
    }

    public void setIsAccount(String isAccount) {
        this.isAccount = isAccount;
    }
}
