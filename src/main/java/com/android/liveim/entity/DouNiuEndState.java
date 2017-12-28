package com.android.liveim.entity;

import java.util.List;

/**
 * 斗牛游戏结束
 * Created by admin on 1/3.
 */
public class DouNiuEndState {
    //此组牌属于谁 1 黄黄 2 瓜瓜 3仔仔
    int iswho = 0;
    //此组牌的牌型14炸弹>13五小牛>12五花>11四花牛>10牛牛>9牛九>8牛八>7牛七>6牛六>5牛五>4牛四> 3牛三> 2牛二> 1牛一 > 0无牛
    int cardtype = 0;
    //每组牌的类型"1,8"格式(花色,牌大小)
    String[] card = {};
    List<PokeEntity> pokeData;

    public int getIswho() {
        return iswho;
    }

    public void setIswho(int iswho) {
        this.iswho = iswho;
    }

    public int getCardtype() {
        return cardtype;
    }

    public void setCardtype(int cardtype) {
        this.cardtype = cardtype;
    }

    public String[] getCard() {
        return card;
    }

    public void setCard(String[] card) {
        this.card = card;
    }

    public List<PokeEntity> getPokeData() {
        if (card.length > 0) {
            PokeEntity pokeEntity = null;
            for (String data : card) {
                pokeEntity = new PokeEntity();
                String[] arr = data.split(",");
                pokeEntity.setHuaSe(Integer.valueOf(arr[0]));
                pokeEntity.setPokeNum(Integer.valueOf(arr[1]));
                this.pokeData.add(pokeEntity);
            }
        }
        return pokeData;
    }

    public void setPokeData(List<PokeEntity> pokeData) {
        this.pokeData = pokeData;
    }
}
