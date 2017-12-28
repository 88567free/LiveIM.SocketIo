package com.android.liveim.event;

import com.android.liveim.entity.DouNiuEndState;

import java.util.List;

/**
 *  游戏结束状态
 * Created by admin on 1/3.
 */
public class EventGameEndState {
    //三副牌数组
    List<DouNiuEndState> cards ;


    public List<DouNiuEndState> getCards() {
        return cards;
    }

    public void setCards(List<DouNiuEndState> cards) {
        this.cards = cards;
    }

}
