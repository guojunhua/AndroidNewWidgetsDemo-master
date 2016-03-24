package com.ht.expresspost;

import java.util.List;


/**
 * Created by 郭君华 on 2016/3/11.
 * Email：guojunhua3369@163.com
 */
public class ExpressQuery {

    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"data":[{"context":"已签收,感谢使用顺丰,期待再次为您服务","time":"2016-01-08 09:38:57"},{"context":"正在派送途中,请您准备签收 (派件人:梁松,电话:13277835886)","time":"2016-01-08 09:37:57"},{"context":"快件派送不成功(应客户要求,按新地址转寄),待转运","time":"2015-11-29 14:47:18"},{"context":"已与收方客户约定新派送时间 ,待派送","time":"2015-11-28 10:15:03"},{"context":"快件正送往顺丰店/站","time":"2015-11-28 09:35:03"},{"context":"快件到达 【南宁邕宁龙岗营业站】","time":"2015-11-28 09:28:32"},{"context":"快件离开【南宁良庆荣旺商城营业点】,正发往下一站","time":"2015-11-28 08:35:57"},{"context":"快件到达 【南宁良庆荣旺商城营业点】","time":"2015-11-28 07:18:23"},{"context":"快件离开【南宁江南集散中心】,正发往 【南宁良庆荣旺商城营业点】","time":"2015-11-28 06:26:19"},{"context":"快件到达 【南宁江南集散中心】","time":"2015-11-27 20:32:21"},{"context":"快件离开【南宁青秀医疗机械园营业点】,正发往 【南宁江南集散中心】","time":"2015-11-27 19:56:10"},{"context":"顺丰速运 已收取快件","time":"2015-11-27 19:28:14"}],"expSpellName":"shunfeng","expTextName":"顺丰速运","flag":true,"mailNo":"471690940271","ret_code":0,"status":4,"tel":"95338"}
     */

    private int showapi_res_code;
    private String showapi_res_error;
    /**
     * data : [{"context":"已签收,感谢使用顺丰,期待再次为您服务","time":"2016-01-08 09:38:57"},{"context":"正在派送途中,请您准备签收 (派件人:梁松,电话:13277835886)","time":"2016-01-08 09:37:57"},{"context":"快件派送不成功(应客户要求,按新地址转寄),待转运","time":"2015-11-29 14:47:18"},{"context":"已与收方客户约定新派送时间 ,待派送","time":"2015-11-28 10:15:03"},{"context":"快件正送往顺丰店/站","time":"2015-11-28 09:35:03"},{"context":"快件到达 【南宁邕宁龙岗营业站】","time":"2015-11-28 09:28:32"},{"context":"快件离开【南宁良庆荣旺商城营业点】,正发往下一站","time":"2015-11-28 08:35:57"},{"context":"快件到达 【南宁良庆荣旺商城营业点】","time":"2015-11-28 07:18:23"},{"context":"快件离开【南宁江南集散中心】,正发往 【南宁良庆荣旺商城营业点】","time":"2015-11-28 06:26:19"},{"context":"快件到达 【南宁江南集散中心】","time":"2015-11-27 20:32:21"},{"context":"快件离开【南宁青秀医疗机械园营业点】,正发往 【南宁江南集散中心】","time":"2015-11-27 19:56:10"},{"context":"顺丰速运 已收取快件","time":"2015-11-27 19:28:14"}]
     * expSpellName : shunfeng
     * expTextName : 顺丰速运
     * flag : true
     * mailNo : 471690940271
     * ret_code : 0
     * status : 4
     * tel : 95338
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

    @Override
    public String toString() {
        return "ExpressQuery{" +
                "showapi_res_code=" + showapi_res_code +
                ", showapi_res_error='" + showapi_res_error + '\'' +
                ", showapi_res_body=" + showapi_res_body +
                '}';
    }

    public static class ShowapiResBodyEntity {
        private String expSpellName;
        private String expTextName;
        private boolean flag;
        private String mailNo;
        private int ret_code;
        private int status;
        private String tel;
        /**
         * context : 已签收,感谢使用顺丰,期待再次为您服务
         * time : 2016-01-08 09:38:57
         */

        private List<DataEntity> data;

        public void setExpSpellName(String expSpellName) {
            this.expSpellName = expSpellName;
        }

        public void setExpTextName(String expTextName) {
            this.expTextName = expTextName;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        public void setMailNo(String mailNo) {
            this.mailNo = mailNo;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public void setData(List<DataEntity> data) {
            this.data = data;
        }

        public String getExpSpellName() {
            return expSpellName;
        }

        public String getExpTextName() {
            return expTextName;
        }

        public boolean isFlag() {
            return flag;
        }

        public String getMailNo() {
            return mailNo;
        }

        public int getRet_code() {
            return ret_code;
        }

        public int getStatus() {
            return status;
        }

        public String getTel() {
            return tel;
        }

        public List<DataEntity> getData() {
            return data;
        }

        @Override
        public String toString() {
            return "ShowapiResBodyEntity{" +
                    "tel='" + tel + '\'' +
                    ", data=" + data +
                    ", ret_code=" + ret_code +
                    ", status=" + status +
                    ", mailNo='" + mailNo + '\'' +
                    ", flag=" + flag +
                    ", expTextName='" + expTextName + '\'' +
                    ", expSpellName='" + expSpellName + '\'' +
                    '}';
        }

        public static class DataEntity {
            private String context;
            private String time;

            public void setContext(String context) {
                this.context = context;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getContext() {
                return context;
            }

            public String getTime() {
                return time;
            }

            @Override
            public String toString() {
                return "DataEntity{" +
                        "context='" + context + '\'' +
                        ", time='" + time + '\'' +
                        '}';
            }
        }
    }
}
