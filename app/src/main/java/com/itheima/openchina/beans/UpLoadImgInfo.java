package com.itheima.openchina.beans;

import java.util.List;

/**
 * User:DoctorHe <p/>
 * Date: 2017/11/8 <p/>
 * Time: 20:40 <p/>
 * Project:OpenChina <p/>
 * Package:com.itheima.openchina.beans <p/>
 * Desc:上传图片返回信息
 */

public class UpLoadImgInfo {

    /**
     * code : 1
     * message : SUCCESS
     * notice : {"like":0,"review":0,"letter":0,"mention":0,"fans":0}
     * result : {"resources":[{"h":200,"href":"http://static.oschina
     * .net/uploads/space/2017/1108/203918_gK6S_3197930.jpg","name":"203918_gK6S_3197930",
     * "thumb":"http://static.oschina.net/uploads/space/2017/1108/203918_gK6S_3197930_thumb.jpg",
     * "type":"jpg","w":200}],"token":"CFF479138D9AE418"}
     * time : 2017-11-08 20:39:18
     */

    private int code;
    private String message;
    private NoticeBean notice;
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

    public NoticeBean getNotice() {
        return notice;
    }

    public void setNotice(NoticeBean notice) {
        this.notice = notice;
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

    public static class NoticeBean {
        /**
         * like : 0
         * review : 0
         * letter : 0
         * mention : 0
         * fans : 0
         */

        private int like;
        private int review;
        private int letter;
        private int mention;
        private int fans;

        public int getLike() {
            return like;
        }

        public void setLike(int like) {
            this.like = like;
        }

        public int getReview() {
            return review;
        }

        public void setReview(int review) {
            this.review = review;
        }

        public int getLetter() {
            return letter;
        }

        public void setLetter(int letter) {
            this.letter = letter;
        }

        public int getMention() {
            return mention;
        }

        public void setMention(int mention) {
            this.mention = mention;
        }

        public int getFans() {
            return fans;
        }

        public void setFans(int fans) {
            this.fans = fans;
        }
    }

    public static class ResultBean {
        /**
         * resources : [{"h":200,"href":"http://static.oschina
         * .net/uploads/space/2017/1108/203918_gK6S_3197930.jpg","name":"203918_gK6S_3197930",
         * "thumb":"http://static.oschina.net/uploads/space/2017/1108/203918_gK6S_3197930_thumb
         * .jpg","type":"jpg","w":200}]
         * token : CFF479138D9AE418
         */

        private String token;
        private List<ResourcesBean> resources;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public List<ResourcesBean> getResources() {
            return resources;
        }

        public void setResources(List<ResourcesBean> resources) {
            this.resources = resources;
        }

        public static class ResourcesBean {
            /**
             * h : 200
             * href : http://static.oschina.net/uploads/space/2017/1108/203918_gK6S_3197930.jpg
             * name : 203918_gK6S_3197930
             * thumb : http://static.oschina.net/uploads/space/2017/1108/203918_gK6S_3197930_thumb.jpg
             * type : jpg
             * w : 200
             */

            private int h;
            private String href;
            private String name;
            private String thumb;
            private String type;
            private int w;

            public int getH() {
                return h;
            }

            public void setH(int h) {
                this.h = h;
            }

            public String getHref() {
                return href;
            }

            public void setHref(String href) {
                this.href = href;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getW() {
                return w;
            }

            public void setW(int w) {
                this.w = w;
            }
        }
    }
}
