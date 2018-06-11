package com.leyijf.bean;

import com.google.gson.Gson;

/**
 * Created by wmq on 2018/5/28.
 */

public class FyPayBean {

    public static FyPayBean objectFromData(String str) {

        return new Gson().fromJson(str, FyPayBean.class);
    }

        /**
         * api : https://mpay.fuiou.com:16128/h5pay/payAction.pay
         * data : {"VERSION":"2.0","ENCTP":1,"LOGOTP":"0","MCHNTCD":"0001000F1118695","FM":"gUJUt9iHAVxeIj2KT3myToWbvg08umwCwZb3CP09yHPe0d+J4lUFqctSbXq74WZNM2glMQwrlk8vXdLlWcUcOlofcuW80dKFqS5yek9MGBo1IhQ1qpSL7W4VH6eg/Iarg20k0LcGZdoFiolBijp6m29Gu2VtGFxgQrX4HZYl0CtAT/YgK71k4UspelG+OGzy9FdJAFWUBqTcEh/o+0H0MasfXJdKM58MQs1CgO0Wzuh4g+xTfpvT6FShigsO+Pc00e48g3EN9V3o89dTOFoW6bVQWksiFfWahk1jIj7PdDOelkcVPvF2SKQf6n/5Ap7AOdrV4ho0jEeX+Y753JpYE2J7194Pm3gfZJ9S1mIExhgdJgQ7psyAkz5d6JXvvTCQ1t4aQezIX/svGheRBvKmpKeOzaumV9oUe/3uNDXNSXOAPpTALiEUqFRtLzzman/m9dRsXBUAFA9zPq1vxK2LRuRFl7POIY42yh/6dpGluxgfeq8BnNw30QVOqyUZWrluAs/4UnVNkxL38Y5h5POppNW5SKzu1Z/AuwUf+OeIkgLuLYDWyCv1IJCJ8mMCSmf1rfIo1w7hbY00WOGIuRh1In5nTqAOQlkyUKDzS5LidGQ="}
         */

        private String api;
        private DataBean data;


        public String getApi() {
            return api;
        }

        public void setApi(String api) {
            this.api = api;
        }

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * VERSION : 2.0
             * ENCTP : 1
             * LOGOTP : 0
             * MCHNTCD : 0001000F1118695
             * FM : gUJUt9iHAVxeIj2KT3myToWbvg08umwCwZb3CP09yHPe0d+J4lUFqctSbXq74WZNM2glMQwrlk8vXdLlWcUcOlofcuW80dKFqS5yek9MGBo1IhQ1qpSL7W4VH6eg/Iarg20k0LcGZdoFiolBijp6m29Gu2VtGFxgQrX4HZYl0CtAT/YgK71k4UspelG+OGzy9FdJAFWUBqTcEh/o+0H0MasfXJdKM58MQs1CgO0Wzuh4g+xTfpvT6FShigsO+Pc00e48g3EN9V3o89dTOFoW6bVQWksiFfWahk1jIj7PdDOelkcVPvF2SKQf6n/5Ap7AOdrV4ho0jEeX+Y753JpYE2J7194Pm3gfZJ9S1mIExhgdJgQ7psyAkz5d6JXvvTCQ1t4aQezIX/svGheRBvKmpKeOzaumV9oUe/3uNDXNSXOAPpTALiEUqFRtLzzman/m9dRsXBUAFA9zPq1vxK2LRuRFl7POIY42yh/6dpGluxgfeq8BnNw30QVOqyUZWrluAs/4UnVNkxL38Y5h5POppNW5SKzu1Z/AuwUf+OeIkgLuLYDWyCv1IJCJ8mMCSmf1rfIo1w7hbY00WOGIuRh1In5nTqAOQlkyUKDzS5LidGQ=
             */

            private String VERSION;
            private int ENCTP;
            private String LOGOTP;
            private String MCHNTCD;
            private String FM;

            public static DataBean objectFromData(String str) {

                return new Gson().fromJson(str, DataBean.class);
            }

            public String getVERSION() {
                return VERSION;
            }

            public void setVERSION(String VERSION) {
                this.VERSION = VERSION;
            }

            public int getENCTP() {
                return ENCTP;
            }

            public void setENCTP(int ENCTP) {
                this.ENCTP = ENCTP;
            }

            public String getLOGOTP() {
                return LOGOTP;
            }

            public void setLOGOTP(String LOGOTP) {
                this.LOGOTP = LOGOTP;
            }

            public String getMCHNTCD() {
                return MCHNTCD;
            }

            public void setMCHNTCD(String MCHNTCD) {
                this.MCHNTCD = MCHNTCD;
            }

            public String getFM() {
                return FM;
            }

            public void setFM(String FM) {
                this.FM = FM;
            }
        }

}
