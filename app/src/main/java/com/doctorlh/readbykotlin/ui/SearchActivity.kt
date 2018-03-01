package com.doctorlh.readbykotlin.ui

import android.content.Context
import android.content.Intent
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.SearchView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.doctorlh.readbykotlin.BaseActivity
import com.doctorlh.readbykotlin.LinearLayoutManagerDivider
import com.doctorlh.readbykotlin.R
import com.doctorlh.readbykotlin.bean.BookResult
import com.doctorlh.readbykotlin.constant.ApiConstant
import com.doctorlh.readbykotlin.net.RxUtils
import com.lzy.okgo.model.HttpMethod
import com.lzy.okgo.model.HttpParams
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_search.*

/**
 * Created by Administrator on 2018/2/27 0027.
 */
class SearchActivity : BaseActivity(), SearchView.OnQueryTextListener {

    private lateinit var mSearchView: SearchView
    private lateinit var mAdapter: BookAdapter

    companion object {
        fun launch(context: Context) {
            var intent = Intent(context, SearchActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_search
    }

    override fun initView() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        title = "搜索"
        recycler_view.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        recycler_view.addItemDecoration(LinearLayoutManagerDivider(10, 10, 10, 10))
        mAdapter = BookAdapter(this)
        recycler_view.adapter = mAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search, menu)
        val menuItem = menu!!.findItem(R.id.search)
        mSearchView = MenuItemCompat.getActionView(menuItem) as SearchView
        mSearchView.setOnQueryTextListener(this)
        mSearchView.isSubmitButtonEnabled = false
        mSearchView.onActionViewExpanded()
        mSearchView.isIconified = false
        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        val params = HttpParams()
        params.put("query", query ?: "")
        RxUtils.request(HttpMethod.GET, ApiConstant.URL_SEARCH, BookResult::class.java, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.ok) {
                        mAdapter.setData(it.books)
                        mAdapter.notifyDataSetChanged()
                    }
                }, { Log.e("lh", "", it) })
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }


}