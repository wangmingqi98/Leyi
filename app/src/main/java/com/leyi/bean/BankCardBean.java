package com.leyi.bean;

/**
 * Created by Administrator on 2018/3/27.
 */

public class BankCardBean {
//     "id":"2",
//             　　　　　　　　"bankcard":"6228480455541746679",
//             　　　　　　　　"real_name":"朱迪迪",
//             　　　　　　　　"bank_name":"中国农业银行",
//             　　　　　　　　"bank_id":"55",
//             　　　　　　　　"bankcode":"6228480455541746679",
//             　　　　　　　　"img":"http://leyibank.com/public/bank/55.jpg"



  String id;
    String bankcard;
    String real_name;
    String bank_name;
    String bank_id;
    String  bankcode;
    String img;

    public BankCardBean(String id, String bankcard, String real_name, String bank_name, String bank_id, String bankcode, String img) {
        this.id = id;
        this.bankcard = bankcard;
        this.real_name = real_name;
        this.bank_name = bank_name;
        this.bank_id = bank_id;
        this.bankcode = bankcode;
        this.img = img;
    }

    @Override
    public String toString() {
        return "BankCardBean{" +
                "id='" + id + '\'' +
                ", bankcard='" + bankcard + '\'' +
                ", real_name='" + real_name + '\'' +
                ", bank_name='" + bank_name + '\'' +
                ", bank_id='" + bank_id + '\'' +
                ", bankcode='" + bankcode + '\'' +
                ", img='" + img + '\'' +
                '}';
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBank_id() {
        return bank_id;
    }

    public void setBank_id(String bank_id) {
        this.bank_id = bank_id;
    }

    public String getBankcode() {
        return bankcode;
    }

    public void setBankcode(String bankcode) {
        this.bankcode = bankcode;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
