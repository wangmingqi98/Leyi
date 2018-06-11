package com.leyijf.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Administrator on 2018/3/30.
 */

public class GongGao {

    public static GongGao objectFromData(String str) {

        return new Gson().fromJson(str, GongGao.class);
    }

        private List<NoticeListBean> notice_list;


        public List<NoticeListBean> getNotice_list() {
            return notice_list;
        }

        public void setNotice_list(List<NoticeListBean> notice_list) {
            this.notice_list = notice_list;
        }

        public static class NoticeListBean {
            /**
             * article_id : 2048
             * title : “新手专享”上标公告
             */

            private String article_id;
            private String title;

            public static NoticeListBean objectFromData(String str) {

                return new Gson().fromJson(str, NoticeListBean.class);
            }

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

}
