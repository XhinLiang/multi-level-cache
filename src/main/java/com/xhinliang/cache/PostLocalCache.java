package com.xhinliang.cache;

import static java.util.concurrent.TimeUnit.SECONDS;

import com.xhinliang.cache.model.Post;
import com.xhinliang.cache.utils.TimeUtils;

/**
 * @author xhinliang
 */
public class PostLocalCache extends AbstractPostCache {

    private static final long SET_COST = 2L;
    private static final long GET_COST = 1L;
    private static final long EXPIRE_MILLIS = SECONDS.toMillis(12L);

    @Override
    protected void preSet(Long key, Post value) {
        TimeUtils.sleepMillis(SET_COST);
    }

    @Override
    protected void preGet(Long key) {
        TimeUtils.sleepMillis(GET_COST);
    }

    @Override
    protected long expireMillis() {
        return EXPIRE_MILLIS;
    }
}
