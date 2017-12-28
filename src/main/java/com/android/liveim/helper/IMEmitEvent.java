package com.android.liveim.helper;

/**
 * Created by admin on/12/27.
 */

public interface IMEmitEvent {
    void joinOnEvent(String roomId, boolean isStart, String gameType);

    void sendMsg(String msg, String roomId);

    void sendGift(String id, String roomId);

    void jin(String token, String isJin);

    void setAdmin(String token);

    void stakeCoin(String roomId, String coin, String isWho);

    void leave(String roomId);

    /**
     * 观众重连
     */
    void joinOnAudienceDisConn(String roomId, boolean isStart, String gameType);

    void changeGameType(String roomId, String type);
}
