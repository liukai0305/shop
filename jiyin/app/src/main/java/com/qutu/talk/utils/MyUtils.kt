package com.qutu.talk.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.*
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.text.Html
import android.util.Base64
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

object MyUtils {

    fun timestamp2Time(timestampSecond:Long,format:String):String=
            SimpleDateFormat(format, Locale.CHINA).format(Date(timestampSecond*1000))

//    fun showSoftPan(context: Context,view:View){
//        view.requestFocus()
//        val inputMethodManager=context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        inputMethodManager.showSoftInput(view,InputMethodManager.SHOW_IMPLICIT)
//    }

    /**
     * 拨打电话
     */
    fun callPhone(context: Context,phoneNum:String){
        val builder= AlertDialog.Builder(context)
        builder.setTitle("拨打电话")
        builder.setMessage(phoneNum)
        builder.setPositiveButton("确定") { dialog, which ->
            val intent= Intent(Intent.ACTION_CALL)
            val uri= Uri.parse("tel:$phoneNum")
            intent.data = uri
            context.startActivity(intent)
        }
        builder.setNegativeButton("取消",{dialog, which ->

        })

        builder.show()

    }

    /**
     * convert dp to its equivalent px
     *
     * 将dp转换为与之相等的px
     */
    fun dp2px(context: Context, dipValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dipValue * scale + 0.5f).toInt()
    }


    /**
     * 弹出popupwindow
     * @param activity
     * @param contentView
     */
    fun initDialog(activity: Activity, contentView: View): AlertDialog.Builder {
        val builder = AlertDialog.Builder(activity)
        builder.setView(contentView)
        builder.setCancelable(false)
        return builder
    }



    /**
     * popupwindow弹出时，周围背景变暗
     * @param activity
     * @param bgAlpha
     */
    fun backgroundAlpha(activity: Activity, bgAlpha: Float) {
        val lp = activity.window.attributes
        lp.alpha = bgAlpha //0.0-1.0
        activity.window.attributes = lp
    }

    fun packageName(context: Context): String? {
        val manager = context.packageManager
        var name: String? = null
        try {
            val info = manager.getPackageInfo(context.packageName, 0)
            name = info.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return name
    }

    /**
     * bitmap转为base64
     *
     * @param bitmap
     * @return
     */
    fun bitmapToBase64(bitmap: Bitmap?): String? {

        var result: String? = null
        var baos: ByteArrayOutputStream? = null
        try {
            if (bitmap != null) {
                baos = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)

                baos.flush()
                baos.close()

                val bitmapBytes = baos.toByteArray()
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                if (baos != null) {
                    baos.flush()
                    baos.close()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
        return result
    }

    /**
     * 复制内容到剪切板
     *
     * @param copyStr
     * @return
     */
    fun copy(context: Context, copyStr: String): Boolean {
        try {
            //获取剪贴板管理器
            val cm = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            // 创建普通字符型ClipData
            val mClipData = ClipData.newPlainText("Label", copyStr)
            // 将ClipData内容放到系统剪贴板里。
            cm.primaryClip = mClipData
            return true
        } catch (e: Exception) {
            return false
        }

    }



}
