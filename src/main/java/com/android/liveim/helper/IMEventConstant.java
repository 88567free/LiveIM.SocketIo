package com.android.liveim.helper;

/**
 * Created by admin on/12/27.
 */

public class IMEventConstant {
    public final static String EVENT_PROTOCOL_DISCONNECT = "disconnection";
    public final static String EVENT_PROTOCOL_JOIN = "join";
    public final static String EVENT_MESSAGE_AUDIENCE_REJION = "join2";
    public final static String EVENT_MESSAGE_ERROR = "messagerror";
    public final static String EVENT_MESSAGE_CHAT = "chat";
    public final static String EVENT_MESSAGE_PINTO_ROOM = "pIntoRoom";
    public final static String EVENT_MESSAGE_JIN = "jin";
    public final static String EVENT_MESSAGE_GIFT = "gift";
    public final static String EVENT_MESSAGE_STATES = "states";
    public final static String EVENT_MESSAGE_ROOMADMIN = "roomadmin";
    public final static String EVENT_MESSAGE_JINZHUSORT = "jinzhusort";
    public final static String EVENT_MESSAGE_SYSMSG = "systemessage";
    public final static String EVENT_MESSAGE_LEAVE = "leave";
    public final static String EVENT_MESSAGE_PAUSE = "leave2";//pause or Resume
    public final static String EVENT_MESSAGE_GAME_INIT = "gameinit";//初始化游戏
    public final static String EVENT_MESSAGE_GAME_FIRST_STATES = "gamestates";//游戏起始
    public final static String EVENT_MESSAGE_GAME_END_STATES = "endstate";//游戏回合结束
    public final static String EVENT_MESSAGE_GAME_STAKE = "stakegame";//押注
    public final static String EVENT_MESSAGE_GAME_END_RANKING = "endstateanchor";//回合排名
    public final static String EVENT_MESSAGE_GAME_STAKE_RESULT = "winorlose";//押注结果
    public final static String EVENT_MESSAGE_GAME_REPLACE_GAME = "replacegame";//更换游戏事件通知
    public final static String EVENT_MESSAGE_GAME_SWITCH_GAME = "switchgame";//主播更换游戏
    public final static String EVENT_MESSAGE_TOP_NOTIFY = "noticemessage";//顶部消息通知
    public final static String EVENT_MESSAGE_VIP_STATE = "closevip";//VIP变更通知
    public final static String EVENT_MESSAGE_HOST_VIP_STATE = "anchorvip";//主播接收VIP变更通知
    public final static String EVENT_MESSAGE_CHARGE_NO_PAY = "closenopay";//房间变成付费播
    public final static String EVENT_MESSAGE_HOST_CHARGE_STATE = "anchorfee";//主播接收房间变成付费播
    public final static String EVENT_MESSAGE_HOST_PAUSE_LEAVE = "timeout";//主播暂时退出房间1小时
    public final static String EVENT_MESSAGE_HOST_KICK = "kick";//主播踢人
    public final static String EVENT_MESSAGE_HOST_CAMERA_CLOSE = "camerastate";//主播关闭摄像头
    public final static String EVENT_MESSAGE_USER_CAMERA_STATE = "camerahandel";//摄像头开关
    public final static String EVENT_MESSAGE_GET_RED_PACKAGE= "getred";//红包


}
