package com.android.liveim.event;

import com.android.liveim.entity.GameEndRankingEntity;

import java.util.List;

/**
 *  游戏回合结束排名
 * Created by admin on 1/4.
 */
public class EventGameEndRanking {
    List<GameEndRankingEntity> winuser ;
    long gameid = 0;

    public EventGameEndRanking() {
    }

    public List<GameEndRankingEntity> getWinuser() {
        return winuser;
    }

    public void setWinuser(List<GameEndRankingEntity> winuser) {
        this.winuser = winuser;
    }

    public long getGameid() {
        return gameid;
    }

    public void setGameid(long gameid) {
        this.gameid = gameid;
    }
}
