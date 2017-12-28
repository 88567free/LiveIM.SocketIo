package com.android.liveim.entity;

import android.text.TextUtils;

/**
 * 斗牛起始状态
 * Created by admin on 1/3.
 */
public class DouNiuFirstState extends PokeEntity {
    //第一张牌
    String one = "";
    int iswho = 0;

    public String getOne() {
        return one;
    }

    public void setOne(String one) {
        this.one = one;
    }

    public int getIswho() {
        return iswho;
    }

    public void setIswho(int iswho) {
        this.iswho = iswho;
    }

    public int getHuaSe() {
        if (!TextUtils.isEmpty(one)&&one.contains(",")) {
            String[] arr = one.split(",");
            return Integer.valueOf(arr[0]);
        }
        return huaSe;
    }

    public void setHuaSe(int huaSe) {
        this.huaSe = huaSe;
    }

    public int getPokeNum() {
        if (!TextUtils.isEmpty(one)&&one.contains(",")) {
            String[] arr = one.split(",");
            return Integer.valueOf(arr[1]);
        }
        return pokeNum;
    }

    public void setPokeNum(int pokeNum) {
        this.pokeNum = pokeNum;
    }
}
