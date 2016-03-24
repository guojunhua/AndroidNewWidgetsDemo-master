package com.ht.yiyuan.bean;

import java.util.List;


/**
 * Created by 郭君华 on 2016/2/20.
 * Email：guojunhua3369@163.com
 */
public class A {

    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"expressList":[{"_id":{"counter":5265231,"date":1431513403000,"machineIdentifier":6203591,"processIdentifier":9812,"time":1431513403000,"timeSecond":1431513403,"timestamp":1431513403},"expName":"宅急送快递","imgUrl":"http://www.ickd.cn/img/logo/zjs.jpg","note":"","phone":"4006-789-000","simpleName":"zhaijisong","url":"http://www.zjs.com.cn"},{"_id":{"counter":5265238,"date":1431513403000,"machineIdentifier":6203591,"processIdentifier":9812,"time":1431513403000,"timeSecond":1431513403,"timestamp":1431513403},"expName":"Aramex","imgUrl":"http://www.ickd.cn/img/logo/aramex.jpg","note":"","phone":"+86 (0571) 85092777","simpleName":"aramex","url":"http://www.aramex.com"}],"flag":true,"ret_code":0}
     */

    private int showapi_res_code;
    private String showapi_res_error;
    /**
     * expressList : [{"_id":{"counter":5265231,"date":1431513403000,"machineIdentifier":6203591,"processIdentifier":9812,"time":1431513403000,"timeSecond":1431513403,"timestamp":1431513403},"expName":"宅急送快递","imgUrl":"http://www.ickd.cn/img/logo/zjs.jpg","note":"","phone":"4006-789-000","simpleName":"zhaijisong","url":"http://www.zjs.com.cn"},{"_id":{"counter":5265238,"date":1431513403000,"machineIdentifier":6203591,"processIdentifier":9812,"time":1431513403000,"timeSecond":1431513403,"timestamp":1431513403},"expName":"Aramex","imgUrl":"http://www.ickd.cn/img/logo/aramex.jpg","note":"","phone":"+86 (0571) 85092777","simpleName":"aramex","url":"http://www.aramex.com"}]
     * flag : true
     * ret_code : 0
     */

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

    public static class ShowapiResBodyEntity {
        private boolean flag;
        private int ret_code;
        /**
         * _id : {"counter":5265231,"date":1431513403000,"machineIdentifier":6203591,"processIdentifier":9812,"time":1431513403000,"timeSecond":1431513403,"timestamp":1431513403}
         * expName : 宅急送快递
         * imgUrl : http://www.ickd.cn/img/logo/zjs.jpg
         * note :
         * phone : 4006-789-000
         * simpleName : zhaijisong
         * url : http://www.zjs.com.cn
         */

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

        public static class ExpressListEntity {
            /**
             * counter : 5265231
             * date : 1431513403000
             * machineIdentifier : 6203591
             * processIdentifier : 9812
             * time : 1431513403000
             * timeSecond : 1431513403
             * timestamp : 1431513403
             */

            private IdEntity _id;
            private String expName;
            private String imgUrl;
            private String note;
            private String phone;
            private String simpleName;
            private String url;

            public void set_id(IdEntity _id) {
                this._id = _id;
            }

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

            public IdEntity get_id() {
                return _id;
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

            public static class IdEntity {
                private int counter;
                private long date;
                private int machineIdentifier;
                private int processIdentifier;
                private long time;
                private int timeSecond;
                private int timestamp;

                public void setCounter(int counter) {
                    this.counter = counter;
                }

                public void setDate(long date) {
                    this.date = date;
                }

                public void setMachineIdentifier(int machineIdentifier) {
                    this.machineIdentifier = machineIdentifier;
                }

                public void setProcessIdentifier(int processIdentifier) {
                    this.processIdentifier = processIdentifier;
                }

                public void setTime(long time) {
                    this.time = time;
                }

                public void setTimeSecond(int timeSecond) {
                    this.timeSecond = timeSecond;
                }

                public void setTimestamp(int timestamp) {
                    this.timestamp = timestamp;
                }

                public int getCounter() {
                    return counter;
                }

                public long getDate() {
                    return date;
                }

                public int getMachineIdentifier() {
                    return machineIdentifier;
                }

                public int getProcessIdentifier() {
                    return processIdentifier;
                }

                public long getTime() {
                    return time;
                }

                public int getTimeSecond() {
                    return timeSecond;
                }

                public int getTimestamp() {
                    return timestamp;
                }

                @Override
                public String toString() {
                    return "IdEntity{" +
                            "counter=" + counter +
                            ", date=" + date +
                            ", machineIdentifier=" + machineIdentifier +
                            ", processIdentifier=" + processIdentifier +
                            ", time=" + time +
                            ", timeSecond=" + timeSecond +
                            ", timestamp=" + timestamp +
                            '}';
                }
            }

            @Override
            public String toString() {
                return "ExpressListEntity{" +
                        "_id=" + _id +
                        ", expName='" + expName + '\'' +
                        ", imgUrl='" + imgUrl + '\'' +
                        ", note='" + note + '\'' +
                        ", phone='" + phone + '\'' +
                        ", simpleName='" + simpleName + '\'' +
                        ", url='" + url + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "ShowapiResBodyEntity{" +
                    "flag=" + flag +
                    ", ret_code=" + ret_code +
                    ", expressList=" + expressList +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "A{" +
                "showapi_res_code=" + showapi_res_code +
                ", showapi_res_error='" + showapi_res_error + '\'' +
                ", showapi_res_body=" + showapi_res_body +
                '}';
    }
}
