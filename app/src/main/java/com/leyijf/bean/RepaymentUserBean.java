package com.leyijf.bean;

import com.google.gson.Gson;

/**
 * Created by wmq on 2018/5/16.
 */

public class RepaymentUserBean {

    public static RepaymentUserBean objectFromData(String str) {

        return new Gson().fromJson(str, RepaymentUserBean.class);
    }

        /**
         * project_details : {"user":{"u_info":{"user_name":"安慕希2号","sex":"1","byear":"1991","marriage":"未婚","graduation":"本科","address":"北京市海淀区友谊路","idno":"412723194106161565","user_type":"0","age":"27"},"user_type":"0","deal_count":"1","yuqi_count":"0","cate_id":"1"},"description":"1111111111111","risk_security":"11111111111111"}
         */

        private ProjectDetailsBean project_details;


        public ProjectDetailsBean getProject_details() {
            return project_details;
        }

        public void setProject_details(ProjectDetailsBean project_details) {
            this.project_details = project_details;
        }

        public static class ProjectDetailsBean {
            /**
             * user : {"u_info":{"user_name":"安慕希2号","sex":"1","byear":"1991","marriage":"未婚","graduation":"本科","address":"北京市海淀区友谊路","idno":"412723194106161565","user_type":"0","age":"27"},"user_type":"0","deal_count":"1","yuqi_count":"0","cate_id":"1"}
             * description : 1111111111111
             * risk_security : 11111111111111
             */

            private UserBean user;
            private String description;
            private String risk_security;

            public static ProjectDetailsBean objectFromData(String str) {

                return new Gson().fromJson(str, ProjectDetailsBean.class);
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getRisk_security() {
                return risk_security;
            }

            public void setRisk_security(String risk_security) {
                this.risk_security = risk_security;
            }

            public static class UserBean {
                /**
                 * u_info : {"user_name":"安慕希2号","sex":"1","byear":"1991","marriage":"未婚","graduation":"本科","address":"北京市海淀区友谊路","idno":"412723194106161565","user_type":"0","age":"27"}
                 * user_type : 0
                 * deal_count : 1
                 * yuqi_count : 0
                 * cate_id : 1
                 */

                private UInfoBean u_info;
                private String user_type;
                private String deal_count;
                private String yuqi_count;
                private String cate_id;

                public static UserBean objectFromData(String str) {

                    return new Gson().fromJson(str, UserBean.class);
                }

                public UInfoBean getU_info() {
                    return u_info;
                }

                public void setU_info(UInfoBean u_info) {
                    this.u_info = u_info;
                }

                public String getUser_type() {
                    return user_type;
                }

                public void setUser_type(String user_type) {
                    this.user_type = user_type;
                }

                public String getDeal_count() {
                    return deal_count;
                }

                public void setDeal_count(String deal_count) {
                    this.deal_count = deal_count;
                }

                public String getYuqi_count() {
                    return yuqi_count;
                }

                public void setYuqi_count(String yuqi_count) {
                    this.yuqi_count = yuqi_count;
                }

                public String getCate_id() {
                    return cate_id;
                }

                public void setCate_id(String cate_id) {
                    this.cate_id = cate_id;
                }

                public static class UInfoBean {
                    /**
                     * user_name : 安慕希2号
                     * sex : 1
                     * byear : 1991
                     * marriage : 未婚
                     * graduation : 本科
                     * address : 北京市海淀区友谊路
                     * idno : 412723194106161565
                     * user_type : 0
                     * age : 27
                     */

                    private String user_name;
                    private String sex;
                    private String byear;
                    private String marriage;
                    private String graduation;
                    private String address;
                    private String idno;
                    private String user_type;
                    private String age;

                    public static UInfoBean objectFromData(String str) {

                        return new Gson().fromJson(str, UInfoBean.class);
                    }

                    public String getUser_name() {
                        return user_name;
                    }

                    public void setUser_name(String user_name) {
                        this.user_name = user_name;
                    }

                    public String getSex() {
                        return sex;
                    }

                    public void setSex(String sex) {
                        this.sex = sex;
                    }

                    public String getByear() {
                        return byear;
                    }

                    public void setByear(String byear) {
                        this.byear = byear;
                    }

                    public String getMarriage() {
                        return marriage;
                    }

                    public void setMarriage(String marriage) {
                        this.marriage = marriage;
                    }

                    public String getGraduation() {
                        return graduation;
                    }

                    public void setGraduation(String graduation) {
                        this.graduation = graduation;
                    }

                    public String getAddress() {
                        return address;
                    }

                    public void setAddress(String address) {
                        this.address = address;
                    }

                    public String getIdno() {
                        return idno;
                    }

                    public void setIdno(String idno) {
                        this.idno = idno;
                    }

                    public String getUser_type() {
                        return user_type;
                    }

                    public void setUser_type(String user_type) {
                        this.user_type = user_type;
                    }

                    public String getAge() {
                        return age;
                    }

                    public void setAge(String age) {
                        this.age = age;
                    }
                }
            }
        }

}
