package com.android.liveim.event;

import com.android.liveim.entity.RewardGift;

import java.util.List;

/**
 * 押注输赢结果
 * Created by admin on 1/4.
 */
public class EventGameStakeResult {

    long gameid = 0;
    //是否押注赢 3此牌押注赢 2押注输 0无押注
    int iswin = 0;
    //账户余额
    long stake_total = 0;
    //如果押注赢钱表示赢的钱数
    long win_total = 0;

    List<RewardGift> reward ;

    public long getGameid() {
        return gameid;
    }

    public void setGameid(long gameid) {
        this.gameid = gameid;
    }

    public int getIswin() {
        return iswin;
    }

    public void setIswin(int iswin) {
        this.iswin = iswin;
    }

    public long getStake_total() {
        return stake_total;
    }

    public void setStake_total(long stake_total) {
        this.stake_total = stake_total;
    }

    public long getWin_total() {
        return win_total;
    }

    public void setWin_total(long win_total) {
        this.win_total = win_total;
    }

    public List<RewardGift> getReward() {
        return reward;
    }

    public void setReward(List<RewardGift> reward) {
        this.reward = reward;
    }

}
