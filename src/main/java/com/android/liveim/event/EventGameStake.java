package com.android.liveim.event;

/**
 * 押注情况事件
 * Created by admin on 1/3.
 */
public class EventGameStake {

    long gameid = 0;
    int iswho = 0;
    //当前钻石数
    long staketotal= 0;

    public int getIswho() {
        return iswho;
    }

    public void setIswho(int iswho) {
        this.iswho = iswho;
    }

    public long getStaketotal() {
        return staketotal;
    }

    public void setStaketotal(long staketotal) {
        this.staketotal = staketotal;
    }

    public long getGameid() {
        return gameid;
    }

    public void setGameid(long gameid) {
        this.gameid = gameid;
    }
}
