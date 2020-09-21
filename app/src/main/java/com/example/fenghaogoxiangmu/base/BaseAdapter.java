package com.example.fenghaogoxiangmu.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter {
    protected List<T> mData;
    protected Context context;

    public interface OnItemClickLisf{
        void click(int position);
    }


    private OnItemClickLisf onItemClickLis;

    public void setOnItemClickLis(OnItemClickLisf onItemClickLis) {
        this.onItemClickLis = onItemClickLis;
    }

    public BaseAdapter(List<T> data, Context context) {
        this.mData = data;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(getLayout(),parent,false);
        BaseViewHolder vh = new BaseViewHolder(view);
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickLis!=null){
                    onItemClickLis.click(vh.getLayoutPosition());
                }
            }
        });
        return vh;
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BaseViewHolder vh=(BaseViewHolder) holder;
        T t = mData.get(position);
        bindData(vh,t,position);
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }


    protected abstract int getLayout();

    protected abstract void bindData(BaseViewHolder vh, T data, int position);

    public class BaseViewHolder extends RecyclerView.ViewHolder{
            SparseArray views=new SparseArray();
        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
        }
        public View getViewById(int id){
            View view= (View) views.get(id);
            if (view==null){
                view=itemView.findViewById(id);
                views.append(id,view);
            }
            return view;
        }
    }
}
