package com.leyijf.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by wmq on 2018/5/22.
 */

public class BannerBean {

    public static BannerBean objectFromData(String str) {

        return new Gson().fromJson(str, BannerBean.class);
    }

        private List<TopBean> top;
        private List<HeadlineBean> headline;


        public List<TopBean> getTop() {
            return top;
        }

        public void setTop(List<TopBean> top) {
            this.top = top;
        }

        public List<HeadlineBean> getHeadline() {
            return headline;
        }

        public void setHeadline(List<HeadlineBean> headline) {
            this.headline = headline;
        }

        public static class TopBean {
            /**
             * banner_id : 67
             * name : 51活动
             * img : http://www.leyibank.com./public/attachment/201804/24/17/5adeffb2d67e8.png
             * type : 1
             * page : top
             * data :
             * url : http://m.leyibank.com#/newsApp/.html
             */

            private String banner_id;
            private String name;
            private String img;
            private String type;
            private String page;
            private String data;
            private String url;

            public static TopBean objectFromData(String str) {

                return new Gson().fromJson(str, TopBean.class);
            }

            public String getBanner_id() {
                return banner_id;
            }

            public void setBanner_id(String banner_id) {
                this.banner_id = banner_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getPage() {
                return page;
            }

            public void setPage(String page) {
                this.page = page;
            }

            public String getData() {
                return data;
            }

            public void setData(String data) {
                this.data = data;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class HeadlineBean {
            /**
             * banner_id : 2048
             * name : “新手专享”上标公告
             * img :
             * type : 1
             * page : headline
             * data : 2048
             * url : http://m.leyibank.com#/newsApp/2048.html
             */

            private String banner_id;
            private String name;
            private String img;
            private int type;
            private String page;
            private String data;
            private String url;

            public static HeadlineBean objectFromData(String str) {

                return new Gson().fromJson(str, HeadlineBean.class);
            }

            public String getBanner_id() {
                return banner_id;
            }

            public void setBanner_id(String banner_id) {
                this.banner_id = banner_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getPage() {
                return page;
            }

            public void setPage(String page) {
                this.page = page;
            }

            public String getData() {
                return data;
            }

            public void setData(String data) {
                this.data = data;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

}
