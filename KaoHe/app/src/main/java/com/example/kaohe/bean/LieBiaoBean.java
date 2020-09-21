package com.example.kaohe.bean;

import java.util.List;

public class LieBiaoBean {
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
         * countNumber : 4000
         * dynamics : [{"userID":100001,"peopleNearby":0,"pullWires":0,"activityShow":0,"id":0,"rankName":"","level":8,"rankType":0,"typeName":"","rank":0,"expPrefix":0,"expSuffix":0,"ignb":2,"nickName":"官方小袍","headUrl":"https://tpcdn.whfpsoft.com:443/File/headPhoto/20200404/fa5134d048f08eff6f3617dc35d3a836.jpg","sex":"1","repeatId":0,"repeatUserId":0,"isRepeat":0,"repeatContent":"","repeatNumber":0,"suggestion":"","scene":"","isHomeActivity":0,"postId":87182,"labels":"","title":"","content":"官宣！欢迎@一叶馨香汉服 入驻同袍APP商城！\n\n成立于2018年的一叶馨香汉服，作为一家集设计、生产、销售服务于一体的专业性的汉服品牌，深入了解客户群体和需求，穿衣习惯，从服饰的色彩、面料、款式和搭配等多方面的考虑，做客户满意的可爱少女风的汉服。一叶馨香汉服以传承华夏之美为己任，大力传播汉民族的传统服饰\u2014\u2014汉服。\n\n还不知道在哪里去找剁手一叶家汉服？打开http://tongpao.whfpsoft.com:8095/shop/100?isReadOnly=1\n或者在商城搜索一叶馨香汉服加购！\n\n来说说，你最想拥有一叶家的：__________？\n","allPostsNumber":"","location":"","albumID":0,"photoAlbumName":"","photoAlbumCover":"","longitude":0,"latitude":0,"isVisible":1,"shortUrl":"","installedAccording":"","tapeLength":0,"outboundPersonnel":"","clothing":"","shoot":"","type":1,"createTime":"2020-08-24 10:24","playTourNumber":0,"likeNumber":11,"commentNumber":0,"postLike":0,"isCollected":0,"relation":1,"cover":"","videoPath":"","videoWidth":1280,"videoHeight":720,"rewards":[],"images":[{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200824/084d0fffcd96726f002d360a265b6db2.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":507,"imageHeight":800,"fileType":0}],"voteOptions":[],"likeDetails":[]},{"userID":304429,"peopleNearby":0,"pullWires":0,"activityShow":0,"id":0,"rankName":"","level":3,"rankType":0,"typeName":"","rank":0,"expPrefix":0,"expSuffix":0,"ignb":0,"nickName":"妆造私塾","headUrl":"https://tpcdn.whfpsoft.com:443/File/headPhoto/20200422/162290ce1152f361d67c5151060dedfb.jpg","sex":"1","repeatId":0,"repeatUserId":0,"isRepeat":0,"repeatContent":"","repeatNumber":0,"suggestion":"","scene":"","isHomeActivity":0,"postId":87274,"labels":"妆造私塾","title":"","content":"【八款无假发包发型教程】#妆造私塾# \n巨简单的八款发型教程！单马尾基础的无假发包发型，发量多的妹子大胆造作！\n\n授权转载自：老八捌，纯属交流分享，欢迎来交作业哦~ ","allPostsNumber":"","location":"","albumID":0,"photoAlbumName":"","photoAlbumCover":"","longitude":0,"latitude":0,"isVisible":0,"shortUrl":"","installedAccording":"","tapeLength":0,"outboundPersonnel":"","clothing":"","shoot":"","type":4,"createTime":"2020-08-24 17:11","playTourNumber":0,"likeNumber":10,"commentNumber":0,"postLike":0,"isCollected":0,"relation":0,"cover":"https://tpcdn.whfpsoft.com:443/File/backstage/20200824/856949812550352.jpg","videoPath":"https://tpcdn.whfpsoft.com:443/File/backstage/20200824/9009254976022144.mp4","videoWidth":640,"videoHeight":360,"rewards":[],"images":[],"voteOptions":[],"likeDetails":[]},{"userID":192219,"peopleNearby":0,"pullWires":0,"activityShow":0,"id":0,"rankName":"","level":2,"rankType":0,"typeName":"","rank":0,"expPrefix":0,"expSuffix":0,"ignb":0,"nickName":"抖音袍可爱","headUrl":"https://tpcdn.whfpsoft.com:443/File/headPhoto/20200729/680c49a3494ce7d3d6a1cb7f15b4e3b6.jpg","sex":"1","repeatId":0,"repeatUserId":0,"isRepeat":0,"repeatContent":"","repeatNumber":0,"suggestion":"","scene":"","isHomeActivity":0,"postId":87264,"labels":"","title":"","content":"【汉服之美\u2014汉服形制小科普】#抖音袍可爱#\n刚入坑的萌新袍袍们，分不清形制，马了这篇~来自@安迪温斯顿","allPostsNumber":"","location":"","albumID":0,"photoAlbumName":"","photoAlbumCover":"","longitude":0,"latitude":0,"isVisible":0,"shortUrl":"","installedAccording":"","tapeLength":0,"outboundPersonnel":"","clothing":"","shoot":"","type":4,"createTime":"2020-08-24 16:13","playTourNumber":0,"likeNumber":10,"commentNumber":0,"postLike":0,"isCollected":0,"relation":0,"cover":"https://tpcdn.whfpsoft.com:443/File/backstage/20200824/6577858543436192.png","videoPath":"https://tpcdn.whfpsoft.com:443/File/backstage/20200824/8024357874795586.mp4","videoWidth":709,"videoHeight":301,"rewards":[],"images":[],"voteOptions":[],"likeDetails":[]},{"userID":304403,"peopleNearby":0,"pullWires":0,"activityShow":0,"id":0,"rankName":"","level":3,"rankType":0,"typeName":"","rank":0,"expPrefix":0,"expSuffix":0,"ignb":0,"nickName":"喂！上新啦！","headUrl":"https://tpcdn.whfpsoft.com:443/File/headPhoto/20200422/e18b0624c996f00614cf23e4a26d09b1.jpg","sex":"1","repeatId":0,"repeatUserId":0,"isRepeat":0,"repeatContent":"","repeatNumber":0,"suggestion":"","scene":"","isHomeActivity":0,"postId":87176,"labels":"喂！上新啦！,白菜汉服上新","title":"","content":"\n#喂！上新啦！# #白菜汉服上新#\n店铺：芷蘅间原创汉服\n上新时间：8-16\n上新内容：\n立领对襟窄袖长衫 89\n \n纯属整理分享，请谨慎种草~","allPostsNumber":"","location":"","albumID":0,"photoAlbumName":"","photoAlbumCover":"","longitude":0,"latitude":0,"isVisible":0,"shortUrl":"","installedAccording":"","tapeLength":0,"outboundPersonnel":"","clothing":"","shoot":"","type":1,"createTime":"2020-08-24 09:42","playTourNumber":0,"likeNumber":13,"commentNumber":0,"postLike":0,"isCollected":0,"relation":0,"cover":"","videoPath":"","videoWidth":1280,"videoHeight":720,"rewards":[],"images":[{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200824/2a78e2e3c66073b8ea1f608b7c6789cd.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":828,"imageHeight":620,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200824/730c8b43c9d4b6125d4a9d716520c1a2.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":828,"imageHeight":620,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200824/5ec7f4f1e44860269c3afde67cf627ff.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":828,"imageHeight":620,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200824/8084745acc09751f31039e4e1e745e74.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":828,"imageHeight":620,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200824/16e849c9fdb9246c590b6058acf2cd43.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":828,"imageHeight":620,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200824/92cf8ef063b338f25356fe966a5b3a1a.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":828,"imageHeight":620,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200824/70193ccaadf04ca38a7c8d53fc1b98b8.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":828,"imageHeight":620,"fileType":0}],"voteOptions":[],"likeDetails":[]},{"userID":294151,"peopleNearby":0,"pullWires":0,"activityShow":0,"id":0,"rankName":"","level":3,"rankType":0,"typeName":"","rank":0,"expPrefix":0,"expSuffix":0,"ignb":0,"nickName":"安迪温斯顿","headUrl":"https://tpcdn.whfpsoft.com:443/File/headPhoto/20200403/87457b1fc940837c07910826e9a47e8d.jpg","sex":"0","repeatId":0,"repeatUserId":0,"isRepeat":0,"repeatContent":"","repeatNumber":0,"suggestion":"","scene":"","isHomeActivity":0,"postId":87134,"labels":"","title":"小视频","content":"日常练习","allPostsNumber":"","location":"","albumID":0,"photoAlbumName":"","photoAlbumCover":"","longitude":0,"latitude":0,"isVisible":0,"shortUrl":"","installedAccording":"","tapeLength":0,"outboundPersonnel":"","clothing":"","shoot":"","type":4,"createTime":"2020-08-23 21:04","playTourNumber":0,"likeNumber":10,"commentNumber":0,"postLike":0,"isCollected":0,"relation":0,"cover":"https://tpcdn.whfpsoft.com:443/File/cover/20200823/1b1b206bc523eef6201f4c90989dbf80.jpeg","videoPath":"https://tpcdn.whfpsoft.com:443/File/video/20200823/311d4d5bef9a9a7bf5c52905d6e36687.mp4","videoWidth":1280,"videoHeight":720,"rewards":[],"images":[],"voteOptions":[],"likeDetails":[]},{"userID":304429,"peopleNearby":0,"pullWires":0,"activityShow":0,"id":0,"rankName":"","level":3,"rankType":0,"typeName":"","rank":0,"expPrefix":0,"expSuffix":0,"ignb":0,"nickName":"妆造私塾","headUrl":"https://tpcdn.whfpsoft.com:443/File/headPhoto/20200422/162290ce1152f361d67c5151060dedfb.jpg","sex":"1","repeatId":0,"repeatUserId":0,"isRepeat":0,"repeatContent":"","repeatNumber":0,"suggestion":"","scene":"","isHomeActivity":0,"postId":87096,"labels":"妆造私塾","title":"","content":"【敦煌〈净土之旅〉妆造欣赏】#妆造私塾#\n昨天那组图的另一位白衣小哥，你们的柱子哥！是神仙本仙吧，是吧是吧！\n\n图片授权转载自wb :小何力，纯属交流分享，欢迎来交作业哦~","allPostsNumber":"","location":"","albumID":0,"photoAlbumName":"","photoAlbumCover":"","longitude":0,"latitude":0,"isVisible":0,"shortUrl":"","installedAccording":"","tapeLength":0,"outboundPersonnel":"","clothing":"","shoot":"","type":1,"createTime":"2020-08-23 17:18","playTourNumber":0,"likeNumber":30,"commentNumber":3,"postLike":0,"isCollected":0,"relation":0,"cover":"","videoPath":"","videoWidth":1280,"videoHeight":720,"rewards":[],"images":[{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200823/5fc8545e0875531c4b3acdc6bc20af3d.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":828,"imageHeight":1225,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200823/ecbf45d70402aaca1c179a32441332b1.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":828,"imageHeight":1241,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200823/3a8ecc78d13e97449ede7ca06a3cbbdd.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":828,"imageHeight":569,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200823/b0839b90b581a01ab08057edc88e6f34.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":828,"imageHeight":552,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200823/4ecb0f212c0cca2377a78bd8fdd462d3.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":828,"imageHeight":1241,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200823/2ee59610906c6b97e301e87fe0b198ac.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":828,"imageHeight":569,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200823/4df15c9dce8d01a6c61f3dbc06bf15c5.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":828,"imageHeight":1279,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200823/14c20fbeee62246e1a7e3552848957d9.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":828,"imageHeight":616,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200823/720202383067a61db123174a72050412.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":828,"imageHeight":570,"fileType":0}],"voteOptions":[],"likeDetails":[]},{"userID":304431,"peopleNearby":0,"pullWires":0,"activityShow":0,"id":0,"rankName":"","level":3,"rankType":0,"typeName":"","rank":0,"expPrefix":0,"expSuffix":0,"ignb":0,"nickName":"趣谈笔录","headUrl":"https://tpcdn.whfpsoft.com:443/File/headPhoto/20200422/9e02ba136dda3b1cf1887694e96cb0a5.jpg","sex":"0","repeatId":0,"repeatUserId":0,"isRepeat":0,"repeatContent":"","repeatNumber":0,"suggestion":"","scene":"","isHomeActivity":0,"postId":87019,"labels":"历史趣知识,文物之美","title":"","content":"【古代也有玻璃吗？】#历史趣知识# #文物之美# \r自然是有的，不过跟你印象中的透明玻璃是不一样的。\r中国的早期玻璃被称作铅钡玻璃，色彩浑浊而且容易碎，不过也挺好看就是了。而我们现在用的西方玻璃是钠钙玻璃，透明度高，耐高温。\r\r在古代，玻璃常常被称作琉璃，琉璃包括上面说的这两种玻璃，也包括被古人误认为是玻璃的天然矿物或者玉石。\r\r魏晋南北朝时，西方的玻璃技术传入，早期不透明的铅钡玻璃逐渐地被钠钙玻璃取代。元末明初，彩色玻璃发展起来，又称之为料器，在清代时达到一个发展高峰，现存的文物色彩非常美丽。","allPostsNumber":"","location":"","albumID":0,"photoAlbumName":"","photoAlbumCover":"","longitude":0,"latitude":0,"isVisible":0,"shortUrl":"","installedAccording":"","tapeLength":0,"outboundPersonnel":"","clothing":"","shoot":"","type":1,"createTime":"2020-08-23 11:08","playTourNumber":0,"likeNumber":27,"commentNumber":1,"postLike":0,"isCollected":0,"relation":0,"cover":"","videoPath":"","videoWidth":1280,"videoHeight":720,"rewards":[],"images":[{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200823/61645e154fc02c91e2382f872a28fa80.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":828,"imageHeight":523,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200823/36a8917dfae7d442c95c1b2b20d08170.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":828,"imageHeight":1095,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200823/8c7b2a6a7e4cec129a55471f7d3f10bb.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":828,"imageHeight":536,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200823/846de49d794f2096bb3ec0e66d5e546b.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":828,"imageHeight":1242,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200823/7e3709c268ad8e7cd0e5057bc35500aa.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":828,"imageHeight":536,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200823/e7594829f071ad069096e18ac46cd979.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":828,"imageHeight":1242,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200823/9071cdc0c174af84d58d2296c34f4853.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":828,"imageHeight":552,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200823/f3f4dec9cbd8a71b92fd29ce91e470f1.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":828,"imageHeight":1192,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200823/368902b1b758da19f063c0cd5fea60da.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":828,"imageHeight":1242,"fileType":0}],"voteOptions":[],"likeDetails":[]},{"userID":304403,"peopleNearby":0,"pullWires":0,"activityShow":0,"id":0,"rankName":"","level":3,"rankType":0,"typeName":"","rank":0,"expPrefix":0,"expSuffix":0,"ignb":0,"nickName":"喂！上新啦！","headUrl":"https://tpcdn.whfpsoft.com:443/File/headPhoto/20200422/e18b0624c996f00614cf23e4a26d09b1.jpg","sex":"1","repeatId":0,"repeatUserId":0,"isRepeat":0,"repeatContent":"","repeatNumber":0,"suggestion":"","scene":"","isHomeActivity":0,"postId":86990,"labels":"喂！上新啦！","title":"","content":"#喂！上新啦！# \r店铺：时夏汉服\n上新时间：8-20\n上新内容：\r迟迟系列褙子套 全套169\n褙子 秘色、毛月、宫墙红、琉璃黄、黛灰五色 90\n宋抹 宫墙红、姜黄、玉白色、灰色四色 19.9\n百迭裙 红色渐变、粉绿渐变、黛灰色、蓝紫渐变、浅蓝渐变 90\r\r纯属整理分享，请谨慎种草~","allPostsNumber":"","location":"","albumID":0,"photoAlbumName":"","photoAlbumCover":"","longitude":0,"latitude":0,"isVisible":0,"shortUrl":"","installedAccording":"","tapeLength":0,"outboundPersonnel":"","clothing":"","shoot":"","type":1,"createTime":"2020-08-23 09:58","playTourNumber":0,"likeNumber":29,"commentNumber":0,"postLike":0,"isCollected":0,"relation":0,"cover":"","videoPath":"","videoWidth":1280,"videoHeight":720,"rewards":[],"images":[{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200823/96dd9ad6cb03a1b1794a3df592bfa304.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":750,"imageHeight":1125,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200823/b3464846b0e011e034a138a6b39e9ac0.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":750,"imageHeight":1125,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200823/e691c3898fe8b98dd902864d18655c77.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":750,"imageHeight":1125,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200823/f0dacf97caab91eb0184267963a43205.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":750,"imageHeight":1125,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200823/967b883b4655146ce8c23d42cda962db.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":750,"imageHeight":1125,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200823/c1098d99a2c519e941bf25aca80429b1.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":750,"imageHeight":500,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200823/34291a0a9a405024573d86ca53b793ea.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":750,"imageHeight":1125,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200823/ea0723d4720df04bcd035641bd7f0a0d.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":750,"imageHeight":1125,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200823/95571d7d15c85ab2a39dd2922c9e9081.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":750,"imageHeight":1125,"fileType":0}],"voteOptions":[],"likeDetails":[]},{"userID":381379,"peopleNearby":0,"pullWires":0,"activityShow":0,"id":0,"rankName":"","level":1,"rankType":0,"typeName":"","rank":0,"expPrefix":0,"expSuffix":0,"ignb":0,"nickName":"小呀嘛小疏雨","headUrl":"https://tpcdn.whfpsoft.com:443/File/headPhoto/20200806/23dec3a9c5ed282d6a29dfa3fa7eb830.0","sex":"1","repeatId":0,"repeatUserId":0,"isRepeat":0,"repeatContent":"","repeatNumber":0,"suggestion":"","scene":"","isHomeActivity":0,"postId":86967,"labels":"","title":"","content":"每个人的心里都有一座玻璃房。\n玻璃房里透着光，里面住着一位姑娘。","allPostsNumber":"","location":"","albumID":0,"photoAlbumName":"","photoAlbumCover":"","longitude":0,"latitude":0,"isVisible":0,"shortUrl":"","installedAccording":"","tapeLength":0,"outboundPersonnel":"","clothing":"","shoot":"","type":6,"createTime":"2020-08-22 23:23","playTourNumber":0,"likeNumber":29,"commentNumber":1,"postLike":0,"isCollected":0,"relation":0,"cover":"","videoPath":"","videoWidth":1280,"videoHeight":720,"rewards":[],"images":[{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200822/0350c48e747c36d4322d9502a2c1aa52.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":1280,"imageHeight":1075,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200822/e09c2e13a04300fc97cd158b230bd382.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":1280,"imageHeight":4000,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200822/3b6e110f892815186078cab2c3b1e3c5.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":1280,"imageHeight":5580,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200822/3fa6be84c83f5b69151d663366e99769.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":640,"imageHeight":971,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200822/2313ca2d894b0c61332f55fbb3c37166.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":640,"imageHeight":3224,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200822/be138de6c3c6c6cbf3112d0168146d68.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":640,"imageHeight":2567,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200822/4a8992eeccccbb9184626cce93bb75b2.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":1280,"imageHeight":3764,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200822/eaa8efc6306fed6795f79323e5b225b8.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":1280,"imageHeight":1040,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200822/13d75875093baafe6b0a598bb51eb13c.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":1259,"imageHeight":1011,"fileType":0}],"voteOptions":[],"likeDetails":[]},{"userID":381045,"peopleNearby":0,"pullWires":0,"activityShow":0,"id":0,"rankName":"","level":1,"rankType":0,"typeName":"","rank":0,"expPrefix":0,"expSuffix":0,"ignb":0,"nickName":"是暮色啊","headUrl":"https://tpcdn.whfpsoft.com:443/File/headPhoto/20200805/868bf8c476fea2345c5756a2450c792b.jpg","sex":"","repeatId":0,"repeatUserId":0,"isRepeat":0,"repeatContent":"","repeatNumber":0,"suggestion":"","scene":"","isHomeActivity":0,"postId":86945,"labels":"","title":"小视频","content":"汉服穿越久，越偏爱温柔的纯色系","allPostsNumber":"","location":"","albumID":0,"photoAlbumName":"","photoAlbumCover":"","longitude":0,"latitude":0,"isVisible":0,"shortUrl":"","installedAccording":"","tapeLength":0,"outboundPersonnel":"","clothing":"","shoot":"","type":4,"createTime":"2020-08-22 19:58","playTourNumber":0,"likeNumber":12,"commentNumber":0,"postLike":0,"isCollected":0,"relation":0,"cover":"https://tpcdn.whfpsoft.com:443/File/cover/20200822/e7d1ca6546b3f428789bd23c46870d7a.jpeg","videoPath":"https://tpcdn.whfpsoft.com:443/File/video/20200822/1a17b77176737167accc7d42dd4ad3d7.mp4","videoWidth":1088,"videoHeight":1920,"rewards":[],"images":[],"voteOptions":[],"likeDetails":[]},{"userID":304429,"peopleNearby":0,"pullWires":0,"activityShow":0,"id":0,"rankName":"","level":3,"rankType":0,"typeName":"","rank":0,"expPrefix":0,"expSuffix":0,"ignb":0,"nickName":"妆造私塾","headUrl":"https://tpcdn.whfpsoft.com:443/File/headPhoto/20200422/162290ce1152f361d67c5151060dedfb.jpg","sex":"1","repeatId":0,"repeatUserId":0,"isRepeat":0,"repeatContent":"","repeatNumber":0,"suggestion":"","scene":"","isHomeActivity":0,"postId":86917,"labels":"妆造私塾","title":"","content":"【敦煌=引路==== 妆造欣赏】#妆造私塾# \n美得不像凡间人，像敦煌仙女下凡！Awsl，彩虹屁不够了，笔交给你们，你们来夸！\n \n图片授权转载自wb : 小何力，纯属交流分享，欢迎来交作业哦~","allPostsNumber":"","location":"","albumID":0,"photoAlbumName":"","photoAlbumCover":"","longitude":0,"latitude":0,"isVisible":0,"shortUrl":"","installedAccording":"","tapeLength":0,"outboundPersonnel":"","clothing":"","shoot":"","type":1,"createTime":"2020-08-22 17:02","playTourNumber":0,"likeNumber":24,"commentNumber":1,"postLike":0,"isCollected":0,"relation":0,"cover":"","videoPath":"","videoWidth":1280,"videoHeight":720,"rewards":[],"images":[{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200822/8ac160c21289811ed731b73998db664f.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":828,"imageHeight":345,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200822/33c75c165ae052370a84e56ef629362f.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":828,"imageHeight":552,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200822/bba4744decfc151f464eca50f04ce88a.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":828,"imageHeight":1301,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200822/1d7b4bd8f153117512436cac17260def.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":828,"imageHeight":552,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200822/fdc26f92de97aa163fb669e59e77479d.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":828,"imageHeight":1241,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200822/84229c704ab7d7a8247cc1f76a82b510.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":828,"imageHeight":1241,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200822/7445b2e677ef46337499251fcac09964.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":828,"imageHeight":1315,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200822/052d5d367929fee79c852f9ca8f86745.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":828,"imageHeight":552,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200822/78301a1783dd71a93c54228c1cb65dae.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":828,"imageHeight":1241,"fileType":0}],"voteOptions":[],"likeDetails":[]},{"userID":139243,"peopleNearby":0,"pullWires":0,"activityShow":0,"id":0,"rankName":"","level":3,"rankType":0,"typeName":"","rank":0,"expPrefix":0,"expSuffix":0,"ignb":0,"nickName":"羽月司衣局","headUrl":"https://tpcdn.whfpsoft.com:443/File/headPhoto/20191016/e4c406dbd43a009b9c613e75ebc7094d.jpg","sex":"0","repeatId":0,"repeatUserId":0,"isRepeat":0,"repeatContent":"","repeatNumber":0,"suggestion":"","scene":"","isHomeActivity":0,"postId":86902,"labels":"汉服日常","title":"","content":"太温柔小清新啦#汉服日常# ","allPostsNumber":"","location":"","albumID":0,"photoAlbumName":"","photoAlbumCover":"","longitude":0,"latitude":0,"isVisible":0,"shortUrl":"","installedAccording":"","tapeLength":0,"outboundPersonnel":"","clothing":"","shoot":"","type":4,"createTime":"2020-08-22 15:03","playTourNumber":0,"likeNumber":13,"commentNumber":0,"postLike":0,"isCollected":0,"relation":0,"cover":"https://tpcdn.whfpsoft.com:443/File/cover/20200822/54699d9152e80ba6f631d6c05b2874a7.jpg","videoPath":"https://tpcdn.whfpsoft.com:443/File/video/20200822/7e5e508948e814868e05cecdafce0ed4.mp4","videoWidth":1920,"videoHeight":1080,"rewards":[],"images":[],"voteOptions":[],"likeDetails":[]},{"userID":380341,"peopleNearby":0,"pullWires":0,"activityShow":0,"id":0,"rankName":"","level":1,"rankType":0,"typeName":"","rank":0,"expPrefix":0,"expSuffix":0,"ignb":0,"nickName":"钰惠菇凉","headUrl":"https://tpcdn.whfpsoft.com:443/File/headPhoto/20200804/f33efc1e1e6a748a9d21eea4517458e8.png","sex":"","repeatId":0,"repeatUserId":0,"isRepeat":0,"repeatContent":"","repeatNumber":0,"suggestion":"","scene":"","isHomeActivity":0,"postId":86900,"labels":"","title":"","content":"控制自己不买汉服最好的办法不是管住手，而是找点其他事情分散注意力","allPostsNumber":"","location":"","albumID":0,"photoAlbumName":"","photoAlbumCover":"","longitude":0,"latitude":0,"isVisible":0,"shortUrl":"","installedAccording":"","tapeLength":0,"outboundPersonnel":"","clothing":"","shoot":"","type":4,"createTime":"2020-08-22 15:01","playTourNumber":0,"likeNumber":14,"commentNumber":1,"postLike":0,"isCollected":0,"relation":0,"cover":"https://tpcdn.whfpsoft.com:443/File/backstage/20200822/17679324744560687.png","videoPath":"https://tpcdn.whfpsoft.com:443/File/backstage/20200822/2528308376307986.mp4","videoWidth":404,"videoHeight":712,"rewards":[],"images":[],"voteOptions":[],"likeDetails":[]},{"userID":331409,"peopleNearby":0,"pullWires":0,"activityShow":0,"id":0,"rankName":"","level":2,"rankType":0,"typeName":"","rank":0,"expPrefix":0,"expSuffix":0,"ignb":0,"nickName":"古小谷","headUrl":"https://tpcdn.whfpsoft.com:443/File/headPhoto/20200703/fc6a040d47d9442ff0c6b289c1a340fb.jpg","sex":"1","repeatId":0,"repeatUserId":0,"isRepeat":0,"repeatContent":"","repeatNumber":0,"suggestion":"","scene":"","isHomeActivity":0,"postId":86831,"labels":"","title":"","content":"头发不够，发包来凑","allPostsNumber":"","location":"","albumID":0,"photoAlbumName":"","photoAlbumCover":"","longitude":0,"latitude":0,"isVisible":0,"shortUrl":"","installedAccording":"","tapeLength":0,"outboundPersonnel":"","clothing":"","shoot":"","type":4,"createTime":"2020-08-22 10:45","playTourNumber":0,"likeNumber":13,"commentNumber":0,"postLike":0,"isCollected":0,"relation":0,"cover":"https://tpcdn.whfpsoft.com:443/File/cover/20200822/b35dbb85ac88fa10b383a69725193a6d.jpg","videoPath":"https://tpcdn.whfpsoft.com:443/File/video/20200822/0b74ec8edbe15fafea7f6c148de0d4d4.mp4","videoWidth":360,"videoHeight":640,"rewards":[],"images":[],"voteOptions":[],"likeDetails":[]},{"userID":304403,"peopleNearby":0,"pullWires":0,"activityShow":0,"id":0,"rankName":"","level":3,"rankType":0,"typeName":"","rank":0,"expPrefix":0,"expSuffix":0,"ignb":0,"nickName":"喂！上新啦！","headUrl":"https://tpcdn.whfpsoft.com:443/File/headPhoto/20200422/e18b0624c996f00614cf23e4a26d09b1.jpg","sex":"1","repeatId":0,"repeatUserId":0,"isRepeat":0,"repeatContent":"","repeatNumber":0,"suggestion":"","scene":"","isHomeActivity":0,"postId":86820,"labels":"喂！上新啦！,白菜汉服上新","title":"","content":"#喂！上新啦！#  #白菜汉服上新#\r店铺：凤吟阁\n上新时间：8-21\n上新内容：\n仿孔府复原图麒麟织金马面裙 暗夜黑、香芋紫、焦糖色、雾霾蓝四色 129\n麒麟方领对襟织金补服 奶白色、绯红色、焦糖色三色 129\r\r纯属整理分享，请谨慎种草~","allPostsNumber":"","location":"","albumID":0,"photoAlbumName":"","photoAlbumCover":"","longitude":0,"latitude":0,"isVisible":0,"shortUrl":"","installedAccording":"","tapeLength":0,"outboundPersonnel":"","clothing":"","shoot":"","type":1,"createTime":"2020-08-22 10:05","playTourNumber":0,"likeNumber":29,"commentNumber":0,"postLike":0,"isCollected":0,"relation":0,"cover":"","videoPath":"","videoWidth":1280,"videoHeight":720,"rewards":[],"images":[{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200822/6d1e42a6ced5cceda7b8ae83eaaffbcf.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":750,"imageHeight":750,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200822/bdb04c101c4855d4b1bc0362fbd0e3d4.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":750,"imageHeight":1000,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200822/6bf72254ceb3751f2b5f1be90b12d76c.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":750,"imageHeight":1000,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200822/38042fbf73d3fbf2337c2e728f44fae8.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":750,"imageHeight":1000,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200822/f715a92ae14bcb13c02bf9e5df4c08e9.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":750,"imageHeight":1000,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200822/29f71983d1f50e740fc5cfb5e13af588.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":750,"imageHeight":655,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200822/de58f85503549c684ccdb41790e31079.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":750,"imageHeight":934,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200822/4a4a28e7a6b5449c1bc2c42cabd8dbd6.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":750,"imageHeight":1000,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200822/436a1267cf60bd0e69ef0069da4973c9.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":634,"imageHeight":795,"fileType":0}],"voteOptions":[],"likeDetails":[]},{"userID":321701,"peopleNearby":0,"pullWires":0,"activityShow":0,"id":0,"rankName":"","level":2,"rankType":0,"typeName":"","rank":0,"expPrefix":0,"expSuffix":0,"ignb":0,"nickName":"小雨摄影工作室","headUrl":"https://tpcdn.whfpsoft.com:443/File/headPhoto/20200627/e919c1148f8b5b5c65084235370d312f.jpg","sex":"1","repeatId":0,"repeatUserId":0,"isRepeat":0,"repeatContent":"","repeatNumber":0,"suggestion":"","scene":"","isHomeActivity":0,"postId":86779,"labels":"","title":"","content":"我在等风，也在等你。\n摄影:我呀\n出境:嘉嘉\n#同袍开屏图投稿# #汉服穿搭# #摄影写真# #汉服日常# #汉服写真# #礼衣华夏汉服超模大赛# #寻找汉服摄影师#","allPostsNumber":"","location":"华贸中心","albumID":0,"photoAlbumName":"","photoAlbumCover":"","longitude":23.110908009375905,"latitude":114.42439514748617,"isVisible":0,"shortUrl":"","installedAccording":"","tapeLength":0,"outboundPersonnel":"","clothing":"","shoot":"","type":6,"createTime":"2020-08-22 09:31","playTourNumber":0,"likeNumber":7,"commentNumber":0,"postLike":0,"isCollected":0,"relation":0,"cover":"","videoPath":"","videoWidth":1280,"videoHeight":720,"rewards":[],"images":[{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200822/473e68928f740c4d8bb294ac1eb452b2.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":750,"imageHeight":1125,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200822/9692710deb7414b452088023b3d175f9.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":750,"imageHeight":1125,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200822/5e89e493367fa2df6854be7e02f830ac.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":750,"imageHeight":1125,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200822/a4f301f8be77aa3012fc3cfb356594f8.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":750,"imageHeight":1125,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200822/22651ce149450cb08778f96e082260e9.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":750,"imageHeight":1125,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200822/41a30c3d36a44161a0883e59926b424d.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":750,"imageHeight":1125,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200822/4763ba9c3c249789d32dc2dab451af7b.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":1500,"imageHeight":2250,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200822/cfa48f6f08c897c5689573487500f7f6.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":750,"imageHeight":1112,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200822/cba93012a628035e2d88ed3f98b19f65.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":1500,"imageHeight":1000,"fileType":0}],"voteOptions":[],"likeDetails":[]},{"userID":378723,"peopleNearby":0,"pullWires":0,"activityShow":0,"id":0,"rankName":"","level":1,"rankType":0,"typeName":"","rank":0,"expPrefix":0,"expSuffix":0,"ignb":0,"nickName":"一梦Ss","headUrl":"https://tpcdn.whfpsoft.com:443/File/headPhoto/20200821/d2487750b01d673ffd7cf88cb69f5ead.jpg","sex":"1","repeatId":0,"repeatUserId":0,"isRepeat":0,"repeatContent":"","repeatNumber":0,"suggestion":"","scene":"","isHomeActivity":0,"postId":86756,"labels":"","title":"","content":"天高地厚无人问\n萼绿花红有客怜.","allPostsNumber":"","location":"樊楼酒店","albumID":0,"photoAlbumName":"","photoAlbumCover":"","longitude":120.316174,"latitude":29.147939,"isVisible":0,"shortUrl":"","installedAccording":"","tapeLength":0,"outboundPersonnel":"","clothing":"","shoot":"","type":1,"createTime":"2020-08-21 23:14","playTourNumber":0,"likeNumber":16,"commentNumber":2,"postLike":0,"isCollected":0,"relation":0,"cover":"","videoPath":"","videoWidth":1280,"videoHeight":720,"rewards":[],"images":[{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200821/4a0e67a6a1d1ce65714a4be1ae4fc607.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":750,"imageHeight":1000,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200821/f0a5c419275a84120bc8f86bb6fea95a.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":750,"imageHeight":1000,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200821/1a0b54e553c13223f5130e695ec951b1.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":750,"imageHeight":537,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200821/de9961a7bbc3f36e72b3c9a72aeb14bd.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":750,"imageHeight":1000,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200821/4edcd7ec873cfbd32887887efceec59c.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":750,"imageHeight":1000,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200821/3e21708f6885573fa195b6619e5a2dbb.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":750,"imageHeight":563,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200821/44bc31ab9a24097b30bd76ef3a952167.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":750,"imageHeight":563,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200821/31b2212b7ed80dafad0983f03faa6853.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":750,"imageHeight":563,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200821/cdc086a369195723dcababb14d8b15b8.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":750,"imageHeight":1000,"fileType":0}],"voteOptions":[],"likeDetails":[]},{"userID":304393,"peopleNearby":0,"pullWires":0,"activityShow":0,"id":0,"rankName":"","level":3,"rankType":0,"typeName":"","rank":0,"expPrefix":0,"expSuffix":0,"ignb":0,"nickName":"袍哥科普笔记","headUrl":"https://tpcdn.whfpsoft.com:443/File/headPhoto/20200422/9f1a5f136037ca8e889741a0fae40958.jpg","sex":"0","repeatId":0,"repeatUserId":0,"isRepeat":0,"repeatContent":"","repeatNumber":0,"suggestion":"","scene":"","isHomeActivity":0,"postId":86723,"labels":"科普笔记-明","title":"","content":"今天分享的这个视频是接上次的【明代皇后的礼服冠】，这一期讲的是明初制度规定的皇后燕居服（大衫霞帔）所戴双凤翊龙冠。 \n\n戳视频学习，欣赏绝美的【皇后燕居冠】！视频最后还有皇后的燕居冠服穿搭层次介绍，要看完哦！#汉服配饰# #科普笔记-明#\n\n授权转载自wb :撷芳主人，纯属交流分享，欢迎讨论~ ","allPostsNumber":"","location":"","albumID":0,"photoAlbumName":"","photoAlbumCover":"","longitude":0,"latitude":0,"isVisible":0,"shortUrl":"","installedAccording":"","tapeLength":0,"outboundPersonnel":"","clothing":"","shoot":"","type":4,"createTime":"2020-08-21 14:45","playTourNumber":0,"likeNumber":29,"commentNumber":1,"postLike":0,"isCollected":0,"relation":0,"cover":"https://tpcdn.whfpsoft.com:443/File/backstage/20200821/11260083487628758.jpg","videoPath":"https://tpcdn.whfpsoft.com:443/File/backstage/20200821/5326424470649336.mp4","videoWidth":1918,"videoHeight":1067,"rewards":[],"images":[],"voteOptions":[],"likeDetails":[]},{"userID":374199,"peopleNearby":0,"pullWires":0,"activityShow":0,"id":0,"rankName":"","level":1,"rankType":0,"typeName":"","rank":0,"expPrefix":0,"expSuffix":0,"ignb":0,"nickName":"筱柒姝","headUrl":"https://tpcdn.whfpsoft.com:443/File/headPhoto/20200809/e5bba867f845408450a5af0f62220d3c.jpg","sex":"","repeatId":0,"repeatUserId":0,"isRepeat":0,"repeatContent":"","repeatNumber":0,"suggestion":"","scene":"","isHomeActivity":0,"postId":86711,"labels":"","title":"小视频","content":"桃子","allPostsNumber":"","location":"","albumID":0,"photoAlbumName":"","photoAlbumCover":"","longitude":0,"latitude":0,"isVisible":0,"shortUrl":"","installedAccording":"","tapeLength":0,"outboundPersonnel":"","clothing":"","shoot":"","type":4,"createTime":"2020-08-21 10:23","playTourNumber":0,"likeNumber":20,"commentNumber":0,"postLike":0,"isCollected":0,"relation":0,"cover":"https://tpcdn.whfpsoft.com:443/File/cover/20200821/3a9db7feed1f0779e252ff9a72aa50d5.jpeg","videoPath":"https://tpcdn.whfpsoft.com:443/File/video/20200821/40bb79f9a19d294b2dbb4bb39c68fbb4.mp4","videoWidth":720,"videoHeight":1280,"rewards":[],"images":[],"voteOptions":[],"likeDetails":[]},{"userID":304747,"peopleNearby":0,"pullWires":0,"activityShow":0,"id":0,"rankName":"","level":1,"rankType":0,"typeName":"","rank":0,"expPrefix":0,"expSuffix":0,"ignb":0,"nickName":"热心市民周公子","headUrl":"https://tpcdn.whfpsoft.com:443/File/headPhoto/20200820/b1a1910108f9f6036e110585bbece33f.jpg","sex":"0","repeatId":0,"repeatUserId":0,"isRepeat":0,"repeatContent":"","repeatNumber":0,"suggestion":"","scene":"","isHomeActivity":0,"postId":86708,"labels":"","title":"","content":"【江南百景表情包集】这个游戏好多表情包！你们下了没？反正我朋友圈被刷屏惹～\n大家快把严大人的表情包交出来~\n\n\n图源见水印\n","allPostsNumber":"","location":"","albumID":0,"photoAlbumName":"","photoAlbumCover":"","longitude":0,"latitude":0,"isVisible":0,"shortUrl":"","installedAccording":"","tapeLength":0,"outboundPersonnel":"","clothing":"","shoot":"","type":1,"createTime":"2020-08-21 09:23","playTourNumber":0,"likeNumber":17,"commentNumber":1,"postLike":0,"isCollected":0,"relation":0,"cover":"","videoPath":"","videoWidth":1280,"videoHeight":720,"rewards":[],"images":[{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200821/9fb592acd46cb9326e53e9b7daca092e.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":160,"imageHeight":160,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200821/8bb65667df1a3715ca0869fa3d9e22c0.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":160,"imageHeight":160,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200821/fcd0bf321cce0315cf5853295d3d0a7a.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":160,"imageHeight":160,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200821/0f452b95fc981af3b68c4dd69ff209b4.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":160,"imageHeight":160,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200821/e38650f09b3ced0b70f014df3ae1e295.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":640,"imageHeight":640,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200821/53b59fd59bae74cd6ed3bad22314d0be.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":440,"imageHeight":440,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200821/20d397f460a5e0eeabe42391d5a4eb64.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":200,"imageHeight":167,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200821/26bebdd0b7b90aaac2cc244ba62b6fd2.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":640,"imageHeight":604,"fileType":0},{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200821/1762557c0d4fc342e82ffc40f27e9dd0.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":240,"imageHeight":240,"fileType":0}],"voteOptions":[],"likeDetails":[]},{"userID":133847,"peopleNearby":0,"pullWires":0,"activityShow":0,"id":0,"rankName":"","level":5,"rankType":0,"typeName":"","rank":0,"expPrefix":0,"expSuffix":0,"ignb":0,"nickName":"沐雨莲生","headUrl":"https://tpcdn.whfpsoft.com:443/File/headPhoto/20190927/5aa77fa8646bc4b76b48812e98e86886.jpg","sex":"1","repeatId":0,"repeatUserId":0,"isRepeat":0,"repeatContent":"","repeatNumber":0,"suggestion":"","scene":"","isHomeActivity":0,"postId":86705,"labels":"","title":"小视频","content":"似此星辰非昨夜，为谁风露立中宵","allPostsNumber":"","location":"","albumID":0,"photoAlbumName":"","photoAlbumCover":"","longitude":0,"latitude":0,"isVisible":0,"shortUrl":"","installedAccording":"","tapeLength":0,"outboundPersonnel":"","clothing":"","shoot":"","type":4,"createTime":"2020-08-21 08:50","playTourNumber":0,"likeNumber":16,"commentNumber":0,"postLike":0,"isCollected":0,"relation":0,"cover":"https://tpcdn.whfpsoft.com:443/File/cover/20200821/f61ab2a84da87dce87994039054f3e25.jpeg","videoPath":"https://tpcdn.whfpsoft.com:443/File/video/20200821/a5640d2babedfae01d383fce43e8a4cf.mp4","videoWidth":640,"videoHeight":368,"rewards":[],"images":[],"voteOptions":[],"likeDetails":[]}]
         */

        private int countNumber;
        private List<DynamicsBean> dynamics;

        public int getCountNumber() {
            return countNumber;
        }

        public void setCountNumber(int countNumber) {
            this.countNumber = countNumber;
        }

        public List<DynamicsBean> getDynamics() {
            return dynamics;
        }

        public void setDynamics(List<DynamicsBean> dynamics) {
            this.dynamics = dynamics;
        }

        public static class DynamicsBean {
            /**
             * userID : 100001
             * peopleNearby : 0
             * pullWires : 0
             * activityShow : 0
             * id : 0
             * rankName :
             * level : 8
             * rankType : 0
             * typeName :
             * rank : 0
             * expPrefix : 0
             * expSuffix : 0
             * ignb : 2
             * nickName : 官方小袍
             * headUrl : https://tpcdn.whfpsoft.com:443/File/headPhoto/20200404/fa5134d048f08eff6f3617dc35d3a836.jpg
             * sex : 1
             * repeatId : 0
             * repeatUserId : 0
             * isRepeat : 0
             * repeatContent :
             * repeatNumber : 0
             * suggestion :
             * scene :
             * isHomeActivity : 0
             * postId : 87182
             * labels :
             * title :
             * content : 官宣！欢迎@一叶馨香汉服 入驻同袍APP商城！
             * <p>
             * 成立于2018年的一叶馨香汉服，作为一家集设计、生产、销售服务于一体的专业性的汉服品牌，深入了解客户群体和需求，穿衣习惯，从服饰的色彩、面料、款式和搭配等多方面的考虑，做客户满意的可爱少女风的汉服。一叶馨香汉服以传承华夏之美为己任，大力传播汉民族的传统服饰——汉服。
             * <p>
             * 还不知道在哪里去找剁手一叶家汉服？打开http://tongpao.whfpsoft.com:8095/shop/100?isReadOnly=1
             * 或者在商城搜索一叶馨香汉服加购！
             * <p>
             * 来说说，你最想拥有一叶家的：__________？
             * allPostsNumber :
             * location :
             * albumID : 0
             * photoAlbumName :
             * photoAlbumCover :
             * longitude : 0.0
             * latitude : 0.0
             * isVisible : 1
             * shortUrl :
             * installedAccording :
             * tapeLength : 0
             * outboundPersonnel :
             * clothing :
             * shoot :
             * type : 1
             * createTime : 2020-08-24 10:24
             * playTourNumber : 0
             * likeNumber : 11
             * commentNumber : 0
             * postLike : 0
             * isCollected : 0
             * relation : 1
             * cover :
             * videoPath :
             * videoWidth : 1280.0
             * videoHeight : 720.0
             * rewards : []
             * images : [{"fileId":0,"userID":0,"fileName":"","filePath":"https://tpcdn.whfpsoft.com:443/File/post/20200824/084d0fffcd96726f002d360a265b6db2.jpg","fileSize":0,"fileMD5":"","createTime":"","voicePath":"","imageWidth":507,"imageHeight":800,"fileType":0}]
             * voteOptions : []
             * likeDetails : []
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
            private int repeatId;
            private int repeatUserId;
            private int isRepeat;
            private String repeatContent;
            private int repeatNumber;
            private String suggestion;
            private String scene;
            private int isHomeActivity;
            private int postId;
            private String labels;
            private String title;
            private String content;
            private String allPostsNumber;
            private String location;
            private int albumID;
            private String photoAlbumName;
            private String photoAlbumCover;
            private double longitude;
            private double latitude;
            private int isVisible;
            private String shortUrl;
            private String installedAccording;
            private int tapeLength;
            private String outboundPersonnel;
            private String clothing;
            private String shoot;
            private int type;
            private String createTime;
            private int playTourNumber;
            private int likeNumber;
            private int commentNumber;
            private int postLike;
            private int isCollected;
            private int relation;
            private String cover;
            private String videoPath;
            private double videoWidth;
            private double videoHeight;
            private List<?> rewards;
            private List<ImagesBean> images;
            private List<?> voteOptions;
            private List<?> likeDetails;

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

            public int getRepeatId() {
                return repeatId;
            }

            public void setRepeatId(int repeatId) {
                this.repeatId = repeatId;
            }

            public int getRepeatUserId() {
                return repeatUserId;
            }

            public void setRepeatUserId(int repeatUserId) {
                this.repeatUserId = repeatUserId;
            }

            public int getIsRepeat() {
                return isRepeat;
            }

            public void setIsRepeat(int isRepeat) {
                this.isRepeat = isRepeat;
            }

            public String getRepeatContent() {
                return repeatContent;
            }

            public void setRepeatContent(String repeatContent) {
                this.repeatContent = repeatContent;
            }

            public int getRepeatNumber() {
                return repeatNumber;
            }

            public void setRepeatNumber(int repeatNumber) {
                this.repeatNumber = repeatNumber;
            }

            public String getSuggestion() {
                return suggestion;
            }

            public void setSuggestion(String suggestion) {
                this.suggestion = suggestion;
            }

            public String getScene() {
                return scene;
            }

            public void setScene(String scene) {
                this.scene = scene;
            }

            public int getIsHomeActivity() {
                return isHomeActivity;
            }

            public void setIsHomeActivity(int isHomeActivity) {
                this.isHomeActivity = isHomeActivity;
            }

            public int getPostId() {
                return postId;
            }

            public void setPostId(int postId) {
                this.postId = postId;
            }

            public String getLabels() {
                return labels;
            }

            public void setLabels(String labels) {
                this.labels = labels;
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

            public String getAllPostsNumber() {
                return allPostsNumber;
            }

            public void setAllPostsNumber(String allPostsNumber) {
                this.allPostsNumber = allPostsNumber;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public int getAlbumID() {
                return albumID;
            }

            public void setAlbumID(int albumID) {
                this.albumID = albumID;
            }

            public String getPhotoAlbumName() {
                return photoAlbumName;
            }

            public void setPhotoAlbumName(String photoAlbumName) {
                this.photoAlbumName = photoAlbumName;
            }

            public String getPhotoAlbumCover() {
                return photoAlbumCover;
            }

            public void setPhotoAlbumCover(String photoAlbumCover) {
                this.photoAlbumCover = photoAlbumCover;
            }

            public double getLongitude() {
                return longitude;
            }

            public void setLongitude(double longitude) {
                this.longitude = longitude;
            }

            public double getLatitude() {
                return latitude;
            }

            public void setLatitude(double latitude) {
                this.latitude = latitude;
            }

            public int getIsVisible() {
                return isVisible;
            }

            public void setIsVisible(int isVisible) {
                this.isVisible = isVisible;
            }

            public String getShortUrl() {
                return shortUrl;
            }

            public void setShortUrl(String shortUrl) {
                this.shortUrl = shortUrl;
            }

            public String getInstalledAccording() {
                return installedAccording;
            }

            public void setInstalledAccording(String installedAccording) {
                this.installedAccording = installedAccording;
            }

            public int getTapeLength() {
                return tapeLength;
            }

            public void setTapeLength(int tapeLength) {
                this.tapeLength = tapeLength;
            }

            public String getOutboundPersonnel() {
                return outboundPersonnel;
            }

            public void setOutboundPersonnel(String outboundPersonnel) {
                this.outboundPersonnel = outboundPersonnel;
            }

            public String getClothing() {
                return clothing;
            }

            public void setClothing(String clothing) {
                this.clothing = clothing;
            }

            public String getShoot() {
                return shoot;
            }

            public void setShoot(String shoot) {
                this.shoot = shoot;
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

            public int getPlayTourNumber() {
                return playTourNumber;
            }

            public void setPlayTourNumber(int playTourNumber) {
                this.playTourNumber = playTourNumber;
            }

            public int getLikeNumber() {
                return likeNumber;
            }

            public void setLikeNumber(int likeNumber) {
                this.likeNumber = likeNumber;
            }

            public int getCommentNumber() {
                return commentNumber;
            }

            public void setCommentNumber(int commentNumber) {
                this.commentNumber = commentNumber;
            }

            public int getPostLike() {
                return postLike;
            }

            public void setPostLike(int postLike) {
                this.postLike = postLike;
            }

            public int getIsCollected() {
                return isCollected;
            }

            public void setIsCollected(int isCollected) {
                this.isCollected = isCollected;
            }

            public int getRelation() {
                return relation;
            }

            public void setRelation(int relation) {
                this.relation = relation;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public String getVideoPath() {
                return videoPath;
            }

            public void setVideoPath(String videoPath) {
                this.videoPath = videoPath;
            }

            public double getVideoWidth() {
                return videoWidth;
            }

            public void setVideoWidth(double videoWidth) {
                this.videoWidth = videoWidth;
            }

            public double getVideoHeight() {
                return videoHeight;
            }

            public void setVideoHeight(double videoHeight) {
                this.videoHeight = videoHeight;
            }

            public List<?> getRewards() {
                return rewards;
            }

            public void setRewards(List<?> rewards) {
                this.rewards = rewards;
            }

            public List<ImagesBean> getImages() {
                return images;
            }

            public void setImages(List<ImagesBean> images) {
                this.images = images;
            }

            public List<?> getVoteOptions() {
                return voteOptions;
            }

            public void setVoteOptions(List<?> voteOptions) {
                this.voteOptions = voteOptions;
            }

            public List<?> getLikeDetails() {
                return likeDetails;
            }

            public void setLikeDetails(List<?> likeDetails) {
                this.likeDetails = likeDetails;
            }

            public static class ImagesBean {
                /**
                 * fileId : 0
                 * userID : 0
                 * fileName :
                 * filePath : https://tpcdn.whfpsoft.com:443/File/post/20200824/084d0fffcd96726f002d360a265b6db2.jpg
                 * fileSize : 0
                 * fileMD5 :
                 * createTime :
                 * voicePath :
                 * imageWidth : 507
                 * imageHeight : 800
                 * fileType : 0
                 */

                private int fileId;
                private int userID;
                private String fileName;
                private String filePath;
                private int fileSize;
                private String fileMD5;
                private String createTime;
                private String voicePath;
                private int imageWidth;
                private int imageHeight;
                private int fileType;

                public int getFileId() {
                    return fileId;
                }

                public void setFileId(int fileId) {
                    this.fileId = fileId;
                }

                public int getUserID() {
                    return userID;
                }

                public void setUserID(int userID) {
                    this.userID = userID;
                }

                public String getFileName() {
                    return fileName;
                }

                public void setFileName(String fileName) {
                    this.fileName = fileName;
                }

                public String getFilePath() {
                    return filePath;
                }

                public void setFilePath(String filePath) {
                    this.filePath = filePath;
                }

                public int getFileSize() {
                    return fileSize;
                }

                public void setFileSize(int fileSize) {
                    this.fileSize = fileSize;
                }

                public String getFileMD5() {
                    return fileMD5;
                }

                public void setFileMD5(String fileMD5) {
                    this.fileMD5 = fileMD5;
                }

                public String getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(String createTime) {
                    this.createTime = createTime;
                }

                public String getVoicePath() {
                    return voicePath;
                }

                public void setVoicePath(String voicePath) {
                    this.voicePath = voicePath;
                }

                public int getImageWidth() {
                    return imageWidth;
                }

                public void setImageWidth(int imageWidth) {
                    this.imageWidth = imageWidth;
                }

                public int getImageHeight() {
                    return imageHeight;
                }

                public void setImageHeight(int imageHeight) {
                    this.imageHeight = imageHeight;
                }

                public int getFileType() {
                    return fileType;
                }

                public void setFileType(int fileType) {
                    this.fileType = fileType;
                }
            }
        }
    }
}

