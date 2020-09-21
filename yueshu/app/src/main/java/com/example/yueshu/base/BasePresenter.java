package com.example.yueshu.base;

import java.util.ArrayList;

public abstract class BasePresenter<V extends BaseView> {
    public V mView;
    public ArrayList<BaseMedol> medols=new ArrayList<>();
    public BasePresenter(){
        initModel();
    }
    public  void BindView(V view){
        this.mView=view;
    }
    public void addModel(BaseMedol baseMedol){
        medols.add(baseMedol);
    }

    protected abstract void initModel();

    public void Disposable(){
        mView=null;
        for (int i = 0; i < medols.size(); i++) {
            medols.get(i).Destroy();
        }
    }
}
