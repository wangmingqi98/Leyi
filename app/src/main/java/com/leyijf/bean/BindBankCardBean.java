package com.leyijf.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by wmq on 2018/5/25.
 */

public class BindBankCardBean {

    public static BindBankCardBean objectFromData(String str) {

        return new Gson().fromJson(str, BindBankCardBean.class);
    }

        /**
         * bank_name : 中国建设银行
         * bank_logo : http://www.leyibank.com/public/bank_info/logo/CCB.png
         * bank_code : CCB
         * bankcard : 6217000010035076796
         * real_name : 李会锋
         * show_err : 保存成功
         * user_info : {"user_id":5,"user_name":"阿发几份","user_img":"https://www.baidu.com/img/bd_logo1.png?e=1524587652&token=Wlzx_o-SAmn38Hp43BgOrw1YGrci8oNIo7GHGIzK:Kwqex7_Cm3-b031pfYL6j1tF2eA=","user_mobile":"135****2668","id_passed":1,"has_paypassword":1,"binded_card":[{"bank_card_icon":"http://www.leyibank.com./public/bank/CCB.png","bank_card_num":"6796","bank_id":"58","bank_name":"中国建设银行"}]}
         */

        private String bank_name;
        private String bank_logo;
        private String bank_code;
        private String bankcard;
        private String real_name;
        private String show_err;
        private UserInfoBean user_info;


        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public String getBank_logo() {
            return bank_logo;
        }

        public void setBank_logo(String bank_logo) {
            this.bank_logo = bank_logo;
        }

        public String getBank_code() {
            return bank_code;
        }

        public void setBank_code(String bank_code) {
            this.bank_code = bank_code;
        }

        public String getBankcard() {
            return bankcard;
        }

        public void setBankcard(String bankcard) {
            this.bankcard = bankcard;
        }

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public String getShow_err() {
            return show_err;
        }

        public void setShow_err(String show_err) {
            this.show_err = show_err;
        }

        public UserInfoBean getUser_info() {
            return user_info;
        }

        public void setUser_info(UserInfoBean user_info) {
            this.user_info = user_info;
        }

        public static class UserInfoBean {
            /**
             * user_id : 5
             * user_name : 阿发几份
             * user_img : https://www.baidu.com/img/bd_logo1.png?e=1524587652&token=Wlzx_o-SAmn38Hp43BgOrw1YGrci8oNIo7GHGIzK:Kwqex7_Cm3-b031pfYL6j1tF2eA=
             * user_mobile : 135****2668
             * id_passed : 1
             * has_paypassword : 1
             * binded_card : [{"bank_card_icon":"http://www.leyibank.com./public/bank/CCB.png","bank_card_num":"6796","bank_id":"58","bank_name":"中国建设银行"}]
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

            public static class BindedCardBean {
                /**
                 * bank_card_icon : http://www.leyibank.com./public/bank/CCB.png
                 * bank_card_num : 6796
                 * bank_id : 58
                 * bank_name : 中国建设银行
                 */

                private String bank_card_icon;
                private String bank_card_num;
                private String bank_id;
                private String bank_name;

                public static BindedCardBean objectFromData(String str) {

                    return new Gson().fromJson(str, BindedCardBean.class);
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

}