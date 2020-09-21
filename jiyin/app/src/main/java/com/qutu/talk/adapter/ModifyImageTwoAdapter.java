package com.qutu.talk.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.lzy.imagepicker.bean.ImageItem;
import com.qutu.talk.R;
import com.qutu.talk.utils.FullScreenUtil;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;

public class ModifyImageTwoAdapter extends RecyclerView.Adapter<ModifyImageTwoAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private Context context;
    private List<String> list = new ArrayList<>();
    private List<String> mList = new ArrayList<>();
    private int selectMax = 1;

    public static final int TYPE_CAMERA = 1;
    public static final int TYPE_PICTURE = 2;

    /**
     * 点击添加图片跳转
     */
    private onAddPicClickListener mOnAddPicClickListener;

    public interface onAddPicClickListener {
        void onAddPicClick();
    }

    public ModifyImageTwoAdapter(Context context, onAddPicClickListener mOnAddPicClickListener) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        this.mOnAddPicClickListener = mOnAddPicClickListener;
    }

    @NonNull
    @Override
    public ModifyImageTwoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.modiy_item, viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    /**
     * 设置值
     */
    @Override
    public void onBindViewHolder(@NonNull ModifyImageTwoAdapter.ViewHolder viewHolder, int position) {
        if (getItemViewType(position) == TYPE_CAMERA) {
            if (list.size() != 0) {
                viewHolder.imageView.setImageResource(R.mipmap.zl_sctp);
                viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mOnAddPicClickListener.onAddPicClick();
                    }
                });
            }else {
                viewHolder.imageView.setImageResource(R.mipmap.zl_sctp);
                viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mOnAddPicClickListener.onAddPicClick();
                    }
                });
            }
            viewHolder.iv_del.setVisibility(View.GONE);
        } else {
            viewHolder.iv_del.setVisibility(View.VISIBLE);
            viewHolder.iv_del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index = viewHolder.getAdapterPosition();
                    // 这里有时会返回-1造成数据下标越界,具体可参考getAdapterPosition()源码，
                    // 通过源码分析应该是bindViewHolder()暂未绘制完成导致，知道原因的也可联系我~感谢
                    if (index != RecyclerView.NO_POSITION) {
                        list.remove(index);
                        notifyItemRemoved(index);
                        notifyItemRangeChanged(index, list.size());
                    }
                }
            });

            viewHolder.imageView.setOnClickListener(v -> {
                FullScreenUtil.showFullScreenDialog(viewHolder.itemView.getContext(), position, list);
            });

            String path = list.get(position);
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.color.white)
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(viewHolder.itemView.getContext())
                    .load(path)
                    .apply(options)
                    .into(viewHolder.imageView);
        }


    }

    @Override
    public int getItemCount() {
        if (list.size() < selectMax) {
            return list.size() + 1;
        } else {
            return list.size();
        }
    }

    private boolean isShowAddItem(int position) {
        int size = list.size() == 0 ? 0 : list.size();
        return position == size;
    }

    @Override
    public int getItemViewType(int position) {
        if (isShowAddItem(position)) {
            return TYPE_CAMERA;
        } else {
            return TYPE_PICTURE;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        QMUIRadiusImageView imageView;
        ImageView iv_del;

        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.fiv);
            iv_del = view.findViewById(R.id.iv_del);
        }
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setSelectMax(int selectMax) {
        this.selectMax = selectMax;
    }

    public List<String> getList() {
        return list;
    }
}
