package com.leyijf.bean;

import com.google.gson.Gson;

/**
 * Created by wmq on 2018/5/16.
 */

public class RepaymentCompanyBean {


    public static RepaymentCompanyBean objectFromData(String str) {

        return new Gson().fromJson(str, RepaymentCompanyBean.class);
    }

        /**
         * project_details : {"user":{"u_info":{"user_id":"14","company_name":"金华迎曼信息科技有限公司","contact":"范焱达","officetype":"一般民营企业","officedomain":"IT","officecale":"10-100人","register_capital":"500000","asset_value":"100","officeaddress":"浙江省金华市婺城区金带街938号金华电子商务创业园115室","description":"计算机软件开发","bankLicense":"3301-05072768","orgNo":"3301-05072768","businessLicense":"91330702ma28e3rr5j","taxNo":"J3380006237501"},"user_type":"1","deal_count":"4","yuqi_count":"0","cate_id":"1"},"description":"该企业的经营范围工程机械设备租赁。本次通过乐毅金服申请借款5万元，借款用于资金周转，还款来源为经营收入，还款保障措施为企业信用及无限连带保证。同时，经风控部门审核借款人所提供资料真实有效，符合乐毅金服的借款审核标准。","risk_security":"乐毅金服针对不同类型的产品特点和风险特征分别建立了相对应的产品管理制度和项目贷前、贷中、贷后管理制度，防范欺诈风险和信用风险。经平台撮合成交的项目一旦逾期，平台将协助相关方面通过提供逾期证明、融资服务协议 (电子合同)、资金流转记录等必须的信息和资金链证据，协助相关方面实现短信、电话、诉讼、外包等合规催收手段。"}
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
             * user : {"u_info":{"user_id":"14","company_name":"金华迎曼信息科技有限公司","contact":"范焱达","officetype":"一般民营企业","officedomain":"IT","officecale":"10-100人","register_capital":"500000","asset_value":"100","officeaddress":"浙江省金华市婺城区金带街938号金华电子商务创业园115室","description":"计算机软件开发","bankLicense":"3301-05072768","orgNo":"3301-05072768","businessLicense":"91330702ma28e3rr5j","taxNo":"J3380006237501"},"user_type":"1","deal_count":"4","yuqi_count":"0","cate_id":"1"}
             * description : 该企业的经营范围工程机械设备租赁。本次通过乐毅金服申请借款5万元，借款用于资金周转，还款来源为经营收入，还款保障措施为企业信用及无限连带保证。同时，经风控部门审核借款人所提供资料真实有效，符合乐毅金服的借款审核标准。
             * risk_security : 乐毅金服针对不同类型的产品特点和风险特征分别建立了相对应的产品管理制度和项目贷前、贷中、贷后管理制度，防范欺诈风险和信用风险。经平台撮合成交的项目一旦逾期，平台将协助相关方面通过提供逾期证明、融资服务协议 (电子合同)、资金流转记录等必须的信息和资金链证据，协助相关方面实现短信、电话、诉讼、外包等合规催收手段。
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
                 * u_info : {"user_id":"14","company_name":"金华迎曼信息科技有限公司","contact":"范焱达","officetype":"一般民营企业","officedomain":"IT","officecale":"10-100人","register_capital":"500000","asset_value":"100","officeaddress":"浙江省金华市婺城区金带街938号金华电子商务创业园115室","description":"计算机软件开发","bankLicense":"3301-05072768","orgNo":"3301-05072768","businessLicense":"91330702ma28e3rr5j","taxNo":"J3380006237501"}
                 * user_type : 1
                 * deal_count : 4
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
                     * user_id : 14
                     * company_name : 金华迎曼信息科技有限公司
                     * contact : 范焱达
                     * officetype : 一般民营企业
                     * officedomain : IT
                     * officecale : 10-100人
                     * register_capital : 500000
                     * asset_value : 100
                     * officeaddress : 浙江省金华市婺城区金带街938号金华电子商务创业园115室
                     * description : 计算机软件开发
                     * bankLicense : 3301-05072768
                     * orgNo : 3301-05072768
                     * businessLicense : 91330702ma28e3rr5j
                     * taxNo : J3380006237501
                     */

                    private String user_id;
                    private String company_name;
                    private String contact;
                    private String officetype;
                    private String officedomain;
                    private String officecale;
                    private String register_capital;
                    private String asset_value;
                    private String officeaddress;
                    private String description;
                    private String bankLicense;
                    private String orgNo;
                    private String businessLicense;
                    private String taxNo;

                    public static UInfoBean objectFromData(String str) {

                        return new Gson().fromJson(str, UInfoBean.class);
                    }

                    public String getUser_id() {
                        return user_id;
                    }

                    public void setUser_id(String user_id) {
                        this.user_id = user_id;
                    }

                    public String getCompany_name() {
                        return company_name;
                    }

                    public void setCompany_name(String company_name) {
                        this.company_name = company_name;
                    }

                    public String getContact() {
                        return contact;
                    }

                    public void setContact(String contact) {
                        this.contact = contact;
                    }

                    public String getOfficetype() {
                        return officetype;
                    }

                    public void setOfficetype(String officetype) {
                        this.officetype = officetype;
                    }

                    public String getOfficedomain() {
                        return officedomain;
                    }

                    public void setOfficedomain(String officedomain) {
                        this.officedomain = officedomain;
                    }

                    public String getOfficecale() {
                        return officecale;
                    }

                    public void setOfficecale(String officecale) {
                        this.officecale = officecale;
                    }

                    public String getRegister_capital() {
                        return register_capital;
                    }

                    public void setRegister_capital(String register_capital) {
                        this.register_capital = register_capital;
                    }

                    public String getAsset_value() {
                        return asset_value;
                    }

                    public void setAsset_value(String asset_value) {
                        this.asset_value = asset_value;
                    }

                    public String getOfficeaddress() {
                        return officeaddress;
                    }

                    public void setOfficeaddress(String officeaddress) {
                        this.officeaddress = officeaddress;
                    }

                    public String getDescription() {
                        return description;
                    }

                    public void setDescription(String description) {
                        this.description = description;
                    }

                    public String getBankLicense() {
                        return bankLicense;
                    }

                    public void setBankLicense(String bankLicense) {
                        this.bankLicense = bankLicense;
                    }

                    public String getOrgNo() {
                        return orgNo;
                    }

                    public void setOrgNo(String orgNo) {
                        this.orgNo = orgNo;
                    }

                    public String getBusinessLicense() {
                        return businessLicense;
                    }

                    public void setBusinessLicense(String businessLicense) {
                        this.businessLicense = businessLicense;
                    }

                    public String getTaxNo() {
                        return taxNo;
                    }

                    public void setTaxNo(String taxNo) {
                        this.taxNo = taxNo;
                    }
                }
            }
        }

}
