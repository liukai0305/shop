package com.opensource.svgaplayer

import android.app.Application
import android.content.Context
import android.os.Handler
import android.util.Log
import com.lzy.okgo.OkGo
import com.lzy.okgo.cache.CacheMode
import com.lzy.okgo.callback.Callback
import com.lzy.okgo.callback.FileCallback
import com.lzy.okgo.db.CookieManager.init
import com.lzy.okgo.model.Progress
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.opensource.svgaplayer.proto.MovieEntity
import com.opensource.svgaplayer.utils.DownloadUtil
import com.opensource.svgaplayer.utils.DownloadUtil.OnDownloadListener
import org.json.JSONObject
import java.io.*
import java.lang.ref.WeakReference
import java.net.URL
import java.security.MessageDigest
import java.util.concurrent.Executors
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.atomic.AtomicInteger
import java.util.zip.Inflater
import java.util.zip.ZipInputStream


/**
 * Created by PonyCui 16/6/18.
 */

private var fileLock: Int = 0
private var isUnzipping = false

class SVGAParser(context: Context?) {
    private var mContextRef = WeakReference(context)

    @Volatile
    private var mFrameWidth: Int = 0

    @Volatile
    private var mFrameHeight: Int = 0

    interface ParseCompletion {
        fun onComplete(videoItem: SVGAVideoEntity)
        fun onError()
    }

    open class FileDownloader {

        var noCache = false

        open fun resume(url: URL, complete: (inputStream: InputStream) -> Unit, failure: (e: Exception) -> Unit): () -> Unit {

            var cancelled = false
            val cancelBlock = {
                cancelled = true
            }
            threadPoolExecutor.execute {
                try {
                    val urll = url.toString()
                    if (isLoading||System.currentTimeMillis()-lastClickTime<5000) {
                        Log.e("xxxxxx 正在加载中", urll)
                        throw IOException("正在加载中")
                    }
                    Log.e("xxxxxx", urll)
                    lastClickTime=System.currentTimeMillis()

//                    DownloadUtil.get().download(urll, object : OnDownloadListener {
//
//                        override fun onDownloadSuccess(file: InputStream) {
//                            complete(file)
//                        }
//
//                        override fun onDownloadFailed(e: java.lang.Exception) {
//                            failure(e)
//                        }
//
//                    })
//                    OkGo.get<File>(urll) // 请求方式和请求url
                    OkGo.get<InputStream>(urll) // 请求方式和请求url
                            .tag(this) // 请求的 tag, 主要用于取消对应的请求
                            .cacheKey(urll) // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                            .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST) // 缓存模式，详细请看缓存介绍
                              .cacheTime(-1)//缓存时间
                            .execute(object : Callback<InputStream>{
                                override fun onSuccess(response: Response<InputStream>?) {
                                    response?.body()?.let { complete(it) }
                                    isLoading=false
                                }

                                override fun onFinish() {
                                    isLoading=false
                                }

                                override fun downloadProgress(progress: Progress?) {
                                }

                                override fun uploadProgress(progress: Progress?) {
                                }

                                override fun convertResponse(response: okhttp3.Response): InputStream {
                                    if (response.isSuccessful) {
                                        return response!!.body()!!.byteStream()
                                    }else{
                                        throw IOException("下载失败")
                                    }

                                }

                                override fun onError(response: Response<InputStream>?) {
                                    failure(IOException("onError"))
                                    isLoading=false
                                }

                                override fun onCacheSuccess(response: Response<InputStream>?) {
                                    response?.body()?.let { complete(it) }
                                    isLoading=false
                                }

                                override fun onStart(request: Request<InputStream, out Request<Any, Request<*, *>>>?) {
                                    isLoading=true
                                    lastClickTime = System.currentTimeMillis()

                                }
                            })


//                            .execute(object : FileCallback() {
//                                override fun onSuccess(response: Response<File>?) {
//
//                                    response?.body()?.inputStream()?.let { complete(it) }
//                                }
//
//                                override fun onError(response: Response<File>?) {
//                                    super.onError(response)
//                                }
//
//                            })
                } catch (e: Exception) {
                    failure(e)
                }


//                try {
//                    if (HttpResponseCache.getInstalled() == null && !noCache) {
//                        Log.e("SVGAParser", "SVGAParser can not handle cache before install HttpResponseCache. see https://github.com/yyued/SVGAPlayer-Android#cache")
//                        Log.e("SVGAParser", "在配置 HttpResponseCache 前 SVGAParser 无法缓存. 查看 https://github.com/yyued/SVGAPlayer-Android#cache ")
//                    }
//                    (url.openConnection() as? HttpURLConnection)?.let {
//                        it.connectTimeout = 20 * 1000
//                        it.requestMethod = "GET"
//                        it.connect()
//                        it.inputStream.use { inputStream ->
//                            ByteArrayOutputStream().use { outputStream ->
//                                val buffer = ByteArray(4096)
//                                var count: Int
//                                while (true) {
//                                    if (cancelled) {
//                                        break
//                                    }
//                                    count = inputStream.read(buffer, 0, 4096)
//                                    if (count == -1) {
//                                        break
//                                    }
//                                    outputStream.write(buffer, 0, count)
//                                }
//                                if (cancelled) {
//                                    return@execute
//                                }
//                                ByteArrayInputStream(outputStream.toByteArray()).use {
//                                    complete(it)
//                                }
//                            }
//                        }
//                    }
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                    failure(e)
//                }
            }
            return cancelBlock
        }
    }

    var fileDownloader = FileDownloader()

    companion object {
        var isLoading = false
        var lastClickTime = 0L
        private val threadNum = AtomicInteger(0)
        private var mShareParser = SVGAParser(null)

        internal var threadPoolExecutor = Executors.newCachedThreadPool { r ->
            Thread(r, "SVGAParser-Thread-${threadNum.getAndIncrement()}")
        }

        fun setThreadPoolExecutor(executor: ThreadPoolExecutor) {
            threadPoolExecutor = executor
        }

        fun shareParser(): SVGAParser {
            return mShareParser
        }
    }

    fun init(context: Application) {
        OkGo.getInstance().init(context)
        mContextRef = WeakReference<Context?>(context)
    }

    fun setFrameSize(frameWidth: Int, frameHeight: Int) {
        mFrameWidth = frameWidth
        mFrameHeight = frameHeight
    }

    fun decodeFromAssets(name: String, callback: ParseCompletion?) {
        if (mContextRef.get() == null) {
            Log.e("SVGAParser", "在配置 SVGAParser context 前, 无法解析 SVGA 文件。")
        }
        try {
            threadPoolExecutor.execute {
                mContextRef.get()?.assets?.open(name)?.let {
                    this.decodeFromInputStream(it, buildCacheKey("file:///assets/$name"), callback, true)
                }
            }
        } catch (e: java.lang.Exception) {
            this.invokeErrorCallback(e, callback)
        }
    }

    fun decodeFromURL(url: URL, callback: ParseCompletion?): (() -> Unit)? {
        if (this.isCached(buildCacheKey(url))) {
            threadPoolExecutor.execute {
                this.decodeFromCacheKey(buildCacheKey(url), callback)
            }
            return null
        } else {
            return fileDownloader.resume(url, {
                this.decodeFromInputStream(it, this.buildCacheKey(url), callback)
            }, {
                this.invokeErrorCallback(it, callback)
            })
        }
    }

    fun decodeFromInputStream(inputStream: InputStream, cacheKey: String, callback: ParseCompletion?, closeInputStream: Boolean = false) {
        threadPoolExecutor.execute {
            try {
                readAsBytes(inputStream)?.let { bytes ->
                    if (bytes.size > 4 && bytes[0].toInt() == 80 && bytes[1].toInt() == 75 && bytes[2].toInt() == 3 && bytes[3].toInt() == 4) {
                        if (!buildCacheDir(cacheKey).exists() || isUnzipping) {
                            synchronized(fileLock) {
                                if (!buildCacheDir(cacheKey).exists()) {
                                    isUnzipping = true
                                    ByteArrayInputStream(bytes).use {
                                        unzip(it, cacheKey)
                                        isUnzipping = false
                                    }
                                }
                            }
                        }
                        this.decodeFromCacheKey(cacheKey, callback)
                    } else {
                        inflate(bytes)?.let {
                            val videoItem = SVGAVideoEntity(MovieEntity.ADAPTER.decode(it), File(cacheKey), mFrameWidth, mFrameHeight)
                            videoItem.prepare {
                                this.invokeCompleteCallback(videoItem, callback)
                            }
                        }
                    }
                }
            } catch (e: java.lang.Exception) {
                this.invokeErrorCallback(e, callback)
            } finally {
                if (closeInputStream) {
                    inputStream.close()
                }
            }
        }
    }

    /**
     * @deprecated from 2.4.0
     */
    @Deprecated("This method has been deprecated from 2.4.0.", ReplaceWith("this.decodeFromAssets(assetsName, callback)"))
    fun parse(assetsName: String, callback: ParseCompletion?) {
        this.decodeFromAssets(assetsName, callback)
    }

    /**
     * @deprecated from 2.4.0
     */
    @Deprecated("This method has been deprecated from 2.4.0.", ReplaceWith("this.decodeFromURL(url, callback)"))
    fun parse(url: URL, callback: ParseCompletion?) {
        this.decodeFromURL(url, callback)
    }

    /**
     * @deprecated from 2.4.0
     */
    @Deprecated("This method has been deprecated from 2.4.0.", ReplaceWith("this.decodeFromInputStream(inputStream, cacheKey, callback, closeInputStream)"))
    fun parse(inputStream: InputStream, cacheKey: String, callback: ParseCompletion?, closeInputStream: Boolean = false) {
        this.decodeFromInputStream(inputStream, cacheKey, callback, closeInputStream)
    }

    private fun invokeCompleteCallback(videoItem: SVGAVideoEntity, callback: ParseCompletion?) {
        if (mContextRef.get() == null) {
            Log.e("SVGAParser", "在配置 SVGAParser context 前, 无法解析 SVGA 文件。")
        }
        Handler(mContextRef.get()?.mainLooper).post {
            callback?.onComplete(videoItem)
        }
    }

    private fun invokeErrorCallback(e: java.lang.Exception, callback: ParseCompletion?) {
        e.printStackTrace()
        if (mContextRef.get() == null) {
            Log.e("SVGAParser", "在配置 SVGAParser context 前, 无法解析 SVGA 文件。")
        }
        Handler(mContextRef.get()?.mainLooper).post {
            callback?.onError()
        }
    }

    private fun isCached(cacheKey: String): Boolean {
        return buildCacheDir(cacheKey).exists()
    }

    private fun decodeFromCacheKey(cacheKey: String, callback: ParseCompletion?) {
        if (mContextRef.get() == null) {
            Log.e("SVGAParser", "在配置 SVGAParser context 前, 无法解析 SVGA 文件。")
        }
        try {
            val cacheDir = File(mContextRef.get()?.cacheDir?.absolutePath + "/" + cacheKey + "/")
            File(cacheDir, "movie.binary").takeIf { it.isFile }?.let { binaryFile ->
                try {
                    FileInputStream(binaryFile).use {
                        this.invokeCompleteCallback(SVGAVideoEntity(MovieEntity.ADAPTER.decode(it), cacheDir, mFrameWidth, mFrameHeight), callback)
                    }
                } catch (e: Exception) {
                    cacheDir.delete()
                    binaryFile.delete()
                    throw e
                }
            }
            File(cacheDir, "movie.spec").takeIf { it.isFile }?.let { jsonFile ->
                try {
                    FileInputStream(jsonFile).use { fileInputStream ->
                        ByteArrayOutputStream().use { byteArrayOutputStream ->
                            val buffer = ByteArray(2048)
                            while (true) {
                                val size = fileInputStream.read(buffer, 0, buffer.size)
                                if (size == -1) {
                                    break
                                }
                                byteArrayOutputStream.write(buffer, 0, size)
                            }
                            byteArrayOutputStream.toString().let {
                                JSONObject(it).let {
                                    this.invokeCompleteCallback(SVGAVideoEntity(it, cacheDir, mFrameWidth, mFrameHeight), callback)
                                }
                            }
                        }
                    }
                } catch (e: Exception) {
                    cacheDir.delete()
                    jsonFile.delete()
                    throw e
                }
            }
        } catch (e: Exception) {
            this.invokeErrorCallback(e, callback)
        }
    }

    private fun buildCacheKey(str: String): String {
        val messageDigest = MessageDigest.getInstance("MD5")
        messageDigest.update(str.toByteArray(charset("UTF-8")))
        val digest = messageDigest.digest()
        var sb = ""
        for (b in digest) {
            sb += String.format("%02x", b)
        }
        return sb
    }

    private fun buildCacheKey(url: URL): String = buildCacheKey(url.toString())

    private fun buildCacheDir(cacheKey: String): File = File(mContextRef.get()?.cacheDir?.absolutePath + "/" + cacheKey + "/")

    private fun readAsBytes(inputStream: InputStream): ByteArray? {
        ByteArrayOutputStream().use { byteArrayOutputStream ->
            val byteArray = ByteArray(2048)
            while (true) {
                val count = inputStream.read(byteArray, 0, 2048)
                if (count <= 0) {
                    break
                } else {
                    byteArrayOutputStream.write(byteArray, 0, count)
                }
            }
            return byteArrayOutputStream.toByteArray()
        }
    }

    private fun inflate(byteArray: ByteArray): ByteArray? {
        val inflater = Inflater()
        inflater.setInput(byteArray, 0, byteArray.size)
        val inflatedBytes = ByteArray(2048)
        ByteArrayOutputStream().use { inflatedOutputStream ->
            while (true) {
                val count = inflater.inflate(inflatedBytes, 0, 2048)
                if (count <= 0) {
                    break
                } else {
                    inflatedOutputStream.write(inflatedBytes, 0, count)
                }
            }
            inflater.end()
            return inflatedOutputStream.toByteArray()
        }
    }

    private fun unzip(inputStream: InputStream, cacheKey: String) {
        val cacheDir = this.buildCacheDir(cacheKey)
        cacheDir.mkdirs()
        try {
            BufferedInputStream(inputStream).use {
                ZipInputStream(it).use { zipInputStream ->
                    while (true) {
                        val zipItem = zipInputStream.nextEntry ?: break
                        if (zipItem.name.contains("../")) {
                            // 解压路径存在路径穿越问题，直接过滤
                            continue
                        }
                        if (zipItem.name.contains("/")) {
                            continue
                        }
                        val file = File(cacheDir, zipItem.name)
                        FileOutputStream(file).use { fileOutputStream ->
                            val buff = ByteArray(2048)
                            while (true) {
                                val readBytes = zipInputStream.read(buff)
                                if (readBytes <= 0) {
                                    break
                                }
                                fileOutputStream.write(buff, 0, readBytes)
                            }
                        }
                        zipInputStream.closeEntry()
                    }
                }
            }
        } catch (e: Exception) {
            cacheDir.delete()
            throw e
        }
    }
}
