package com.restapi.Restfull.API.Server.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Data
@Getter
@Setter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Log4j2
public class Message {
    private Map<String, Object> map;

    public Message() {
        this.map = new HashMap<>();
    }

    public <T> void put(String key, T object) {
        map.put(key, object);
    }

    public <T> boolean pop(String key) {
        Set set = map.keySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            boolean check = ((String) iterator.next()).equals(key);
            if (check) {
                map.remove(key);
                return true;
            }
            continue;
        }
        return false;
    }

    public Map<String, Object> getHashMap(String fn) throws JSONException {
        String log_str = "Function : " + fn;
        log.info(log_str);
        return map;
    }

    public Map<String, Object> getHashMap() throws JSONException {
        Set set = map.keySet();
        Iterator iterator = set.iterator();
        String log_str = "Started";
        log.info(log_str);
        return map;
    }
}
