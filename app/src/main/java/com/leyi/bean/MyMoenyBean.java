package com.leyi.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/15.
 */

public class MyMoenyBean {


    @Override
    public String toString() {
        return "MyMoenyBean{" +
                "response_code=" + response_code +
                ", objects=" + objects +
                '}';
    }

    /**
     * response_code : 1
     * objects : {"user_id":1,"user_img_url":"","idcardpassed":1,"paypassword":1,"autobid":0,"total_money":"111656.00","useful_money":"10489.50","accumulated_income":"289.50","deals":[{"id":"5","name":"产业链1号","deal_status":"4","create_time":1521112231,"end_time":1523721600,"true_repay_time":0,"load_id":"17","yuqi_money":"604.00","actual_return":0,"money":"10000","is_auto":"0"},{"id":"3","name":"货抵贷1号","deal_status":"5","create_time":1521111820,"end_time":1523635200,"true_repay_time":1521609932,"load_id":"10","yuqi_money":"289.50","actual_return":"289.50","money":"10000","is_auto":"0"},{"id":"2","name":"新手专享1号","deal_status":"4","create_time":1521110280,"end_time":1522339200,"true_repay_time":0,"load_id":"4","yuqi_money":"562.50","actual_return":0,"money":"90000","is_auto":"0"}],"my_bank":{"bank_card_icon":"http://leyibank.com./public/bank/ABC.png","bank_card_num":"6679","bank_id":"55","bank_name":"中国农业银行"},"user_info":{"user_id":1,"user_name":"judy","user_img":"","user_mobile":"176****3146","id_passed":1,"has_paypassword":1,"binded_card":[{"bank_card_icon":"http://leyibank.com./public/bank/ABC.png","bank_card_num":"6679","bank_id":"55","bank_name":"中国农业银行"}]}}
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
         * user_id : 1
         * user_img_url :
         * idcardpassed : 1
         * paypassword : 1
         * autobid : 0
         * total_money : 111656.00
         * useful_money : 10489.50
         * accumulated_income : 289.50
         * deals : [{"id":"5","name":"产业链1号","deal_status":"4","create_time":1521112231,"end_time":1523721600,"true_repay_time":0,"load_id":"17","yuqi_money":"604.00","actual_return":0,"money":"10000","is_auto":"0"},{"id":"3","name":"货抵贷1号","deal_status":"5","create_time":1521111820,"end_time":1523635200,"true_repay_time":1521609932,"load_id":"10","yuqi_money":"289.50","actual_return":"289.50","money":"10000","is_auto":"0"},{"id":"2","name":"新手专享1号","deal_status":"4","create_time":1521110280,"end_time":1522339200,"true_repay_time":0,"load_id":"4","yuqi_money":"562.50","actual_return":0,"money":"90000","is_auto":"0"}]
         * my_bank : {"bank_card_icon":"http://leyibank.com./public/bank/ABC.png","bank_card_num":"6679","bank_id":"55","bank_name":"中国农业银行"}
         * user_info : {"user_id":1,"user_name":"judy","user_img":"","user_mobile":"176****3146","id_passed":1,"has_paypassword":1,"binded_card":[{"bank_card_icon":"http://leyibank.com./public/bank/ABC.png","bank_card_num":"6679","bank_id":"55","bank_name":"中国农业银行"}]}
         */

        private int user_id;
        private String user_img_url;
        private int idcardpassed;
        private int paypassword;
        private int autobid;
        private String total_money;
        private String useful_money;
        private String accumulated_income;
        private MyBankBean my_bank;
        private UserInfoBean user_info;

        @Override
        public String toString() {
            return "ObjectsBean{" +
                    "user_id=" + user_id +
                    ", user_img_url='" + user_img_url + '\'' +
                    ", idcardpassed=" + idcardpassed +
                    ", paypassword=" + paypassword +
                    ", autobid=" + autobid +
                    ", total_money='" + total_money + '\'' +
                    ", useful_money='" + useful_money + '\'' +
                    ", accumulated_income='" + accumulated_income + '\'' +
                    ", my_bank=" + my_bank +
                    ", user_info=" + user_info +
                    ", deals=" + deals +
                    '}';
        }

        private List<DealsBean> deals;

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getUser_img_url() {
            return user_img_url;
        }

        public void setUser_img_url(String user_img_url) {
            this.user_img_url = user_img_url;
        }

        public int getIdcardpassed() {
            return idcardpassed;
        }

        public void setIdcardpassed(int idcardpassed) {
            this.idcardpassed = idcardpassed;
        }

        public int getPaypassword() {
            return paypassword;
        }

        public void setPaypassword(int paypassword) {
            this.paypassword = paypassword;
        }

        public int getAutobid() {
            return autobid;
        }

        public void setAutobid(int autobid) {
            this.autobid = autobid;
        }

        public String getTotal_money() {
            return total_money;
        }

        public void setTotal_money(String total_money) {
            this.total_money = total_money;
        }

        public String getUseful_money() {
            return useful_money;
        }

        public void setUseful_money(String useful_money) {
            this.useful_money = useful_money;
        }

        public String getAccumulated_income() {
            return accumulated_income;
        }

        public void setAccumulated_income(String accumulated_income) {
            this.accumulated_income = accumulated_income;
        }

        public MyBankBean getMy_bank() {
            return my_bank;
        }

        public void setMy_bank(MyBankBean my_bank) {
            this.my_bank = my_bank;
        }

        public UserInfoBean getUser_info() {
            return user_info;
        }

        public void setUser_info(UserInfoBean user_info) {
            this.user_info = user_info;
        }

        public List<DealsBean> getDeals() {
            return deals;
        }

        public void setDeals(List<DealsBean> deals) {
            this.deals = deals;
        }

        public static class MyBankBean {
            /**
             * bank_card_icon : http://leyibank.com./public/bank/ABC.png
             * bank_card_num : 6679
             * bank_id : 55
             * bank_name : 中国农业银行
             */

            private String bank_card_icon;
            private String bank_card_num;
            private String bank_id;
            private String bank_name;

            public String getBank_card_icon() {
                return bank_card_icon;
            }

            public void setBank_card_icon(String bank_card_icon) {
                this.bank_card_icon = bank_card_icon;
            }

            public String getBank_card_num() {
                return bank_card_num;
            }

            public void setBank_card_num(String bank_card_num) {
                this.bank_card_num = bank_card_num;
            }

            public String getBank_id() {
                return bank_id;
            }

            public void setBank_id(String bank_id) {
                this.bank_id = bank_id;
            }

            public String getBank_name() {
                return bank_name;
            }

            public void setBank_name(String bank_name) {
                this.bank_name = bank_name;
            }
        }

        public static class UserInfoBean {
            /**
             * user_id : 1
             * user_name : judy
             * user_img :
             * user_mobile : 176****3146
             * id_passed : 1
             * has_paypassword : 1
             * binded_card : [{"bank_card_icon":"http://leyibank.com./public/bank/ABC.png","bank_card_num":"6679","bank_id":"55","bank_name":"中国农业银行"}]
             */

            private int user_id;
            private String user_name;
            private String user_img;
            private String user_mobile;
            private int id_passed;
            private int has_paypassword;
            private List<BindedCardBean> binded_card;

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public String getUser_img() {
                return user_img;
            }

            public void setUser_img(String user_img) {
                this.user_img = user_img;
            }

            public String getUser_mobile() {
                return user_mobile;
            }

            public void setUser_mobile(String user_mobile) {
                this.user_mobile = user_mobile;
            }

            public int getId_passed() {
                return id_passed;
            }

            public void setId_passed(int id_passed) {
                this.id_passed = id_passed;
            }

            public int getHas_paypassword() {
                return has_paypassword;
            }

            public void setHas_paypassword(int has_paypassword) {
                this.has_paypassword = has_paypassword;
            }

            public List<BindedCardBean> getBinded_card() {
                return binded_card;
            }

            public void setBinded_card(List<BindedCardBean> binded_card) {
                this.binded_card = binded_card;
            }

            public static class BindedCardBean {
                /**
                 * bank_card_icon : http://leyibank.com./public/bank/ABC.png
                 * bank_card_num : 6679
                 * bank_id : 55
                 * bank_name : 中国农业银行
                 */

                private String bank_card_icon;
                private String bank_card_num;
                private String bank_id;
                private String bank_name;

                public String getBank_card_icon() {
                    return bank_card_icon;
                }

                public void setBank_card_icon(String bank_card_icon) {
                    this.bank_card_icon = bank_card_icon;
                }

                public String getBank_card_num() {
                    return bank_card_num;
                }

                public void setBank_card_num(String bank_card_num) {
                    this.bank_card_num = bank_card_num;
                }

                public String getBank_id() {
                    return bank_id;
                }

                public void setBank_id(String bank_id) {
                    this.bank_id = bank_id;
                }

                public String getBank_name() {
                    return bank_name;
                }

                public void setBank_name(String bank_name) {
                    this.bank_name = bank_name;
                }
            }
        }

        public static class DealsBean {
            /**
             * id : 5
             * name : 产业链1号
             * deal_status : 4
             * create_time : 1521112231
             * end_time : 1523721600
             * true_repay_time : 0
             * load_id : 17
             * yuqi_money : 604.00
             * actual_return : 0
             * money : 10000
             * is_auto : 0
             */

            private String id;
            private String name;
            private String deal_status;
            private int create_time;
            private int end_time;
            private int true_repay_time;
            private String load_id;
            private String yuqi_money;
            private String actual_return;
            private String money;
            private String is_auto;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDeal_status() {
                return deal_status;
            }

            public void setDeal_status(String deal_status) {
                this.deal_status = deal_status;
            }

            public int getCreate_time() {
                return create_time;
            }

            public void setCreate_time(int create_time) {
                this.create_time = create_time;
            }

            public int getEnd_time() {
                return end_time;
            }

            public void setEnd_time(int end_time) {
                this.end_time = end_time;
            }

            public int getTrue_repay_time() {
                return true_repay_time;
            }

            public void setTrue_repay_time(int true_repay_time) {
                this.true_repay_time = true_repay_time;
            }

            public String getLoad_id() {
                return load_id;
            }

            public void setLoad_id(String load_id) {
                this.load_id = load_id;
            }

            public String getYuqi_money() {
                return yuqi_money;
            }

            public void setYuqi_money(String yuqi_money) {
                this.yuqi_money = yuqi_money;
            }

            public String getActual_return() {
                return actual_return;
            }

            public void setActual_return(String actual_return) {
                this.actual_return = actual_return;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            @Override
            public String toString() {
                return "DealsBean{" +
                        "id='" + id + '\'' +
                        ", name='" + name + '\'' +
                        ", deal_status='" + deal_status + '\'' +
                        ", create_time=" + create_time +
                        ", end_time=" + end_time +
                        ", true_repay_time=" + true_repay_time +
                        ", load_id='" + load_id + '\'' +
                        ", yuqi_money='" + yuqi_money + '\'' +
                        ", actual_return='" + actual_return + '\'' +
                        ", money='" + money + '\'' +
                        ", is_auto='" + is_auto + '\'' +
                        '}';
            }

            public String getIs_auto() {
                return is_auto;
            }

            public void setIs_auto(String is_auto) {
                this.is_auto = is_auto;
            }
        }
    }
}
