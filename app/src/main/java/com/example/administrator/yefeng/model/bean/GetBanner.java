package com.example.administrator.yefeng.model.bean;

import java.util.List;

public class GetBanner {

    /**
     * code : 1
     * message : 成功！
     * encrypt : false
     * data : {"banner":[{"inId":1,"name":"91捷贷","logo":"https://jkqb-jsb-test.oss-cn-shanghai.aliyuncs.com/http://borrow-logo.oss-cn-shenzhen.aliyuncs.com/banner-logo/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20181103123947.jpg","type":1},{"inId":2,"name":"11狂欢","logo":"https://jkqb-jsb-test.oss-cn-shanghai.aliyuncs.com/https://borrow-logo.oss-cn-shenzhen.aliyuncs.com/banner-logo/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20181103123952.jpg","type":1},{"inId":3,"name":"贷款推荐","logo":"https://jkqb-jsb-test.oss-cn-shanghai.aliyuncs.com/https://borrow-logo.oss-cn-shenzhen.aliyuncs.com/banner-logo/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20181103123956.jpg","type":1}]}
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
        private List<BannerBean> banner;

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public static class BannerBean {
            /**
             * inId : 1
             * name : 91捷贷
             * logo : https://jkqb-jsb-test.oss-cn-shanghai.aliyuncs.com/http://borrow-logo.oss-cn-shenzhen.aliyuncs.com/banner-logo/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20181103123947.jpg
             * type : 1
             */

            private int inId;
            private String name;
            private String logo;
            private int type;
            private String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getInId() {
                return inId;
            }

            public void setInId(int inId) {
                this.inId = inId;
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

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }
    }
}
