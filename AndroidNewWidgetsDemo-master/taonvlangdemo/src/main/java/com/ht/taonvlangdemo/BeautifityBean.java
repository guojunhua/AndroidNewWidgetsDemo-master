package com.ht.taonvlangdemo;

import java.util.List;

/**
 * Created by 郭君华 on 2016/3/4.
 * Email：guojunhua3369@163.com
 */
public class BeautifityBean {

    private String showapi_res_code;
    private String showapi_res_error;
    private ShowapiResBodyEntity showapi_res_body;

    public void setShowapi_res_code(String showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public void setShowapi_res_body(ShowapiResBodyEntity showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public String getShowapi_res_code() {
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
        return "BeautifityBean{" +
                "showapi_res_code=" + showapi_res_code +
                ", showapi_res_error='" + showapi_res_error + '\'' +
                ", showapi_res_body=" + showapi_res_body +
                '}';
    }

    public static class ShowapiResBodyEntity {

        private PagebeanEntity pagebean;
        private String ret_code;

        public void setPagebean(PagebeanEntity pagebean) {
            this.pagebean = pagebean;
        }

        public void setRet_code(String ret_code) {
            this.ret_code = ret_code;
        }

        public PagebeanEntity getPagebean() {
            return pagebean;
        }

        public String getRet_code() {
            return ret_code;
        }

        @Override
        public String toString() {
            return "ShowapiResBodyEntity{" +
                    "pagebean=" + pagebean +
                    ", ret_code=" + ret_code +
                    '}';
        }

        public static class PagebeanEntity {
            private String allNum;
            private String allPages;
            private String currentPage;
            private String maxResult;

            private List<ContentlistEntity> contentlist;

            public void setAllNum(String allNum) {
                this.allNum = allNum;
            }

            public void setAllPages(String allPages) {
                this.allPages = allPages;
            }

            public void setCurrentPage(String currentPage) {
                this.currentPage = currentPage;
            }

            public void setMaxResult(String maxResult) {
                this.maxResult = maxResult;
            }

            public void setContentlist(List<ContentlistEntity> contentlist) {
                this.contentlist = contentlist;
            }

            public String getAllNum() {
                return allNum;
            }

            public String getAllPages() {
                return allPages;
            }

            public String getCurrentPage() {
                return currentPage;
            }

            public String getMaxResult() {
                return maxResult;
            }

            public List<ContentlistEntity> getContentlist() {
                return contentlist;
            }

            @Override
            public String toString() {
                return "PagebeanEntity{" +
                        "allNum=" + allNum +
                        ", allPages=" + allPages +
                        ", currentPage=" + currentPage +
                        ", maxResult=" + maxResult +
                        ", contentlist=" + contentlist +
                        '}';
            }

            public static class ContentlistEntity {
                private String avatarUrl;
                private String cardUrl;
                private String city;
                private String height;
                private String link;
                private String realName;
                private String totalFanNum;
                private String totalFavorNum;
                private String type;
                private String userId;
                private String weight;
                private List<String> imgList;

                public void setAvatarUrl(String avatarUrl) {
                    this.avatarUrl = avatarUrl;
                }

                public void setCardUrl(String cardUrl) {
                    this.cardUrl = cardUrl;
                }

                public void setCity(String city) {
                    this.city = city;
                }

                public void setHeight(String height) {
                    this.height = height;
                }

                public void setLink(String link) {
                    this.link = link;
                }

                public void setRealName(String realName) {
                    this.realName = realName;
                }

                public void setTotalFanNum(String totalFanNum) {
                    this.totalFanNum = totalFanNum;
                }

                public void setTotalFavorNum(String totalFavorNum) {
                    this.totalFavorNum = totalFavorNum;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public void setUserId(String userId) {
                    this.userId = userId;
                }

                public void setWeight(String weight) {
                    this.weight = weight;
                }

                public void setImgList(List<String> imgList) {
                    this.imgList = imgList;
                }

                public String getAvatarUrl() {
                    return avatarUrl;
                }

                public String getCardUrl() {
                    return cardUrl;
                }

                public String getCity() {
                    return city;
                }

                public String getHeight() {
                    return height;
                }

                public String getLink() {
                    return link;
                }

                public String getRealName() {
                    return realName;
                }

                public String getTotalFanNum() {
                    return totalFanNum;
                }

                public String getTotalFavorNum() {
                    return totalFavorNum;
                }

                public String getType() {
                    return type;
                }

                public String getUserId() {
                    return userId;
                }

                public String getWeight() {
                    return weight;
                }

                @Override
                public String toString() {
                    return "ContentlistEntity{" +
                            "avatarUrl='" + avatarUrl + '\'' +
                            ", cardUrl='" + cardUrl + '\'' +
                            ", city='" + city + '\'' +
                            ", height=" + height +
                            ", link='" + link + '\'' +
                            ", realName='" + realName + '\'' +
                            ", totalFanNum=" + totalFanNum +
                            ", totalFavorNum=" + totalFavorNum +
                            ", type='" + type + '\'' +
                            ", userId='" + userId + '\'' +
                            ", weight=" + weight +
                            ", imgList=" + imgList +
                            '}';
                }

                public List<String> getImgList() {
                    return imgList;

                }
            }
        }
    }
}
