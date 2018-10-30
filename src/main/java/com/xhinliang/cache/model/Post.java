package com.xhinliang.cache.model;

/**
 * @author xhinliang
 */
public class Post {

    private long id;

    private String title;

    private String content;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Post{" + //
                "id=" + id + //
                ", title='" + title + '\'' + //
                ", content='" + content + '\'' + //
                '}';
    }
}
