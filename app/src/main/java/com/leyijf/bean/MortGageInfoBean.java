package com.leyijf.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by wmq on 2018/5/16.
 */

public class MortGageInfoBean {


    public static MortGageInfoBean objectFromData(String str) {

        return new Gson().fromJson(str, MortGageInfoBean.class);
    }

        private List<MortgageBean> mortgage;



        public List<MortgageBean> getMortgage() {
            return mortgage;
        }

        public void setMortgage(List<MortgageBean> mortgage) {
            this.mortgage = mortgage;
        }

        public static class MortgageBean {
            /**
             * contract : [{"name":"","img":"./public/attachment/201805/16/09/5afb88f8a6665.jpg"},{"name":"","img":"./public/attachment/201805/16/09/5afb89001500c.jpg"},{"name":"","img":"./public/attachment/201805/16/09/5afb8906de361.jpg"},{"name":"","img":"./public/attachment/201805/16/09/5afb890ab7a9d.jpg"}]
             * type : 1
             * infos : [{"name":"","img":"./public/attachment/201805/16/09/5afb890f967e4.jpg"},{"name":"","img":"./public/attachment/201805/16/09/5afb8913b1156.jpg"}]
             */

            private int type;
            private List<ContractBean> contract;
            private List<InfosBean> infos;

            public static MortgageBean objectFromData(String str) {

                return new Gson().fromJson(str, MortgageBean.class);
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public List<ContractBean> getContract() {
                return contract;
            }

            public void setContract(List<ContractBean> contract) {
                this.contract = contract;
            }

            public List<InfosBean> getInfos() {
                return infos;
            }

            public void setInfos(List<InfosBean> infos) {
                this.infos = infos;
            }

            public static class ContractBean {
                /**
                 * name :
                 * img : ./public/attachment/201805/16/09/5afb88f8a6665.jpg
                 */

                private String name;
                private String img;

                public static ContractBean objectFromData(String str) {

                    return new Gson().fromJson(str, ContractBean.class);
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }
            }

            public static class InfosBean {
                /**
                 * name :
                 * img : ./public/attachment/201805/16/09/5afb890f967e4.jpg
                 */

                private String name;
                private String img;

                public static InfosBean objectFromData(String str) {

                    return new Gson().fromJson(str, InfosBean.class);
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }
            }
        }

}
