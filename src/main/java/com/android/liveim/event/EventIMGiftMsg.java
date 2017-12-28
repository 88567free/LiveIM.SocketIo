package com.android.liveim.event;


/**
 * 收到礼物
 */
public class EventIMGiftMsg {
    String id = "";
    String time = "";
    String f_uuid = "";
    String nickname = "";
    String nickname_colour = "";
    String head = "";
    String level = "";
    String is_anchor = "";
    String gift_colour = "";

    public EventIMGiftMsg() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getF_uuid() {
        return f_uuid;
    }

    public void setF_uuid(String f_uuid) {
        this.f_uuid = f_uuid;
    }

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

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getIs_anchor() {
        return is_anchor;
    }

    public void setIs_anchor(String is_anchor) {
        this.is_anchor = is_anchor;
    }

    public String getGift_colour() {
        return gift_colour;
    }

    public void setGift_colour(String gift_colour) {
        this.gift_colour = gift_colour;
    }
}
