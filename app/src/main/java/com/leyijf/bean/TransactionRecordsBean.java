package com.leyijf.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by wmq on 2018/5/4.
 */

public class TransactionRecordsBean {

    public static TransactionRecordsBean objectFromData(String str) {

        return new Gson().fromJson(str, TransactionRecordsBean.class);
    }

        /**
         * status : 0
         * item : [{"id":"2034","user_id":"5","money":"-100.00","memo":"产业链16号","type":"2","create_time":"2018-04-24 17:20:04","title":"投标成功"}]
         * page : {"page":1,"page_total":26,"page_size":"10"}
         */

        private int status;
        private PageBean page;
        private List<ItemBean> item;


        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public PageBean getPage() {
            return page;
        }

        public void setPage(PageBean page) {
            this.page = page;
        }

        public List<ItemBean> getItem() {
            return item;
        }

        public void setItem(List<ItemBean> item) {
            this.item = item;
        }

        public static class PageBean {
            /**
             * page : 1
             * page_total : 26
             * page_size : 10
             */

            private int page;
            private int page_total;
            private String page_size;

            public static PageBean objectFromData(String str) {

                return new Gson().fromJson(str, PageBean.class);
            }

            public int getPage() {
                return page;
            }

            public void setPage(int page) {
                this.page = page;
            }

            public int getPage_total() {
                return page_total;
            }

            public void setPage_total(int page_total) {
                this.page_total = page_total;
            }

            public String getPage_size() {
                return page_size;
            }

            public void setPage_size(String page_size) {
                this.page_size = page_size;
            }
        }

        public static class ItemBean {
            /**
             * id : 2034
             * user_id : 5
             * money : -100.00
             * memo : 产业链16号
             * type : 2
             * create_time : 2018-04-24 17:20:04
             * title : 投标成功
             */

            private String id;
            private String user_id;
            private String money;
            private String memo;
            private String type;
            private String create_time;
            private String title;

            public static ItemBean objectFromData(String str) {

                return new Gson().fromJson(str, ItemBean.class);
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getMemo() {
                return memo;
            }

            public void setMemo(String memo) {
                this.memo = memo;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

}
