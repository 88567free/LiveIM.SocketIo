package com.android.liveim.event;

import android.text.TextUtils;

import com.android.liveim.entity.LiveUserInfo;


/**
 */
public class EventMsgBase {

    public String tag;
    /**
     * {@link LiveUserInfo#USER_TYPE_AUDIENCE;LiveUserInfo#USER_TYPE_HOST;LiveUserInfo#USER_TYPE_MANAGE;LiveUserInfo#USER_TYPE_SUPER_MANAGE
     * }
     * <p/>
     * 普通聊天\礼物消息\弹幕消息\晋级提醒\土豪进房提醒\普通用户进房提醒\关注主播消息
     */
    protected String roleType = LiveUserInfo.USER_TYPE_AUDIENCE;

    private boolean isVipBubble; // 土豪气泡特权

    public boolean isVipBubble() {
        return isVipBubble;
    }

    protected void setVipBubble(String level) {
        // ToDo:
        //isVipBubble = !TextUtils.isEmpty(level) && LiveWealthLevelHelper.getBubbleLevel() <= Integer.parseInt(level);
        isVipBubble = !TextUtils.isEmpty(level) && 10 <= Integer.parseInt(level);
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }
}
