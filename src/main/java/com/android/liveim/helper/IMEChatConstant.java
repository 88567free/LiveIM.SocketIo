package com.android.liveim.helper;

/**
 * Created by admin on/12/27.
 */

public class IMEChatConstant {
    public final static String EVENT_MESSAGE_ERROR = "messagerror";
    public final static String EVENT_MESSAGE_SEND_CHAT = "sendchat";//用户发起
    public final static String EVENT_MESSAGE_ANCHOR_REPLY = "anchoreply";//
    public final static String EVENT_MESSAGE_REPLY_CHAT = "replychat";//主播回复用户
    public final static String EVENT_MESSAGE_END_CHAT = "endchat";
    public final static String EVENT_MESSAGE_SEND_GIFT = "sendgift";
    public final static String EVENT_MESSAGE_DEAD_LINE = "deadline";//倒计时事件
    public final static String EVENT_MESSAGE_CLOSE_CHAT = "closechat";//服务端强制结束聊天
    public final static String EVENT_MESSAGE_WAIT_USER_CHAT = "waituserchat";//主播进入消息列表获取等待主播一对一聊天的用户，激活聊天
    public final static String EVENT_MESSAGE_USER_END_CHAT = "userendchat";//挂断
}
