package com.zhy.sample_okhttp;

/**
 * Created by 郭君华 on 2016/1/25.
 * Email：guojunhua3369@163.com
 */
public class Users {

    /**
     * code : 0
     * msg : success
     * data : {"uid":"100292","name":"zaqxsw","photo":"http://www.gravatar.com/avatar/353a3f847836d20d967d753afe88ccb7?s=80&d=identicon","bind":{"email":{"auth_id":"zaqxsw","auth_name":"zaqxsw"}},"token":"N2AwQtnfEDGV1ebafzsWU1D6kIgwO3d4ZeOarbVw2ff98LfDT3tKM8SCN5V8aAD0"}
     */

    private int code;
    private String msg;
    /**
     * uid : 100292
     * name : zaqxsw
     * photo : http://www.gravatar.com/avatar/353a3f847836d20d967d753afe88ccb7?s=80&d=identicon
     * bind : {"email":{"auth_id":"zaqxsw","auth_name":"zaqxsw"}}
     * token : N2AwQtnfEDGV1ebafzsWU1D6kIgwO3d4ZeOarbVw2ff98LfDT3tKM8SCN5V8aAD0
     */

    private DataEntity data;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public DataEntity getData() {
        return data;
    }

    public static class DataEntity {
        private String uid;
        private String name;
        private String photo;
        /**
         * email : {"auth_id":"zaqxsw","auth_name":"zaqxsw"}
         */

        private BindEntity bind;
        private String token;

        public void setUid(String uid) {
            this.uid = uid;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public void setBind(BindEntity bind) {
            this.bind = bind;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUid() {
            return uid;
        }

        public String getName() {
            return name;
        }

        public String getPhoto() {
            return photo;
        }

        public BindEntity getBind() {
            return bind;
        }

        public String getToken() {
            return token;
        }

        public static class BindEntity {
            /**
             * auth_id : zaqxsw
             * auth_name : zaqxsw
             */

            private EmailEntity email;

            public void setEmail(EmailEntity email) {
                this.email = email;
            }

            public EmailEntity getEmail() {
                return email;
            }

            public static class EmailEntity {
                private String auth_id;
                private String auth_name;

                public void setAuth_id(String auth_id) {
                    this.auth_id = auth_id;
                }

                public void setAuth_name(String auth_name) {
                    this.auth_name = auth_name;
                }

                public String getAuth_id() {
                    return auth_id;
                }

                public String getAuth_name() {
                    return auth_name;
                }

                @Override
                public String toString() {
                    return "EmailEntity{" +
                            "auth_id='" + auth_id + '\'' +
                            ", auth_name='" + auth_name + '\'' +
                            '}';
                }
            }

            @Override
            public String toString() {
                return "BindEntity{" +
                        "email=" + email +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "DataEntity{" +
                    "uid='" + uid + '\'' +
                    ", name='" + name + '\'' +
                    ", photo='" + photo + '\'' +
                    ", bind=" + bind +
                    ", token='" + token + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Users{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
