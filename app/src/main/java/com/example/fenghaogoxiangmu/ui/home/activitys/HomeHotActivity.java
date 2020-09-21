package com.example.fenghaogoxiangmu.ui.home.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fenghaogoxiangmu.R;
import com.example.fenghaogoxiangmu.adapter.home.activity.HomeHotAdapter;
import com.example.fenghaogoxiangmu.adapter.home.activity.ImgCommentAdapter;
import com.example.fenghaogoxiangmu.base.BaseActivity;
import com.example.fenghaogoxiangmu.bean.home.activitybean.AddCartInfoBean;
import com.example.fenghaogoxiangmu.bean.home.activitybean.GoodDetailBean;
import com.example.fenghaogoxiangmu.common.CartCustomView;
import com.example.fenghaogoxiangmu.interfaces.home.activity.ICart;
import com.example.fenghaogoxiangmu.persenter.home.activitypersneter.GoodDetailpersenter;
import com.example.fenghaogoxiangmu.ui.own.auth.activity.LoginActivity;
import com.example.fenghaogoxiangmu.utils.SpUtils;
import com.example.fenghaogoxiangmu.utils.SystemUtils;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeHotActivity extends BaseActivity<ICart.Persenter> implements ICart.View {


    @BindView(R.id.layout_back)
    RelativeLayout layoutBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_name)
    TextView txtName;
    @BindView(R.id.txt_price)
    TextView txtPrice;
    @BindView(R.id.txt_product)
    TextView txtProduct;
    @BindView(R.id.layout_norms)
    FrameLayout layoutNorms;
    @BindView(R.id.layout_comment)
    FrameLayout layoutComment;
    @BindView(R.id.layout_imgs)
    LinearLayout layoutImgs;
    @BindView(R.id.layout_parameter)
    LinearLayout layoutParameter;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.rcy)
    RecyclerView rcy;
    @BindView(R.id.layout_collect)
    RelativeLayout layoutCollect;
    @BindView(R.id.img_cart)
    ImageView imgCart;
    @BindView(R.id.txt_buy)
    TextView txtBuy;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.item_gooddetail_addtime)
    TextView itemGooddetailAddtime;
    @BindView(R.id.item_gooddetail_content)
    TextView itemGooddetailContent;
    @BindView(R.id.item_gooddetail_comment_rv)
    RecyclerView itemGooddetailCommentRv;
    @BindView(R.id.hot_popu)
    TextView hotPopu;
    @BindView(R.id.layout)
    LinearLayout layout;
    @BindView(R.id.layout_cart)
    RelativeLayout layoutCart;
    @BindView(R.id.txt_addCart)
    TextView txtAddCart;
    @BindView(R.id.txtcount)
    TextView txtCount;

    private GoodDetailBean goodDetailBean;
    private int currentNum = 1;
    private String html = "<html>\n" +
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
    private ArrayList<GoodDetailBean.DataBeanX.InfoBean> list;
    private HomeHotAdapter homeHotAdapter;
    private ArrayList<GoodDetailBean.DataBeanX.CommentBean.DataBean.PicListBean> picListBeans;
    private ImgCommentAdapter imgCommentAdapter;
    private GoodDetailBean.DataBeanX.InfoBean info;
    private PopupWindow mPopWindow;


    @Override
    protected int getLayout() {
        return R.layout.activity_home_hot;
    }

    @Override
    protected void initView() {
        rcy.setLayoutManager(new GridLayoutManager(this, 2));
        list = new ArrayList<>();
        homeHotAdapter = new HomeHotAdapter(list, this);
        rcy.setAdapter(homeHotAdapter);
        hotPopu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopWindow();
            }
        });

    }


    @Override
    protected ICart.Persenter initPersenter() {
        return new GoodDetailpersenter();
    }

    @Override
    protected void initData() {
        int id = getIntent().getIntExtra("id", 0);
        persenter.getGoodDetail(id);
    }


    @Override
    public void getGoodDetailReturn(GoodDetailBean result) {
        goodDetailBean=result;
        info = result.getData().getInfo();
        list.add(info);
        homeHotAdapter.notifyDataSetChanged();
        //单个
        txtName.setText(info.getName());
        txtPrice.setText("$" + info.getRetail_price());
        txtTitle.setText(info.getGoods_brief());
        //banner刷新
        updateBanner(result.getData().getGallery());
        //品论
        if (result.getData().getComment().getCount() > 0) {
            layoutComment.setVisibility(View.VISIBLE);
            updateComment(result.getData().getComment());
        } else {
            layoutComment.setVisibility(View.GONE);
        }
        //设置参数
        updateParameter(result.getData().getAttribute());
        //详情信息的展示
        updateDetailInfo(result.getData().getInfo());
    }



    private void updateDetailInfo(GoodDetailBean.DataBeanX.InfoBean info) {
        if (!TextUtils.isEmpty(info.getGoods_desc())) {
            String h5 = info.getGoods_desc();
            html = html.replace("$", h5);
            webView.loadData(html, "text/html", "utf-8");
        }
    }

    private void updateParameter(List<GoodDetailBean.DataBeanX.AttributeBean> attribute) {
        layoutParameter.removeAllViews(); //清空
        for (GoodDetailBean.DataBeanX.AttributeBean item : attribute) {
            View view = LayoutInflater.from(this).inflate(R.layout.layout_parameter, null);
            TextView name = view.findViewById(R.id.txt_parameter_name);
            TextView value = view.findViewById(R.id.txt_parameter_value);
            name.setText(item.getName());
            value.setText(item.getValue());
            layoutParameter.addView(view);
        }
    }

    private void updateComment(GoodDetailBean.DataBeanX.CommentBean comment) {
        itemGooddetailAddtime.setText(comment.getData().getAdd_time());
        itemGooddetailContent.setText(comment.getData().getContent());
        picListBeans = new ArrayList<>();
        imgCommentAdapter = new ImgCommentAdapter(picListBeans, this);
        itemGooddetailCommentRv.setLayoutManager(new GridLayoutManager(this, 3));
        itemGooddetailCommentRv.setAdapter(imgCommentAdapter);
        picListBeans.addAll(comment.getData().getPic_list());
        imgCommentAdapter.notifyDataSetChanged();
    }

    private void updateBanner(List<GoodDetailBean.DataBeanX.GalleryBean> gallery) {
        if (banner.getTag() == null || (int) banner.getTag() == 0) {
            ArrayList<String> strings = new ArrayList<>();
            for (GoodDetailBean.DataBeanX.GalleryBean item : gallery) {
                strings.add(item.getImg_url());
            }
            banner.setImages(gallery);
            banner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    GoodDetailBean.DataBeanX.GalleryBean bean = (GoodDetailBean.DataBeanX.GalleryBean) path;
                    Glide.with(context).load(bean.getImg_url()).into(imageView);
                }
            }).start();

        }
    }

    private void showPopWindow() {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_popwindow_good, null);
        int height = SystemUtils.dp2px(this, 250);
        mPopWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, height);
        mPopWindow.setFocusable(false);
        mPopWindow.setOutsideTouchable(false);
        mPopWindow.setContentView(view);
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        CartCustomView cartCustomView = view.findViewById(R.id.layout_cartwindow);
        TextView txtClose = view.findViewById(R.id.txt_close);
        ImageView imggood = view.findViewById(R.id.img_good);
        TextView txtprice = view.findViewById(R.id.txt_price);
        Aplha(0.3f);
        TextView tvname = view.findViewById(R.id.tv_name);
        if (info != null){
            tvname.setText("已选择:   " + info.getName());
            txtprice.setText("价格：   " + info.getRetail_price());
            Glide.with(this).load(info.getPrimary_pic_url()).into(imggood);
        }
        txtClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindow.dismiss();
            }
        });
        //设置关闭弹窗时布局页面恢复不透明
        mPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Aplha(1f);
            }
        });
        int[] pt = new int[2];
        layout.getLocationInWindow(pt);
        mPopWindow.showAtLocation(layout, Gravity.NO_GRAVITY, 0, pt[1] - height);
        cartCustomView.initView();
        cartCustomView.setOnClickListener(new CartCustomView.IClick() {
            @Override
            public void clickCB(int value) {
                currentNum = value;
            }
        });
    }
    public void Aplha(float alpha) {

        Window window = getWindow();
        window.findViewById(R.id.nest).setAlpha(alpha);
    }


    @OnClick({R.id.layout_collect, R.id.layout_cart, R.id.txt_addCart})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_collect:
                break;
            case R.id.layout_cart:
                Intent intent = new Intent();
                setResult(1000,intent);
                finish();
                break;
            case R.id.txt_addCart:
                addCart();
                break;
        }
    }

    /**
     * 添加到购物车
     */
    private void addCart() {
        String islogin = SpUtils.getInstance().getString("token");
        if (!TextUtils.isEmpty(islogin)) {
            if (mPopWindow != null && mPopWindow.isShowing()) {
                if (goodDetailBean.getData().getProductList()!=null&&goodDetailBean.getData().getProductList().size() > 0) {
                        int goods_id = goodDetailBean.getData().getProductList().get(0).getGoods_id();
                        int id = goodDetailBean.getData().getProductList().get(0).getId();
                        persenter.addCart(goods_id, currentNum, id);
                }else{
                    Toast.makeText(this,"没有产品数据",Toast.LENGTH_SHORT).show();
                }
            }else{
                showPopWindow();
            }
        }else{
            Toast.makeText(this, "未登录", Toast.LENGTH_SHORT).show();
            //Intent跳转到登录
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    @Override
    public void addCartInfoReturn(AddCartInfoBean result) {
        if (result.getData() != null){
            int count = result.getData().getCartTotal().getGoodsCount();
            txtCount.setText(String.valueOf(count));
        }else {
            Toast.makeText(this, result.getErrmsg(), Toast.LENGTH_SHORT).show();
        }
    }
}

