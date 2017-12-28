package com.android.liveim.event;


import android.text.TextUtils;

/**********************
 * @author: wusongyuan
 * @date: 2016-07-12
 * @desc: 进房消息
 **********************/
public class EventMsgIntoRoom extends EventMsgBase {
    public static final int HeightLevel = 20;//高等级界限
    private String f_uuid = "";
    private String nickname = "";
    private String nickname_colour = "#ffffff";
    private String head = "";
    private String level = "1";
    private String is_anchor = "";

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

    public boolean isHeightLevel(){
        if(TextUtils.isEmpty(level)){
            level = "1";
        }
        return Integer.valueOf(level) >= HeightLevel;
    }

    @Override
    public String toString() {
        return "EventMsgIntoRoom{" +
                "f_uuid='" + f_uuid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", nickname_colour='" + nickname_colour + '\'' +
                ", head='" + head + '\'' +
                ", level='" + level + '\'' +
                ", is_anchor='" + is_anchor + '\'' +
                '}';
    }
}
