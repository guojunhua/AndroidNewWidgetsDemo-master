package com.ht.taonvlangdemo;

import java.util.List;

/**
 * Created by 郭君华 on 2016/3/4.
 * Email：guojunhua3369@163.com
 */
public class TypeBean {

    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"allTypeList":["欧美","韩版","日系","英伦","OL风","学院","淑女","性感","复古","街头","休闲","民族","甜美","运动","可爱","大码","中老年","其他"],"num":18,"ret_code":0}
     */

    private int showapi_res_code;
    private String showapi_res_error;

    @Override
    public String toString() {
        return "TypeBean{" +
                "showapi_res_code=" + showapi_res_code +
                ", showapi_res_error='" + showapi_res_error + '\'' +
                ", showapi_res_body=" + showapi_res_body +
                '}';
    }

    /**
     * allTypeList : ["欧美","韩版","日系","英伦","OL风","学院","淑女","性感","复古","街头","休闲","民族","甜美","运动","可爱","大码","中老年","其他"]
     * num : 18
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
        private int num;
        private int ret_code;
        private List<String> allTypeList;

        public void setNum(int num) {
            this.num = num;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public void setAllTypeList(List<String> allTypeList) {
            this.allTypeList = allTypeList;
        }

        public int getNum() {
            return num;
        }

        public int getRet_code() {
            return ret_code;
        }

        public List<String> getAllTypeList() {
            return allTypeList;
        }

        @Override
        public String toString() {
            return "ShowapiResBodyEntity{" +
                    "num=" + num +
                    ", ret_code=" + ret_code +
                    ", allTypeList=" + allTypeList +
                    '}';
        }
    }
}
