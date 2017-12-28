package com.android.liveim.event;

import static com.android.liveim.event.EventSocket.SocketState.ERROR;

/**
 * Created by admin on/12/14.
 */

public class EventSocket {
    private SocketState state = SocketState.CONNECTING;
    private String error = "";

    public enum SocketState{
        CONNECTING,CONNECTED,RECONNECT,DISCONNECT,ERROR
    }

    public EventSocket(SocketState state) {
        this.state = state;
    }

    public EventSocket(String error) {
        this.state = ERROR;
        this.error = error;
    }

    public SocketState getState() {
        return state;
    }

    public void setState(SocketState state) {
        this.state = state;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
