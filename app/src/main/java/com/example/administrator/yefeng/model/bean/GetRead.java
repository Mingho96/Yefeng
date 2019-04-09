package com.example.administrator.yefeng.model.bean;

import java.util.List;

public class GetRead {

    /**
     * code : 1
     * message : 成功
     * encrypt : false
     * data : {"dataList":[{"productId":2,"name":"随时钱袋","logo":"https://jkqb-jsb-test.oss-cn-shanghai.aliyuncs.com/http://borrow-logo.oss-cn-shenzhen.aliyuncs.com/%E9%87%91%E8%A2%8B%E9%BC%A0.png","time":"2018-11-03 12:59"},{"productId":1,"name":"金袋鼠","logo":"https://jkqb-jsb-test.oss-cn-shanghai.aliyuncs.com/http://borrow-logo.oss-cn-shenzhen.aliyuncs.com/%E9%87%91%E8%A2%8B%E9%BC%A0.png","time":"2018-11-03 12:48"},{"productId":null,"name":"浦发银行信用卡","logo":"https://jkqb-jsb-test.oss-cn-shanghai.aliyuncs.com/http://borrow-logo.oss-cn-shenzhen.aliyuncs.com/card-logo/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20181101102005.png","time":"2018-11-03 12:34"}]}
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
             * productId : 2
             * name : 随时钱袋
             * logo : https://jkqb-jsb-test.oss-cn-shanghai.aliyuncs.com/http://borrow-logo.oss-cn-shenzhen.aliyuncs.com/%E9%87%91%E8%A2%8B%E9%BC%A0.png
             * time : 2018-11-03 12:59
             */

            private int productId;
            private String name;
            private String logo;
            private String time;

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

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }
        }
    }
}
