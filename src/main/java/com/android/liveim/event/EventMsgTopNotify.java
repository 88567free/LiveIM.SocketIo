package com.android.liveim.event;

/**
 * Created by admin on 3/15.
 */
//type  0礼物消息 1游戏赢钱消息
//one_name  用户昵称
//        one_color 昵称颜色
//        two_name  当type=0时 此字段为主播昵称 当type=1时此字段为钻石数字
//        two_color 当type=0时 此字段为主播昵称颜色 当type=1时此字段为钻石颜色
//
//        text 当type=0时 此字段为礼物昵称 当type=1时此字段为游戏名称
public class EventMsgTopNotify {
    int type = 0;
    String one_name = "";
    String one_color = "#fcd934";
    String two_name = "";
    String two_color = "#fd4277";
    String text = "";
    String quantifier = "";

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getOne_name() {
        return one_name;
    }

    public void setOne_name(String one_name) {
        this.one_name = one_name;
    }

    public String getOne_color() {
        return one_color;
    }

    public void setOne_color(String one_color) {
        this.one_color = one_color;
    }

    public String getTwo_name() {
        return two_name;
    }

    public void setTwo_name(String two_name) {
        this.two_name = two_name;
    }

    public String getTwo_color() {
        return two_color;
    }

    public void setTwo_color(String two_color) {
        this.two_color = two_color;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getQuantifier() {
        return quantifier;
    }

    public void setQuantifier(String quantifier) {
        this.quantifier = quantifier;
    }
}
