package com.itheima.openchina.beans;

import com.itheima.openchina.interfaces.HeadType;

import java.util.List;

/**
 * Created by 佘本民
 * When:  --- 2017/11/7---
 * Time:  --- 14:30---
 * Function:
 */

public class ActionHeadBean {

    /**
     * code : 1
     * message : success
     * result : {"items":[{"detail":"开源中国西安站源创会将作为本次\u201c全球程序员节\u201c开源技术分论坛，秉承着\u201c自由，开放，分享\u201d的思想，为大家带来满满的干货技术分享。","href":"https://www.oschina.net/event/2267764","id":2267764,"img":"https://static.oschina.net/uploads/cover/2686220_zdisJ_bi.jpg","name":"【西安】OSC西安源创会报名开始","pubDate":"2017-10-23 20:15:22","type":5}],"nextPageToken":"21FB2AC056ABF7EE","prevPageToken":"794CDC3289641AA3","requestCount":1,"responseCount":1,"totalResults":1}
     * time : 2017-11-07 14:25:58
     */

    private int code;
    private String message;
    private ResultBean result;
    private String time;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public static class ResultBean {
        /**
         * items : [{"detail":"开源中国西安站源创会将作为本次\u201c全球程序员节\u201c开源技术分论坛，秉承着\u201c自由，开放，分享\u201d的思想，为大家带来满满的干货技术分享。","href":"https://www.oschina.net/event/2267764","id":2267764,"img":"https://static.oschina.net/uploads/cover/2686220_zdisJ_bi.jpg","name":"【西安】OSC西安源创会报名开始","pubDate":"2017-10-23 20:15:22","type":5}]
         * nextPageToken : 21FB2AC056ABF7EE
         * prevPageToken : 794CDC3289641AA3
         * requestCount : 1
         * responseCount : 1
         * totalResults : 1
         */

        private String nextPageToken;
        private String prevPageToken;
        private int requestCount;
        private int responseCount;
        private int totalResults;
        private List<ActionItems> items;

        public String getNextPageToken() {
            return nextPageToken;
        }

        public void setNextPageToken(String nextPageToken) {
            this.nextPageToken = nextPageToken;
        }

        public String getPrevPageToken() {
            return prevPageToken;
        }

        public void setPrevPageToken(String prevPageToken) {
            this.prevPageToken = prevPageToken;
        }

        public int getRequestCount() {
            return requestCount;
        }

        public void setRequestCount(int requestCount) {
            this.requestCount = requestCount;
        }

        public int getResponseCount() {
            return responseCount;
        }

        public void setResponseCount(int responseCount) {
            this.responseCount = responseCount;
        }

        public int getTotalResults() {
            return totalResults;
        }

        public void setTotalResults(int totalResults) {
            this.totalResults = totalResults;
        }

        public List<ActionItems> getItems() {
            return items;
        }

        public void setItems(List<ActionItems> items) {
            this.items = items;
        }

        public static class ActionItems implements HeadType{
            /**
             * detail : 开源中国西安站源创会将作为本次“全球程序员节“开源技术分论坛，秉承着“自由，开放，分享”的思想，为大家带来满满的干货技术分享。
             * href : https://www.oschina.net/event/2267764
             * id : 2267764
             * img : https://static.oschina.net/uploads/cover/2686220_zdisJ_bi.jpg
             * name : 【西安】OSC西安源创会报名开始
             * pubDate : 2017-10-23 20:15:22
             * type : 5
             */

            private String detail;
            private String href;
            private int id;
            private String img;
            private String name;
            private String pubDate;
            private int type;

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public String getHref() {
                return href;
            }

            public void setHref(String href) {
                this.href = href;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPubDate() {
                return pubDate;
            }

            public void setPubDate(String pubDate) {
                this.pubDate = pubDate;
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
