package com.example.administrator.yefeng.model.bean;

import java.util.List;

public class CardImage {

    /**
     * code : 1
     * message : 成功！
     * encrypt : false
     * data : {"dataList":[{"slogan2":"信用","cardId":1,"name":"浦发银行信用卡","logo":"http://borrow-logo.oss-cn-shenzhen.aliyuncs.com/card-logo/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20181101102005.png","slogan":"浦发","url":"http://www.qichangkeji.vip/gongzhonghaoWeb/page/cardAction.html?id=26&userId=120784&otherId=0"}]}
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
             * slogan2 : 信用
             * cardId : 1
             * name : 浦发银行信用卡
             * logo : http://borrow-logo.oss-cn-shenzhen.aliyuncs.com/card-logo/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20181101102005.png
             * slogan : 浦发
             * url : http://www.qichangkeji.vip/gongzhonghaoWeb/page/cardAction.html?id=26&userId=120784&otherId=0
             */

            private String slogan2;
            private int cardId;
            private String name;
            private String logo;
            private String slogan;
            private String url;

            public String getSlogan2() {
                return slogan2;
            }

            public void setSlogan2(String slogan2) {
                this.slogan2 = slogan2;
            }

            public int getCardId() {
                return cardId;
            }

            public void setCardId(int cardId) {
                this.cardId = cardId;
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

            public String getSlogan() {
                return slogan;
            }

            public void setSlogan(String slogan) {
                this.slogan = slogan;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
