package com.android.liveim.event;

/**
 * 主播接收到聊天请求
 * Created by admin on 2017/11/27.
 */

public class EventEChatHostReply {
    String nickname = "";
    String userfuuid = "";
    String level = "";
    String head = "";
    String price = "";

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserfuuid() {
        return userfuuid;
    }

    public void setUserfuuid(String userfuuid) {
        this.userfuuid = userfuuid;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}


