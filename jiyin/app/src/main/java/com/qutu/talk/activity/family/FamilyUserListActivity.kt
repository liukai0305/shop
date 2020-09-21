package com.qutu.talk.activity.family

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.support.annotation.NonNull
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.jess.arms.di.component.AppComponent
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.qutu.talk.R
import com.qutu.talk.adapter.FamilyUserListAdapter
import com.qutu.talk.app.utils.RxUtils
import com.qutu.talk.base.MyBaseArmActivity
import com.qutu.talk.bean.BaseBean
import com.qutu.talk.bean.FamilyUser
import com.qutu.talk.bean.GetFamilyUserListResult
import com.qutu.talk.bean.PullRefreshBean
import com.qutu.talk.di.CommonModule
import com.qutu.talk.di.DaggerCommonComponent
import com.qutu.talk.popup.EditeFamilyUserDialog
import com.qutu.talk.service.CommonModel
import com.qutu.talk.utils.DealRefreshHelper
import com.qutu.talk.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_family_list.*
import kotlinx.android.synthetic.main.activity_family_user_list.*
import kotlinx.android.synthetic.main.fragment_order.ly_view_no_data
import kotlinx.android.synthetic.main.fragment_order.refresh_layout
import kotlinx.android.synthetic.main.include_title.*
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class FamilyUserListActivity : MyBaseArmActivity() {

    @Inject
    open lateinit var commonModel: CommonModel

    internal var mPullRefreshBean = PullRefreshBean()

    var mDataList = ArrayList<FamilyUser>()

    var mAdapter: FamilyUserListAdapter? = null

    var mFamily_id = ""

    var mFamilyName = ""

    var mUser_type = 0

    override fun setupActivityComponent(appComponent: AppComponent) {

        DaggerCommonComponent.builder()
                .appComponent(appComponent)
                .commonModule(CommonModule())
                .build()
                .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_family_user_list
    }

    override fun initData(savedInstanceState: Bundle?) {

        setTitle("家族成员")

        mFamily_id = intent.getStringExtra("family_id")

        mFamilyName = intent.getStringExtra("family_name")

        mUser_type = intent.getIntExtra("user_type",0)

        if(mUser_type == 1 || mUser_type == 2){

            toolbar_right.visibility = View.VISIBLE

            img_bar_right.setImageResource(R.mipmap.gd)

            img_bar_right.setOnClickListener {//添加删除群成员，管理员只能添加成员

                var selectGameSegmentDialog: EditeFamilyUserDialog = EditeFamilyUserDialog(this, mUser_type)

                selectGameSegmentDialog.setOnItemSelectListener(object : EditeFamilyUserDialog.onItemClickListener {

                    override fun onAddMemeber() {

                        selectGameSegmentDialog.dismiss()

                        var intent = Intent(this@FamilyUserListActivity,CreateFamilySecondActivity::class.java)

                        intent.putExtra("family_id",mFamily_id)

                        intent.putExtra("family_name",mFamilyName)

                        intent.putExtra("is_from_create",1)

                        startActivity(intent)

                    }

                    override fun onDeleteMemeber() {

                        selectGameSegmentDialog.dismiss()

                        var intent = Intent(this@FamilyUserListActivity,CreateFamilySecondActivity::class.java)

                        intent.putExtra("family_id",mFamily_id)

                        intent.putExtra("is_from_create",2)

                        startActivity(intent)

                    }

                })
                selectGameSegmentDialog.show()

            }

        }

        initRecyclerView()

        initRefreshLayout()

        getDataFromServer()

    }

    private fun initRefreshLayout() {

        refresh_layout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(@NonNull refreshLayout: RefreshLayout) {

                mPullRefreshBean.setLoardMore(mPullRefreshBean, refresh_layout)

                getDataFromServer()

            }

            override fun onRefresh(@NonNull refreshLayout: RefreshLayout) {

                mPullRefreshBean.setRefresh(mPullRefreshBean, refresh_layout)

                getDataFromServer()

            }
        })

    }

    private fun initRecyclerView() {

        val manager = LinearLayoutManager(this)

        manager.setOrientation(RecyclerView.VERTICAL)

        rlv_family.setHasFixedSize(true)

        rlv_family.setLayoutManager(manager)

        mAdapter = FamilyUserListAdapter(this, R.layout.item_family_user, mDataList)

        mAdapter!!.setOnItemLongClickListener { adapter, view, position ->
            if(mUser_type==2){
                val builder:AlertDialog.Builder=AlertDialog.Builder(this)
                if(mAdapter!!.data[position].user_type==1){
                    builder.setMessage("取消${mAdapter!!.data[position].nickname}的管理")
                }else{
                    builder.setMessage("设${mAdapter!!.data[position].nickname}为管理")
                }

                builder.setNegativeButton("取消") { dialog, which ->
                    dialog.dismiss()
                }
                builder.setPositiveButton("确定") { dialog, which ->
                    if(mAdapter!!.data[position].user_type==1){
                        cancelFamilyManager(mAdapter!!.data[position].user_id)
                    }else{
                        setFamilyManager(mAdapter!!.data[position].user_id)
                    }
                    dialog.dismiss()
                }
                builder.show()
            }
            return@setOnItemLongClickListener true
        }

        rlv_family.setHasFixedSize(true)

        rlv_family.setLayoutManager(manager)

//        mAdapter!!.setHasStableIds(true)//防止数据错乱

        rlv_family.adapter = mAdapter

    }

    //获取数据
    private fun getDataFromServer() {
        RxUtils.loading(commonModel.getFamilyUserList(mFamily_id,mPullRefreshBean.pageIndex.toString()))
                .subscribe(object : ErrorHandleSubscriber<GetFamilyUserListResult>(mErrorHandler) {
                    override fun onNext(orderResult: GetFamilyUserListResult) {

                        if (orderResult != null && orderResult.data != null) {

                            var list: List<FamilyUser> = orderResult.data

                            DealRefreshHelper<FamilyUser>().dealDataToUI(refresh_layout, mAdapter!!, ly_view_no_data, list, mDataList, mPullRefreshBean)

                        } else {
                            DealRefreshHelper<FamilyUser>().dealDataToUI(refresh_layout, mAdapter!!, ly_view_no_data, ArrayList<FamilyUser>(), mDataList, mPullRefreshBean)
                        }
                    }

                    override fun onError(t: Throwable) {
                        super.onError(t)
                        DealRefreshHelper<FamilyUser>().dealDataToUI(refresh_layout, mAdapter!!, ly_view_no_data, ArrayList<FamilyUser>(), mDataList, mPullRefreshBean)
                    }

                })

    }

    //获取数据
    private fun setFamilyManager(userId:String) {
        RxUtils.loading(commonModel.setFamilyManager(mFamily_id,userId))
                .subscribe(object:ErrorHandleSubscriber<BaseBean>(mErrorHandler){
                    override fun onNext(t: BaseBean) {
                        ToastUtil.showToast(this@FamilyUserListActivity,"设置成功")
                        refresh_layout.autoRefresh()
                    }

                })

    }

    //获取数据
    private fun cancelFamilyManager(userId:String) {
        RxUtils.loading(commonModel.cancelFamilyManager(mFamily_id,userId))
                .subscribe(object:ErrorHandleSubscriber<BaseBean>(mErrorHandler){
                    override fun onNext(t: BaseBean) {
                        ToastUtil.showToast(this@FamilyUserListActivity,"取消成功")
                        refresh_layout.autoRefresh()
                    }

                })

    }

}
