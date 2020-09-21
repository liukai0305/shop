package com.example.xiangmuyi.ui.home.fragments.zheshiwenzhang;

import android.Manifest;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import com.example.xiangmuyi.R;
import com.example.xiangmuyi.base.BaseActivity;
import com.example.xiangmuyi.bean.homebean.WanZhangEventsBean;
import com.example.xiangmuyi.interfaces.home.IHome;
import com.example.xiangmuyi.persenter.home.wenzhang.EventPersenter;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import butterknife.BindView;

public class YanJinActivity extends BaseActivity<IHome.EventPersenter> implements IHome.EventView {

    @BindView(R.id.mTool)
    Toolbar mTool;
    @BindView(R.id.tv_detail_title)
    TextView tvDetailTitle;
    @BindView(R.id.tv_detail_date)
    TextView tvDetailDate;
    @BindView(R.id.tv_bg)
    TextView tvBg;
    @BindView(R.id.tv_detail_content)
    HtmlTextView tvDetailContent;
    private int id;


    @Override
    protected int getLayout() {
        return R.layout.activity_yan_jin;
    }

    @Override
    protected void initView() {
        initPer();
        setSupportActionBar(mTool);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);

    }

    private void initPer() {
        String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
        ActivityCompat.requestPermissions(this, mPermissionList, 123);
    }

    @Override
    protected IHome.EventPersenter initPersenter() {
        return new EventPersenter();
    }

    @Override
    protected void initData() {
        persenter.getEvent(id);
    }

    @Override
    public void getEvent(WanZhangEventsBean result) {
        String content = result.getData().getContent();
        tvDetailTitle.setText(result.getData().getTitle());
        tvDetailDate.setText(result.getData().getCreateTime());
        tvDetailContent.setHtml(result.getData().getContent(), new HtmlHttpImageGetter(tvDetailContent));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "分享");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                in();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void in() {
        new ShareAction(this)
                .withText("hello")
                .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN)
                .setCallback(shareListener)
                .open();
    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(YanJinActivity.this, "成功", Toast.LENGTH_SHORT).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(YanJinActivity.this, "失败", Toast.LENGTH_SHORT).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(YanJinActivity.this, "取消", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }


}