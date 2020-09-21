package com.qutu.talk.bean;

import java.util.List;

public class MiLiSZJiLuBean {

    /**
     * code : 1
     * message : 请求成功
     * data : [{"id":8,"skill_apply_id":1,"order_no":"GM20191114153852783465","user_id":1103,"god_id":1100002,"status":2,"skill_id":1,"start_time":1575626913,"num":1,"remarks":"求带","price":5,"unit":"局","total_price":5,"fee":"0.50","real_price":"4.50","refund":"0.00","pay_type":3,"is_first":0,"is_discuss":0,"addtime":1573717132,"paytime":1573717240,"refusetime":"","finishtime":"","skill_img":"http://tp5_test.miniyuyin.cn/upload/game/logo/wzry.png","skill_name":"王者荣耀","user_name":"呼噜娃12","headimgurl":"http://tp5_test.miniyuyin.cn/upload/avatar/20191021/15716509366195.jpg","status_text":"待接单"},{"id":6,"skill_apply_id":1,"order_no":"GM20191112113138445813","user_id":1103,"god_id":1100025,"status":5,"skill_id":1,"start_time":1573444800,"num":1,"remarks":"","price":5,"unit":"局","total_price":5,"fee":"0.50","real_price":"4.50","refund":"0.00","pay_type":3,"is_first":0,"is_discuss":1,"addtime":1573529498,"paytime":1573537087,"refusetime":"","finishtime":1573546960,"skill_img":"http://tp5_test.miniyuyin.cn/upload/game/logo/wzry.png","skill_name":"王者荣耀","user_name":"123456","headimgurl":"http://tp5_test.miniyuyin.cn/upload/avatar/20191024/15718942292819.png","status_text":"已完成"},{"id":3,"skill_apply_id":1,"order_no":"GM20191112113138445810","user_id":1103,"god_id":1100025,"status":3,"skill_id":1,"start_time":1573526918,"num":1,"remarks":"","price":5,"unit":"局","total_price":5,"fee":"0.50","real_price":"4.50","refund":"0.00","pay_type":3,"is_first":0,"is_discuss":0,"addtime":1573529498,"paytime":1573537087,"refusetime":"","finishtime":"","skill_img":"http://tp5_test.miniyuyin.cn/upload/game/logo/wzry.png","skill_name":"王者荣耀","user_name":"123456","headimgurl":"http://tp5_test.miniyuyin.cn/upload/avatar/20191024/15718942292819.png","status_text":"待服务"}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 8
         * skill_apply_id : 1
         * order_no : GM20191114153852783465
         * user_id : 1103
         * god_id : 1100002
         * status : 2
         * skill_id : 1
         * start_time : 1575626913
         * num : 1
         * remarks : 求带
         * price : 5
         * unit : 局
         * total_price : 5
         * fee : 0.50
         * real_price : 4.50
         * refund : 0.00
         * pay_type : 3
         * is_first : 0
         * is_discuss : 0
         * addtime : 1573717132
         * paytime : 1573717240
         * refusetime :
         * finishtime :
         * skill_img : http://tp5_test.miniyuyin.cn/upload/game/logo/wzry.png
         * skill_name : 王者荣耀
         * user_name : 呼噜娃12
         * headimgurl : http://tp5_test.miniyuyin.cn/upload/avatar/20191021/15716509366195.jpg
         * status_text : 待接单
         */

        private int id;
        private int skill_apply_id;
        private String order_no;
        private int user_id;
        private int god_id;
        private int status;
        private int skill_id;
        private String start_time;
        private int num;
        private String remarks;
        private String price;
        private String unit;
        private String total_price;
        private String fee;
        private String real_price;
        private String refund;
        private int pay_type;
        private int is_first;
        private int is_discuss;
        private String addtime;
        private String paytime;
        private String refusetime;
        private String finishtime;
        private String skill_img;
        private String skill_name;
        private String user_name;
        private String headimgurl;
        private String status_text;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSkill_apply_id() {
            return skill_apply_id;
        }

        public void setSkill_apply_id(int skill_apply_id) {
            this.skill_apply_id = skill_apply_id;
        }

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getGod_id() {
            return god_id;
        }

        public void setGod_id(int god_id) {
            this.god_id = god_id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getSkill_id() {
            return skill_id;
        }

        public void setSkill_id(int skill_id) {
            this.skill_id = skill_id;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getTotal_price() {
            return total_price;
        }

        public void setTotal_price(String total_price) {
            this.total_price = total_price;
        }

        public String getFee() {
            return fee;
        }

        public void setFee(String fee) {
            this.fee = fee;
        }

        public String getReal_price() {
            return real_price;
        }

        public void setReal_price(String real_price) {
            this.real_price = real_price;
        }

        public String getRefund() {
            return refund;
        }

        public void setRefund(String refund) {
            this.refund = refund;
        }

        public int getPay_type() {
            return pay_type;
        }

        public void setPay_type(int pay_type) {
            this.pay_type = pay_type;
        }

        public int getIs_first() {
            return is_first;
        }

        public void setIs_first(int is_first) {
            this.is_first = is_first;
        }

        public int getIs_discuss() {
            return is_discuss;
        }

        public void setIs_discuss(int is_discuss) {
            this.is_discuss = is_discuss;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getPaytime() {
            return paytime;
        }

        public void setPaytime(String paytime) {
            this.paytime = paytime;
        }

        public String getRefusetime() {
            return refusetime;
        }

        public void setRefusetime(String refusetime) {
            this.refusetime = refusetime;
        }

        public String getFinishtime() {
            return finishtime;
        }

        public void setFinishtime(String finishtime) {
            this.finishtime = finishtime;
        }

        public String getSkill_img() {
            return skill_img;
        }

        public void setSkill_img(String skill_img) {
            this.skill_img = skill_img;
        }

        public String getSkill_name() {
            return skill_name;
        }

        public void setSkill_name(String skill_name) {
            this.skill_name = skill_name;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(String headimgurl) {
            this.headimgurl = headimgurl;
        }

        public String getStatus_text() {
            return status_text;
        }

        public void setStatus_text(String status_text) {
            this.status_text = status_text;
        }
    }
}
