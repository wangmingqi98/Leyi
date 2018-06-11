package com.leyijf.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by wmq on 2018/5/14.
 */

public class RepaymentClasificationBean {

    public static RepaymentClasificationBean objectFromData(String str) {

        return new Gson().fromJson(str, RepaymentClasificationBean.class);
    }

        private List<CateInfoBean> cate_info;


        public List<CateInfoBean> getCate_info() {
            return cate_info;
        }

        public void setCate_info(List<CateInfoBean> cate_info) {
            this.cate_info = cate_info;
        }

        public static class CateInfoBean {
            /**
             * cate_id : 0
             * name : 新手专享
             */

            private String cate_id;
            private String name;

            public static CateInfoBean objectFromData(String str) {

                return new Gson().fromJson(str, CateInfoBean.class);
            }

            public String getCate_id() {
                return cate_id;
            }

            public void setCate_id(String cate_id) {
                this.cate_id = cate_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

}
