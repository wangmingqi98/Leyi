package com.leyijf.bean;

/**
 * Created by Administrator on 2018/3/13.
 */

public class GetCodeBean {


    /**
     * response_code : 1
     * objects : {"token":"az0PO389enzX4voZqKAAYln1l8TDzZs0","show_err":"验证短信已经发送，请注意查收"}
     */

    private int response_code;
    private ObjectsBean objects;

    public int getResponse_code() {
        return response_code;
    }

    public void setResponse_code(int response_code) {
        this.response_code = response_code;
    }

    public ObjectsBean getObjects() {
        return objects;
    }

    public void setObjects(ObjectsBean objects) {
        this.objects = objects;
    }

    public static class ObjectsBean {
        /**
         * token : az0PO389enzX4voZqKAAYln1l8TDzZs0
         * show_err : 验证短信已经发送，请注意查收
         */

        private String token;
        private String show_err;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getShow_err() {
            return show_err;
        }

        public void setShow_err(String show_err) {
            this.show_err = show_err;
        }
    }
}
