package com.xhinliang.cache;

import com.xhinliang.cache.model.Post;
import com.xhinliang.cache.utils.TimeUtils;
import com.xhinliang.cache.utils.Watcher;

/**
 * @author xhinliang
 */
public class TestRetrieveCacheUtils {

    public static void main(String[] args) {
        PostDao postDao = new PostDao();
        ICache<Long, Post> postDaoCacheWrapper = new ICache<Long, Post>() {

            @Override
            public void set(Long key, Post value) {
                postDao.insertPost(value.getTitle(), value.getContent());
            }

            @Override
            public Post get(Long key) {
                return postDao.queryById(key);
            }
        };

        ICache<Long, Post> memcachedCache = new PostMemcached();

        ICache<Long, Post> localCache = new PostLocalCache();

        // CHECKSTYLE:OFF
        GlobalLogger.log("init value...");
        for (long i = 0; i < 100L; i++) {
            postDao.insertPost("title" + i, "content" + i);
        }

        GlobalLogger.log("first");
        Watcher firstWatcher = new Watcher();
        for (long i = 1; i < 100L; i++) {
            Post post = RetrieveCacheUtils.retrieveGetById(i, localCache, memcachedCache,
                    postDaoCacheWrapper);
            if (post == null) {
                throw new RuntimeException("post is null!");
            }
        }
        GlobalLogger.log("first cost: {}", firstWatcher.stop());

        GlobalLogger.log("second");
        Watcher secondWatcher = new Watcher();
        for (long i = 1; i < 100L; i++) {
            Post post = RetrieveCacheUtils.retrieveGetById(i, localCache, memcachedCache,
                    postDaoCacheWrapper);
            if (post == null) {
                throw new RuntimeException("post is null!");
            }
        }
        GlobalLogger.log("second cost: {}", secondWatcher.stop());

        GlobalLogger.log("sleep 20s...");
        TimeUtils.sleepMillis(20000L);
        GlobalLogger.log("third");
        Watcher thirdWatcher = new Watcher();
        for (long i = 1; i < 100L; i++) {
            Post post = RetrieveCacheUtils.retrieveGetById(i, localCache, memcachedCache,
                    postDaoCacheWrapper);
            if (post == null) {
                throw new RuntimeException("post is null!");
            }
        }
        GlobalLogger.log("third cost: {}", thirdWatcher.stop());

        // CHECKSTYLE:ON
    }
}
