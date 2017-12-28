package com.android.liveim.event;


/**
 * 消息
 */
public class EventMsgChat extends EventMsgBase{
    private String f_uuid = "";
    private String nickname = "";
    private String head = "";
    private String level = ""; // 财富等级
    private String is_anchor;
    private String msg = "";
    private String msg_colour = "";
    private String nickname_colour = "";
    private String event = "";

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg_colour() {
        return msg_colour;
    }

    public void setMsg_colour(String msg_colour) {
        this.msg_colour = msg_colour;
    }

    public String getNickname_colour() {
        return nickname_colour;
    }

    public void setNickname_colour(String nickname_colour) {
        this.nickname_colour = nickname_colour;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "EventMsgChat{" +
                "f_uuid='" + f_uuid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", head='" + head + '\'' +
                ", level='" + level + '\'' +
                ", is_anchor='" + is_anchor + '\'' +
                ", msg='" + msg + '\'' +
                ", msg_colour='" + msg_colour + '\'' +
                ", nickname_colour='" + nickname_colour + '\'' +
                '}';
    }
}
