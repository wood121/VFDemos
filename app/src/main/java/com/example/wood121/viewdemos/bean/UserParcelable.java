package com.example.wood121.viewdemos.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wood121 on 2018/7/12.
 */

public class UserParcelable implements Parcelable {

    private int userid;
    private String userName;
    private boolean isMale;


    protected UserParcelable(Parcel in) {
        userid = in.readInt();
        userName = in.readString();
        isMale = in.readByte() != 0;
    }

    /**
     * 反序列化
     */
    public static final Creator<UserParcelable> CREATOR = new Creator<UserParcelable>() {
        @Override
        public UserParcelable createFromParcel(Parcel in) {
            return new UserParcelable(in);
        }

        @Override
        public UserParcelable[] newArray(int size) {
            return new UserParcelable[size];
        }
    };

    /**
     * 内容描述
     * @return
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * 序列化功能
     *
     * @param dest
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userid);
        dest.writeString(userName);
        dest.writeByte((byte) (isMale ? 1 : 0));
    }
}
