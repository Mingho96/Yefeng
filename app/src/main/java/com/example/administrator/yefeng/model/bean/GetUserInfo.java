package com.example.administrator.yefeng.model.bean;

import java.io.Serializable;

public class GetUserInfo implements Serializable {

    /**
     * code : 1
     * message : 成功！
     * encrypt : false
     * data : {"token":"cb98f0ea-4d9a-43cf-b858-a54f64768753","cookie":"cb98f0ea-4d9a-43cf-b858-a54f64768753","user":{"id":2,"name":null,"phone":"18680906723","pass":null,"platform":2,"channel":0,"site":null,"regTime":null,"id_number":null}}
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
         * token : cb98f0ea-4d9a-43cf-b858-a54f64768753
         * cookie : cb98f0ea-4d9a-43cf-b858-a54f64768753
         * user : {"id":2,"name":null,"phone":"18680906723","pass":null,"platform":2,"channel":0,"site":null,"regTime":null,"id_number":null}
         */

        private String token;
        private String cookie;
        private UserBean user;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getCookie() {
            return cookie;
        }

        public void setCookie(String cookie) {
            this.cookie = cookie;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * id : 2
             * name : null
             * phone : 18680906723
             * pass : null
             * platform : 2
             * channel : 0
             * site : null
             * regTime : null
             * id_number : null
             */

            private int id;
            private String name;
            private String phone;
            private String pass;
            private int platform;
            private int channel;
            private String site;
            private String regTime;
            private String id_number;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getPass() {
                return pass;
            }

            public void setPass(String pass) {
                this.pass = pass;
            }

            public int getPlatform() {
                return platform;
            }

            public void setPlatform(int platform) {
                this.platform = platform;
            }

            public int getChannel() {
                return channel;
            }

            public void setChannel(int channel) {
                this.channel = channel;
            }

            public String getSite() {
                return site;
            }

            public void setSite(String site) {
                this.site = site;
            }

            public String getRegTime() {
                return regTime;
            }

            public void setRegTime(String regTime) {
                this.regTime = regTime;
            }

            public String getId_number() {
                return id_number;
            }

            public void setId_number(String id_number) {
                this.id_number = id_number;
            }
        }
    }
}
