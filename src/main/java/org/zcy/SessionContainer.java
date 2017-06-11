package org.zcy;

import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Created by zcy on 2017/3/29.
 */

@Component
public class SessionContainer {

    private HashMap<String, String> hashMap;

    String get(String key){
        return hashMap.get(key);
    }

    void set(String key, String value){
        hashMap.put(key, value);
    }

    void remove(String key){
        hashMap.remove(key);
    }

}
