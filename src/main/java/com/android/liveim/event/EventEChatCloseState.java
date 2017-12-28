package com.android.liveim.event;

/**
 * Created by admin on 2017/11/27.
 */

public class EventEChatCloseState {
    String giftPMei = "";      //礼物收益魅力
    String chatime = "";       // 本次通话时长
    String timeMei = "";       //时长收益魅力
    String timePMoney = "";   //时长收益多少钱
    String giftPMoney = "";   //礼物收益多少钱
    String anchorf_uuid = ""; //主播房間號
    String userf_uuid = "";   //用戶房間號
    String msg = "";
    String event = "";
    String sign = "";

    public String getGiftPMei() {
        return giftPMei;
    }

    public void setGiftPMei(String giftPMei) {
        this.giftPMei = giftPMei;
    }

    public String getChatime() {
        return chatime;
    }

    public void setChatime(String chatime) {
        this.chatime = chatime;
    }

    public String getTimeMei() {
        return timeMei;
    }

    public void setTimeMei(String timeMei) {
        this.timeMei = timeMei;
    }

    public String getTimePMoney() {
        return timePMoney;
    }

    public void setTimePMoney(String timePMoney) {
        this.timePMoney = timePMoney;
    }

    public String getGiftPMoney() {
        return giftPMoney;
    }

    public void setGiftPMoney(String giftPMoney) {
        this.giftPMoney = giftPMoney;
    }

    public String getAnchorf_uuid() {
        return anchorf_uuid;
    }

    public void setAnchorf_uuid(String anchorf_uuid) {
        this.anchorf_uuid = anchorf_uuid;
    }

    public String getUserf_uuid() {
        return userf_uuid;
    }

    public void setUserf_uuid(String userf_uuid) {
        this.userf_uuid = userf_uuid;
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

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
