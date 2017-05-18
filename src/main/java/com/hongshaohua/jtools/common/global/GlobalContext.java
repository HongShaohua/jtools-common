package com.hongshaohua.jtools.common.global;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shaoh on 2017/4/28.
 */
public class GlobalContext {

    private static GlobalContext instance = null;

    public static synchronized GlobalContext getInstance() {
        if(instance == null) {
            instance = new GlobalContext();
        }
        return instance;
    }

    private Map<String, Object> objects;

    private GlobalContext() {
        this.objects = new HashMap<String, Object>();
    }

    public synchronized void set(String key, Object obj) {
        this.objects.put(key, obj);
    }

    public synchronized Object get(String key) {
        return this.objects.get(key);
    }

    public synchronized <T> T get(String key, Class<T> T) {
        if(key == null) {
            return null;
        }
        Object obj = this.get(key);
        if(obj == null) {
            return null;
        }
        return T.cast(obj);
    }
}
