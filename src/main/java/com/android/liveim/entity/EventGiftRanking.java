package com.android.liveim.entity;

/**
 * 房间内礼物排行列表
 * Created by admin on/12/24.
 */
public class EventGiftRanking {
    String nickname = "";
    String nickname_color = "";
    String level = "";
    String head = "";
    String atomicInteger = "";
    String f_uuid = "";
    public EventGiftRanking() {
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname_color() {
        return nickname_color;
    }

    public void setNickname_color(String nickname_color) {
        this.nickname_color = nickname_color;
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

    public String getAtomicInteger() {
        return atomicInteger;
    }

    public void setAtomicInteger(String atomicInteger) {
        this.atomicInteger = atomicInteger;
    }

    public String getF_uuid() {
        return f_uuid;
    }

    public void setF_uuid(String f_uuid) {
        this.f_uuid = f_uuid;
    }
}
