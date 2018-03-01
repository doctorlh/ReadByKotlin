package com.doctorlh.readbykotlin

import android.app.Application
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.doctorlh.readbykotlin.dao.DaoMaster
import com.doctorlh.readbykotlin.dao.DaoSession
import com.lzy.okgo.OkGo
import com.lzy.okgo.cache.CacheEntity
import com.lzy.okgo.cache.CacheMode
import com.lzy.okgo.cookie.CookieJarImpl
import com.lzy.okgo.cookie.store.DBCookieStore
import com.lzy.okgo.https.HttpsUtils
import com.lzy.okgo.interceptor.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import java.util.logging.Level

/**
 * Created by Administrator on 2018/2/27 0027.
 */
class App : Application() {

    lateinit var mDaoSession: DaoSession

    companion object {
        private lateinit var sInstance: App
        fun getInstance() = sInstance
    }

    override fun onCreate() {
        super.onCreate()
        sInstance = this
        val logger = HttpLoggingInterceptor("lh")
        logger.setPrintLevel(HttpLoggingInterceptor.Level.BODY)
        logger.setColorLevel(Level.ALL)
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(logger)
        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)
        builder.cookieJar(CookieJarImpl(DBCookieStore(this)))

        val sslParams = HttpsUtils.getSslSocketFactory()
        builder.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
        builder.hostnameVerifier(HttpsUtils.UnSafeHostnameVerifier)

        OkGo.getInstance()
                .init(this)
                .setOkHttpClient(builder.build())
                .setCacheMode(CacheMode.DEFAULT)
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
                .retryCount = 3

        var helper = DaoMaster.DevOpenHelper(this, "book")
        mDaoSession = DaoMaster(helper.writableDatabase).newSession()
    }
}