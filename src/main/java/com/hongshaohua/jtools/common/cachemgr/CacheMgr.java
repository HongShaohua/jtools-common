package com.hongshaohua.jtools.common.cachemgr;

import java.util.*;

/**
 * Created by Aska on 2017/6/8.
 */
public class CacheMgr<K,V> {

    private long delayMillis;

    private class Data {
        long lastTime;
        V value;

        Data(long lastTime, V value) {
            this.lastTime = lastTime;
            this.value = value;
        }
    }
    private Map<K, Data> datas;

    public CacheMgr(long delayMillis) {
        this.delayMillis = delayMillis;
        this.datas = new HashMap<>();
    }

    public long getDelayMillis() {
        return delayMillis;
    }

    private long curTime() {
        return System.currentTimeMillis();
    }

    public V add(K key, V value) {
        return this.datas.put(key, new Data(this.curTime(), value)).value;
    }

    public V get(K key) {
        Data data = this.datas.get(key);
        if(data == null) {
            return null;
        }
        data.lastTime = this.curTime();
        return data.value;
    }

    public V remove(K key) {
        Data data = this.datas.remove(key);
        if(data == null) {
            return null;
        }
        return data.value;
    }

    public Set<K> keySet() {
        return this.datas.keySet();
    }

    public List<V> values() {
        List<V> values = new ArrayList<>();
        Iterator<Data> it = this.datas.values().iterator();
        while(it.hasNext()) {
            Data data = it.next();
            values.add(data.value);
        }
        return values;
    }

    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = new HashSet<>();
        Iterator<Map.Entry<K, Data>> it = this.datas.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<K, Data> entry = it.next();
            Map.Entry<K, V> setEntry = new AbstractMap.SimpleEntry<K, V>(entry.getKey(), entry.getValue().value);
            set.add(setEntry);
        }
        return set;
    }

    public boolean containsKey(K key) {
        return this.datas.containsKey(key);
    }

    public int size() {
        return this.datas.size();
    }

    public boolean isEmpty() {
        return this.datas.isEmpty();
    }

    public void clear() {
        this.datas.clear();
    }

    public List<V> release() {
        long curTime = this.curTime();
        List<V> values = new ArrayList<>();
        Iterator<Map.Entry<K, Data>> it = this.datas.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<K, Data> entry = it.next();
            Data data = entry.getValue();
            if(curTime - data.lastTime > this.delayMillis) {
                values.add(data.value);
                it.remove();
            }
        }
        return values;
    }
}
