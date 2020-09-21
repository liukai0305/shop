package com.example.fenghaogoxiangmu.common;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.fenghaogoxiangmu.R;

public class CartCustomView extends LinearLayout implements View.OnClickListener {

    private int min=1;
    private int max=9999;
    private TextView txtSubtract;
    private TextView txtValue;
    private TextView txtAdd;
    private Context context;
    private int value=1;
    private IClick cb;

    public CartCustomView(Context context) {
        super(context);
    }

    public CartCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CartCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CartCustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    public void initView(){
        txtSubtract = findViewById(R.id.txt_subtract);
        txtValue = findViewById(R.id.txt_value);
        txtAdd = findViewById(R.id.txt_add);

        if(txtSubtract != null && txtValue != null && txtAdd != null){
            txtSubtract.setOnClickListener(this);
            txtAdd.setOnClickListener(this);
        }else{
            Toast.makeText(context, "初始化调用错误", Toast.LENGTH_SHORT).show();
        }
    }

    public void initView(int min,int max){
        this.min=min;
        this.max=max;
        initView();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.txt_add:
                value++;
                if(value > max){
                    value = max;
                }else{
                    if(cb != null){
                        cb.clickCB(value);
                    }
                }
                txtValue.setText(String.valueOf(value));
                break;
            case R.id.txt_subtract:
                value--;
                if(value < 1){
                    value = 1;
                }else{
                    txtValue.setText(String.valueOf(value));
                    if(cb != null){
                        cb.clickCB(value);
                    }
                }
                break;
        }
    }
    /**
     * 设置接口回调
     * @param cb
     */
    public void setOnClickListener(IClick cb){
        this.cb = cb;
    }


    public void setValue(int num){
        this.value = num;
        txtValue.setText(String.valueOf(this.value));
    }


    public interface IClick{
        void clickCB(int value);
    }
}
