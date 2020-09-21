package com.qutu.talk.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.qutu.talk.R;
import com.qutu.talk.adapter.MyMusciAdapter;
import com.qutu.talk.base.MyBaseArmFragment;
import com.qutu.talk.bean.FirstEvent;
import com.qutu.talk.bean.LocalMusicInfo;
import com.qutu.talk.bean.MessageEvent;
import com.qutu.talk.bean.StateMessage;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.popup.MiniCustomPopWindow;
import com.qutu.talk.utils.Constant;
import com.qutu.talk.utils.MyUtil;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.litepal.LitePal;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.qutu.talk.utils.Constant.YINYUESHUAXIN;

/**
 * 作者:sgm
 * 描述:我的音乐
 */
public class MyMusicFragment extends MyBaseArmFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.no_data_image)
    ImageView noDataImage;
    @BindView(R.id.no_data_text)
    TextView noDataText;
    @BindView(R.id.no_data)
    LinearLayout noData;

    private MyMusciAdapter localMusciAdapter;
    private PopupWindow mDelMusic;
    private ImageView mDelImage;
    private int lastItemPosition;

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerCommonComponent.builder()
                .appComponent(appComponent)
                .commonModule(new CommonModule())
                .build()
                .inject(this);
    }

    @Override
    public View getLayoutView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return ArmsUtils.inflate(getActivity(), R.layout.fragment_my_music);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        noDataImage.setImageResource(R.mipmap.no_music);
        noDataText.setText("还没有音乐哦，快去添加吧~");
        localMusciAdapter = new MyMusciAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(localMusciAdapter);
        try {
            List<LocalMusicInfo> listLocal = LitePal.findAll(LocalMusicInfo.class);
            if (listLocal.size() == 0 || listLocal == null) {
                noData.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            } else {
                noData.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                localMusciAdapter.setNewData(listLocal);
            }
        } catch (Exception e) {

        }

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                //判断是当前layoutManager是否为LinearLayoutManager
                // 只有LinearLayoutManager才有查找第一个和最后一个可见view位置的方法
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
                    //获取最后一个可见view的位置
                    lastItemPosition = linearManager.findLastVisibleItemPosition();
                    LogUtils.debugInfo("====最后一个item", lastItemPosition + "");
                    //获取第一个可见view的位置
//                    int firstItemPosition = linearManager.findFirstVisibleItemPosition();

                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }
        });
        localMusciAdapter.setOnItemClickListener((adapter, view, position) -> {
            boolean isStart = localMusciAdapter.getData().get(position).isStart;
            List<LocalMusicInfo> data = localMusciAdapter.getData();
            if (isStart) {
                localMusciAdapter.getData().get(position).isStart = false;
                localMusciAdapter.notifyItemChanged(position);
                EventBus.getDefault().post(new FirstEvent(position + "", Constant.YINYUEZANTING));
            } else {
                for (LocalMusicInfo list : data) {
                    list.isStart = false;
                }
                localMusciAdapter.getData().get(position).isStart = true;
                localMusciAdapter.notifyDataSetChanged();
                EventBus.getDefault().post(new FirstEvent(position + "", Constant.YINYUEBOFANG));
            }
        });
        localMusciAdapter.setOnItemLongClickListener((adapter, view, position) -> {
            LogUtils.debugInfo("====点击的地方", position + "");
//            if (mDelMusic == null) {
                View view1 = getLayoutInflater().inflate(R.layout.cancel_collection_item, null);
//                mDelMusic = new PopupWindow(view1, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
               ImageView imgDel = view1.findViewById(R.id.cancel_collection);
//                mDelMusic.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                mDelMusic.setFocusable(true);

                int windowPos[] = MyUtil.calculatePopWindowPos(view, view1);
                int xOff = QMUIDisplayHelper.dp2px(getActivity(),20);//偏移
                windowPos[0] -= xOff;
                final MiniCustomPopWindow popWindow = new MiniCustomPopWindow.PopupWindowBuilder(getActivity())
                        .setView(view1)
                        .setFocusable(true)
                        .setOutsideTouchable(true)
                        .create();
                popWindow.showAtLocation(view, Gravity.TOP | Gravity.START, windowPos[0], windowPos[1]);

                boolean isUp = MyUtil.isPopWindowUp(view, view1);

//            }
            if(isUp){
                imgDel.setImageResource(R.mipmap.tc);
            } else {
                imgDel.setImageResource(R.mipmap.music_sc);
            }
//            mDelMusic.showAsDropDown(view, 0, -200, Gravity.RIGHT | Gravity.TOP);


            imgDel.setOnClickListener(v -> {
//                int delete = LitePal.delete(LocalMusicInfo.class, position + 1);
//                int i = LitePal.deleteAll(LocalMusicInfo.class, "name=?", localMusciAdapter.getData().get(position).name);
//                LogUtils.debugInfo("====删除的歌曲name"+localMusciAdapter.getData().get(position).name);
                localMusciAdapter.getData().remove(position);
                localMusciAdapter.notifyDataSetChanged();
                //通知音乐列表改变
                MessageEvent messageEvent = new MessageEvent();
                messageEvent.setStateMessage(StateMessage.MUSIC_CHANGE);
                EventBus.getDefault().post(messageEvent);
                LogUtils.debugInfo("====发送音乐改变");
//                LogUtils.debugInfo("====删除的" + i);
//                mDelMusic.dismiss();
                popWindow.dissmiss();
            });
            return true;
        });
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveMsg(FirstEvent event) {
        String tag = event.getTag();
        if (YINYUESHUAXIN.equals(tag)) {
            recyclerView.setVisibility(View.VISIBLE);
            noData.setVisibility(View.GONE);
            List<LocalMusicInfo> listLocal = LitePal.findAll(LocalMusicInfo.class);
            localMusciAdapter.setNewData(listLocal);
        }
    }
}
