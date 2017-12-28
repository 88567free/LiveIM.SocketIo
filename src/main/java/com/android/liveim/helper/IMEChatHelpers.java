package com.android.liveim.helper;

import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.android.liveim.entity.SocketResponse;
import com.android.liveim.event.EventEChatCloseState;
import com.android.liveim.event.EventEChatDeadLine;
import com.android.liveim.event.EventEChatEndState;
import com.android.liveim.event.EventEChatGiftMsg;
import com.android.liveim.event.EventEChatHostReply;
import com.android.liveim.event.EventEChatResponse;
import com.android.liveim.event.EventEChatSocketState;
import com.android.liveim.event.EventSocket;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.socket.client.Manager;
import io.socket.client.Socket;

/**
 * Created by admin on 2017/11/21.
 */

public class IMEChatHelpers extends IBaseSocket {
    private static volatile IMEChatHelpers instance;

    public static IMEChatHelpers getInstance() {
        if (instance == null) {
            synchronized (IMEChatHelpers.class) {
                if (instance == null) {
                    instance = new IMEChatHelpers();
                }
            }
        }
        return instance;
    }

    public void connEChat() {
        connectSocket();
    }

    @Override
    protected void connectSocketSuccess() {
        EventBus.getDefault().post(new EventEChatSocketState(EventSocket.SocketState.CONNECTED));
    }

    @Override
    protected void connectError(String msg) {
        EventBus.getDefault().post(new EventEChatSocketState(msg));
    }

    @Override
    public void disConnect() {
        if (mSocket == null) {
            return;
        }
        cancelLoopRun();
        mSocket.disconnect();
        unRegisterSocketEvent();
        mSocket.off();
        mSocket.io().off();
        mSocket = null;
        resetStates();
        Log.d(TAG,"disConnect");
    }

    private void resetStates() {
        countReconnectTimes = 0;
        isConnectSuccess = false;
        isLooping = false;
        isConnecting = false;
    }

    @Override
    protected void registerSocketEvent() {

        setSocketOnEvent(IMEChatConstant.EVENT_MESSAGE_ERROR, onMsgErrorListener);
        setSocketOnEvent(IMEChatConstant.EVENT_MESSAGE_ANCHOR_REPLY, onMsgAnchorReplyListener);
        setSocketOnEvent(IMEChatConstant.EVENT_MESSAGE_END_CHAT, onMsgEndChatListener);
        setSocketOnEvent(IMEChatConstant.EVENT_MESSAGE_SEND_GIFT, onMsgSendGiftListener);
        setSocketOnEvent(IMEChatConstant.EVENT_MESSAGE_DEAD_LINE, onMsgDeadLineListener);
        setSocketOnEvent(IMEChatConstant.EVENT_MESSAGE_CLOSE_CHAT, onMsgCloseChatListener);
    }

    @Override
    protected void unRegisterSocketEvent() {
        offSocketOnEvent(Socket.EVENT_CONNECT, onConnectListener);
        offSocketOnEvent(Socket.EVENT_CONNECT_ERROR, onConnectErrorListener);
        offSocketOnEvent(Socket.EVENT_CONNECT_TIMEOUT, onConnectErrorListener);
        offSocketOnEvent(Socket.EVENT_DISCONNECT, onDisConnectListener);
        offSocketIoOnEvent(Manager.EVENT_TRANSPORT, onTransportHeader);

        offSocketOnEvent(IMEChatConstant.EVENT_MESSAGE_ERROR, onMsgErrorListener);
        offSocketOnEvent(IMEChatConstant.EVENT_MESSAGE_ANCHOR_REPLY, onMsgAnchorReplyListener);
        offSocketOnEvent(IMEChatConstant.EVENT_MESSAGE_END_CHAT, onMsgEndChatListener);
        offSocketOnEvent(IMEChatConstant.EVENT_MESSAGE_SEND_GIFT, onMsgSendGiftListener);
        offSocketOnEvent(IMEChatConstant.EVENT_MESSAGE_DEAD_LINE, onMsgDeadLineListener);
        offSocketOnEvent(IMEChatConstant.EVENT_MESSAGE_CLOSE_CHAT, onMsgCloseChatListener);
    }

    @Override
    protected Map<String, List<String>> setSocketHeader(Map<String, List<String>> headers) {
        String h = TextUtils.isEmpty(headerMap.get("h")) ? "null" : headerMap.get("h");
        String k = TextUtils.isEmpty(headerMap.get("k")) ? "null" : headerMap.get("k");
        String driveid = TextUtils.isEmpty(headerMap.get("driveid")) ? "null" : headerMap.get("driveid");
        String t = TextUtils.isEmpty(headerMap.get("t")) ? "null" : headerMap.get("t");
        String t1 = TextUtils.isEmpty(headerMap.get("t1")) ? "null" : headerMap.get("t1");
        headers.put("h", Arrays.asList(h));
        headers.put("k", Arrays.asList(k));
        headers.put("driveid", Arrays.asList(driveid));
        headers.put("t", Arrays.asList(t));
        headers.put("t1", Arrays.asList(t1));
        return headers;
    }

    /**
     * 统一错误事件
     */
    private BaseEmitterListener onMsgErrorListener = new BaseEmitterListener() {
        @Override
        public void callback(Object... args) {
            try {
                Log.d(TAG,String.valueOf(args[0]));
                SocketResponse<EventEChatResponse> errorResponse = JSON.parseObject(String.valueOf(args[0]), new TypeReference<SocketResponse<EventEChatResponse>>() {
                });
                errorResponse.getD().setSign(errorResponse.getSign() + "");
                EventBus.getDefault().post(errorResponse.getD());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    /**
     * 主播接收到请求
     */
    private BaseEmitterListener onMsgAnchorReplyListener = new BaseEmitterListener() {
        @Override
        public void callback(Object... args) {
            try {
                Log.d(TAG,String.valueOf(args[0]));
                SocketResponse<EventEChatHostReply> errorResponse = JSON.parseObject(String.valueOf(args[0]), new TypeReference<SocketResponse<EventEChatHostReply>>() {
                });
                EventBus.getDefault().post(errorResponse.getD());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    /**
     * 结束/终止聊天
     */
    private BaseEmitterListener onMsgEndChatListener = new BaseEmitterListener() {
        @Override
        public void callback(Object... args) {
            try {
                Log.d(TAG,String.valueOf(args[0]));
                SocketResponse<EventEChatEndState> errorResponse = JSON.parseObject(String.valueOf(args[0]), new TypeReference<SocketResponse<EventEChatEndState>>() {
                });
                EventBus.getDefault().post(errorResponse.getD());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    /**
     * 送礼物
     */
    private BaseEmitterListener onMsgSendGiftListener = new BaseEmitterListener() {
        @Override
        public void callback(Object... args) {
            try {
                Log.d(TAG,String.valueOf(args[0]));
                SocketResponse<EventEChatGiftMsg> errorResponse = JSON.parseObject(String.valueOf(args[0]), new TypeReference<SocketResponse<EventEChatGiftMsg>>() {
                });
                EventBus.getDefault().post(errorResponse.getD());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    /**
     * 倒计时
     */
    private BaseEmitterListener onMsgDeadLineListener = new BaseEmitterListener() {
        @Override
        public void callback(Object... args) {
            try {
                Log.d(TAG,String.valueOf(args[0]));
                SocketResponse<EventEChatDeadLine> errorResponse = JSON.parseObject(String.valueOf(args[0]), new TypeReference<SocketResponse<EventEChatDeadLine>>() {
                });
                EventBus.getDefault().post(errorResponse.getD());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    /**
     * 强制终止聊天
     */
    private BaseEmitterListener onMsgCloseChatListener = new BaseEmitterListener() {
        @Override
        public void callback(Object... args) {
            try {
                Log.d(TAG,String.valueOf(args[0]));
                SocketResponse<EventEChatCloseState> errorResponse = JSON.parseObject(String.valueOf(args[0]), new TypeReference<SocketResponse<EventEChatCloseState>>() {
                });
                errorResponse.setSign(errorResponse.getSign());
                EventBus.getDefault().post(errorResponse.getD());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };


    public void callAnchor(String hostId) {
        JSONObject jsonObject = new JSONObject();
        Log.d(TAG,IMEChatConstant.EVENT_MESSAGE_SEND_CHAT + ":" + hostId);
        try {
            jsonObject.put("f_uuid", hostId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mSocket.emit(IMEChatConstant.EVENT_MESSAGE_SEND_CHAT, jsonObject);
    }

    /**
     * @param userId
     * @param replyState 0 接收 1 拒绝
     */
    public void hostReplyToUser(String userId, String replyState) {
        JSONObject jsonObject = new JSONObject();
        Log.d(TAG,IMEChatConstant.EVENT_MESSAGE_REPLY_CHAT + ":" + userId + " reply: " + replyState);
        try {
            jsonObject.put("f_uuid", userId);
            jsonObject.put("reply", replyState);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mSocket.emit(IMEChatConstant.EVENT_MESSAGE_REPLY_CHAT, jsonObject);
    }

    public void sendGift(String virtual) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("virtual", virtual);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mSocket.emit(IMEChatConstant.EVENT_MESSAGE_SEND_GIFT, jsonObject);
    }

    public void waitUserChat(String hostId) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("f_uuid", hostId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mSocket.emit(IMEChatConstant.EVENT_MESSAGE_WAIT_USER_CHAT, jsonObject);
    }

    public void userHandUp(String hostId) {
        if (mSocket == null) {
            return;
        }
        JSONObject jsonObject = new JSONObject();
        Log.d(TAG,IMEChatConstant.EVENT_MESSAGE_USER_END_CHAT + ":" + hostId );
        try {
            jsonObject.put("f_uuid", hostId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mSocket.emit(IMEChatConstant.EVENT_MESSAGE_USER_END_CHAT, jsonObject);
    }

    public void hostHandUp() {
        if (mSocket == null) {
            return;
        }
        mSocket.emit(IMEChatConstant.EVENT_MESSAGE_END_CHAT);
    }



}
