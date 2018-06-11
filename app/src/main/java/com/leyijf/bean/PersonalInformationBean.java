package com.leyijf.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 *
 * Created by wmq on 2018/4/25.
 */

public class PersonalInformationBean {

    public static PersonalInformationBean objectFromData(String str) {

        return new Gson().fromJson(str, PersonalInformationBean.class);
    }


        /**
         * user_id : 5
         * user_img_url : https://www.baidu.com/img/bd_logo1.png?e=1524583911&token=Wlzx_o-SAmn38Hp43BgOrw1YGrci8oNIo7GHGIzK:tm83wZSaZlIdXqNukHAim-IswNs=
         * idcardpassed : 1
         * paypassword : 1
         * autobid : 0
         * total_money : 10582595.89
         * useful_money : 10267300.45
         * accumulated_income : 1846.70
         * deals : [{"id":"61","name":"产业链16号","deal_status":"1","create_time":1524561643,"end_time":0,"true_repay_time":0,"load_id":"596","yuqi_money":"0.77","actual_return":0,"money":"102","is_auto":"0"}]
         * my_bank : {"bank_card_icon":"","bank_card_num":"","bank_id":0,"bank_name":""}
         * user_info : {"user_id":5,"user_name":"阿发几份","user_img":"https://www.baidu.com/img/bd_logo1.png?e=1524583911&token=Wlzx_o-SAmn38Hp43BgOrw1YGrci8oNIo7GHGIzK:tm83wZSaZlIdXqNukHAim-IswNs=","user_mobile":"135****2668","id_passed":1,"has_paypassword":1,"binded_card":[]}
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
             * bank_card_icon :
             * bank_card_num :
             * bank_id : 0
             * bank_name :
             */

            private String bank_card_icon;
            private String bank_card_num;
            private int bank_id;
            private String bank_name;

            public static MyBankBean objectFromData(String str) {

                return new Gson().fromJson(str, MyBankBean.class);
            }

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

            public int getBank_id() {
                return bank_id;
            }

            public void setBank_id(int bank_id) {
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
             * user_id : 5
             * user_name : 阿发几份
             * user_img : https://www.baidu.com/img/bd_logo1.png?e=1524583911&token=Wlzx_o-SAmn38Hp43BgOrw1YGrci8oNIo7GHGIzK:tm83wZSaZlIdXqNukHAim-IswNs=
             * user_mobile : 135****2668
             * id_passed : 1
             * has_paypassword : 1
             * binded_card : []
             */

            private int user_id;
            private String user_name;
            private String user_img;
            private String user_mobile;
            private int id_passed;
            private int has_paypassword;
            private List<BindedCardBean> binded_card;

            public static UserInfoBean objectFromData(String str) {

                return new Gson().fromJson(str, UserInfoBean.class);
            }

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
        }

        public static class DealsBean {
            /**
             * id : 61
             * name : 产业链16号
             * deal_status : 1
             * create_time : 1524561643
             * end_time : 0
             * true_repay_time : 0
             * load_id : 596
             * yuqi_money : 0.77
             * actual_return : 0
             * money : 102
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
            private int actual_return;
            private String money;
            private String is_auto;

            public static DealsBean objectFromData(String str) {

                return new Gson().fromJson(str, DealsBean.class);
            }

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

            public int getActual_return() {
                return actual_return;
            }

            public void setActual_return(int actual_return) {
                this.actual_return = actual_return;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getIs_auto() {
                return is_auto;
            }

            public void setIs_auto(String is_auto) {
                this.is_auto = is_auto;
            }
        }

}
