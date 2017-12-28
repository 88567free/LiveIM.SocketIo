package com.android.liveim.event;

/**
 * 1    "主播接受聊天"
 * 60003 "对方拒绝"
 * 60002 "对方正在通话中"
 * 60001 "主播不在线"
 * Created by admin on 2017/11/27.
 */

public class EventEChatResponse {
    public static final String MSG_ERROR = "0";
    /**
     * 主播接受聊天
     */
    public static final String MSG_CHAT = "1";
    /**
     * 对方拒绝
     */
    public static final String MSG_REJECT = "60003";
    /**
     * 对方正在通话中
     */
    public static final String MSG_CHATTING = "60002";
    /**
     * 主播不在线
     */
    public static final String MSG_NOT_ONLINE = "60001";

    public static final String MSG_USER_HANDUP = "60007";

    /**
     * 主播离开
     */
    public static final String MSG_HOST_OFFLINE = "60011";

    public static final String MSG_NO_COIN = "60010";

    String msg = "";
    String event = "";
    //主播本次开房唯一值
    String anchor_uuid = "";
    //用户虚拟币
    String balance_virtual = "";
    String sign = "";

    public EventEChatResponse() {
    }

    public EventEChatResponse(String sign, String msg) {
        this.msg = msg;
        this.sign = sign;
    }

    public EventEChatResponse(String sign) {
        this.sign = sign;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getAnchor_uuid() {
        return anchor_uuid;
    }

    public void setAnchor_uuid(String anchor_uuid) {
        this.anchor_uuid = anchor_uuid;
    }

    public String getBalance_virtual() {
        return balance_virtual;
    }

    public void setBalance_virtual(String balance_virtual) {
        this.balance_virtual = balance_virtual;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}

