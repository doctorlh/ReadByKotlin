package com.doctorlh.readbykotlin.ui

import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.doctorlh.readbykotlin.BaseActivity
import com.doctorlh.readbykotlin.LinearLayoutManagerDivider
import com.doctorlh.readbykotlin.R
import com.doctorlh.readbykotlin.bean.Book
import com.doctorlh.readbykotlin.bean.Chapter
import com.doctorlh.readbykotlin.bean.ChapterResult
import com.doctorlh.readbykotlin.constant.ApiConstant
import com.doctorlh.readbykotlin.net.RxUtils
import com.lzy.okgo.model.HttpMethod
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_chapter.*

/**
 * Created by Administrator on 2018/2/28 0028.
 */
class ChapterActivity : BaseActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var mAdapter: ChapterAdapter

    private lateinit var mBook: Book

    private lateinit var mChapters: List<Chapter>

    companion object {
        fun launch(context: Context, book: Book?) {
            context.startActivity(Intent(context, ChapterActivity::class.java).putExtra("book", book))
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_chapter
    }

    override fun initView() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        swipe.isEnabled = false

        linearLayoutManager = LinearLayoutManager(this)
        recycler_view.layoutManager = linearLayoutManager
        recycler_view.addItemDecoration(LinearLayoutManagerDivider(0, 0, 0, 1))

        mBook = intent.getSerializableExtra("book") as Book
        title = mBook.title

        mAdapter = ChapterAdapter(this, mBook)
        recycler_view.adapter = mAdapter

        RxUtils.request(HttpMethod.GET, ApiConstant.URL_CHAPTER.format(mBook.bookId), ChapterResult::class.java)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.ok) {
                        mChapters = it.mixToc!!.chapters!!
                        mAdapter.setData(mChapters)
                        mAdapter.notifyDataSetChanged()
                        recycler_view.postDelayed({ linearLayoutManager.scrollToPosition(mBook.lastRead + 10) }, 200)
                    } else {

                    }
                }, { Log.e("lh", "", it) })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.chapter_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> finish()
            R.id.star -> {
            }
            R.id.unstar -> {
            }
            R.id.reverse -> {
            }
            R.id.locate -> {
            }
            else -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }

}