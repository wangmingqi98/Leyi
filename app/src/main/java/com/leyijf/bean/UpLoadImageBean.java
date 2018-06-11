package com.leyijf.bean;

import com.google.gson.Gson;

/**
 * Created by wmq on 2018/4/23.
 */

public class UpLoadImageBean {


    public static UpLoadImageBean objectFromData(String str) {

        return new Gson().fromJson(str, UpLoadImageBean.class);
    }


        /**
         * show_err : 上传成功
         * user_img : http://oxyts6up6.bkt.clouddn.com/o_1cbbkp6iif2a1mnr1acn84b14p67.jpg?e=1524034142&token=Wlzx_o-SAmn38Hp43BgOrw1YGrci8oNIo7GHGIzK:uq1vpJYpKUx55s5ZdJpHhvXHkA4=
         */

        private String show_err;
        private String base_url;



        public String getShow_err() {
            return show_err;
        }

        public void setShow_err(String show_err) {
            this.show_err = show_err;
        }

    public String getBase_url() {
        return base_url;
    }

    public void setBase_url(String base_url) {
        this.base_url = base_url;
    }

    @Override
    public String toString() {
        return "UpLoadImageBean{" +
                "show_err='" + show_err + '\'' +
                ", user_img='" + base_url + '\'' +
                '}';
    }
}
