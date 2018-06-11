package com.leyijf.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by wmq on 2018/5/3.
 */

public class HotActiveBean {

    public static HotActiveBean objectFromData(String str) {

        return new Gson().fromJson(str, HotActiveBean.class);
    }


        private List<HotactivityBean> hotactivity;



        public List<HotactivityBean> getHotactivity() {
            return hotactivity;
        }

        public void setHotactivity(List<HotactivityBean> hotactivity) {
            this.hotactivity = hotactivity;
        }

        public static class HotactivityBean {
            /**
             * id : 67
             * name : 活动测试
             * img : http://www.leyi.com./public/attachment/201804/08/17/5ac9df72596ce.png
             * type : 1
             * page : hotactivity
             * data : null
             * url : http://m.leyibank.com#/newsApp/.html
             */

            private String id;
            private String name;
            private String img;
            private String type;
            private String page;
            private Object data;
            private String url;

            public static HotactivityBean objectFromData(String str) {

                return new Gson().fromJson(str, HotactivityBean.class);
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public Object getData() {
                return data;
            }

            public void setData(Object data) {
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
