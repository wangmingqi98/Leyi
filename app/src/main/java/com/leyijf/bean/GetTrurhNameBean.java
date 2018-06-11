package com.leyijf.bean;

import com.google.gson.Gson;

/**
 * Created by wmq on 2018/4/28.
 */

public class GetTrurhNameBean {

    public static GetTrurhNameBean objectFromData(String str) {

        return new Gson().fromJson(str, GetTrurhNameBean.class);
    }


        /**
         * info : {"real_name":"李会锋","idno":"412723199310241658"}
         */

        private InfoBean info;


        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * real_name : 李会锋
             * idno : 412723199310241658
             */

            private String real_name;
            private String idno;

            public static InfoBean objectFromData(String str) {

                return new Gson().fromJson(str, InfoBean.class);
            }

            public String getReal_name() {
                return real_name;
            }

            public void setReal_name(String real_name) {
                this.real_name = real_name;
            }

            public String getIdno() {
                return idno;
            }

            public void setIdno(String idno) {
                this.idno = idno;
            }
        }

}
