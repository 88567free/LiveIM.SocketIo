package com.android.liveim.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by admin on 1/3.
 */
public class GameEndRankingEntity implements Parcelable {
    String nickname = "";
    String nickname_colour = "";
    String level  = "";
    String head  = "";
    String f_uuid = "";

    public GameEndRankingEntity() {
    }

    protected GameEndRankingEntity(Parcel in) {
        nickname = in.readString();
        nickname_colour = in.readString();
        level = in.readString();
        head = in.readString();
        f_uuid = in.readString();
    }

    public static final Creator<GameEndRankingEntity> CREATOR = new Creator<GameEndRankingEntity>() {
        @Override
        public GameEndRankingEntity createFromParcel(Parcel in) {
            return new GameEndRankingEntity(in);
        }

        @Override
        public GameEndRankingEntity[] newArray(int size) {
            return new GameEndRankingEntity[size];
        }
    };

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname_colour() {
        return nickname_colour;
    }

    public void setNickname_colour(String nickname_colour) {
        this.nickname_colour = nickname_colour;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getF_uuid() {
        return f_uuid;
    }

    public void setF_uuid(String f_uuid) {
        this.f_uuid = f_uuid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nickname);
        parcel.writeString(nickname_colour);
        parcel.writeString(level);
        parcel.writeString(head);
        parcel.writeString(f_uuid);
    }
}
