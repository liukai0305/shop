package com.qutu.talk.bean;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.jess.arms.base.BaseApplication;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.qutu.talk.R;
import com.qutu.talk.app.Api;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.Constant;
import com.qutu.talk.utils.OkGoUpdateHttpUtil;
import com.qutu.talk.utils.ToastUtil;
import com.vector.update_app.HttpManager;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.provider.IContainerItemProvider;
import io.rong.imlib.model.Message;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

@ProviderTag(messageContent = RequestEnterGroupMessage.class)
public class EnterGroupMessageItemProvider extends IContainerItemProvider.MessageProvider<RequestEnterGroupMessage> {

    //将数据填充 View 上。
    @Override
    public void bindView(View view, int i, RequestEnterGroupMessage requestEnterGroupMessage, UIMessage uiMessage) {

        ViewHolder holder = (ViewHolder) view.getTag();

        if (uiMessage.getMessageDirection() == Message.MessageDirection.SEND) {//消息方向，自己发送的
            holder.lineView.setVisibility(View.GONE);
            holder.layoutButton.setVisibility(View.GONE);
            holder.message.setText(requestEnterGroupMessage.from_content);
//            holder.message.setBackgroundResource(io.rong.imkit.R.drawable.rc_ic_bubble_right);
        } else {
            holder.lineView.setVisibility(View.VISIBLE);
            holder.layoutButton.setVisibility(View.VISIBLE);
            holder.message.setText(requestEnterGroupMessage.to_content);
            holder.tvRefuse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Map<String, String> params = new HashMap<String, String>();

                    params.put("family_id", requestEnterGroupMessage.family_id);

                    new OkGoUpdateHttpUtil().asyncPost(Api.APP_DOMAIN + "actionRefuseJoin",params,new HttpManager.Callback() {
                        @Override
                        public void onResponse(String result) {
                            if (result != null) {
                                LogUtils.debugInfo("发送请求成功"+result);
                                BaseBean baseBean = JSON.parseObject(result, BaseBean.class);
                                try{
                                    ToastUtil.showToast(BaseApplication.mApplication,baseBean.getMessage());
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onError(String error) {
                            LogUtils.debugInfo("发送请求失败"+error);
                        }
                    });
//                    CommonModel commonModel = new CommonModel(new IRepositoryManager() {
//                        @NonNull
//                        @Override
//                        public <T> T obtainRetrofitService(@NonNull Class<T> service) {
//                            return null;
//                        }
//
//                        @NonNull
//                        @Override
//                        public <T> T obtainCacheService(@NonNull Class<T> cache) {
//                            return null;
//                        }
//
//                        @Override
//                        public void clearAllCache() {
//
//                        }
//
//                        @NonNull
//                        @Override
//                        public Context getContext() {
//                            return null;
//                        }
//                    });
//
//                    RxUtils.loading(commonModel.actionRefuseJoin(requestEnterGroupMessage.family_id))
//                            .subscribe(new ErrorHandleSubscriber<BaseBean>(ArmsUtils.obtainAppComponentFromContext(BaseApplication.mApplication).rxErrorHandler()) {
//                                @Override
//                                public void onNext(BaseBean agreementBean) {
//                                    LogUtils.debugInfo("发送请求成功");
//                                    ToastUtil.showToast(BaseApplication.mApplication,agreementBean.getMessage());
//                                }
//
//                                @Override
//                                public void onError(Throwable t) {
//                                    super.onError(t);
//                                    LogUtils.debugInfo("发送请求失败");
//                                }
//                            });
                }
            });

            holder.tvAgree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Map<String, String> params = new HashMap<String, String>();

                    params.put("family_id", requestEnterGroupMessage.family_id);

                    new OkGoUpdateHttpUtil().asyncPost(Api.APP_DOMAIN + "actionAgreeJoin",params,new HttpManager.Callback() {
                        @Override
                        public void onResponse(String result) {
                            if (result != null) {
                                LogUtils.debugInfo("发送请求成功"+result);
                                BaseBean baseBean = JSON.parseObject(result, BaseBean.class);
                                try{
                                    ToastUtil.showToast(BaseApplication.mApplication,baseBean.getMessage());
                                    EventBus.getDefault().post(new FirstEvent("指定发送", Constant.TUICHUFAMILY));
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onError(String error) {
                            LogUtils.debugInfo("发送请求失败"+error);
                        }
                    });

//                    CommonModel commonModel = new CommonModel(new IRepositoryManager() {
//                        @NonNull
//                        @Override
//                        public <T> T obtainRetrofitService(@NonNull Class<T> service) {
//                            return null;
//                        }
//
//                        @NonNull
//                        @Override
//                        public <T> T obtainCacheService(@NonNull Class<T> cache) {
//                            return null;
//                        }
//
//                        @Override
//                        public void clearAllCache() {
//
//                        }
//
//                        @NonNull
//                        @Override
//                        public Context getContext() {
//                            return null;
//                        }
//                    });
//
//                    RxUtils.loading(commonModel.actionAgreeJoin(requestEnterGroupMessage.family_id))
//                            .subscribe(new ErrorHandleSubscriber<BaseBean>(ArmsUtils.obtainAppComponentFromContext(BaseApplication.mApplication).rxErrorHandler()) {
//                                @Override
//                                public void onNext(BaseBean agreementBean) {
//                                    LogUtils.debugInfo("发送请求成功");
//                                    ToastUtil.showToast(BaseApplication.mApplication,agreementBean.getMessage());
//                                }
//
//                                @Override
//                                public void onError(Throwable t) {
//                                    super.onError(t);
//                                    LogUtils.debugInfo("发送请求失败");
//                                }
//                            });

                }
            });
//            holder.message.setBackgroundResource(io.rong.imkit.R.drawable.rc_ic_bubble_left);
        }
//        AndroidEmoji.ensure((Spannable) holder.message.getText());//显示消息中的 Emoji 表情。
    }

    //该条消息为该会话的最后一条消息时，会话列表要显示的内容，通过该方法进行定义。
    @Override
    public Spannable getContentSummary(RequestEnterGroupMessage requestEnterGroupMessage) {
        return  new SpannableString("家族邀请消息");
    }

    //点击该类型消息触发。
    @Override
    public void onItemClick(View view, int i, RequestEnterGroupMessage requestEnterGroupMessage, UIMessage uiMessage) {

    }

    //初始化 View。
    @Override
    public View newView(Context context, ViewGroup viewGroup) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_rongyun_enter_group, null);

        ViewHolder holder = new ViewHolder();

        holder.message = (TextView) view.findViewById(R.id.tv_invate_intro);

        holder.tvRefuse = (TextView) view.findViewById(R.id.tv_refuse);

        holder.tvAgree = (TextView) view.findViewById(R.id.tv_agree);

        holder.lineView =  view.findViewById(R.id.view_line_msg);

        holder.layoutButton =  view.findViewById(R.id.layout_button);

        view.setTag(holder);

        return view;
    }

    class ViewHolder {
        TextView message;
        TextView tvRefuse;
        TextView tvAgree;
        View lineView;
        LinearLayout layoutButton;
    }
}
