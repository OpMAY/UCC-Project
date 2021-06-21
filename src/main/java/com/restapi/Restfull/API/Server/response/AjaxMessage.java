package com.restapi.Restfull.API.Server.response;

import lombok.extern.log4j.Log4j2;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

@Log4j2
public class AjaxMessage {
    private JSONObject jsonObject;

    public AjaxMessage() {
        this.jsonObject = new JSONObject();
    }

    public <T> void put(String key, T object) throws JSONException {
        jsonObject.put(key, object);
    }

    public <T> boolean pop(String key) {
        Iterator iterator = this.jsonObject.keys();
        while (iterator.hasNext()) {
            boolean check = ((String) iterator.next()).equals(key) ? true : false;
            if (check) {
                this.jsonObject.remove(key);
                return true;
            }
            continue;
        }
        return false;
    }

    public JSONObject getJSONObject() throws JSONException {
        Iterator iterator = this.jsonObject.keys();
        String log_str = "Started";
        while (iterator.hasNext()) {
            String key = iterator.next().toString();
            log_str += "\nkey : " + key + ", data:" + jsonObject.get(key).toString();
        }
        log.info(log_str);
        return this.jsonObject;
    }

    public JSONObject getJSONObject(String func) throws JSONException {
        Iterator iterator = this.jsonObject.keys();
        String log_str = "Started" + func;
        while (iterator.hasNext()) {
            String key = iterator.next().toString();
            log_str += "\nkey : " + key + ", data:" + jsonObject.get(key).toString();
        }
        log.info(log_str);
        return this.jsonObject;
    }
}
