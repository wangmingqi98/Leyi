package com.leyijf.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/15.
 */


public class UserHead {


    /**
     * userphone : 18311347538
     * headBean : [{"userphone":"18311347538","psd":"东城区"}]
     */

    private String userphone;
    private List<HeadBeanBean> headBean;

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public List<HeadBeanBean> getHeadBean() {
        return headBean;
    }

    public void setHeadBean(List<HeadBeanBean> headBean) {
        this.headBean = headBean;
    }

    public static class HeadBeanBean {
        /**
         * userphone : 18311347538
         * psd : 东城区
         */

        private String userphone;
        private String psd;

        public String getUserphone() {
            return userphone;
        }

        public void setUserphone(String userphone) {
            this.userphone = userphone;
        }

        public String getPsd() {
            return psd;
        }

        public void setPsd(String psd) {
            this.psd = psd;
        }
    }
}
