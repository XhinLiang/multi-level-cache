package com.xhinliang.cache;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author xhinliang
 */
public class RetrieveCache<K, V> implements ICache<K, V> {

    private List<ICache<K, V>> caches;

    @SafeVarargs
    public RetrieveCache(ICache<K, V>... cacheArray) {
        if (cacheArray == null || cacheArray.length == 0) {
            this.caches = Collections.emptyList();
        } else {
            this.caches = Arrays.asList(cacheArray);
        }
    }

    @Override
    public void set(K key, V value) {
        caches.forEach(c -> c.set(key, value));
    }

    @Override
    public V get(K key) {
        if (caches == null || caches.size() == 0) {
            return null;
        }
        V finalResult = null;
        int fetchCacheIndex;
        for (fetchCacheIndex = 0; fetchCacheIndex < caches.size(); fetchCacheIndex++) {
            ICache<K, V> cache = caches.get(fetchCacheIndex);
            V currentResult = cache.get(key);
            if (currentResult != null) {
                finalResult = currentResult;
                break;
            }
        }

        // write back
        for (int i = fetchCacheIndex - 1; i >= 0; i--) {
            caches.get(i).set(key, finalResult);
        }

        return finalResult;

    }
}
