package com.android.liveim.event;

/**
 * Created by admin on 2017/11/21.
 */

public class EventEChatSocketState extends EventSocket {
    public EventEChatSocketState(SocketState state) {
        super(state);
    }

    public EventEChatSocketState(String error) {
        super(error);
    }
}
