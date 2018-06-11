package com.leyijf.bean;

import com.google.gson.Gson;

/**
 * Created by wmq on 2018/5/4.
 */

public class NewsDetailsBean {

    public static NewsDetailsBean objectFromData(String str) {

        return new Gson().fromJson(str, NewsDetailsBean.class);
    }


        /**
         * notice_detail : {"title":"\u201c新手专享\u201d上标公告","content":" \r\n\t尊敬的乐毅金服用户： \r\n  \r\n \r\n\t您好！ \r\n  \r\n \r\n\t今天上午10:00点\u201c新手专享12号\u201d标的将准时上线，期待您的加入。项目金额50000元，预期年化收益15%，100元起投，单笔最高投资限额10000元，每个用户ID限投一次，请提前安排好时间进行抢购。 \r\n  \r\n \r\n\t乐毅金服团队 \r\n  \r\n \r\n\t2018.4.24 \r\n  "}
         */

        private NoticeDetailBean notice_detail;



        public NoticeDetailBean getNotice_detail() {
            return notice_detail;
        }

        public void setNotice_detail(NoticeDetailBean notice_detail) {
            this.notice_detail = notice_detail;
        }

        public static class NoticeDetailBean {
            /**
             * title : “新手专享”上标公告
             * content :
             尊敬的乐毅金服用户：


             您好！


             今天上午10:00点“新手专享12号”标的将准时上线，期待您的加入。项目金额50000元，预期年化收益15%，100元起投，单笔最高投资限额10000元，每个用户ID限投一次，请提前安排好时间进行抢购。


             乐毅金服团队


             2018.4.24

             */

            private String title;
            private String content;

            public static NoticeDetailBean objectFromData(String str) {

                return new Gson().fromJson(str, NoticeDetailBean.class);
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
        }

}
