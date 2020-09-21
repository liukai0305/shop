package com.example.tongpao.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tongpao.interfaces.home.IHome;

import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter {
    protected Context context;
    protected List<T> data;
    private IClick callback;

    public void setClick(IClick cb){
        callback = cb;
    }
    public BaseAdapter(Context context, List<T> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(getLayout(), parent,false);
        final BaseVirwHolder baseVirwHolder = new BaseVirwHolder(inflate);
        baseVirwHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callback!=null){
                    callback.click(baseVirwHolder.getLayoutPosition());
                }
            }
        });
        return baseVirwHolder;
    }

    protected abstract int getLayout();

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BaseVirwHolder baseVirwHolder = (BaseVirwHolder)holder;
        T t_data = data.get(position);
        bindData(baseVirwHolder,t_data,position);
    }

    protected abstract void bindData(BaseVirwHolder baseVirwHolder, T t_data,int position);

    public interface IClick{

        void click(int pos);

    }
    @Override
    public int getItemCount() {
        return data.size();
    }
    public class BaseVirwHolder extends RecyclerView.ViewHolder{
            SparseArray sparseArray = new SparseArray();
        public BaseVirwHolder(@NonNull View itemView) {
            super(itemView);
        }

        public View getViewById(int id){
            View view = (View) sparseArray.get(id);
            if (view==null){
                view = itemView.findViewById(id);
                sparseArray.append(id,view);
            }
            return view;
        }
    }
}
