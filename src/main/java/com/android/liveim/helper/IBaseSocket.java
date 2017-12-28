package com.android.liveim.helper;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.android.liveim.ConfigConstant;
import com.android.liveim.event.EventSocket;
import com.android.liveim.event.EventTransportError;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.socket.client.IO;
import io.socket.client.Manager;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import io.socket.engineio.client.Transport;
import io.socket.engineio.client.transports.WebSocket;

/**
 * Created by gfq on 2016/12/27.
 */

public abstract class IBaseSocket {
    public static final String TAG = IBaseSocket.class.getSimpleName();
    public Socket mSocket;
    public HashMap<String, String> headerMap;
    public boolean isConnecting = false;
    public boolean isConnectSuccess = false;
    public int countReconnectTimes = 0;
    public boolean isLooping = false;
    public LoopJoinRoomRunnable loopJoinRoomRunnable;
    public static final Handler handlerLooper = new Handler(Looper.getMainLooper());

    public void initSocket(HashMap<String, String> map, String url) {
        this.headerMap = map;
        try {
            if (mSocket != null) {
                Log.d(TAG,"初始化断开IM");
                mSocket.disconnect();
                mSocket.off();
                mSocket.io().off();
                mSocket = null;
            }
            IO.Options options = new IO.Options();
            options.transports = new String[]{WebSocket.NAME};
            mSocket = IO.socket(url, options);
            setSocketIoOnEvent(Manager.EVENT_TRANSPORT, onTransportHeader);
            mSocket.on(Socket.EVENT_CONNECT, onConnectListener);
            setSocketOnEvent(Socket.EVENT_CONNECT_ERROR, onConnectErrorListener);
            setSocketOnEvent(Socket.EVENT_CONNECT_TIMEOUT, onConnectErrorListener);
            setSocketOnEvent(Socket.EVENT_DISCONNECT, onDisConnectListener);
            registerSocketEvent();
            cancelLoopRun();
            Log.d(TAG,"IM init");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void connectSocket() {
        if (!isConnected()) {
            Log.d(TAG, "IM 开始连接");
            isConnecting = true;
            mSocket.connect();
        }
    }

    ;

    protected abstract void connectSocketSuccess();

    protected abstract void connectError(String msg);

    public abstract void disConnect();

    protected abstract void registerSocketEvent();

    protected abstract void unRegisterSocketEvent();

    protected abstract Map<String, List<String>> setSocketHeader(Map<String, List<String>> headers);

    public boolean isConnected() {
        return mSocket != null && mSocket.connected();
    }

    protected BaseEmitterListener onTransportHeader = new BaseEmitterListener() {
        @Override
        public void callback(Object... args) {
            try {
                Transport transport = (Transport) args[0];
                transport.on(Transport.EVENT_REQUEST_HEADERS, new BaseEmitterListener() {
                    @Override
                    public void callback(Object... args) {
                        @SuppressWarnings("unchecked")
                        Map<String, List<String>> headers = (Map<String, List<String>>) args[0];
                        if (headers != null) {
                            setSocketHeader(headers);
                        }
                    }
                }).on(Transport.EVENT_RESPONSE_HEADERS, new BaseEmitterListener() {
                    @Override
                    public void callback(Object... args) {
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                EventBus.getDefault().post(new EventTransportError(e));
            }
        }
    };

    /**
     * 连接成功监听
     */
    protected BaseEmitterListener onConnectListener = new BaseEmitterListener() {
        @Override
        public void callback(Object... args) {
            Log.d(TAG, "IM 连接成功");
            EventBus.getDefault().post(new EventSocket(EventSocket.SocketState.CONNECTED));
            isConnectSuccess = true;
            countReconnectTimes = 0;
            isConnecting = false;
            connectSocketSuccess();
        }
    };

    /**
     * 连接失败监听
     */
    protected BaseEmitterListener onConnectErrorListener = new BaseEmitterListener() {
        @Override
        public void callback(Object... args) {
            Log.e(TAG,"IM 连接失败" + String.valueOf(args[0]));
            connectError(String.valueOf(args[0]));
            isConnectSuccess = false;
            if (!isLooping && countReconnectTimes < ConfigConstant.MAX_RECONNECT_COUNT) {
                if (loopJoinRoomRunnable == null) {
                    loopJoinRoomRunnable = new LoopJoinRoomRunnable();
                }
                loopJoinRoomRunnable.setPeriodTime(ConfigConstant.PERIOD_CONNECT);
                handlerLooper.postDelayed(loopJoinRoomRunnable, ConfigConstant.PERIOD_CONNECT);
            } else {
                if (!isLooping && countReconnectTimes >= ConfigConstant.MAX_RECONNECT_COUNT) {
                    disConnect();
                }
            }
        }
    };

    /**
     * 关闭连接监听
     */
    protected BaseEmitterListener onDisConnectListener = new BaseEmitterListener() {
        @Override
        public void callback(Object... args) {
            isConnectSuccess = false;
            isConnecting = false;
            countReconnectTimes = 0;
            String info = (args != null && args.length > 0 && args[0] instanceof String) ? (String) args[0] : "";
            Log.d(TAG,"关闭连接监听" + String.valueOf(args) + "--" + info);
            if (info.equals("forced close") || info.equals("transport error")) {
                connectSocket();
            }
        }
    };

    protected synchronized void setSocketOnEvent(String event, Emitter.Listener fn) {
        if (mSocket != null && !mSocket.hasListeners(event)) {
            mSocket.on(event, fn);
        }
    }

    protected synchronized void setSocketIoOnEvent(String event, Emitter.Listener fn) {
        if (mSocket != null && !mSocket.io().hasListeners(event)) {
            mSocket.io().on(event, fn);
        }
    }

    protected synchronized void offSocketIoOnEvent(String event, Emitter.Listener fn) {
        if (mSocket != null && !mSocket.io().hasListeners(event)) {
            mSocket.io().off(event, fn);
        }
    }

    protected synchronized void offSocketOnEvent(String event, Emitter.Listener fn) {
        if (mSocket != null && mSocket.hasListeners(event)) {
            mSocket.off(event, fn);
        }
    }

    protected void cancelLoopRun() {
        if (isLooping || loopJoinRoomRunnable != null) {
            if (loopJoinRoomRunnable != null) {
                handlerLooper.removeCallbacks(loopJoinRoomRunnable);
            }
            loopJoinRoomRunnable = null;
            Log.d(TAG,"Loop Connect cancel");
        }
        isLooping = false;
        isConnecting = false;
    }

    //重连轮询
    protected class LoopJoinRoomRunnable implements Runnable {
        private int mPeriodTime = ConfigConstant.PERIOD_JOIN;

        public void setPeriodTime(int mPeriodTime) {
            this.mPeriodTime = mPeriodTime;
        }

        @Override
        public void run() {
            try {
                isLooping = true;
                handlerLooper.postDelayed(LoopJoinRoomRunnable.this, mPeriodTime);
                if (!isConnectSuccess && countReconnectTimes < ConfigConstant.MAX_RECONNECT_COUNT) {
                    Log.d(TAG,"socket reconnect " + countReconnectTimes);
                    countReconnectTimes++;
                    Log.d(TAG, "手动断开");
                    mSocket.disconnect();
                    connectSocket();
                    return;
                } else {
                    cancelLoopRun();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
