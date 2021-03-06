package com.example.administrator.yefeng.model.rxbean;

import java.util.List;

public class UEverydayRecommend {
    private List<DataListBean> dataList;

    public List<DataListBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataListBean> dataList) {
        this.dataList = dataList;
    }

    public static class DataListBean {
        /**
         * moneyMin : 1000
         * productId : 1
         * moneyMax : 3000.23
         * rate : 0.03
         * adver : 零门槛，1000-3000额度三分钟放款
         * name : 金袋鼠
         * logo : http://borrow-logo.oss-cn-shenzhen.aliyuncs.com/%E9%87%91%E8%A2%8B%E9%BC%A0.png
         * type : ["利率低","无视黑","门槛低"]
         * day : 7
         */

        private int moneyMin;
        private int productId;
        private double moneyMax;
        private double rate;
        private String adver;
        private String name;
        private String logo;
        private int day;
        private List<String> type;

        public int getMoneyMin() {
            return moneyMin;
        }

        public void setMoneyMin(int moneyMin) {
            this.moneyMin = moneyMin;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public double getMoneyMax() {
            return moneyMax;
        }

        public void setMoneyMax(double moneyMax) {
            this.moneyMax = moneyMax;
        }

        public double getRate() {
            return rate;
        }

        public void setRate(double rate) {
            this.rate = rate;
        }

        public String getAdver() {
            return adver;
        }

        public void setAdver(String adver) {
            this.adver = adver;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public List<String> getType() {
            return type;
        }

        public void setType(List<String> type) {
            this.type = type;
        }
    }
    }
