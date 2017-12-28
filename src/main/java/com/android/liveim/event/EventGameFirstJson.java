package com.android.liveim.event;

import com.android.liveim.entity.DouNiuFirstState;

import java.util.List;

/**
 * 游戏回合开始
 * Created by admin on 1/3.
 */
public class EventGameFirstJson {
    String json = "";

    long gameid = 0;
    private int time;
    List<DouNiuFirstState> cards;


    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public long getGameId() {
        return gameid;
    }

    public void setGameId(long gameId) {
        this.gameid = gameId;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public long getGameid() {
        return gameid;
    }

    public void setGameid(long gameid) {
        this.gameid = gameid;
    }

    public List<DouNiuFirstState> getCards() {
        return cards;
    }

    public void setCards(List<DouNiuFirstState> cards) {
        this.cards = cards;
    }
}
