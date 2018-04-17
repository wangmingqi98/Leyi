package com.leyi.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/2.
 */

public class TouziRenshuBean {


    /**
     * response_code : 1
     * objects : {"buy_record":[{"date":"2018-03-19","records":[{"deal_id":"16","user_id":"3","user_name":"大***G","money":"30000","create_time":1521431007,"ecv_money":"0","rate_money":"0.00","time_his":"11:43:27"},{"deal_id":"16","user_id":"43","user_name":"z***g","money":"70000","create_time":1521447104,"ecv_money":"0","rate_money":"0.00","time_his":"16:11:44"},{"deal_id":"16","user_id":"44","user_name":"x***g","money":"20000","create_time":1521447140,"ecv_money":"0","rate_money":"0.00","time_his":"16:12:20"},{"deal_id":"16","user_id":"45","user_name":"x***u","money":"20000","create_time":1521447159,"ecv_money":"0","rate_money":"0.00","time_his":"16:12:39"},{"deal_id":"16","user_id":"13","user_name":"h***i","money":"20000","create_time":1521447181,"ecv_money":"0","rate_money":"0.00","time_his":"16:13:01"},{"deal_id":"16","user_id":"42","user_name":"f***g","money":"10000","create_time":1521447254,"ecv_money":"0","rate_money":"0.00","time_his":"16:14:14"},{"deal_id":"16","user_id":"14","user_name":"f***a","money":"10000","create_time":1521447298,"ecv_money":"0","rate_money":"0.00","time_his":"16:14:58"},{"deal_id":"16","user_id":"12","user_name":"x***n","money":"10000","create_time":1521447521,"ecv_money":"0","rate_money":"0.00","time_his":"16:18:41"},{"deal_id":"16","user_id":"9","user_name":"h***a","money":"10000","create_time":1521447593,"ecv_money":"0","rate_money":"0.00","time_his":"16:19:53"}]}]}
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
        private List<BuyRecordBean> buy_record;

        public List<BuyRecordBean> getBuy_record() {
            return buy_record;
        }

        public void setBuy_record(List<BuyRecordBean> buy_record) {
            this.buy_record = buy_record;
        }

        public static class BuyRecordBean {
            /**
             * date : 2018-03-19
             * records : [{"deal_id":"16","user_id":"3","user_name":"大***G","money":"30000","create_time":1521431007,"ecv_money":"0","rate_money":"0.00","time_his":"11:43:27"},{"deal_id":"16","user_id":"43","user_name":"z***g","money":"70000","create_time":1521447104,"ecv_money":"0","rate_money":"0.00","time_his":"16:11:44"},{"deal_id":"16","user_id":"44","user_name":"x***g","money":"20000","create_time":1521447140,"ecv_money":"0","rate_money":"0.00","time_his":"16:12:20"},{"deal_id":"16","user_id":"45","user_name":"x***u","money":"20000","create_time":1521447159,"ecv_money":"0","rate_money":"0.00","time_his":"16:12:39"},{"deal_id":"16","user_id":"13","user_name":"h***i","money":"20000","create_time":1521447181,"ecv_money":"0","rate_money":"0.00","time_his":"16:13:01"},{"deal_id":"16","user_id":"42","user_name":"f***g","money":"10000","create_time":1521447254,"ecv_money":"0","rate_money":"0.00","time_his":"16:14:14"},{"deal_id":"16","user_id":"14","user_name":"f***a","money":"10000","create_time":1521447298,"ecv_money":"0","rate_money":"0.00","time_his":"16:14:58"},{"deal_id":"16","user_id":"12","user_name":"x***n","money":"10000","create_time":1521447521,"ecv_money":"0","rate_money":"0.00","time_his":"16:18:41"},{"deal_id":"16","user_id":"9","user_name":"h***a","money":"10000","create_time":1521447593,"ecv_money":"0","rate_money":"0.00","time_his":"16:19:53"}]
             */

            private String date;
            private List<RecordsBean> records;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public List<RecordsBean> getRecords() {
                return records;
            }

            public void setRecords(List<RecordsBean> records) {
                this.records = records;
            }

            public static class RecordsBean {
                /**
                 * deal_id : 16
                 * user_id : 3
                 * user_name : 大***G
                 * money : 30000
                 * create_time : 1521431007
                 * ecv_money : 0
                 * rate_money : 0.00
                 * time_his : 11:43:27
                 */

                private String deal_id;
                private String user_id;
                private String user_name;
                private String money;
                private int create_time;
                private String ecv_money;
                private String rate_money;
                private String time_his;

                public String getDeal_id() {
                    return deal_id;
                }

                public void setDeal_id(String deal_id) {
                    this.deal_id = deal_id;
                }

                public String getUser_id() {
                    return user_id;
                }

                public void setUser_id(String user_id) {
                    this.user_id = user_id;
                }

                public String getUser_name() {
                    return user_name;
                }

                public void setUser_name(String user_name) {
                    this.user_name = user_name;
                }

                public String getMoney() {
                    return money;
                }

                public void setMoney(String money) {
                    this.money = money;
                }

                public int getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(int create_time) {
                    this.create_time = create_time;
                }

                public String getEcv_money() {
                    return ecv_money;
                }

                public void setEcv_money(String ecv_money) {
                    this.ecv_money = ecv_money;
                }

                public String getRate_money() {
                    return rate_money;
                }

                public void setRate_money(String rate_money) {
                    this.rate_money = rate_money;
                }

                public String getTime_his() {
                    return time_his;
                }

                public void setTime_his(String time_his) {
                    this.time_his = time_his;
                }
            }
        }
    }
}
