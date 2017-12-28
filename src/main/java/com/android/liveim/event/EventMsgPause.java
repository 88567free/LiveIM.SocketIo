package com.android.liveim.event;

/**
 * 主播暂离
 * Created by admin on 1/14.
 */
public class EventMsgPause {
    int state = 0;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
