package com.android.liveim.event;

import com.android.liveim.entity.DouNiuFirstState;

import java.util.List;

/**
 * 起始游戏状态
 * Created by admin on 1/3.
 */
public class EventGameFirstStates {
    long time = 0;
    List<DouNiuFirstState> cards;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public List<DouNiuFirstState> getCards() {
        return cards;
    }

    public void setCards(List<DouNiuFirstState> cards) {
        this.cards = cards;
    }
}
