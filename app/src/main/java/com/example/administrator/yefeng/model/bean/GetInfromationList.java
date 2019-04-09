package com.example.administrator.yefeng.model.bean;

import java.util.List;

public class GetInfromationList {

    /**
     * code : 1
     * message : 成功！
     * encrypt : false
     * data : {"dataList":[{"logo":"http://borrow-logo.oss-cn-shenzhen.aliyuncs.com/news-logo/%E5%9B%BE%E7%89%871.png","informationId":1,"headline":"5年了你的负面记录没清除？你很可能没做这件事\u2026\u2026","slogan":"凤凰网"},{"logo":"http://borrow-logo.oss-cn-shenzhen.aliyuncs.com/news-logo/%E5%9B%BE%E7%89%872.png","informationId":2,"headline":"借3千要还20万！这类网贷专门针对女性？有人逾期后被骗去夜总会","slogan":"上岸之家"}]}
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
        private List<DataListBean> dataList;

        public List<DataListBean> getDataList() {
            return dataList;
        }

        public void setDataList(List<DataListBean> dataList) {
            this.dataList = dataList;
        }

        public static class DataListBean {
            /**
             * logo : http://borrow-logo.oss-cn-shenzhen.aliyuncs.com/news-logo/%E5%9B%BE%E7%89%871.png
             * informationId : 1
             * headline : 5年了你的负面记录没清除？你很可能没做这件事……
             * slogan : 凤凰网
             */

            private String logo;
            private int informationId;
            private String headline;
            private String slogan;

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public int getInformationId() {
                return informationId;
            }

            public void setInformationId(int informationId) {
                this.informationId = informationId;
            }

            public String getHeadline() {
                return headline;
            }

            public void setHeadline(String headline) {
                this.headline = headline;
            }

            public String getSlogan() {
                return slogan;
            }

            public void setSlogan(String slogan) {
                this.slogan = slogan;
            }
        }
    }
}
