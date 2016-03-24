package com.ht.expresspost;

import java.util.List;

/**
 * Created by 郭君华 on 2016/3/11.
 * Email：guojunhua3369@163.com
 */
public class ExpressTableBean {



    private int showapi_res_code;
    private String showapi_res_error;
    private ShowapiResBodyEntity showapi_res_body;

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public void setShowapi_res_body(ShowapiResBodyEntity showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public ShowapiResBodyEntity getShowapi_res_body() {
        return showapi_res_body;
    }

    @Override
    public String toString() {
        return "ExpressTableBean{" +
                "showapi_res_code=" + showapi_res_code +
                ", showapi_res_error='" + showapi_res_error + '\'' +
                ", showapi_res_body=" + showapi_res_body +
                '}';
    }

    public static class ShowapiResBodyEntity {
        private boolean flag;
        private int ret_code;
        private List<ExpressListEntity> expressList;

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public void setExpressList(List<ExpressListEntity> expressList) {
            this.expressList = expressList;
        }

        public boolean isFlag() {
            return flag;
        }

        public int getRet_code() {
            return ret_code;
        }

        public List<ExpressListEntity> getExpressList() {
            return expressList;
        }

        @Override
        public String toString() {
            return "ShowapiResBodyEntity{" +
                    "flag=" + flag +
                    ", ret_code=" + ret_code +
                    ", expressList=" + expressList +
                    '}';
        }

        public static class ExpressListEntity {
            private String expName;
            private String imgUrl;
            private String note;
            private String phone;
            private String simpleName;
            private String url;

            public void setExpName(String expName) {
                this.expName = expName;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public void setNote(String note) {
                this.note = note;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public void setSimpleName(String simpleName) {
                this.simpleName = simpleName;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getExpName() {
                return expName;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public String getNote() {
                return note;
            }

            public String getPhone() {
                return phone;
            }

            public String getSimpleName() {
                return simpleName;
            }

            public String getUrl() {
                return url;
            }

            @Override
            public String toString() {
                return "ExpressListEntity{" +
                        "expName='" + expName + '\'' +
                        ", imgUrl='" + imgUrl + '\'' +
                        ", note='" + note + '\'' +
                        ", phone='" + phone + '\'' +
                        ", simpleName='" + simpleName + '\'' +
                        ", url='" + url + '\'' +
                        '}';
            }
        }
    }
}
