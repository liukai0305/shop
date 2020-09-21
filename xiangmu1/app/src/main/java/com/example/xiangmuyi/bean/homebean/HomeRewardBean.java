package com.example.xiangmuyi.bean.homebean;

import java.util.List;

public class HomeRewardBean {


    /**
     * status : {"statusCode":100,"message":"请求成功！","serverTime":"2020-08-04 15:52:04"}
     * data : {"countNumber":2,"counts":{},"list":[{"userID":293281,"peopleNearby":0,"pullWires":0,"activityShow":0,"id":0,"rankName":"","level":3,"rankType":0,"typeName":"","rank":0,"expPrefix":0,"expSuffix":0,"ignb":0,"nickName":"元君","headUrl":"https://tpcdn.whfpsoft.com:443/File/headPhoto/20200718/30c2183ee2d1868f43f1c58790394e95.jpg","sex":"1","rewardid":999,"title":"","content":"小哥哥，小姐姐们，空间第一个动态点一个💟，【点进去点大爱心要不然呢点不上谢谢了麻烦了】","picurl":"","cutofftime":"2020-08-07 10:56:12","rewardmoney":10,"address":"","limitday":"","createtime":"2020-07-31 10:56:11","state":"1","longititude":0,"latitude":0,"acceptPerson":"","fileBeans":[],"joinCount":27,"userList":[],"surplusDate":"还剩2天"},{"userID":293281,"peopleNearby":0,"pullWires":0,"activityShow":0,"id":0,"rankName":"","level":3,"rankType":0,"typeName":"","rank":0,"expPrefix":0,"expSuffix":0,"ignb":0,"nickName":"元君","headUrl":"https://tpcdn.whfpsoft.com:443/File/headPhoto/20200718/30c2183ee2d1868f43f1c58790394e95.jpg","sex":"1","rewardid":998,"title":"","content":"主页置顶帖点个💓💓💓💓💓💓💓💓💓💓💓💓💓💓谢谢，小哥哥，小姐姐们。","picurl":"","cutofftime":"2020-08-07 07:11:38","rewardmoney":10,"address":"","limitday":"","createtime":"2020-07-31 07:11:37","state":"1","longititude":0,"latitude":0,"acceptPerson":"","fileBeans":[],"joinCount":15,"userList":[],"surplusDate":"还剩2天"}]}
     */

    private StatusBean status;
    private DataBean data;

    public StatusBean getStatus() {
        return status;
    }

    public void setStatus(StatusBean status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class StatusBean {
        /**
         * statusCode : 100
         * message : 请求成功！
         * serverTime : 2020-08-04 15:52:04
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
         * countNumber : 2
         * counts : {}
         * list : [{"userID":293281,"peopleNearby":0,"pullWires":0,"activityShow":0,"id":0,"rankName":"","level":3,"rankType":0,"typeName":"","rank":0,"expPrefix":0,"expSuffix":0,"ignb":0,"nickName":"元君","headUrl":"https://tpcdn.whfpsoft.com:443/File/headPhoto/20200718/30c2183ee2d1868f43f1c58790394e95.jpg","sex":"1","rewardid":999,"title":"","content":"小哥哥，小姐姐们，空间第一个动态点一个💟，【点进去点大爱心要不然呢点不上谢谢了麻烦了】","picurl":"","cutofftime":"2020-08-07 10:56:12","rewardmoney":10,"address":"","limitday":"","createtime":"2020-07-31 10:56:11","state":"1","longititude":0,"latitude":0,"acceptPerson":"","fileBeans":[],"joinCount":27,"userList":[],"surplusDate":"还剩2天"},{"userID":293281,"peopleNearby":0,"pullWires":0,"activityShow":0,"id":0,"rankName":"","level":3,"rankType":0,"typeName":"","rank":0,"expPrefix":0,"expSuffix":0,"ignb":0,"nickName":"元君","headUrl":"https://tpcdn.whfpsoft.com:443/File/headPhoto/20200718/30c2183ee2d1868f43f1c58790394e95.jpg","sex":"1","rewardid":998,"title":"","content":"主页置顶帖点个💓💓💓💓💓💓💓💓💓💓💓💓💓💓谢谢，小哥哥，小姐姐们。","picurl":"","cutofftime":"2020-08-07 07:11:38","rewardmoney":10,"address":"","limitday":"","createtime":"2020-07-31 07:11:37","state":"1","longititude":0,"latitude":0,"acceptPerson":"","fileBeans":[],"joinCount":15,"userList":[],"surplusDate":"还剩2天"}]
         */

        private int countNumber;
        private CountsBean counts;
        private List<ListBean> list;

        public int getCountNumber() {
            return countNumber;
        }

        public void setCountNumber(int countNumber) {
            this.countNumber = countNumber;
        }

        public CountsBean getCounts() {
            return counts;
        }

        public void setCounts(CountsBean counts) {
            this.counts = counts;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class CountsBean {
        }

        public static class ListBean {
            /**
             * userID : 293281
             * peopleNearby : 0
             * pullWires : 0
             * activityShow : 0
             * id : 0
             * rankName :
             * level : 3
             * rankType : 0
             * typeName :
             * rank : 0
             * expPrefix : 0
             * expSuffix : 0
             * ignb : 0
             * nickName : 元君
             * headUrl : https://tpcdn.whfpsoft.com:443/File/headPhoto/20200718/30c2183ee2d1868f43f1c58790394e95.jpg
             * sex : 1
             * rewardid : 999
             * title :
             * content : 小哥哥，小姐姐们，空间第一个动态点一个💟，【点进去点大爱心要不然呢点不上谢谢了麻烦了】
             * picurl :
             * cutofftime : 2020-08-07 10:56:12
             * rewardmoney : 10
             * address :
             * limitday :
             * createtime : 2020-07-31 10:56:11
             * state : 1
             * longititude : 0
             * latitude : 0
             * acceptPerson :
             * fileBeans : []
             * joinCount : 27
             * userList : []
             * surplusDate : 还剩2天
             */

            private int userID;
            private int peopleNearby;
            private int pullWires;
            private int activityShow;
            private int id;
            private String rankName;
            private int level;
            private int rankType;
            private String typeName;
            private int rank;
            private int expPrefix;
            private int expSuffix;
            private int ignb;
            private String nickName;
            private String headUrl;
            private String sex;
            private int rewardid;
            private String title;
            private String content;
            private String picurl;
            private String cutofftime;
            private int rewardmoney;
            private String address;
            private String limitday;
            private String createtime;
            private String state;
            private int longititude;
            private int latitude;
            private String acceptPerson;
            private int joinCount;
            private String surplusDate;
            private List<?> fileBeans;
            private List<?> userList;

            public int getUserID() {
                return userID;
            }

            public void setUserID(int userID) {
                this.userID = userID;
            }

            public int getPeopleNearby() {
                return peopleNearby;
            }

            public void setPeopleNearby(int peopleNearby) {
                this.peopleNearby = peopleNearby;
            }

            public int getPullWires() {
                return pullWires;
            }

            public void setPullWires(int pullWires) {
                this.pullWires = pullWires;
            }

            public int getActivityShow() {
                return activityShow;
            }

            public void setActivityShow(int activityShow) {
                this.activityShow = activityShow;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getRankName() {
                return rankName;
            }

            public void setRankName(String rankName) {
                this.rankName = rankName;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public int getRankType() {
                return rankType;
            }

            public void setRankType(int rankType) {
                this.rankType = rankType;
            }

            public String getTypeName() {
                return typeName;
            }

            public void setTypeName(String typeName) {
                this.typeName = typeName;
            }

            public int getRank() {
                return rank;
            }

            public void setRank(int rank) {
                this.rank = rank;
            }

            public int getExpPrefix() {
                return expPrefix;
            }

            public void setExpPrefix(int expPrefix) {
                this.expPrefix = expPrefix;
            }

            public int getExpSuffix() {
                return expSuffix;
            }

            public void setExpSuffix(int expSuffix) {
                this.expSuffix = expSuffix;
            }

            public int getIgnb() {
                return ignb;
            }

            public void setIgnb(int ignb) {
                this.ignb = ignb;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getHeadUrl() {
                return headUrl;
            }

            public void setHeadUrl(String headUrl) {
                this.headUrl = headUrl;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public int getRewardid() {
                return rewardid;
            }

            public void setRewardid(int rewardid) {
                this.rewardid = rewardid;
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

            public String getPicurl() {
                return picurl;
            }

            public void setPicurl(String picurl) {
                this.picurl = picurl;
            }

            public String getCutofftime() {
                return cutofftime;
            }

            public void setCutofftime(String cutofftime) {
                this.cutofftime = cutofftime;
            }

            public int getRewardmoney() {
                return rewardmoney;
            }

            public void setRewardmoney(int rewardmoney) {
                this.rewardmoney = rewardmoney;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getLimitday() {
                return limitday;
            }

            public void setLimitday(String limitday) {
                this.limitday = limitday;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public int getLongititude() {
                return longititude;
            }

            public void setLongititude(int longititude) {
                this.longititude = longititude;
            }

            public int getLatitude() {
                return latitude;
            }

            public void setLatitude(int latitude) {
                this.latitude = latitude;
            }

            public String getAcceptPerson() {
                return acceptPerson;
            }

            public void setAcceptPerson(String acceptPerson) {
                this.acceptPerson = acceptPerson;
            }

            public int getJoinCount() {
                return joinCount;
            }

            public void setJoinCount(int joinCount) {
                this.joinCount = joinCount;
            }

            public String getSurplusDate() {
                return surplusDate;
            }

            public void setSurplusDate(String surplusDate) {
                this.surplusDate = surplusDate;
            }

            public List<?> getFileBeans() {
                return fileBeans;
            }

            public void setFileBeans(List<?> fileBeans) {
                this.fileBeans = fileBeans;
            }

            public List<?> getUserList() {
                return userList;
            }

            public void setUserList(List<?> userList) {
                this.userList = userList;
            }
        }
    }
}
