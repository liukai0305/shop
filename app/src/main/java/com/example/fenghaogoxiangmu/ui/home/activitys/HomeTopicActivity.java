package com.example.fenghaogoxiangmu.ui.home.activitys;

import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fenghaogoxiangmu.R;
import com.example.fenghaogoxiangmu.adapter.home.activity.HomeTopicBean;
import com.example.fenghaogoxiangmu.adapter.home.activity.TopicLieBioaAdapter;
import com.example.fenghaogoxiangmu.adapter.home.activity.TopicPingLunAdapter;
import com.example.fenghaogoxiangmu.base.BaseActivity;
import com.example.fenghaogoxiangmu.bean.home.activitybean.TopicPingLBean;
import com.example.fenghaogoxiangmu.bean.home.activitybean.TopicTuiJBean;
import com.example.fenghaogoxiangmu.interfaces.home.activity.ITopic;
import com.example.fenghaogoxiangmu.persenter.home.activitypersneter.Topicoersenter;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;

public class HomeTopicActivity extends BaseActivity<ITopic.TPresenter> implements ITopic.TView {

    @BindView(R.id.topic_webView)
    WebView topicWebView;
    @BindView(R.id.topic_layout_comment)
    FrameLayout topicLayoutComment;
    @BindView(R.id.topic_view)
    View topicView;
    @BindView(R.id.topic_rcy_pinglun)
    RecyclerView topicRcyPinglun;
    @BindView(R.id.topic_tv_cahkan)
    TextView topicTvCahkan;
    @BindView(R.id.topic_rcy_liebiao)
    RecyclerView topicRcyLiebiao;

    private String htmlStr = "<html>\n" +
            "            <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no\"/>\n" +
            "            <head>\n" +
            "                <style>\n" +
            "                    p{\n" +
            "                        margin:0px;\n" +
            "                    }\n" +
            "                    img{\n" +
            "                        width:100%;\n" +
            "                        height:auto;\n" +
            "                    }\n" +
            "                </style>\n" +
            "            </head>\n" +
            "            <body>\n" +
            "                $\n" +
            "            </body>\n" +
            "        </html>";
    private ArrayList<TopicTuiJBean.DataBean> list;
    private TopicLieBioaAdapter adapter;
    private ArrayList<TopicPingLBean.DataBeanX.DataBean> dataBeans;
    private TopicPingLunAdapter topicPingLunAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_home_topic;
    }

    @Override
    protected void initView() {
        topicRcyLiebiao.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new TopicLieBioaAdapter(list, this);
        topicRcyLiebiao.setAdapter(adapter);
        topicRcyPinglun.setLayoutManager(new LinearLayoutManager(this));
        dataBeans = new ArrayList<>();
        topicPingLunAdapter = new TopicPingLunAdapter(dataBeans, this);
        topicRcyPinglun.setAdapter(topicPingLunAdapter);
    }

    @Override
    protected ITopic.TPresenter initPersenter() {
        return new Topicoersenter();
    }

    @Override
    protected void initData() {
        int id = getIntent().getIntExtra("id", 0);
        persenter.getTopicBean(id);
        persenter.getTopicTuiBean(id);
        HashMap<String, Integer> map = new HashMap<>();
        map.put("valueId",id);
        map.put("typeId",1);
        map.put("size",5);
        persenter.getTopicPingBean(map);
    }

    @Override
    public void getTopicBeanR(HomeTopicBean topicBean) {
        updateDetailInfo(topicBean.getData());

    }

    @Override
    public void getTopicPingLR(TopicPingLBean pingLBean) {

        dataBeans.addAll(pingLBean.getData().getData());
        topicPingLunAdapter.notifyDataSetChanged();

    }

    @Override
    public void getTopicTuiJR(TopicTuiJBean topicTuiJBean) {
        list.addAll(topicTuiJBean.getData());
        adapter.notifyDataSetChanged();
    }



    private void updateDetailInfo(HomeTopicBean.DataBean data) {
        String content = data.getContent();
        if (!TextUtils.isEmpty(content)) {
            int start = 0;
            int index = content.indexOf("//");
            String html = content.substring(index + 2);
            StringBuilder sb = new StringBuilder();
            sb.append(content.substring(start, index));
            while (index != -1) {
                sb.append("http://");
                index = html.indexOf("//");
                if (index == -1) {
                    sb.append(html);
                    break;
                }
                sb.append(html.substring(start, index));
                html = html.substring(index + 2);
            }
            htmlStr = htmlStr.replace("$", sb.toString());
            topicWebView.loadDataWithBaseURL("about:blank", htmlStr, "text/html", "utf-8", null);
        }
    }

}