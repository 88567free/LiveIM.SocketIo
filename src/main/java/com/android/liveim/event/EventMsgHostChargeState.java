package com.android.liveim.event;

/**
 * Created by admin on 2017/9/18.
 */

public class EventMsgHostChargeState {
    String  msg = "";
    int is_fee = 0;
    String fee_count = "";

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getIs_fee() {
        return is_fee;
    }

    public void setIs_fee(int is_fee) {
        this.is_fee = is_fee;
    }

    public String getFee_count() {
        return fee_count;
    }

    public void setFee_count(String fee_count) {
        this.fee_count = fee_count;
    }
}
