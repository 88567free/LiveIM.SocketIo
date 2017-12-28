package com.android.liveim.entity;

/**
 * 统一正确错误事件
 * Created by admin on/12/20.
 */
public class ErrorResponse {
    public static final String CODE_KICK_FOR_USER = "50015";
    public static final String CODE_TASK_USER = "50009";//有可领取任务
    public static final String CODE_JIN_ROE_USER = "50006";//被禁言
    public static final String CODE_NO_COIN = "50005";//余额不足
    public static final String CODE_RED_EMPTY = "50016";//该房间红包已被抢光
    public static final String CODE_HAS_RED = "50017";//您已经抢过该房间红包了！
    public static final String CODE_INIT_RED = "50018";//红包初始化

    String msg = "";
    String event = "";
    //主播本次开房唯一值
    String anchor_uuid = "";
    //用户虚拟币
    String balance_virtual = "";
    String sign = "";

    //游戏押注状态
    int stake = 0;
    int iswho = 0;
    //是否是第一次进入直播间 0否1是
    int is_im_first = 0;

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

    public int getStake() {
        return stake;
    }

    public void setStake(int stake) {
        this.stake = stake;
    }

    public int getIswho() {
        return iswho;
    }

    public void setIswho(int iswho) {
        this.iswho = iswho;
    }

    public String getAnchor_uuid() {
        return anchor_uuid;
    }

    public void setAnchor_uuid(String anchor_uuid) {
        this.anchor_uuid = anchor_uuid;
    }

    public int getIs_im_first() {
        return is_im_first;
    }

    public void setIs_im_first(int is_im_first) {
        this.is_im_first = is_im_first;
    }
}
