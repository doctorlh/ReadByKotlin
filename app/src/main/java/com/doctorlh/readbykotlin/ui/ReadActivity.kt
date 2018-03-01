package com.doctorlh.readbykotlin.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import com.doctorlh.readbykotlin.BaseActivity
import com.doctorlh.readbykotlin.R
import com.doctorlh.readbykotlin.bean.NovelResult
import com.doctorlh.readbykotlin.constant.ApiConstant
import com.doctorlh.readbykotlin.net.RxUtils
import com.lzy.okgo.model.HttpMethod
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_read.*

/**
 * Created by Administrator on 2018/2/28 0028.
 */
class ReadActivity : BaseActivity() {

    private lateinit var mUrl: String
    private lateinit var mChapterName: String

    companion object {
        fun launch(context: Context, url: String?, chapterName: String?) {
            context.startActivity(Intent(context, ReadActivity::class.java)
                    .putExtra("url", url)
                    .putExtra("chapterName", chapterName))
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_read
    }

    override fun initView() {
        mUrl = intent.getStringExtra("url")
        mChapterName = intent.getStringExtra("chapterName")

        toolbar.title = mChapterName
        toolbar.setNavigationOnClickListener { finish() }

        RxUtils.request(HttpMethod.GET, ApiConstant.BASE_CHAPTER_URL.plus(Uri.encode(mUrl)), NovelResult::class.java)
                .map {
                    if (it.ok) {
                        val content = it.chapter!!.body
                        "\u3000\u3000" + content!!.replace("\n".toRegex(), "\n\u3000\u3000")
                    } else {
                        ""
                    }
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ content_tv.text = it }, { Log.e("lh", "", it) })
    }

}