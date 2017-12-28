package com.android.liveim.helper;

import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.android.liveim.ConfigConstant;
import com.android.liveim.entity.DouNiuFirstState;
import com.android.liveim.entity.ErrorResponse;
import com.android.liveim.entity.EventGiftRanking;
import com.android.liveim.entity.SocketResponse;
import com.android.liveim.event.EventGameEndJson;
import com.android.liveim.event.EventGameEndRanking;
import com.android.liveim.event.EventGameFirstJson;
import com.android.liveim.event.EventGameInitState;
import com.android.liveim.event.EventGameReplace;
import com.android.liveim.event.EventGameStake;
import com.android.liveim.event.EventGameStakeResult;
import com.android.liveim.event.EventIMGiftMsg;
import com.android.liveim.event.EventMsgCameraState;
import com.android.liveim.event.EventMsgChargePay;
import com.android.liveim.event.EventMsgChat;
import com.android.liveim.event.EventMsgHostChargeState;
import com.android.liveim.event.EventMsgHostPauseLeave;
import com.android.liveim.event.EventMsgHostVipState;
import com.android.liveim.event.EventMsgIntoRoom;
import com.android.liveim.event.EventMsgLeave;
import com.android.liveim.event.EventMsgPause;
import com.android.liveim.event.EventMsgRedPackage;
import com.android.liveim.event.EventMsgStates;
import com.android.liveim.event.EventMsgSys;
import com.android.liveim.event.EventMsgTopNotify;
import com.android.liveim.event.EventMsgVipState;
import com.android.liveim.event.EventSocket;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.socket.client.Manager;
import io.socket.client.Socket;

import static com.android.liveim.helper.IMEventConstant.EVENT_MESSAGE_AUDIENCE_REJION;
import static com.android.liveim.helper.IMEventConstant.EVENT_MESSAGE_CHARGE_NO_PAY;
import static com.android.liveim.helper.IMEventConstant.EVENT_MESSAGE_CHAT;
import static com.android.liveim.helper.IMEventConstant.EVENT_MESSAGE_ERROR;
import static com.android.liveim.helper.IMEventConstant.EVENT_MESSAGE_GAME_END_RANKING;
import static com.android.liveim.helper.IMEventConstant.EVENT_MESSAGE_GAME_END_STATES;
import static com.android.liveim.helper.IMEventConstant.EVENT_MESSAGE_GAME_FIRST_STATES;
import static com.android.liveim.helper.IMEventConstant.EVENT_MESSAGE_GAME_INIT;
import static com.android.liveim.helper.IMEventConstant.EVENT_MESSAGE_GAME_REPLACE_GAME;
import static com.android.liveim.helper.IMEventConstant.EVENT_MESSAGE_GAME_STAKE;
import static com.android.liveim.helper.IMEventConstant.EVENT_MESSAGE_GAME_STAKE_RESULT;
import static com.android.liveim.helper.IMEventConstant.EVENT_MESSAGE_GAME_SWITCH_GAME;
import static com.android.liveim.helper.IMEventConstant.EVENT_MESSAGE_GET_RED_PACKAGE;
import static com.android.liveim.helper.IMEventConstant.EVENT_MESSAGE_GIFT;
import static com.android.liveim.helper.IMEventConstant.EVENT_MESSAGE_HOST_CAMERA_CLOSE;
import static com.android.liveim.helper.IMEventConstant.EVENT_MESSAGE_HOST_CHARGE_STATE;
import static com.android.liveim.helper.IMEventConstant.EVENT_MESSAGE_HOST_KICK;
import static com.android.liveim.helper.IMEventConstant.EVENT_MESSAGE_HOST_PAUSE_LEAVE;
import static com.android.liveim.helper.IMEventConstant.EVENT_MESSAGE_HOST_VIP_STATE;
import static com.android.liveim.helper.IMEventConstant.EVENT_MESSAGE_JIN;
import static com.android.liveim.helper.IMEventConstant.EVENT_MESSAGE_JINZHUSORT;
import static com.android.liveim.helper.IMEventConstant.EVENT_MESSAGE_LEAVE;
import static com.android.liveim.helper.IMEventConstant.EVENT_MESSAGE_PAUSE;
import static com.android.liveim.helper.IMEventConstant.EVENT_MESSAGE_PINTO_ROOM;
import static com.android.liveim.helper.IMEventConstant.EVENT_MESSAGE_ROOMADMIN;
import static com.android.liveim.helper.IMEventConstant.EVENT_MESSAGE_STATES;
import static com.android.liveim.helper.IMEventConstant.EVENT_MESSAGE_SYSMSG;
import static com.android.liveim.helper.IMEventConstant.EVENT_MESSAGE_TOP_NOTIFY;
import static com.android.liveim.helper.IMEventConstant.EVENT_MESSAGE_USER_CAMERA_STATE;
import static com.android.liveim.helper.IMEventConstant.EVENT_MESSAGE_VIP_STATE;

/**
 * Created by admin on/12/27.
 */
public class IMLiveHelpers extends IBaseSocket implements IMEmitEvent {

    private static volatile IMLiveHelpers instance;
    private boolean isLogin = true;
    private String gameType = "-1";
    private boolean isStart = false;
    private String strRoomUuid = "";
    private boolean isJoinSuccess = false;
    private boolean isAudienceJoin = false;//观众是否join过
    private boolean isHost = false;

    public static IMLiveHelpers getInstance() {
        if (instance == null) {
            synchronized (IMLiveHelpers.class) {
                if (instance == null) {
                    instance = new IMLiveHelpers();
                }
            }
        }
        return instance;
    }

    @Override
    protected void connectSocketSuccess() {
        join(strRoomUuid, isStart, gameType);
    }

    @Override
    protected void connectError(String msg) {
        EventBus.getDefault().post(new EventSocket(msg));
    }

    private void join(String strRoomUuid, boolean isStart, String gameType) {
        if (TextUtils.isEmpty(strRoomUuid)) {
            Log.e(TAG, "roomUuid is null");
            return;
        }
        if (!isHost && isAudienceJoin) {//观众房间重连
            Log.d(TAG, "观众房间重连");
            joinOnAudienceDisConn(strRoomUuid, isStart, gameType);
        } else {
            joinOnEvent(strRoomUuid, isStart, gameType);
        }
    }

    public void connectRoom(String strRoomUuid, boolean isStart, boolean isLogin, String gameType) {
        this.isLogin = isLogin;
        this.strRoomUuid = strRoomUuid;
        this.isStart = isStart;
        this.gameType = gameType;
        if (!isConnecting) {
            connectSocket();
        } else {
            if (!isJoinSuccess && isConnected()) {
                join(strRoomUuid, isStart, gameType);
            }
        }
    }


    @Override
    public void disConnect() {
        strRoomUuid = "";
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
        Log.d(TAG, "IM 断开连接");
    }

    @Override
    protected void registerSocketEvent() {

        setSocketOnEvent(EVENT_MESSAGE_ERROR, onMsgErrorListener);
        setSocketOnEvent(EVENT_MESSAGE_CHAT, onMsgChatListener);
        setSocketOnEvent(EVENT_MESSAGE_PINTO_ROOM, onMsgPIntoRoomListener);
        setSocketOnEvent(EVENT_MESSAGE_GIFT, onMsgGiftListener);
        setSocketOnEvent(EVENT_MESSAGE_JINZHUSORT, onMsgJinZhuListener);
        setSocketOnEvent(EVENT_MESSAGE_STATES, onMsgStatesListener);
        setSocketOnEvent(EVENT_MESSAGE_ROOMADMIN, onMsgChatListener);
        setSocketOnEvent(EVENT_MESSAGE_SYSMSG, onMsgSysMsgListener);
        setSocketOnEvent(EVENT_MESSAGE_LEAVE, onMsgLeaveListener);
        setSocketOnEvent(EVENT_MESSAGE_PAUSE, onMsgPauseListener);
        setSocketOnEvent(EVENT_MESSAGE_TOP_NOTIFY, onMsgTopNotifyListener);
        setSocketOnEvent(EVENT_MESSAGE_GAME_INIT, onMsgGameInitListener);
        setSocketOnEvent(EVENT_MESSAGE_GAME_FIRST_STATES, onMsgGameStatesListener);
        setSocketOnEvent(EVENT_MESSAGE_GAME_END_STATES, onMsgGameEndStatesListener);
        setSocketOnEvent(EVENT_MESSAGE_GAME_STAKE, onMsgGameStakeListener);
        setSocketOnEvent(EVENT_MESSAGE_GAME_END_RANKING, onMsgGameEndRankingListener);
        setSocketOnEvent(EVENT_MESSAGE_GAME_STAKE_RESULT, onMsgGameStakeResultListener);
        setSocketOnEvent(EVENT_MESSAGE_GAME_REPLACE_GAME, onMsgReplaceGameListener);
        setSocketOnEvent(EVENT_MESSAGE_VIP_STATE, onMsgVipStateListener);
        setSocketOnEvent(EVENT_MESSAGE_HOST_VIP_STATE, onMsgHostVipStateListener);
        setSocketOnEvent(EVENT_MESSAGE_CHARGE_NO_PAY, onMsgRoomChargePayStateListener);
        setSocketOnEvent(EVENT_MESSAGE_HOST_CHARGE_STATE, onMsgHostChargeStateListener);
        setSocketOnEvent(EVENT_MESSAGE_HOST_PAUSE_LEAVE, onMsgHostPauseLeaveStateListener);
        setSocketOnEvent(EVENT_MESSAGE_USER_CAMERA_STATE, onMsgCameraStateListener);
        setSocketOnEvent(EVENT_MESSAGE_GET_RED_PACKAGE, onMsgRedPackageListener);

    }

    @Override
    protected void unRegisterSocketEvent() {
        offSocketOnEvent(Socket.EVENT_CONNECT, onConnectListener);
        offSocketOnEvent(Socket.EVENT_CONNECT_ERROR, onConnectErrorListener);
        offSocketOnEvent(Socket.EVENT_CONNECT_TIMEOUT, onConnectErrorListener);
        offSocketOnEvent(Socket.EVENT_DISCONNECT, onDisConnectListener);
        offSocketIoOnEvent(Manager.EVENT_TRANSPORT, onTransportHeader);

        offSocketOnEvent(EVENT_MESSAGE_ERROR, onMsgErrorListener);
        offSocketOnEvent(EVENT_MESSAGE_CHAT, onMsgChatListener);
        offSocketOnEvent(EVENT_MESSAGE_PINTO_ROOM, onMsgPIntoRoomListener);
        offSocketOnEvent(EVENT_MESSAGE_GIFT, onMsgGiftListener);
        offSocketOnEvent(EVENT_MESSAGE_JINZHUSORT, onMsgJinZhuListener);
        offSocketOnEvent(EVENT_MESSAGE_STATES, onMsgStatesListener);
        offSocketOnEvent(EVENT_MESSAGE_ROOMADMIN, onMsgChatListener);
        offSocketOnEvent(EVENT_MESSAGE_SYSMSG, onMsgSysMsgListener);
        offSocketOnEvent(EVENT_MESSAGE_LEAVE, onMsgLeaveListener);
        offSocketOnEvent(EVENT_MESSAGE_PAUSE, onMsgPauseListener);
        offSocketOnEvent(EVENT_MESSAGE_TOP_NOTIFY, onMsgTopNotifyListener);
        offSocketOnEvent(EVENT_MESSAGE_GAME_INIT, onMsgGameInitListener);
        offSocketOnEvent(EVENT_MESSAGE_GAME_FIRST_STATES, onMsgGameStatesListener);
        offSocketOnEvent(EVENT_MESSAGE_GAME_END_STATES, onMsgGameEndStatesListener);
        offSocketOnEvent(EVENT_MESSAGE_GAME_STAKE, onMsgGameStakeListener);
        offSocketOnEvent(EVENT_MESSAGE_GAME_END_RANKING, onMsgGameEndRankingListener);
        offSocketOnEvent(EVENT_MESSAGE_GAME_STAKE_RESULT, onMsgGameStakeResultListener);
        offSocketOnEvent(EVENT_MESSAGE_GAME_REPLACE_GAME, onMsgReplaceGameListener);
        offSocketOnEvent(EVENT_MESSAGE_VIP_STATE, onMsgVipStateListener);
        offSocketOnEvent(EVENT_MESSAGE_HOST_VIP_STATE, onMsgHostVipStateListener);
        offSocketOnEvent(EVENT_MESSAGE_CHARGE_NO_PAY, onMsgRoomChargePayStateListener);
        offSocketOnEvent(EVENT_MESSAGE_HOST_CHARGE_STATE, onMsgHostChargeStateListener);
        offSocketOnEvent(EVENT_MESSAGE_HOST_PAUSE_LEAVE, onMsgHostPauseLeaveStateListener);
        offSocketOnEvent(EVENT_MESSAGE_USER_CAMERA_STATE, onMsgCameraStateListener);
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
        if (isLogin) {
            headers.put("t", Arrays.asList(t));
        }
        headers.put("t1", Arrays.asList(t1));
        return headers;
    }

    private void resetStates() {
        countReconnectTimes = 0;
        isConnectSuccess = false;
        isJoinSuccess = false;
        isLooping = false;
        isHost = false;
        isConnecting = false;
    }


    /**
     * 统一错误事件
     */
    private BaseEmitterListener onMsgErrorListener = new BaseEmitterListener() {
        @Override
        public void callback(Object... args) {
            try {
                Log.d(TAG, String.valueOf(args[0]));
                SocketResponse<ErrorResponse> errorResponse = JSON.parseObject(String.valueOf(args[0]), new TypeReference<SocketResponse<ErrorResponse>>() {
                });
                errorResponse.getD().setSign(errorResponse.getSign() + "");
                EventBus.getDefault().post(errorResponse.getD());
                if (!isSuccessSign(errorResponse) && errorResponse.getD().getEvent().equals(IMEventConstant.EVENT_PROTOCOL_JOIN) && !isLooping) {
                    Log.d(TAG, "加入房间失败");
                    isJoinSuccess = false;
                    if (loopJoinRoomRunnable == null) {
                        loopJoinRoomRunnable = new LoopJoinRoomRunnable();
                    }
                    loopJoinRoomRunnable.setPeriodTime(ConfigConstant.PERIOD_JOIN);
                    handlerLooper.postDelayed(loopJoinRoomRunnable, ConfigConstant.PERIOD_JOIN);
                } else {
                    isJoinSuccess = true;
                    if (!isHost) {
                        isAudienceJoin = true;
                    }
                    countReconnectTimes = 0;
                    cancelLoopRun();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    /**
     * 系统消息监听
     */
    private BaseEmitterListener onMsgSysMsgListener = new BaseEmitterListener() {
        @Override
        public void callback(final Object... args) {
            try {
                Log.d(TAG, "系统消息:" + String.valueOf(args[0]));
                SocketResponse<EventMsgSys> eventMsgSys = parseJson(String.valueOf(args[0]), EventMsgSys.class);
                if (isSuccessSign(eventMsgSys)) {
                    eventMsgSys.getD().setEvent(eventMsgSys.getEvent());
                    EventBus.getDefault().post(eventMsgSys.getD());
                }
                cancelLoopRun();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    /**
     * 普通聊天消息监听
     */
    private BaseEmitterListener onMsgChatListener = new BaseEmitterListener() {
        @Override
        public void callback(final Object... args) {
            try {
                Log.d(TAG, "普通聊天:" + String.valueOf(args[0]));
                final SocketResponse<EventMsgChat> eventMsgChat = parseJson(String.valueOf(args[0]), EventMsgChat.class);
                if (isSuccessSign(eventMsgChat)) {
                    EventBus.getDefault().post(eventMsgChat.getD());
                }
                cancelLoopRun();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    /**
     * 礼物消息监听
     */
    private BaseEmitterListener onMsgGiftListener = new BaseEmitterListener() {
        @Override
        public void callback(final Object... args) {
            try {
                Log.d(TAG, String.valueOf(args[0]));
                final SocketResponse<EventIMGiftMsg> socketResponse = parseJson(String.valueOf(args[0]), EventIMGiftMsg.class);
                if (isSuccessSign(socketResponse)) {
                    EventBus.getDefault().post(socketResponse.getD());
                }
                cancelLoopRun();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    //直播状态
    private BaseEmitterListener onMsgStatesListener = new BaseEmitterListener() {
        @Override
        public void callback(final Object... args) {
            try {
                Log.d(TAG, "直播状态:" + String.valueOf(args[0]));
                final SocketResponse<EventMsgStates> socketResponse = parseJson(String.valueOf(args[0]), EventMsgStates.class);
                if (isSuccessSign(socketResponse)) {
                    EventBus.getDefault().post(socketResponse.getD());
                }
                cancelLoopRun();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    /**
     * 用户进入房间消息
     */
    private BaseEmitterListener onMsgPIntoRoomListener = new BaseEmitterListener() {
        @Override
        public void callback(Object... args) throws JSONException {
            try {
                Log.d(TAG, String.valueOf(args[0]));
                final SocketResponse<EventMsgIntoRoom> socketResponse = parseJson(String.valueOf(args[0]), EventMsgIntoRoom.class);
                if (isSuccessSign(socketResponse)) {
                    EventBus.getDefault().post(socketResponse.getD());
                }
                cancelLoopRun();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    /**
     * 金主排行
     */
    private BaseEmitterListener onMsgJinZhuListener = new BaseEmitterListener() {
        SocketResponse<JSONArray> socketResponse;
        List<EventGiftRanking> giftRanking;

        @Override
        public void callback(Object... args) throws JSONException {
            Log.d(TAG, "金主排行" + String.valueOf(args[0]));
            socketResponse = JSON.parseObject(String.valueOf(args[0]), SocketResponse.class);
            if (isSuccessSign(socketResponse)) {
                giftRanking = JSON.parseArray(socketResponse.getD().toJSONString(), EventGiftRanking.class);
                EventBus.getDefault().post(giftRanking);
            }
            cancelLoopRun();
        }
    };

    private BaseEmitterListener onMsgGameInitListener = new BaseEmitterListener() {
        EventGameInitState eventGameInitState;

        @Override
        public void callback(Object... args) throws JSONException {
            Log.d(TAG, "游戏初始状态" + String.valueOf(args[0]));
            final SocketResponse<com.alibaba.fastjson.JSONObject> socketResponse = JSON.parseObject(String.valueOf(args[0]), SocketResponse.class);
            if (isSuccessSign(socketResponse)) {
                eventGameInitState = JSON.parseObject(socketResponse.getD().toJSONString(), EventGameInitState.class);
                eventGameInitState.setJson(socketResponse.getD().toJSONString());
                EventBus.getDefault().post(eventGameInitState);
            }
        }
    };

    /**
     * 游戏状态
     */
    private BaseEmitterListener onMsgGameStatesListener = new BaseEmitterListener() {
        EventGameFirstJson eventGameFirstJson;

        @Override
        public void callback(Object... args) throws JSONException {
            Log.d(TAG, "游戏开始状态" + String.valueOf(args[0]));
            final SocketResponse<com.alibaba.fastjson.JSONObject> socketResponse = JSON.parseObject(String.valueOf(args[0]), SocketResponse.class);
            if (isSuccessSign(socketResponse)) {
                eventGameFirstJson = new EventGameFirstJson();
                eventGameFirstJson.setGameId(socketResponse.getD().getLongValue("gameid"));
                eventGameFirstJson.setCards(JSON.parseArray(socketResponse.getD().getString("cards"), DouNiuFirstState.class));
                eventGameFirstJson.setJson(socketResponse.getD().toJSONString());
                EventBus.getDefault().post(eventGameFirstJson);
            }
            cancelLoopRun();
        }
    };

    /**
     * 游戏回结束
     */
    private BaseEmitterListener onMsgGameEndStatesListener = new BaseEmitterListener() {
        EventGameEndJson eventGameEndJson;

        @Override
        public void callback(Object... args) throws JSONException {
            Log.d(TAG, "游戏回合结束" + String.valueOf(args[0]));
            final SocketResponse<com.alibaba.fastjson.JSONObject> socketResponse = JSON.parseObject(String.valueOf(args[0]), SocketResponse.class);
            if (isSuccessSign(socketResponse)) {
                eventGameEndJson = new EventGameEndJson();
                eventGameEndJson.setGameId(socketResponse.getD().getLongValue("gameid"));
                eventGameEndJson.setJson(socketResponse.getD().toJSONString());
                EventBus.getDefault().post(eventGameEndJson);
            }
            cancelLoopRun();
        }
    };

    /**
     * 游戏回合排名
     */
    private BaseEmitterListener onMsgGameEndRankingListener = new BaseEmitterListener() {
        @Override
        public void callback(Object... args) throws JSONException {
            Log.d(TAG, "游戏回合排名" + String.valueOf(args[0]));
            final SocketResponse<EventGameEndRanking> socketResponse = parseJson(String.valueOf(args[0]), EventGameEndRanking.class);
            if (isSuccessSign(socketResponse)) {
                EventBus.getDefault().post(socketResponse.getD());
            }
        }
    };

    /**
     * 押注输赢回调
     */
    private BaseEmitterListener onMsgGameStakeResultListener = new BaseEmitterListener() {
        @Override
        public void callback(Object... args) throws JSONException {
            Log.d(TAG, "押注输赢" + String.valueOf(args[0]));
            final SocketResponse<EventGameStakeResult> socketResponse = parseJson(String.valueOf(args[0]), EventGameStakeResult.class);
            if (isSuccessSign(socketResponse)) {
                EventBus.getDefault().post(socketResponse.getD());
            }
        }
    };

    /**
     * 押注回调
     */
    private BaseEmitterListener onMsgGameStakeListener = new BaseEmitterListener() {
        @Override
        public void callback(Object... args) throws JSONException {
            Log.d(TAG, "游戏全盘押注" + String.valueOf(args[0]));
            final SocketResponse<EventGameStake> socketResponse = parseJson(String.valueOf(args[0]), EventGameStake.class);
            if (isSuccessSign(socketResponse)) {
                EventBus.getDefault().post(socketResponse.getD());
            }
            cancelLoopRun();
        }
    };


    //直播停播
    private BaseEmitterListener onMsgLeaveListener = new BaseEmitterListener() {
        @Override
        public void callback(final Object... args) {
            try {
                Log.d(TAG, "直播停播:" + String.valueOf(args[0]));
                final SocketResponse<EventMsgLeave> socketResponse = parseJson(String.valueOf(args[0]), EventMsgLeave.class);
                if (isSuccessSign(socketResponse)) {
                    EventBus.getDefault().post(socketResponse.getD());
                }
                cancelLoopRun();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    /**
     * 直播暂停
     */
    private BaseEmitterListener onMsgPauseListener = new BaseEmitterListener() {
        @Override
        public void callback(final Object... args) {
            try {
                Log.d(TAG, "直播暂离:" + String.valueOf(args[0]));
                final SocketResponse<EventMsgPause> socketResponse = parseJson(String.valueOf(args[0]), EventMsgPause.class);
                if (isSuccessSign(socketResponse)) {
                    EventBus.getDefault().post(socketResponse.getD());
                }
                cancelLoopRun();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    /**
     * 游戏更换
     */
    private BaseEmitterListener onMsgReplaceGameListener = new BaseEmitterListener() {
        @Override
        public void callback(final Object... args) {
            Log.d(TAG, "直播游戏更换:" + String.valueOf(args[0]));
            final SocketResponse<EventGameReplace> socketResponse = parseJson(String.valueOf(args[0]), EventGameReplace.class);
            if (isSuccessSign(socketResponse)) {
                EventBus.getDefault().post(socketResponse.getD());
            }
        }
    };

    /**
     * 房间顶部全局消息
     */
    private BaseEmitterListener onMsgTopNotifyListener = new BaseEmitterListener() {
        @Override
        public void callback(final Object... args) {
            Log.d(TAG, "顶部消息:" + String.valueOf(args[0]));
            final SocketResponse<EventMsgTopNotify> socketResponse = parseJson(String.valueOf(args[0]), EventMsgTopNotify.class);
            if (isSuccessSign(socketResponse)) {
                EventBus.getDefault().post(socketResponse.getD());
            }
        }
    };

    /**
     * 用户Vip变更消息
     */
    private BaseEmitterListener onMsgVipStateListener = new BaseEmitterListener() {
        @Override
        public void callback(final Object... args) {
            Log.d(TAG, "Vip变更消息:" + String.valueOf(args[0]));
            final SocketResponse<EventMsgVipState> socketResponse = parseJson(String.valueOf(args[0]), EventMsgVipState.class);
            if (isSuccessSign(socketResponse)) {
                EventBus.getDefault().post(socketResponse.getD());
            }
        }
    };

    /**
     * 主播Vip变更消息
     */
    private BaseEmitterListener onMsgHostVipStateListener = new BaseEmitterListener() {
        @Override
        public void callback(final Object... args) {
            Log.d(TAG, "主播Vip变更消息:" + String.valueOf(args[0]));
            final SocketResponse<EventMsgHostVipState> socketResponse = parseJson(String.valueOf(args[0]), EventMsgHostVipState.class);
            if (isSuccessSign(socketResponse)) {
                EventBus.getDefault().post(socketResponse.getD());
            }
        }
    };

    /**
     * 房间付费变更消息
     */
    private BaseEmitterListener onMsgRoomChargePayStateListener = new BaseEmitterListener() {
        @Override
        public void callback(final Object... args) {
            Log.d(TAG, "房间付费变更消息:" + String.valueOf(args[0]));
            final SocketResponse<EventMsgChargePay> socketResponse = parseJson(String.valueOf(args[0]), EventMsgChargePay.class);
            if (isSuccessSign(socketResponse)) {
                EventBus.getDefault().post(socketResponse.getD());
            }
        }
    };

    /**
     * 主播房间付费变更消息
     */
    private BaseEmitterListener onMsgHostChargeStateListener = new BaseEmitterListener() {
        @Override
        public void callback(final Object... args) {
            Log.d(TAG, "主播房间付费变更消息:" + String.valueOf(args[0]));
            final SocketResponse<EventMsgHostChargeState> socketResponse = parseJson(String.valueOf(args[0]), EventMsgHostChargeState.class);
            if (isSuccessSign(socketResponse)) {
                EventBus.getDefault().post(socketResponse.getD());
            }
        }
    };

    /**
     * 主播暂时退出
     */
    private BaseEmitterListener onMsgHostPauseLeaveStateListener = new BaseEmitterListener() {
        @Override
        public void callback(final Object... args) {
            Log.d(TAG, "主播暂时退出:" + String.valueOf(args[0]));
            final SocketResponse<EventMsgHostPauseLeave> socketResponse = parseJson(String.valueOf(args[0]), EventMsgHostPauseLeave.class);
            if (isSuccessSign(socketResponse)) {
                EventBus.getDefault().post(socketResponse.getD());
            }
        }
    };

    /**
     * 主播摄像头
     */
    private BaseEmitterListener onMsgCameraStateListener = new BaseEmitterListener() {
        @Override
        public void callback(final Object... args) {
            Log.d(TAG, "主播摄像头:" + String.valueOf(args[0]));
            final SocketResponse<EventMsgCameraState> socketResponse = parseJson(String.valueOf(args[0]), EventMsgCameraState.class);
            if (isSuccessSign(socketResponse)) {
                EventBus.getDefault().post(socketResponse.getD());
            }
        }
    };

    /**
     * 获得红包
     */
    private BaseEmitterListener onMsgRedPackageListener = new BaseEmitterListener() {
        @Override
        public void callback(final Object... args) {
            Log.d(TAG, "获得红包:" + String.valueOf(args[0]));
            final SocketResponse<EventMsgRedPackage> socketResponse = parseJson(String.valueOf(args[0]), EventMsgRedPackage.class);
            if (isSuccessSign(socketResponse)) {
                EventBus.getDefault().post(socketResponse.getD());
            }
        }
    };

    /**
     * 进入房间
     *
     * @param roomId
     * @param isStart  false看主播,true开主播
     * @param gameType
     */
    @Override
    public void joinOnEvent(String roomId, boolean isStart, String gameType) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("f_uuid", roomId);
            jsonObject.put("istart", isStart);
            jsonObject.put("isgame", gameType);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mSocket.emit(IMEventConstant.EVENT_PROTOCOL_JOIN, jsonObject);
    }

    @Override
    public void sendMsg(String msg, String roomId) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("msg", msg);
            obj.put("f_uuid", roomId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (mSocket != null) {
            mSocket.emit(EVENT_MESSAGE_CHAT, obj);
        }
    }

    @Override
    public void sendGift(String id, String roomId) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("id", id);
            obj.put("f_uuid", roomId);
            obj.put("time", System.currentTimeMillis());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (mSocket != null) {
            mSocket.emit(EVENT_MESSAGE_GIFT, obj);
        }
    }

    @Override
    public void jin(String token, String isJin) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("t", token);
            obj.put("isjin", isJin);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mSocket.emit(EVENT_MESSAGE_JIN, obj);
    }

    @Override
    public void setAdmin(String token) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("t", token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mSocket.emit(EVENT_MESSAGE_ROOMADMIN, obj);
    }

    @Override
    public void stakeCoin(String roomId, String coin, String isWho) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("f_uuid", roomId);
            obj.put("stake", coin);
            obj.put("iswho", isWho);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mSocket.emit(EVENT_MESSAGE_GAME_STAKE, obj);
    }

    @Override
    public void leave(String roomId) {
        if (mSocket != null) {
            mSocket.emit(EVENT_MESSAGE_LEAVE, roomId);
        }
    }

    /**
     * 观众重进
     *
     * @param roomId
     * @param isStart  false看主播,true开主播
     * @param gameType
     */
    @Override
    public void joinOnAudienceDisConn(String roomId, boolean isStart, String gameType) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("f_uuid", roomId);
            jsonObject.put("istart", isStart);
            jsonObject.put("isgame", gameType);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mSocket.emit(EVENT_MESSAGE_AUDIENCE_REJION, jsonObject);
    }

    @Override
    public void changeGameType(String roomId, String type) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("f_uuid", roomId);
            jsonObject.put("isgame", type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "游戏切换:" + type);
        mSocket.emit(EVENT_MESSAGE_GAME_SWITCH_GAME, jsonObject);
    }

    public void hostKick(String usesId) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("ufuuid", usesId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "主播踢人:" + usesId);
        mSocket.emit(EVENT_MESSAGE_HOST_KICK, jsonObject);
    }

    public void hostCloseCamera(boolean isClose) {
        mSocket.emit(EVENT_MESSAGE_HOST_CAMERA_CLOSE, isClose ? "0" : "1");
    }

    public void getRedPackage(String roomId) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("f_uuid", roomId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "领红包:" + roomId);
        mSocket.emit("redpacket", jsonObject);
    }

    public void setAudienceJoin(boolean audienceJoin) {
        isAudienceJoin = audienceJoin;
    }

    public void setHost(boolean host) {
        isHost = host;
    }
}
