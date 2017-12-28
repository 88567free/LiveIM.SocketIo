package com.android.liveim;

/**
 * Created by admin on/12/19.
 */

public class ConfigConstant {
    /**
     * 轮询, 延迟时间
     */
    public static final int DELAY = 6000;
    /**
     * 轮询, 加入房间轮询间隔时间
     */
    public static final int PERIOD_JOIN = 5000;
    /**
     * 轮询, 重连轮询间隔时间
     */
    public static final int PERIOD_CONNECT = 6000;
    /**
     * 轮询, 连接失败或断开连接轮询间隔时间
     */
    public static final int PERIOD_FAILED = 6000;
    /**
     * 轮询, 初始化轮询间隔时间
     */
    public static final int PERIOD_INIT = 3000;

    /**
     * 重试连接的最大次数, 超过该次数重新调用{ XjbServiceHelper#requestIMAddr(boolean, XjbServiceHelper.OnRequestImListener)}
     */
    public static final int MAX_RECONNECT_COUNT = 6;
    /**
     * 重试加入房间的最大次数
     */
    public static final int MAX_JOIN_COUNT = 3;
}
