package com.xhinliang.cache;

import static java.lang.System.currentTimeMillis;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.xhinliang.cache.model.Post;

/**
 * @author xhinliang
 */
public abstract class AbstractPostCache implements ICache<Long, Post> {

    private final Map<Long, Post> provider = new ConcurrentHashMap<>();

    private final Map<Long, Long> lastSetTimeMillsMap = new ConcurrentHashMap<>();

    @Override
    public void set(Long key, Post value) {
        preSet(key, value);
        provider.put(key, value);
        lastSetTimeMillsMap.put(key, System.currentTimeMillis());
    }

    @Override
    public Post get(Long key) {
        preGet(key);
        Long lastSetMillis = lastSetTimeMillsMap.get(key);
        if (lastSetMillis == null) {
            return null;
        }
        if (currentTimeMillis() - lastSetMillis < expireMillis()) {
            return provider.get(key);
        } else {
            provider.remove(key);
            lastSetTimeMillsMap.remove(key);
            return null;
        }
    }

    protected abstract void preSet(Long key, Post value);

    protected abstract void preGet(Long key);

    protected abstract long expireMillis();
}
