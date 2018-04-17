package com.leyi.bean;

/**
 * Created by Administrator on 2018/3/30.
 */

public class GongGao {
    public GongGao(String article_id, String title) {
        this.article_id = article_id;
        this.title = title;
    }

    @Override
    public String toString() {
        return "GongGao{" +
                "article_id='" + article_id + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    /**
     * article_id : 2018
     * title : 乐毅金服互联网金融借贷信息平台试运行
     */

    private String article_id;
    private String title;

    public String getArticle_id() {
        return article_id;
    }

    public void setArticle_id(String article_id) {
        this.article_id = article_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
