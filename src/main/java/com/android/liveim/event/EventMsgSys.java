package com.android.liveim.event;

import android.text.TextUtils;

/**
 * 系统消息
 * Created by admin on 1/19.
 */
public class EventMsgSys {
    public static String Event_WARNING = "jinggao";
    public static String Event_TASK = "task";
    public static String Event_ATTENTION = "guanzhu";

    String title = "";
    String time = "";
    String msg = "";
    String f_uuid = "";
    String nickname = "";
    String head = "";
    String level = ""; // 财富等级
    String is_anchor;
    String msg_colour = "";
    String nickname_colour = "";
    String event = "";
    String task_total_num = "";
    String task_complete_num = "";
    int task_has_reward = 0;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getF_uuid() {
        return f_uuid;
    }

    public void setF_uuid(String f_uuid) {
        this.f_uuid = f_uuid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getIs_anchor() {
        return is_anchor;
    }

    public void setIs_anchor(String is_anchor) {
        this.is_anchor = is_anchor;
    }

    public String getMsg_colour() {
        if (TextUtils.isEmpty(msg_colour)) {
            return "#ffffff";
        }
        return msg_colour;
    }

    public void setMsg_colour(String msg_colour) {
        this.msg_colour = msg_colour;
    }

    public String getNickname_colour() {
        return nickname_colour;
    }

    public void setNickname_colour(String nickname_colour) {
        this.nickname_colour = nickname_colour;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getTask_total_num() {
        return task_total_num;
    }

    public void setTask_total_num(String task_total_num) {
        this.task_total_num = task_total_num;
    }

    public String getTask_complete_num() {
        return task_complete_num;
    }

    public void setTask_complete_num(String task_complete_num) {
        this.task_complete_num = task_complete_num;
    }

    public int getTask_has_reward() {
        return task_has_reward;
    }

    public void setTask_has_reward(int task_has_reward) {
        this.task_has_reward = task_has_reward;
    }
}
