package com.android.liveim.helper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.android.liveim.entity.SocketResponse;

import org.json.JSONException;

import io.socket.emitter.Emitter;

/**
 */
public abstract class BaseEmitterListener implements Emitter.Listener {

    @Override
    public final void call(Object... args) {
        try {
            callback(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract void callback(Object... args) throws JSONException;

    public <T> SocketResponse<T> parseJson(String json, Class<T> clazz) {
        try{
            SocketResponse<T> response = JSON.parseObject(json,SocketResponse.class);
            if(!isSuccessSign(response)){
                JSONObject jsonObject = JSON.parseObject(json);
                String msg = jsonObject.getJSONObject("d").getString("msg");
                response.setMsg(msg);
            }else{
                T t = JSON.parseObject(response.getD().toString(), clazz);
                response.setD(t);
            }
            return response;
        }catch (com.alibaba.fastjson.JSONException e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean isSuccessSign(SocketResponse response){
        return response.getSign() == 1;
    }
}
