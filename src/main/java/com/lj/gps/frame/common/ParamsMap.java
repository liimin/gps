package com.lj.gps.frame.common;

import java.util.HashMap;

/**
 * 存取参数的HashMap
 *
 * @param <K>
 * @param <V>
 * @
 */
public class ParamsMap<K, V> extends HashMap<K, V> {
    private static final long serialVersionUID = 6450131208524802942L;

    public ParamsMap() {
        super();
    }

    public ParamsMap<K, V> add(K key, V value) {
        this.put(key, value);
        return this;
    }
}
