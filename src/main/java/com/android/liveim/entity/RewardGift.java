package com.android.liveim.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 赢了游戏后礼物打赏
 * Created by admin on 1/9.
 */
public class RewardGift implements Parcelable {
    String id = "";
    String gift_name = "";
    String gift_virtual = "";
    String gift_head = "";

    public RewardGift() {
    }

    protected RewardGift(Parcel in) {
        id = in.readString();
        gift_name = in.readString();
        gift_virtual = in.readString();
        gift_head = in.readString();
    }

    public static final Creator<RewardGift> CREATOR = new Creator<RewardGift>() {
        @Override
        public RewardGift createFromParcel(Parcel in) {
            return new RewardGift(in);
        }

        @Override
        public RewardGift[] newArray(int size) {
            return new RewardGift[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGift_name() {
        return gift_name;
    }

    public void setGift_name(String gift_name) {
        this.gift_name = gift_name;
    }

    public String getGift_virtual() {
        return gift_virtual;
    }

    public void setGift_virtual(String gift_virtual) {
        this.gift_virtual = gift_virtual;
    }

    public String getGift_head() {
        return gift_head;
    }

    public void setGift_head(String gift_head) {
        this.gift_head = gift_head;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(gift_name);
        parcel.writeString(gift_virtual);
        parcel.writeString(gift_head);
    }
}
