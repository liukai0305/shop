package com.example.xiangmuyi.zhishibean;

import java.util.List;

public class HomeEventshanfuBean {
    /**
     * status : {"statusCode":100,"message":"请求成功！","serverTime":"2020-08-19 15:53:39"}
     * data : [{"hanfuHistoryID":18,"userID":0,"title":"2002","content":"","contentDescribe":"汉服复兴运动的开端 2002年左右，针对当时人们普遍认为唐装（即满式服装旗袍、马褂）是汉族服装的状况，不少有识之士，力求正本清源，致力于努力恢复满清之前的汉族传","type":0,"createTime":"2018-09-12 15:16"},{"hanfuHistoryID":20,"userID":0,"title":"2009-2010","content":"","contentDescribe":"2009年1月16日，百度汉服吧发布\u201c已丑牛年除夕\u2018我给汉服的祝福\u2019视频征集\u201d活动，建议大家穿上汉服，以\u201c我是谁谁、现在在哪里\u201d为内容，向大家送上新年祝福。20","type":0,"createTime":"2018-09-12 15:20"},{"hanfuHistoryID":21,"userID":0,"title":"2011-2013","content":"","contentDescribe":"2011年2月3日（庚寅年初一）23点，首届汉服春晚在优酷网站上发布，内容涉及汉舞、国画、诗词、刀剑等多方面，并在 YouTube和优酷网站上发布了配有字幕的英","type":0,"createTime":"2018-09-12 15:22"},{"hanfuHistoryID":22,"userID":0,"title":"2014\u20142016","content":"","contentDescribe":"2014年8月5日，中央电视台记录频道播出汉服纪录片《矢志青春》，这是第一部讲述汉服复兴故事的纪录片，以一名汉服爱好者个人视角，记录其日常生活，片尾这段话在网上","type":0,"createTime":"2018-09-12 15:31"},{"hanfuHistoryID":23,"userID":0,"title":"2017","content":"","contentDescribe":"2017年1月汉服同袍出战《中国诗词大会》。在2017年春，央视举办的《中国诗词大会》中，有多达十数位同袍着汉服参赛，并取得优异成绩。 2017年5月中旬《匠人","type":0,"createTime":"2018-09-12 15:33"},{"hanfuHistoryID":24,"userID":0,"title":"2018","content":"","contentDescribe":"2018年3月2日为响应\u201c一带一路\u201d政策、加强中意双边合作、促进两国经济发展、打造中意商业文化交流的平台，近日，2018首届中意文化年在意大利洛特拉诺市举办，这","type":0,"createTime":"2018-11-29 10:17"}]
     */

    private StatusBean status;
    private List<DataBean> data;

    public StatusBean getStatus() {
        return status;
    }

    public void setStatus(StatusBean status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class StatusBean {
        /**
         * statusCode : 100
         * message : 请求成功！
         * serverTime : 2020-08-19 15:53:39
         */

        private int statusCode;
        private String message;
        private String serverTime;

        public int getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(int statusCode) {
            this.statusCode = statusCode;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getServerTime() {
            return serverTime;
        }

        public void setServerTime(String serverTime) {
            this.serverTime = serverTime;
        }
    }

    public static class DataBean {
        /**
         * hanfuHistoryID : 18
         * userID : 0
         * title : 2002
         * content :
         * contentDescribe : 汉服复兴运动的开端 2002年左右，针对当时人们普遍认为唐装（即满式服装旗袍、马褂）是汉族服装的状况，不少有识之士，力求正本清源，致力于努力恢复满清之前的汉族传
         * type : 0
         * createTime : 2018-09-12 15:16
         */

        private int hanfuHistoryID;
        private int userID;
        private String title;
        private String content;
        private String contentDescribe;
        private int type;
        private String createTime;

        public int getHanfuHistoryID() {
            return hanfuHistoryID;
        }

        public void setHanfuHistoryID(int hanfuHistoryID) {
            this.hanfuHistoryID = hanfuHistoryID;
        }

        public int getUserID() {
            return userID;
        }

        public void setUserID(int userID) {
            this.userID = userID;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getContentDescribe() {
            return contentDescribe;
        }

        public void setContentDescribe(String contentDescribe) {
            this.contentDescribe = contentDescribe;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }
}
