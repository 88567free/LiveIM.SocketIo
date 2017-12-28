package com.android.liveim.event;

/**
 * Created by admin on 2017/12/4.
 */
public class EventMsgRedPackage {
    String anchorf_uuid = "";
    String userf_uuid = "";
    String redcount = "";
    String user_balance_virtual = "";
    String msg = "";

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

    public String getRedcount() {
        return redcount;
    }

    public void setRedcount(String redcount) {
        this.redcount = redcount;
    }

    public String getUser_balance_virtual() {
        return user_balance_virtual;
    }

    public void setUser_balance_virtual(String user_balance_virtual) {
        this.user_balance_virtual = user_balance_virtual;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
