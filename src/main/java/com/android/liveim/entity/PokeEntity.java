package com.android.liveim.entity;

/**
 * 牌
 * Created by admin on 1/3.
 */
public class PokeEntity {
    //花色 4=黑桃，3=红桃，2=梅花，1=方块
    int huaSe = 0;
    //牌数字
    int pokeNum = 0;

    public int getHuaSe() {
        return huaSe;
    }

    public void setHuaSe(int huaSe) {
        this.huaSe = huaSe;
    }

    public int getPokeNum() {
        return pokeNum;
    }

    public void setPokeNum(int pokeNum) {
        this.pokeNum = pokeNum;
    }
}
