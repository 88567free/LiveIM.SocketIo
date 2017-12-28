package com.android.liveim.event;

/**
 * 下播，禁播
 * Created by admin on/12/22.
 */
public class EventMsgLeave {
    //1表示主播停止直播 -1表示管理员禁播此房间
    int isend = 1;
    long start = 0;
    long end = 0;
    //关注人数
    String followercount = "";
    //魅力值
    String meicount = "";
    //本次观看人数
    String total = "";

    String service = "";
    int action = 0;
    public static final int ACTION_HOST_PAUSE_LEAVE  = 1;


    public EventMsgLeave() {
    }

    public EventMsgLeave(int action) {
        this.action = action;
    }

    public int getIsend() {
        return isend;
    }

    public void setIsend(int isend) {
        this.isend = isend;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public String getFollowercount() {
        return followercount;
    }

    public void setFollowercount(String followercount) {
        this.followercount = followercount;
    }

    public String getMeicount() {
        return meicount;
    }

    public void setMeicount(String meicount) {
        this.meicount = meicount;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }
}

