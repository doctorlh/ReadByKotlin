package com.doctorlh.readbykotlin.net

import com.lzy.okgo.OkGo
import com.lzy.okgo.cache.CacheMode
import com.lzy.okgo.model.HttpHeaders
import com.lzy.okgo.model.HttpMethod
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.request.base.Request
import com.lzy.okrx2.adapter.ObservableBody

import java.lang.reflect.Type

import io.reactivex.Observable

/**
 * ================================================
 * 作    者：jeasonlzy（廖子尧）Github地址：https://github.com/jeasonlzy
 * 版    本：1.0
 * 创建日期：2017/5/28
 * 描    述：
 * 修订历史：
 * ================================================
 */
object RxUtils {

    fun <T> request(method: HttpMethod, url: String, type: Type): Observable<T> {
        return request(method, url, type, null)
    }

    fun <T> request(method: HttpMethod, url: String, type: Type, params: HttpParams?): Observable<T> {
        return request(method, url, type, params, null)
    }

    fun <T> request(method: HttpMethod, url: String, type: Type, params: HttpParams?, headers: HttpHeaders?): Observable<T> {
        return request(method, url, type, null, params, headers, CacheMode.NO_CACHE)
    }

    fun <T> request(method: HttpMethod, url: String, clazz: Class<T>): Observable<T> {
        return request(method, url, clazz, null)
    }

    fun <T> request(method: HttpMethod, url: String, clazz: Class<T>, params: HttpParams?): Observable<T> {
        return request(method, url, clazz, params, null)
    }

    fun <T> request(method: HttpMethod, url: String, clazz: Class<T>, params: HttpParams?, headers: HttpHeaders?): Observable<T> {
        return request(method, url, null, clazz, params, headers, CacheMode.NO_CACHE)
    }

    /**
     */
    fun <T> request(method: HttpMethod, url: String, type: Type?, clazz: Class<T>?, params: HttpParams?, headers: HttpHeaders?, cacheMode: CacheMode): Observable<T> {
        val request: Request<T, out Request<*, *>> = when (method) {
            HttpMethod.GET -> OkGo.get(url)
            HttpMethod.POST -> OkGo.post(url)
            HttpMethod.PUT -> OkGo.put(url)
            HttpMethod.DELETE -> OkGo.delete(url)
            HttpMethod.HEAD -> OkGo.head(url)
            HttpMethod.PATCH -> OkGo.patch(url)
            HttpMethod.OPTIONS -> OkGo.options(url)
            HttpMethod.TRACE -> OkGo.trace(url)
            else -> OkGo.get(url)
        }

        request.headers(headers)
        request.params(params)
        request.cacheMode(cacheMode)
        when {
            type != null -> request.converter(JsonConvert(type))
            clazz != null -> request.converter(JsonConvert(clazz))
            else -> request.converter(JsonConvert())
        }
        return request.adapt(ObservableBody())
    }
}