package com.leyijf.bean;

import com.google.gson.Gson;

/**
 * Created by wmq on 2018/5/31.
 */

public class GetPrpfitBean {

    public static GetPrpfitBean objectFromData(String str) {

        return new Gson().fromJson(str, GetPrpfitBean.class);
    }

        /**
         * profit : ¥ 6.25
         */

        private String profit;


        public String getProfit() {
            return profit;
        }

        public void setProfit(String profit) {
            this.profit = profit;
        }

}
