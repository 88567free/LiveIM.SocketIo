package com.android.liveim.event;

/**
 * Created by admin on 3/2.
 */

public class EventTransportError {
    Exception exception;

    public EventTransportError(Exception exception) {
        this.exception = exception;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}
