package com.android.liveim.event;

import com.android.liveim.entity.EventGiftRanking;

import java.util.List;

/**
 * 状态刷新事件
 * Created by admin on/12/22.
 */
public class EventMsgStates {
    public static final String StateType_Top = "topcount";
    public static final String StateType_JinZhuSort = "jinzhusort";
    public static final String StateType_MeiCount = "meicount";
    public static final String StateType_FollowerCount = "followercount";
    public static final String StateType_REALCOUNT = "realcount";
    public static final String StateType_SecretCharm = "secretmeicount";

    String position = "";
    String state  = "";

    List<EventGiftRanking> giftRankings;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<EventGiftRanking> getGiftRankings() {
        return giftRankings;
    }

    public void setGiftRankings(List<EventGiftRanking> giftRankings) {
        this.giftRankings = giftRankings;
    }

}
