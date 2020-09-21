package com.qutu.talk.activity.message;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.qutu.talk.R;
import com.qutu.talk.activity.SetActivity;
import com.qutu.talk.activity.my.MyPersonalCenterActivity;
import com.qutu.talk.activity.my.MyPersonalCenterTwoActivity;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.UserInfo;

/**
 * 会话页面
 */
public class MessageActivity extends MyBaseArmActivity {

    @BindView(R.id.toolbar_back)
    RelativeLayout toolbarBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_right)
    RelativeLayout toolbarRight;
    @BindView(R.id.img_bar_right)
    ImageView imgBarRight;
    private String targetId = "";

    boolean mIsGroupChat = false;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerCommonComponent.builder()
                .appComponent(appComponent)
                .commonModule(new CommonModule())
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_message;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        Intent intents = getIntent();
        Uri uri = intents.getData();
        if(uri.toString().contains("conversation/group")){//群聊
            mIsGroupChat = true;
        }
        targetId = uri.getQueryParameter("targetId");
//        LogUtils.debugInfo("====targetId:" + targetId);
//        FragmentManager fragmentManage = getSupportFragmentManager();
//        ConversationFragment fragement = (ConversationFragment) fragmentManage.findFragmentById(R.id.conversation);
//        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
//                .appendPath("conversation").appendPath(Conversation.ConversationType.PRIVATE.getName().toLowerCase())
//                .appendQueryParameter("targetId", ta).build();
//
//        fragement.setUri(uri);

//        RongIM.getInstance().startConversation(mContext, Conversation.ConversationType.PRIVATE , targetId, title)
//        RongIM.getInstance().
        RongIM.setConversationBehaviorListener(new RongIM.ConversationBehaviorListener() {

            @Override
            public boolean onUserPortraitClick(Context context, Conversation.ConversationType conversationType, UserInfo userInfo) {

                //在这里处理你想要跳转的activity，示例代码为YourAcitivy
//                Intent in = new Intent(context, SetActivity.class);
//                context.startActivity(in);
                Intent intent = new Intent(MessageActivity.this, MyPersonalCenterTwoActivity.class);
                intent.putExtra("sign", 1);
                intent.putExtra("id", userInfo.getUserId());
                LogUtils.debugInfo("====要跳了哟");
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onUserPortraitLongClick(Context context, Conversation.ConversationType conversationType, UserInfo userInfo) {
                return false;
            }

            @Override
            public boolean onMessageClick(Context context, View view, Message message) {
                return false;
            }

            @Override
            public boolean onMessageLinkClick(Context context, String s) {
                return false;
            }

            @Override
            public boolean onMessageLongClick(Context context, View view, Message message) {
                return false;
            }
        });
        toolbarRight.setVisibility(View.VISIBLE);
        if(targetId.equals("500")){
            imgBarRight.setVisibility(View.GONE);
        }
        if(mIsGroupChat){
            imgBarRight.setImageResource(R.mipmap.gd);
        }
        toolbarRight.setOnClickListener(v -> {
            if(mIsGroupChat){//群聊
                Intent intent = new Intent(this, MessageSetGroupActivity.class);
                intent.putExtra("family_id", targetId);
                ArmsUtils.startActivity(intent);
            } else {
                Intent intent = new Intent(this, MessageSetActivity.class);
                intent.putExtra("targetId", targetId);
                ArmsUtils.startActivity(intent);
            }
        });

        //删除会话
//        RongIM.getInstance().getRongIMClient().removeConversation(Conversation.ConversationType.PRIVATE, "18825077460", new RongIMClient.ResultCallback<Boolean>() {
//            @Override
//            public void onSuccess(Boolean aBoolean) {
////                Toast.makeText(context,aBoolean + "" ,Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onError(RongIMClient.ErrorCode errorCode) {
//
//            }
//        });
    }

    @Override
    public void onResume() {
        super.onResume();
        String title = getIntent().getData().getQueryParameter("title");
        toolbarTitle.setText(title);
    }

}
