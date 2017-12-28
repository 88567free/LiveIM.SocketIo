package com.android.liveim.event;

/**
 * 游戏回合结束
 * Created by admin on 1/3.
 */
public class EventGameEndJson {
    String json = "";
    long gameId = 0;

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }
}
