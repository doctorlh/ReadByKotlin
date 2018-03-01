package com.doctorlh.readbykotlin.ui

import android.content.Context
import android.support.v7.app.AppCompatDelegate
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.doctorlh.readbykotlin.App
import com.doctorlh.readbykotlin.BaseActivity
import com.doctorlh.readbykotlin.LinearLayoutManagerDivider
import com.doctorlh.readbykotlin.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

/**
 * Created by Administrator on 2018/2/27 0027.
 */
class MainActivity : BaseActivity() {

    private lateinit var mAdapter: BookAdapter

    private var isNight = false

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        toolbar.title = "小说"
        setSupportActionBar(toolbar)

        fab.setOnClickListener { SearchActivity.launch(this) }

        val layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        recycler_view.layoutManager = layoutManager
        recycler_view.addItemDecoration(LinearLayoutManagerDivider(10, 10, 10, 10))
        mAdapter = BookAdapter(this)
        recycler_view.adapter = mAdapter

        isNight = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES

    }

    override fun onResume() {
        super.onResume()
        var books = App.getInstance().mDaoSession.bookDao.loadAll()
        mAdapter.setData(books)
        mAdapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.day_night, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu!!.findItem(R.id.day).isVisible = isNight
        menu.findItem(R.id.night).isVisible = !isNight
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.day -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                invalidateOptionsMenu()
                recreate()
                isNight = false
            }
            R.id.night -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                invalidateOptionsMenu()
                recreate()
                isNight = true
            }
            else -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun Context.showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

}