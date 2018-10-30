package com.xhinliang.cache;

/**
 * @author xhinliang
 */
public interface ICache<K, V> {

    void set(K key, V value);

    V get(K key);
}
