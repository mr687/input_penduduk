package com.penduduk.penduduk;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONParse {
    public String uid, token, json;

    public JSONParse(String json){
        this.json = json;
    }

    protected void JSONParse(){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            uid = jsonObject.getString("userid");
            token = jsonObject.getString("token");
        } catch (JSONException e){
            e.printStackTrace();
        }
    }
}
