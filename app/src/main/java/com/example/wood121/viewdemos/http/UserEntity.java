package com.example.wood121.viewdemos.http;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wood121 on 2018/7/17.
 */

public class UserEntity extends HttpResult implements Parcelable {
    public String capital_mode_sign_first;
    public String logo;
    public String bank_name;
    public String capital_mode;
    public String functions_sign_first;
    public String description;
    public String id_tbankslist;
    public String serial_num;
    public String bank_no;
    public String functions_sign_secone;
    public String short_name;
    public String sign_mode;

    public UserEntity(String capital_mode_sign_first, String logo, String bank_name, String capital_mode, String functions_sign_first, String description, String id_tbankslist, String serial_num, String bank_no, String functions_sign_secone, String short_name, String sign_mode) {
        this.capital_mode_sign_first = capital_mode_sign_first;
        this.logo = logo;
        this.bank_name = bank_name;
        this.capital_mode = capital_mode;
        this.functions_sign_first = functions_sign_first;
        this.description = description;
        this.id_tbankslist = id_tbankslist;
        this.serial_num = serial_num;
        this.bank_no = bank_no;
        this.functions_sign_secone = functions_sign_secone;
        this.short_name = short_name;
        this.sign_mode = sign_mode;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "capital_mode_sign_first='" + capital_mode_sign_first + '\'' +
                ", logo='" + logo + '\'' +
                ", bank_name='" + bank_name + '\'' +
                ", capital_mode='" + capital_mode + '\'' +
                ", functions_sign_first='" + functions_sign_first + '\'' +
                ", description='" + description + '\'' +
                ", id_tbankslist='" + id_tbankslist + '\'' +
                ", serial_num='" + serial_num + '\'' +
                ", bank_no='" + bank_no + '\'' +
                ", functions_sign_secone='" + functions_sign_secone + '\'' +
                ", short_name='" + short_name + '\'' +
                ", sign_mode='" + sign_mode + '\'' +
                '}';
    }

    public String getCapital_mode_sign_first() {
        return capital_mode_sign_first;
    }

    public void setCapital_mode_sign_first(String capital_mode_sign_first) {
        this.capital_mode_sign_first = capital_mode_sign_first;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getCapital_mode() {
        return capital_mode;
    }

    public void setCapital_mode(String capital_mode) {
        this.capital_mode = capital_mode;
    }

    public String getFunctions_sign_first() {
        return functions_sign_first;
    }

    public void setFunctions_sign_first(String functions_sign_first) {
        this.functions_sign_first = functions_sign_first;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId_tbankslist() {
        return id_tbankslist;
    }

    public void setId_tbankslist(String id_tbankslist) {
        this.id_tbankslist = id_tbankslist;
    }

    public String getSerial_num() {
        return serial_num;
    }

    public void setSerial_num(String serial_num) {
        this.serial_num = serial_num;
    }

    public String getBank_no() {
        return bank_no;
    }

    public void setBank_no(String bank_no) {
        this.bank_no = bank_no;
    }

    public String getFunctions_sign_secone() {
        return functions_sign_secone;
    }

    public void setFunctions_sign_secone(String functions_sign_secone) {
        this.functions_sign_secone = functions_sign_secone;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public String getSign_mode() {
        return sign_mode;
    }

    public void setSign_mode(String sign_mode) {
        this.sign_mode = sign_mode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public static final Parcelable.Creator<UserEntity> CREATOR = new Parcelable.Creator<UserEntity>() {

        @Override
        public UserEntity createFromParcel(Parcel source) {
            return new UserEntity(source);
        }

        @Override
        public UserEntity[] newArray(int size) {
            return new UserEntity[0];
        }
    };

    public UserEntity(Parcel source) {
        capital_mode_sign_first = source.readString();
        logo = source.readString();
        bank_name = source.readString();
        capital_mode = source.readString();
        functions_sign_first = source.readString();
        description = source.readString();
        id_tbankslist = source.readString();
        serial_num = source.readString();
        bank_no = source.readString();
        functions_sign_secone = source.readString();
        short_name = source.readString();
        sign_mode = source.readString();
    }

}
