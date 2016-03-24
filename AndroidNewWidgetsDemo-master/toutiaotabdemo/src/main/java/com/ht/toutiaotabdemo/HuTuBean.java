package com.ht.toutiaotabdemo;

/**
 * Created by 郭君华 on 2016/3/2.
 * Email：guojunhua3369@163.com
 */
public class HuTuBean {
    private String thumb;
    private String title;
    private String url;

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "HuTuBean{" +
                "thumb='" + thumb + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
