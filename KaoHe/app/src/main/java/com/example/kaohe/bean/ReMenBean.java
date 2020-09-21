package com.example.kaohe.bean;

import java.util.List;

public class ReMenBean {

    /**
     * status : {"statusCode":100,"message":"璇锋眰鎴愬姛锛�","serverTime":"2020-08-24 21:02:07"}
     * data : [{"labelId":499,"name":"绀艰。鍗庡姹夋湇瓒呮ā澶ц禌","imageUrl":"https://tpcdn.whfpsoft.com:443/File/labels/20200731/c50cc9f1da3c1a1bcb0537b270706ae2.png","depict":"璁╁崕澶忓缇庡紩棰員鍙版椂灏氾紝璁╂眽鏈嶆枃鍖栭噸褰掑ぇ浼楄閲�","labelTypeName":"","useTime":111,"attentionNum":52,"type":1,"isAttention":0,"createTime":"","number":0,"userID":0,"isHot":0,"isRecommend":0},{"labelId":513,"name":"绗簩灞婂悓琚嶇孩绾胯妭","imageUrl":"https://tpcdn.whfpsoft.com:443/File/20200817/7c4f4161850d5095c43f789efa41db82.png","depict":"鐢ㄤ竷澶╂椂闂撮倐閫呬竴鍦烘氮婕亱鐖憋紝鏉ヨ繖閲屾壘鍒板績浠殑琚嶅瓙鍚","labelTypeName":"","useTime":299,"attentionNum":41,"type":1,"isAttention":0,"createTime":"","number":0,"userID":0,"isHot":0,"isRecommend":0},{"labelId":2,"name":"姹夋湇鏃ュ父","imageUrl":"https://tpcdn.whfpsoft.com:443/File/20191219/f1465de4fc4b7891229472a882ddae16.png","depict":"鍘熷垱姹夋湇椹跨珯锛屾杩庡悓琚嶄滑涓\u20ac璧峰垎浜眽鏈嶆棩甯哥敓娲伙紒","labelTypeName":"","useTime":8331,"attentionNum":1210,"type":0,"isAttention":0,"createTime":"","number":0,"userID":0,"isHot":0,"isRecommend":0},{"labelId":15,"name":"濂界墿瀹夊埄","imageUrl":"https://tpcdn.whfpsoft.com:443/File/20191219/f8913cc0882fca43e3203e4c50baaa8b.png","depict":"鎺ㄨ崘鍦堥噷鐨勫ソ鐗╋紝鐩樼偣閭ｄ簺濂界湅鍒板摥鐨勭鍣�...","labelTypeName":"","useTime":4813,"attentionNum":1147,"type":0,"isAttention":0,"createTime":"","number":0,"userID":0,"isHot":0,"isRecommend":0},{"labelId":72,"name":"姣忔棩鍚岃鎵撳崱","imageUrl":"https://tpcdn.whfpsoft.com:443/File/20191219/bd94c6318c6e347beeafd4337ff07d9a.png","depict":"甯︿笂鏈瘽棰樻檼鏅掍綘姣忓ぉ鏉ュ悓琚嶆墦鍗＄殑璁板綍鍚璁板緱杩炵画绛惧埌锛岄摐閽辨洿澶氬摝~","labelTypeName":"","useTime":2679,"attentionNum":1057,"type":0,"isAttention":0,"createTime":"","number":0,"userID":0,"isHot":0,"isRecommend":0},{"labelId":14,"name":"姹夋湇璁捐","imageUrl":"https://tpcdn.whfpsoft.com:443/File/20191219/7ef28a7ed92629c62623e44fccbab376.png","depict":"姹夋湇璁捐鍥俱\u20ac佽璁¤剳娲炴眹鎬汇\u20ac佽璁¤祫鏂欏涔犳帰璁�","labelTypeName":"","useTime":896,"attentionNum":1215,"type":0,"isAttention":0,"createTime":"","number":0,"userID":0,"isHot":0,"isRecommend":0}]
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
         * message : 璇锋眰鎴愬姛锛�
         * serverTime : 2020-08-24 21:02:07
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
         * labelId : 499
         * name : 绀艰。鍗庡姹夋湇瓒呮ā澶ц禌
         * imageUrl : https://tpcdn.whfpsoft.com:443/File/labels/20200731/c50cc9f1da3c1a1bcb0537b270706ae2.png
         * depict : 璁╁崕澶忓缇庡紩棰員鍙版椂灏氾紝璁╂眽鏈嶆枃鍖栭噸褰掑ぇ浼楄閲�
         * labelTypeName :
         * useTime : 111
         * attentionNum : 52
         * type : 1
         * isAttention : 0
         * createTime :
         * number : 0
         * userID : 0
         * isHot : 0
         * isRecommend : 0
         */

        private int labelId;
        private String name;
        private String imageUrl;
        private String depict;
        private String labelTypeName;
        private int useTime;
        private int attentionNum;
        private int type;
        private int isAttention;
        private String createTime;
        private int number;
        private int userID;
        private int isHot;
        private int isRecommend;

        public int getLabelId() {
            return labelId;
        }

        public void setLabelId(int labelId) {
            this.labelId = labelId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getDepict() {
            return depict;
        }

        public void setDepict(String depict) {
            this.depict = depict;
        }

        public String getLabelTypeName() {
            return labelTypeName;
        }

        public void setLabelTypeName(String labelTypeName) {
            this.labelTypeName = labelTypeName;
        }

        public int getUseTime() {
            return useTime;
        }

        public void setUseTime(int useTime) {
            this.useTime = useTime;
        }

        public int getAttentionNum() {
            return attentionNum;
        }

        public void setAttentionNum(int attentionNum) {
            this.attentionNum = attentionNum;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getIsAttention() {
            return isAttention;
        }

        public void setIsAttention(int isAttention) {
            this.isAttention = isAttention;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getUserID() {
            return userID;
        }

        public void setUserID(int userID) {
            this.userID = userID;
        }

        public int getIsHot() {
            return isHot;
        }

        public void setIsHot(int isHot) {
            this.isHot = isHot;
        }

        public int getIsRecommend() {
            return isRecommend;
        }

        public void setIsRecommend(int isRecommend) {
            this.isRecommend = isRecommend;
        }
    }
}
