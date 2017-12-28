package com.android.liveim.entity;

/**
 * Created by admin on/12/20.
 */
public class SocketResponse<T> {
    int sign = 0;
    String msg = "";
    String event = "";
    T d ;

    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
    }

    public T getD() {
        return d;
    }

    public void setD(T d) {
        this.d = d;
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
}
