package com.doctorlh.readbykotlin.ui

import android.content.Context
import android.content.Intent
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import com.doctorlh.readbykotlin.App
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
    /**
     * 是否存在
     */
    private var isExist = false

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

        isExist = App.getInstance().mDaoSession.bookDao.load(mBook.bookId) != null

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
        menu!!.findItem(R.id.star).isVisible = isExist
        menu!!.findItem(R.id.unstar).isVisible = !isExist
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> finish()
            R.id.star -> {
                App.getInstance().mDaoSession.bookDao.delete(mBook)
                isExist = false
                invalidateOptionsMenu()
            }
            R.id.unstar -> {
                App.getInstance().mDaoSession.bookDao.insert(mBook)
                isExist = true
                invalidateOptionsMenu()
            }
            R.id.reverse -> {
                mChapters.reversed()
                mAdapter.setData(mChapters)
                mAdapter.notifyDataSetChanged()
            }
            R.id.locate -> {
                var builder = AlertDialog.Builder(this)
                builder.setTitle("跳转至")
                val view = layoutInflater.inflate(R.layout.view_edittext, null)
                val editText = view.findViewById<EditText>(R.id.editText)
                builder.setView(view)
                builder.setPositiveButton("确定", { dialog, _ ->
                    var position = editText.text.toString().trim().toInt()
                    position = Math.min(position, mChapters.size - 1)
                    recycler_view.layoutManager.scrollToPosition(position)
                    dialog.dismiss()
                })
                builder.setNegativeButton("取消", { dialog, _ -> dialog.dismiss() })
                builder.create().show()
            }
            else -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }

}