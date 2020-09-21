package com.example.fenghaogoxiangmu.interfaces.home.activity;

import com.example.fenghaogoxiangmu.bean.home.activitybean.AddCartInfoBean;
import com.example.fenghaogoxiangmu.bean.shopping.CartBean;
import com.example.fenghaogoxiangmu.bean.home.activitybean.DeleteCartBean;
import com.example.fenghaogoxiangmu.bean.home.activitybean.GoodDetailBean;
import com.example.fenghaogoxiangmu.interfaces.IBasePersenter;
import com.example.fenghaogoxiangmu.interfaces.IBaseView;

public interface ICart {
    interface View extends IBaseView {
        void getGoodDetailReturn(GoodDetailBean result);
        void addCartInfoReturn(AddCartInfoBean result);
    }

    interface Persenter extends IBasePersenter<ICart.View> {
        void getGoodDetail(int id);

        void addCart(int goodsId,int number,int productId);
    }
    /**
     * 购物车接口
     */
    interface ICartView extends IBaseView{
        void getCartListReturn(CartBean result);

        void deleteCartListReturn(DeleteCartBean result);
    }

    interface ICartPersenter extends IBasePersenter<ICartView>{

        //获取购物车的数据
        void getCartList();

        //删除购物车数据
        void deleteCartList(String productIds);

    }

}
