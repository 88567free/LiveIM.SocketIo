package com.android.liveim.event;

import com.android.liveim.entity.DouNiuEndState;
import com.android.liveim.entity.RewardGift;

import java.util.List;

/**
 * 游戏开始初始状态
 * Created by admin n 2017/1/10.
 */
public class EventGameInitState {
    /**
     * 揭晓结果
     */
    public static final int State_Opening = 1;

    /**
     * 开牌结束
     */
    public static final int State_Ended = 2;
    /**
     * 游戏中
     */
    public static final int State_ING = 4;

    long gameid = 0;
    //0等待开牌 1揭晓结果 2开牌结束 3等待下局
    int gamestate = 0;
    long time = 0;
    int iswin = 0;

    List<DouNiuEndState>  cards;
    String json = "";
    /**
     * 开牌期间进入有押注用户状态
     */
    GameInitPeopleState peoplestate;

    public long getGameid() {
        return gameid;
    }

    public void setGameid(long gameid) {
        this.gameid = gameid;
    }

    public int getGamestate() {
        return gamestate;
    }

    public void setGamestate(int gamestate) {
        this.gamestate = gamestate;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getIswin() {
        return iswin;
    }

    public void setIswin(int iswin) {
        this.iswin = iswin;
    }

    public List<DouNiuEndState> getCards() {
        return cards;
    }

    public void setCards(List<DouNiuEndState> cards) {
        this.cards = cards;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public GameInitPeopleState getPeoplestate() {
        return peoplestate;
    }

    public void setPeoplestate(GameInitPeopleState peoplestate) {
        this.peoplestate = peoplestate;
    }

    @Override
    public String toString() {
        return "EventGameInitState{" +
                "json='" + json + '\'' +
                ", cards=" + cards +
                ", iswin=" + iswin +
                ", time=" + time +
                ", gamestate=" + gamestate +
                '}';
    }

    public class GameInitPeopleState{
        String stake_total = "";
        int win_total = 0;
        List<RewardGift> reward ;

        public String getStake_total() {
            return stake_total;
        }

        public void setStake_total(String stake_total) {
            this.stake_total = stake_total;
        }

        public int getWin_total() {
            return win_total;
        }

        public void setWin_total(int win_total) {
            this.win_total = win_total;
        }

        public List<RewardGift> getReward() {
            return reward;
        }

        public void setReward(List<RewardGift> reward) {
            this.reward = reward;
        }
    }
}
