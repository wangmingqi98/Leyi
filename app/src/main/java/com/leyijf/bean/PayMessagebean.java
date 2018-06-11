package com.leyijf.bean;

/**
 * Created by Administrator on 2018/3/26.
 */

public class PayMessagebean {


    /**
     * response_code : 1
     * objects : {"show_err":"验证短信已经发送，请注意查收","post_type":""}
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
         * show_err : 验证短信已经发送，请注意查收
         * post_type :
         */

        private String show_err;
        private String post_type;

        public String getShow_err() {
            return show_err;
        }

        public void setShow_err(String show_err) {
            this.show_err = show_err;
        }

        public String getPost_type() {
            return post_type;
        }

        public void setPost_type(String post_type) {
            this.post_type = post_type;
        }
    }
}
