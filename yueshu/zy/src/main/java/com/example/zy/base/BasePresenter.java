package com.example.zy.base;

import java.util.ArrayList;

public abstract class BasePresenter<V extends BaseView> {
    public V mView;
    public ArrayList<BaseModel> models=new ArrayList<>();
    public BasePresenter(){
        initModel();
    }
    public void BindView(V view){
        this.mView=view;
    }
    public void addModel(BaseModel baseModel){
        models.add(baseModel);
    }

    protected abstract void initModel();

    public void removerModel(){
        mView=null;
        for (int i = 0; i < models.size(); i++) {
            models.get(i).Dostey();
        }
    }
}
