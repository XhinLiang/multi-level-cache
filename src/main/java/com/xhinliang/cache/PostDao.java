package com.xhinliang.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import com.xhinliang.cache.model.Post;
import com.xhinliang.cache.utils.TimeUtils;

/**
 * @author xhinliang
 */
public class PostDao {

    private static final long INSERT_COST = 2L;
    private static final long QUERY_COST = 50L;

    private Map<Long, Post> mockDatabase = new ConcurrentHashMap<>();

    private AtomicLong idGenerator = new AtomicLong(0L);

    public void insertPost(String title, String content) {
        Post newPost = new Post();
        newPost.setId(idGenerator.incrementAndGet());
        newPost.setTitle(title);
        newPost.setContent(content);
        mockDatabase.put(newPost.getId(), newPost);
        TimeUtils.sleepMillis(INSERT_COST);
    }

    public Post queryById(Long id) {
        TimeUtils.sleepMillis(QUERY_COST);
        return mockDatabase.get(id);
    }
}
