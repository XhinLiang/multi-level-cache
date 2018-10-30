package com.xhinliang.cache;

/**
 * @author xhinliang
 */
public class RetrieveCacheUtils {

    @SafeVarargs
    public static <K, V> V retrieveGetById(K key, ICache<K, V>... caches) {
        if (caches == null || caches.length == 0) {
            return null;
        }
        V finalResult = null;
        int fetchCacheIndex;
        for (fetchCacheIndex = 0; fetchCacheIndex < caches.length; fetchCacheIndex++) {
            ICache<K, V> cache = caches[fetchCacheIndex];
            V currentResult = cache.get(key);
            if (currentResult != null) {
                finalResult = currentResult;
                break;
            }
        }

        // retrieve
        for (int i = fetchCacheIndex - 1; i >= 0; i--) {
            caches[i].set(key, finalResult);
        }

        return finalResult;
    }
}
