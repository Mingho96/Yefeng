package com.example.administrator.yefeng.model.bean;

import java.util.List;

public class GetLoanItem {

    /**
     * code : 1
     * message : 成功
     * encrypt : false
     * data : {"productId":1,"name":"金袋鼠","logo":"http://borrow-logo.oss-cn-shenzhen.aliyuncs.com/%E9%87%91%E8%A2%8B%E9%BC%A0.png","adver":"1.身份证；2.收款银行卡；3.手机运营商；4.芝麻授信","moneyMax":3000.23,"moneyMin":1000,"day":7,"rate":0.03,"loanFastest":"3分钟放款","applyCondition":"年龄：18-40周岁，拥有稳定的收入来源，无不良的信用记录","materialScience":"1.身份证；2.收款银行卡；3.手机运营商；4.芝麻授信","authorization":"身份证，银行卡，淘宝，芝麻授信","auditMode":"电脑审核","repaymentMthod":"先息后本/续借/延期","url":"http://t.cn/EzsB2ed","type":["利率低","无视黑","门槛低"]}
     */

    private String code;
    private String message;
    private boolean encrypt;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isEncrypt() {
        return encrypt;
    }

    public void setEncrypt(boolean encrypt) {
        this.encrypt = encrypt;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * productId : 1
         * name : 金袋鼠
         * logo : http://borrow-logo.oss-cn-shenzhen.aliyuncs.com/%E9%87%91%E8%A2%8B%E9%BC%A0.png
         * adver : 1.身份证；2.收款银行卡；3.手机运营商；4.芝麻授信
         * moneyMax : 3000.23
         * moneyMin : 1000
         * day : 7
         * rate : 0.03
         * loanFastest : 3分钟放款
         * applyCondition : 年龄：18-40周岁，拥有稳定的收入来源，无不良的信用记录
         * materialScience : 1.身份证；2.收款银行卡；3.手机运营商；4.芝麻授信
         * authorization : 身份证，银行卡，淘宝，芝麻授信
         * auditMode : 电脑审核
         * repaymentMthod : 先息后本/续借/延期
         * url : http://t.cn/EzsB2ed
         * type : ["利率低","无视黑","门槛低"]
         */

        private int productId;
        private String name;
        private String logo;
        private String adver;
        private double moneyMax;
        private int moneyMin;
        private int day;
        private double rate;
        private String loanFastest;
        private String applyCondition;
        private String materialScience;
        private String authorization;
        private String auditMode;
        private String repaymentMthod;
        private String url;
        private List<String> type;

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
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

        public String getAdver() {
            return adver;
        }

        public void setAdver(String adver) {
            this.adver = adver;
        }

        public double getMoneyMax() {
            return moneyMax;
        }

        public void setMoneyMax(double moneyMax) {
            this.moneyMax = moneyMax;
        }

        public int getMoneyMin() {
            return moneyMin;
        }

        public void setMoneyMin(int moneyMin) {
            this.moneyMin = moneyMin;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public double getRate() {
            return rate;
        }

        public void setRate(double rate) {
            this.rate = rate;
        }

        public String getLoanFastest() {
            return loanFastest;
        }

        public void setLoanFastest(String loanFastest) {
            this.loanFastest = loanFastest;
        }

        public String getApplyCondition() {
            return applyCondition;
        }

        public void setApplyCondition(String applyCondition) {
            this.applyCondition = applyCondition;
        }

        public String getMaterialScience() {
            return materialScience;
        }

        public void setMaterialScience(String materialScience) {
            this.materialScience = materialScience;
        }

        public String getAuthorization() {
            return authorization;
        }

        public void setAuthorization(String authorization) {
            this.authorization = authorization;
        }

        public String getAuditMode() {
            return auditMode;
        }

        public void setAuditMode(String auditMode) {
            this.auditMode = auditMode;
        }

        public String getRepaymentMthod() {
            return repaymentMthod;
        }

        public void setRepaymentMthod(String repaymentMthod) {
            this.repaymentMthod = repaymentMthod;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<String> getType() {
            return type;
        }

        public void setType(List<String> type) {
            this.type = type;
        }
    }
}
