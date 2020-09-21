package com.qutu.talk.activity.family

import android.os.Bundle
import android.support.annotation.NonNull
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.VirtualLayoutManager
import com.alibaba.android.vlayout.layout.LinearLayoutHelper
import com.blankj.utilcode.util.ActivityUtils
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.LogUtils
import com.qutu.talk.R
import com.qutu.talk.activity.MainActivity
import com.qutu.talk.activity.message.MessageSetGroupActivity
import com.qutu.talk.adapter.FriendUserAdapter
import com.qutu.talk.adapter.FriendUserTitleAdapter
import com.qutu.talk.adapter.NotFriendUserAdapter
import com.qutu.talk.adapter.SearchFriendUserAdapter
import com.qutu.talk.app.utils.RxUtils
import com.qutu.talk.base.MyBaseArmActivity
import com.qutu.talk.bean.*
import com.qutu.talk.di.CommonModule
import com.qutu.talk.di.DaggerCommonComponent
import com.qutu.talk.popup.PuTongWindow
import com.qutu.talk.service.CommonModel
import com.qutu.talk.utils.Constant
import com.qutu.talk.utils.DealRefreshHelper
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import io.rong.imkit.RongIM
import io.rong.imlib.IRongCallback
import io.rong.imlib.RongIMClient
import io.rong.imlib.model.Conversation
import io.rong.imlib.model.Message
import io.rong.imlib.model.MessageContent
import kotlinx.android.synthetic.main.activity_create_family_second.*
import kotlinx.android.synthetic.main.include_title.*
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.greenrobot.eventbus.EventBus
import java.io.File
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

//添加成员
class CreateFamilySecondActivity : MyBaseArmActivity(), View.OnClickListener {

    @Inject
    open lateinit var commonModel: CommonModel

    internal var mPullRefreshBeanSearch = PullRefreshBean()

    internal var mPullRefreshBeanFriend = PullRefreshBean()

    //titleAdapter
    var mFriendUserTitleAdapter_Search: FriendUserTitleAdapter? = null

    var mFriendUserTitleAdapter_Not_Friend: FriendUserTitleAdapter? = null

    var mFriendUserTitleAdapter_Friend: FriendUserTitleAdapter? = null

    //数据Adapter
    var mFriendUserAdapter: FriendUserAdapter? = null

    var mNotFriendUserAdapter: NotFriendUserAdapter? = null

    var mSearchFriendUserAdapter: SearchFriendUserAdapter? = null

    //数据
    var mTitleFriendList = ArrayList<String>()

    var mTitleNotFriendList = ArrayList<String>()

    var mTitleSearchFriendList = ArrayList<String>()

    var mSearchFriendList = ArrayList<UserAddItem>()

    var mFriendList = ArrayList<UserAddItem>()

    var mNotFriendList = ArrayList<UserAddItem>()

    var mDelegateAdapter: DelegateAdapter? = null

    var mDelegateSearchAdapter: DelegateAdapter? = null

    var mSelectImgPath:String? = ""

    var mFamilyName:String? = ""

    var mFamilyIntro:String? = ""

    var mFamilyNotice:String? = ""

    var mFromCreateFamily:Int? = 0;// 0是创建家族 1创建后添加成员 2删除成员

    var mFamilyId:String? = ""

    override fun setupActivityComponent(appComponent: AppComponent) {

        DaggerCommonComponent.builder()
                .appComponent(appComponent)
                .commonModule(CommonModule())
                .build()
                .inject(this)

    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_create_family_second
    }

    override fun initData(savedInstanceState: Bundle?) {

        setTitle("添加成员")

        mFromCreateFamily = intent.getIntExtra("is_from_create",0)

        mFamilyId = intent.getStringExtra("family_id")

        if(mFromCreateFamily ==2){
            setTitle("删除成员")
        }

        mSelectImgPath = intent.getStringExtra("img_path")
        mFamilyName = intent.getStringExtra("family_name")
        mFamilyIntro = intent.getStringExtra("family_intro")
        mFamilyNotice = intent.getStringExtra("family_notice")

        initRecyclerView()

        initSearchRecyclerView()

        initRefreshLayout()

        initSearchRefreshLayout()

        initOnClickListener()

        getDataFromServer()

    }

    private fun initOnClickListener() {

        btn_search.setOnClickListener(this)

        btn_confirm.setOnClickListener(this)

    }

    private fun initRecyclerView() {

        val layoutManager = VirtualLayoutManager(this)

        rlv_friend.setLayoutManager(layoutManager)

        //设置服用池设置回收复用池大小，（如果一屏内相同类型的 View 个数比较多，需要设置一个合适的大小，防止来回滚动时重新创建 View）：
        val viewPool = RecyclerView.RecycledViewPool()

        viewPool.setMaxRecycledViews(0, 10)

        rlv_friend.setRecycledViewPool(viewPool)

        //创建delegateadapter
        mDelegateAdapter = DelegateAdapter(layoutManager, false)

        rlv_friend.setAdapter(mDelegateAdapter)

        mFriendUserTitleAdapter_Not_Friend = FriendUserTitleAdapter(R.layout.item_friend_user_title, mTitleNotFriendList, LinearLayoutHelper())

        mDelegateAdapter!!.addAdapter(mFriendUserTitleAdapter_Not_Friend)

        mNotFriendUserAdapter = NotFriendUserAdapter(this, R.layout.item_family_add_user, mNotFriendList, LinearLayoutHelper())

        //非好友点击事件
        mNotFriendUserAdapter!!.setOnClickListener(object : NotFriendUserAdapter.OnChildViewClickListener {
            override fun OnChildClick(view: View?, recommendUser: UserAddItem?) {

                tv_select_count.setText("选择：" + getSelectCount().toString() + "人")

            }

        })

        mDelegateAdapter!!.addAdapter(mNotFriendUserAdapter)

        if(mFromCreateFamily==0 || mFromCreateFamily ==1){
            mTitleFriendList.add("好友列表")
        } else {
            mTitleFriendList.add("家族成员列表")
        }

        mFriendUserTitleAdapter_Friend = FriendUserTitleAdapter(R.layout.item_friend_user_title, mTitleFriendList, LinearLayoutHelper())

        mDelegateAdapter!!.addAdapter(mFriendUserTitleAdapter_Friend)

        mFriendUserAdapter = FriendUserAdapter(this, R.layout.item_family_add_user, mFriendList, LinearLayoutHelper(),mFromCreateFamily!!)

        //好友点击事件
        mFriendUserAdapter!!.setOnClickListener(object : FriendUserAdapter.OnChildViewClickListener {
            override fun OnChildClick(view: View?, recommendUser: UserAddItem?) {

                tv_select_count.setText("选择：" + getSelectCount().toString() + "人")

            }

        })

        mDelegateAdapter!!.addAdapter(mFriendUserAdapter)

    }

    //设置搜索相关
    fun initSearchRecyclerView() {

        val layoutManager1 = VirtualLayoutManager(this)

        rlv_search.setLayoutManager(layoutManager1)

        //设置服用池设置回收复用池大小，（如果一屏内相同类型的 View 个数比较多，需要设置一个合适的大小，防止来回滚动时重新创建 View）：
        val viewPool1 = RecyclerView.RecycledViewPool()

        viewPool1.setMaxRecycledViews(0, 10)

        rlv_search.setRecycledViewPool(viewPool1)

        //创建delegateadapter
        mDelegateSearchAdapter = DelegateAdapter(layoutManager1, false)

        rlv_search.setAdapter(mDelegateSearchAdapter)

        mTitleSearchFriendList.add("搜索列表")

        mFriendUserTitleAdapter_Search = FriendUserTitleAdapter(R.layout.item_friend_user_title, mTitleSearchFriendList, LinearLayoutHelper())

        mDelegateSearchAdapter!!.addAdapter(mFriendUserTitleAdapter_Search)

        mSearchFriendUserAdapter = SearchFriendUserAdapter(this, R.layout.item_family_add_user, mSearchFriendList, LinearLayoutHelper(),mFromCreateFamily!!)

        //搜索点击事件
        mSearchFriendUserAdapter!!.setOnClickListener(object : SearchFriendUserAdapter.OnChildViewClickListener {
            override fun OnChildClick(view: View?, recommendUser: UserAddItem?) {

                refresh_layout_search.visibility = View.GONE

                refresh_layout_friend.visibility = View.VISIBLE

                recommendUser!!.checked = true

                if (mFromCreateFamily == 0 || mFromCreateFamily == 1) {//创建或者添加
                    if (recommendUser!!.is_friend == 0) {//非好友
                        if (mTitleNotFriendList.size == 0) {//没有非好友的title
                            mTitleNotFriendList.add("非好友列表")
                            mFriendUserTitleAdapter_Not_Friend!!.notifyDataSetChanged()
                        }
                        var isHasThisUser = mNotFriendList.filter {
                            it.user_id.equals(recommendUser.user_id)
                        }.size
                        if (isHasThisUser == 0) {
                            mNotFriendList.add(recommendUser)
                            mNotFriendUserAdapter!!.notifyDataSetChanged()
                        } else {
                            for(m in mNotFriendList){
                                if(m.user_id.equals(recommendUser.user_id)){
                                    m.checked = true
                                    mNotFriendUserAdapter!!.notifyDataSetChanged()
                                    break
                                }
                            }
                        }
                    } else {
                        var isHasThisUser = mFriendList.filter {
                            it.user_id.equals(recommendUser.user_id)
                        }.size
                        if (isHasThisUser == 0) {
                            mFriendList.add(recommendUser)
                            mFriendUserAdapter!!.notifyDataSetChanged()
                        } else {
                            for(m in mFriendList){
                                if(m.user_id.equals(recommendUser.user_id)){
                                    m.checked = true
                                    mFriendUserAdapter!!.notifyDataSetChanged()
                                    break
                                }
                            }
                        }
                    }
                } else {//删除

                    var isHasThisUser = mFriendList.filter {
                        it.user_id.equals(recommendUser.user_id)
                    }.size
                    if (isHasThisUser == 0) {
                        mFriendList.add(recommendUser)
                        mFriendUserAdapter!!.notifyDataSetChanged()
                    } else {
                        for(m in mFriendList){
                            if(m.user_id.equals(recommendUser.user_id)){
                                m.checked = true
                                mFriendUserAdapter!!.notifyDataSetChanged()
                                break
                            }
                        }
                    }
                }

                tv_select_count.setText("选择：" + getSelectCount().toString() + "人")

            }

        })

        mDelegateSearchAdapter!!.addAdapter(mSearchFriendUserAdapter)
    }

    private fun initRefreshLayout() {

        refresh_layout_friend.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(@NonNull refreshLayout: RefreshLayout) {

                mPullRefreshBeanFriend.setLoardMore(mPullRefreshBeanFriend, refresh_layout_friend)

                getDataFromServer()

            }

            override fun onRefresh(@NonNull refreshLayout: RefreshLayout) {

                tv_select_count.setText("选择：0人")

                mPullRefreshBeanFriend.setRefresh(mPullRefreshBeanFriend, refresh_layout_friend)

                getDataFromServer()

            }
        })

    }

    private fun initSearchRefreshLayout() {

        refresh_layout_search.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(@NonNull refreshLayout: RefreshLayout) {


                mPullRefreshBeanSearch.setLoardMore(mPullRefreshBeanSearch, refresh_layout_search)

                getSearchDataFromServer()

            }

            override fun onRefresh(@NonNull refreshLayout: RefreshLayout) {

                mPullRefreshBeanSearch.setRefresh(mPullRefreshBeanSearch, refresh_layout_search)

                getSearchDataFromServer()

            }
        })

    }

    private fun getSelectCount(): Int {
        var notFriendCount = mNotFriendList.size
        val filterSize = mFriendList.filter {
            it.checked
        }.size
        return (notFriendCount + filterSize)
    }

    //获取数据
    private fun getDataFromServer() {

        if(mFromCreateFamily==0 || mFromCreateFamily ==1){//创建家族

            RxUtils.loading(commonModel.getSearchUserList("", mPullRefreshBeanFriend.pageIndex.toString()))
                    .subscribe(object : ErrorHandleSubscriber<AddFamilyUserResult>(mErrorHandler) {
                        override fun onNext(orderResult: AddFamilyUserResult) {

                            if (orderResult != null && orderResult.data != null) {

                                var list: List<UserAddItem> = orderResult.data

                                DealRefreshHelper<UserAddItem>().dealDataToUI(refresh_layout_friend, mFriendUserAdapter!!, ly_view_no_data_friend, list, mFriendList, mPullRefreshBeanFriend)

                            } else {
                                DealRefreshHelper<UserAddItem>().dealDataToUI(refresh_layout_friend, mFriendUserAdapter!!, ly_view_no_data_friend, ArrayList<UserAddItem>(), mFriendList, mPullRefreshBeanFriend)
                            }
                        }

                        override fun onError(t: Throwable) {
                            super.onError(t)
                            DealRefreshHelper<UserAddItem>().dealDataToUI(refresh_layout_friend, mFriendUserAdapter!!, ly_view_no_data_friend, ArrayList<UserAddItem>(), mFriendList, mPullRefreshBeanFriend)
                        }

                    })

        } else {//删除成员，列表

            RxUtils.loading(commonModel.getUserDelList("", mFamilyId,mPullRefreshBeanFriend.pageIndex.toString()))
                    .subscribe(object : ErrorHandleSubscriber<AddFamilyUserResult>(mErrorHandler) {
                        override fun onNext(orderResult: AddFamilyUserResult) {

                            if (orderResult != null && orderResult.data != null) {

                                var list: List<UserAddItem> = orderResult.data

                                DealRefreshHelper<UserAddItem>().dealDataToUI(refresh_layout_friend, mFriendUserAdapter!!, ly_view_no_data_friend, list, mFriendList, mPullRefreshBeanFriend)

                            } else {
                                DealRefreshHelper<UserAddItem>().dealDataToUI(refresh_layout_friend, mFriendUserAdapter!!, ly_view_no_data_friend, ArrayList<UserAddItem>(), mFriendList, mPullRefreshBeanFriend)
                            }
                        }

                        override fun onError(t: Throwable) {
                            super.onError(t)
                            DealRefreshHelper<UserAddItem>().dealDataToUI(refresh_layout_friend, mFriendUserAdapter!!, ly_view_no_data_friend, ArrayList<UserAddItem>(), mFriendList, mPullRefreshBeanFriend)
                        }

                    })
        }


    }

    //搜索
    private fun getSearchDataFromServer() {

        if(mFromCreateFamily == 0 || mFromCreateFamily == 1){

            RxUtils.loading(commonModel.searchUser(et_search_content.text.toString().trim(), mPullRefreshBeanSearch.pageIndex.toString()))
                    .subscribe(object : ErrorHandleSubscriber<AddFamilyUserResult>(mErrorHandler) {
                        override fun onNext(orderResult: AddFamilyUserResult) {

                            if (orderResult != null && orderResult.data != null) {

                                var list: List<UserAddItem> = orderResult.data

                                DealRefreshHelper<UserAddItem>().dealDataToUI(refresh_layout_search, mSearchFriendUserAdapter!!, ly_view_no_data_search, list, mSearchFriendList, mPullRefreshBeanSearch)

                            } else {
                                DealRefreshHelper<UserAddItem>().dealDataToUI(refresh_layout_search, mSearchFriendUserAdapter!!, ly_view_no_data_search, ArrayList<UserAddItem>(), mSearchFriendList, mPullRefreshBeanSearch)
                            }
                        }

                        override fun onError(t: Throwable) {
                            super.onError(t)
                            DealRefreshHelper<UserAddItem>().dealDataToUI(refresh_layout_search, mSearchFriendUserAdapter!!, ly_view_no_data_search, ArrayList<UserAddItem>(), mSearchFriendList, mPullRefreshBeanSearch)
                        }

                    })

        } else {

            RxUtils.loading(commonModel.getUserDelList(et_search_content.text.toString().trim(), mFamilyId,mPullRefreshBeanSearch.pageIndex.toString()))
                    .subscribe(object : ErrorHandleSubscriber<AddFamilyUserResult>(mErrorHandler) {
                        override fun onNext(orderResult: AddFamilyUserResult) {

                            if (orderResult != null && orderResult.data != null) {

                                var list: List<UserAddItem> = orderResult.data

                                DealRefreshHelper<UserAddItem>().dealDataToUI(refresh_layout_search, mSearchFriendUserAdapter!!, ly_view_no_data_search, list, mSearchFriendList, mPullRefreshBeanSearch)

                            } else {
                                DealRefreshHelper<UserAddItem>().dealDataToUI(refresh_layout_search, mSearchFriendUserAdapter!!, ly_view_no_data_search, ArrayList<UserAddItem>(), mSearchFriendList, mPullRefreshBeanSearch)
                            }
                        }

                        override fun onError(t: Throwable) {
                            super.onError(t)
                            DealRefreshHelper<UserAddItem>().dealDataToUI(refresh_layout_search, mSearchFriendUserAdapter!!, ly_view_no_data_search, ArrayList<UserAddItem>(), mSearchFriendList, mPullRefreshBeanSearch)
                        }

                    })


        }


    }

    fun commitInfo() {

        val map = HashMap<String, Any?>()

        map["name"] = mFamilyName!!

        map["introduce"] = mFamilyIntro

        map["notice"] = mFamilyNotice

        var builder = StringBuilder()

        var selectList = ArrayList<UserAddItem>()

        if(mNotFriendList.size>0){
            selectList.addAll(mNotFriendList)
        }

        val filterList = mFriendList.filter {
            it.checked
        }

        if(filterList.size>0){
            selectList.addAll(filterList)
        }

        for(m in selectList){
            builder.append(m.user_id)
            builder.append(",")
        }

        if(builder.length>0){
            builder = builder.deleteCharAt(builder.length-1)
        }

        map["users"] = builder.toString()

        var imgS: MultipartBody.Part? = null

        if (!TextUtils.isEmpty(mSelectImgPath)) {

            val imgFile: File = File(mSelectImgPath)

            val requestBody = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), imgFile!!)

            imgS = MultipartBody.Part.createFormData("img", imgFile!!.getName(), requestBody)

        } else {
            imgS = MultipartBody.Part.createFormData("img", "");
        }

        showDialogLoding()

        RxUtils.loading(commonModel.actionCreateFamily(map, imgS), this)
                .subscribe(object : ErrorHandleSubscriber<CreatFamilyResult>(mErrorHandler) {
                    override fun onNext(baseBean: CreatFamilyResult) {

                        disDialogLoding()

                        toast(baseBean.message)

                        if (baseBean.code == 1) {
                            //发送单聊消息
                            for(m in selectList){

                               var message =  RequestEnterGroupMessage()
                                message.from_content = "邀请" + m.nickname + "加入家族"
                                message.to_content = "邀请加入" + mFamilyName + "家族"
                                message.family_id = baseBean.data.family_id

                                sendSingleMessage(m.user_id,Conversation.ConversationType.PRIVATE,message,"家族邀请消息")


                            }

                            EventBus.getDefault().post(FirstEvent("创建家族", Constant.CREAT_FAMILY))

                            ActivityUtils.finishToActivity(MainActivity::class.java,false)


                        }

                    }

                    override fun onError(t: Throwable) {
                        super.onError(t)
                        disDialogLoding()
                    }
                })
    }

    fun addUsers() {

        var builder = StringBuilder()

        var selectList = ArrayList<UserAddItem>()

        if(mNotFriendList.size>0){
            selectList.addAll(mNotFriendList)
        }

        val filterList = mFriendList.filter {
            it.checked
        }

        if(filterList.size>0){
            selectList.addAll(filterList)
        }

        for(m in selectList){
            builder.append(m.user_id)
            builder.append(",")
        }

        if(builder.length>0){
            builder = builder.deleteCharAt(builder.length-1)
        }

        showDialogLoding()

        RxUtils.loading(commonModel.actionAddUser(mFamilyId, builder.toString()), this)
                .subscribe(object : ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    override fun onNext(baseBean: BaseBean) {

                        disDialogLoding()

                        toast(baseBean.message)

                        if (baseBean.code == 1) {
                            //发送单聊消息
                            for(m in selectList){

                               var message =  RequestEnterGroupMessage()
                                message.from_content = "邀请" + m.nickname + "加入家族"
                                message.to_content = "邀请加入" + mFamilyName + "家族"
                                message.family_id = mFamilyId

                                sendSingleMessage(m.user_id,Conversation.ConversationType.PRIVATE,message,"家族邀请消息")


                            }

                            ActivityUtils.finishToActivity(MessageSetGroupActivity::class.java,false)


                        }

                    }

                    override fun onError(t: Throwable) {
                        super.onError(t)
                        disDialogLoding()
                    }
                })
    }

    fun deleteUsers() {

        var builder = StringBuilder()

        var selectList = ArrayList<UserAddItem>()

        if(mNotFriendList.size>0){
            selectList.addAll(mNotFriendList)
        }

        val filterList = mFriendList.filter {
            it.checked
        }

        if(filterList.size>0){
            selectList.addAll(filterList)
        }

        for(m in selectList){
            builder.append(m.user_id)
            builder.append(",")
        }

        if(builder.length>0){
            builder = builder.deleteCharAt(builder.length-1)
        }

        showDialogLoding()

        RxUtils.loading(commonModel.actionDelUser(mFamilyId, builder.toString()), this)
                .subscribe(object : ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    override fun onNext(baseBean: BaseBean) {

                        disDialogLoding()

                        toast(baseBean.message)

                        if (baseBean.code == 1) {
//                            //发送单聊消息
//                            for(m in selectList){
//
//                               var message =  RequestEnterGroupMessage()
//                                message.from_content = "邀请" + m.nickname + "加入家族"
//                                message.to_content = "邀请加入" + mFamilyName + "家族"
//                                message.family_id = mFamilyId
//
//                                sendSingleMessage(m.user_id,Conversation.ConversationType.PRIVATE,message,"家族邀请消息")
//
//
//                            }

                            ActivityUtils.finishToActivity(MessageSetGroupActivity::class.java,false)


                        }

                    }

                    override fun onError(t: Throwable) {
                        super.onError(t)
                        disDialogLoding()
                    }
                })
    }

    /**
     * 发送自定义消息， pushContent 字段必须填写，否则无法收到该消息的推送。
     * message	将要发送的消息体。
     * pushContent	当客户端离线，接受推送通知时，通知的内容会显示为 pushContent 的内容。
     * pushData	收到该消息的推送时的附加信息。如果设置该字段，用户在收到该消息的推送时，能通过推送监听 onNotificationMessageArrived() 里的参数 PushNotificationMessage 的 getPushData() 方法获取。
     * callback	发送消息的回调。
     */
    private fun sendSingleMessage(mTargetId: String, mConversationType: Conversation.ConversationType, contactMessage: MessageContent, pushContent: String) {
        RongIM.getInstance().sendMessage(Message.obtain(mTargetId, mConversationType, contactMessage),
                pushContent, null, object : IRongCallback.ISendMessageCallback {
            override fun onAttached(message: Message) {

            }

            override fun onSuccess(message: Message) {

                LogUtils.debugInfo("发送家族邀请单聊消息成功")


            }

            override fun onError(message: Message, errorCode: RongIMClient.ErrorCode) {
                LogUtils.debugInfo("发送家族邀请单聊消息失败"+errorCode)
            }
        })
    }

    override fun onClick(v: View?) {
        when (v!!.id) {

            R.id.btn_search -> {//搜索按钮

                var textSearch = et_search_content.text.toString().trim()

                if (TextUtils.isEmpty(textSearch)) {//搜素内容为空

                    if (refresh_layout_search.visibility == View.VISIBLE) {//搜索页面在显示

                        refresh_layout_search.visibility = View.GONE

                        refresh_layout_friend.visibility = View.VISIBLE

                    } else {
                        toast("请输入搜索内容")
                        return
                    }

                }

                refresh_layout_search.visibility = View.VISIBLE

                refresh_layout_friend.visibility = View.GONE

                mPullRefreshBeanSearch.setRefresh(mPullRefreshBeanSearch, refresh_layout_search)

                getSearchDataFromServer()


            }
            R.id.btn_confirm -> {//确定按钮

                if (getSelectCount() == 0) {
                    toast("至少选择一个成员")
                    return
                }

                if(mFromCreateFamily==0){//如果是创建家族

                    commitInfo()

                } else if(mFromCreateFamily ==1){

                    addUsers()

                } else if(mFromCreateFamily ===2){


                    val puTongWindow1 = PuTongWindow(this)
                    puTongWindow1.showAtLocation(rightTitle, Gravity.CENTER, 0, 0)
                    puTongWindow1.titText.text = "确定要删除所选成员吗？"
                    puTongWindow1.cancel.setOnClickListener { v -> puTongWindow1.dismiss() }
                    puTongWindow1.sure.setOnClickListener { v ->
                        puTongWindow1.dismiss()
                        deleteUsers()
                    }

                }


            }

        }
    }

}
