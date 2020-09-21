package com.example.fenghaogoxiangmu.bean.home.activitybean;

public class BrandRBean {

    /**
     * errno : 0
     * errmsg :
     * data : {"brand":{"id":1001038,"name":"Coach制造商","list_pic_url":"http://yanxuan.nosdn.127.net/1b1cc16135fd8467d40983f75f644127.png","simple_desc":"严选为制作高品质高颜值皮具配件，\n由Coach、MK等品牌制造商生产，\n由严选360度全程监制，给你带来优质皮具。","pic_url":"http://yanxuan.nosdn.127.net/c933a662bb79b2a47280363e35ab994b.png","sort_order":3,"is_show":1,"floor_price":49,"app_list_pic_url":"http://yanxuan.nosdn.127.net/1b1cc16135fd8467d40983f75f644127.png","is_new":1,"new_pic_url":"http://yanxuan.nosdn.127.net/b5cd73d3b310bad02539412f064d4ea1.jpg","new_sort_order":10}}
     */

    private int errno;
    private String errmsg;
    private DataBean data;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * brand : {"id":1001038,"name":"Coach制造商","list_pic_url":"http://yanxuan.nosdn.127.net/1b1cc16135fd8467d40983f75f644127.png","simple_desc":"严选为制作高品质高颜值皮具配件，\n由Coach、MK等品牌制造商生产，\n由严选360度全程监制，给你带来优质皮具。","pic_url":"http://yanxuan.nosdn.127.net/c933a662bb79b2a47280363e35ab994b.png","sort_order":3,"is_show":1,"floor_price":49,"app_list_pic_url":"http://yanxuan.nosdn.127.net/1b1cc16135fd8467d40983f75f644127.png","is_new":1,"new_pic_url":"http://yanxuan.nosdn.127.net/b5cd73d3b310bad02539412f064d4ea1.jpg","new_sort_order":10}
         */

        private BrandBean brand;

        public BrandBean getBrand() {
            return brand;
        }

        public void setBrand(BrandBean brand) {
            this.brand = brand;
        }

        public static class BrandBean {
            /**
             * id : 1001038
             * name : Coach制造商
             * list_pic_url : http://yanxuan.nosdn.127.net/1b1cc16135fd8467d40983f75f644127.png
             * simple_desc : 严选为制作高品质高颜值皮具配件，
             由Coach、MK等品牌制造商生产，
             由严选360度全程监制，给你带来优质皮具。
             * pic_url : http://yanxuan.nosdn.127.net/c933a662bb79b2a47280363e35ab994b.png
             * sort_order : 3
             * is_show : 1
             * floor_price : 49
             * app_list_pic_url : http://yanxuan.nosdn.127.net/1b1cc16135fd8467d40983f75f644127.png
             * is_new : 1
             * new_pic_url : http://yanxuan.nosdn.127.net/b5cd73d3b310bad02539412f064d4ea1.jpg
             * new_sort_order : 10
             */

            private int id;
            private String name;
            private String list_pic_url;
            private String simple_desc;
            private String pic_url;
            private int sort_order;
            private int is_show;
            private int floor_price;
            private String app_list_pic_url;
            private int is_new;
            private String new_pic_url;
            private int new_sort_order;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getList_pic_url() {
                return list_pic_url;
            }

            public void setList_pic_url(String list_pic_url) {
                this.list_pic_url = list_pic_url;
            }

            public String getSimple_desc() {
                return simple_desc;
            }

            public void setSimple_desc(String simple_desc) {
                this.simple_desc = simple_desc;
            }

            public String getPic_url() {
                return pic_url;
            }

            public void setPic_url(String pic_url) {
                this.pic_url = pic_url;
            }

            public int getSort_order() {
                return sort_order;
            }

            public void setSort_order(int sort_order) {
                this.sort_order = sort_order;
            }

            public int getIs_show() {
                return is_show;
            }

            public void setIs_show(int is_show) {
                this.is_show = is_show;
            }

            public int getFloor_price() {
                return floor_price;
            }

            public void setFloor_price(int floor_price) {
                this.floor_price = floor_price;
            }

            public String getApp_list_pic_url() {
                return app_list_pic_url;
            }

            public void setApp_list_pic_url(String app_list_pic_url) {
                this.app_list_pic_url = app_list_pic_url;
            }

            public int getIs_new() {
                return is_new;
            }

            public void setIs_new(int is_new) {
                this.is_new = is_new;
            }

            public String getNew_pic_url() {
                return new_pic_url;
            }

            public void setNew_pic_url(String new_pic_url) {
                this.new_pic_url = new_pic_url;
            }

            public int getNew_sort_order() {
                return new_sort_order;
            }

            public void setNew_sort_order(int new_sort_order) {
                this.new_sort_order = new_sort_order;
            }
        }
    }
}
