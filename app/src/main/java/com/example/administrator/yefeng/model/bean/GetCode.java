package com.example.administrator.yefeng.model.bean;

public class GetCode {

    /**
     * code : 1
     * message : 成功
     * encrypt : false
     * data : {"verificationCode":470072}
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
         * verificationCode : 470072
         */

        private int verificationCode;

        public int getVerificationCode() {
            return verificationCode;
        }

        public void setVerificationCode(int verificationCode) {
            this.verificationCode = verificationCode;
        }
    }
}
