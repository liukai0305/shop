package com.qutu.talk.bean;

import android.os.Parcel;

import com.jess.arms.utils.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import io.rong.common.ParcelUtils;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;

@MessageTag(value = "app:custom", flag = MessageTag.ISCOUNTED | MessageTag.ISPERSISTED)
public class RequestEnterGroupMessage extends MessageContent {

    public String from_content = "";//发送方显示的字符：邀请xx加入家族

    public String to_content = "";//接受方显示的字符：邀请加入xx家族

    public String family_id = "";//群组id

    //覆盖父类的 MessageContent(byte[] data) 构造方法，该方法将对收到的消息进行解析，先由 byte 转成 json 字符串，再将 json 中内容取出赋值给消息属性。
    public RequestEnterGroupMessage(byte[] data) {

        String jsonStr = null;

        try {
            jsonStr = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e1) {

        }

        try {
            JSONObject jsonObj = new JSONObject(jsonStr);

            if (jsonObj.has("from_content")) {
                from_content = jsonObj.optString("from_content");
            }
            if (jsonObj.has("to_content")) {
                to_content = jsonObj.optString("to_content");
            }

            if (jsonObj.has("family_id")) {
                family_id = jsonObj.optString("family_id");
            }

        } catch (JSONException e) {
            LogUtils.debugInfo("JSONException", e.getMessage());
        }


    }

    public RequestEnterGroupMessage(){

    }

    //给消息赋值。
    public RequestEnterGroupMessage(Parcel in) {
        from_content = ParcelUtils.readFromParcel(in);//该类为工具类，消息属性
        to_content = ParcelUtils.readFromParcel(in);//该类为工具类，消息属性
        family_id = ParcelUtils.readFromParcel(in);//该类为工具类，消息属性
        //这里可继续增加你消息的属性
    }

    // 实现 encode() 方法，将消息属性封装成 json 串，再将 json 串转成 byte 数组，该方法会在发消息时调用。
    @Override
    public byte[] encode() {
        JSONObject jsonObj = new JSONObject();

        try {
//            jsonObj.put("content", "这是一条消息内容");
            jsonObj.put("from_content", from_content);
            jsonObj.put("to_content", to_content);
            jsonObj.put("family_id", family_id);
        } catch (JSONException e) {
            LogUtils.debugInfo("JSONException", e.getMessage());
        }

        try {
            return jsonObj.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 读取接口，目的是要从Parcel中构造一个实现了Parcelable的类的实例处理。
     */
    public static final Creator<RequestEnterGroupMessage> CREATOR = new Creator<RequestEnterGroupMessage>() {

        @Override
        public RequestEnterGroupMessage createFromParcel(Parcel source) {
            return new RequestEnterGroupMessage(source);
        }

        @Override
        public RequestEnterGroupMessage[] newArray(int size) {
            return new RequestEnterGroupMessage[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        ParcelUtils.writeToParcel(dest, from_content);//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(dest, to_content);//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(dest, family_id);//该类为工具类，对消息中属性进行序列化

        //这里可继续增加你消息的属性

    }

}
