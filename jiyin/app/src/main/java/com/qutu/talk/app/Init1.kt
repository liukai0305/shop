package com.qutu.talk.app

import android.app.Application
import android.content.Context
import com.opensource.svgaplayer.SVGAParser

/**
 *    @author : ywj
 *    date   : 2020/7/20 23:25
 *    desc   :
 */
public final class Init1{
    companion object {
        @JvmStatic
        fun init(context: Application) {
            SVGAParser.shareParser().init(context)
        }
    }

}