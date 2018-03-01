package com.doctorlh.readbykotlin.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.doctorlh.readbykotlin.R
import com.doctorlh.readbykotlin.bean.Book
import com.doctorlh.readbykotlin.constant.ApiConstant
import java.util.*


/**
 * Created by Administrator on 2017/12/29 0029.
 */

class BookAdapter(private val mContext: Context) : RecyclerView.Adapter<BookAdapter.BookHolder>() {

    private var mData: List<Book>? = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {
        return BookHolder(LayoutInflater.from(mContext).inflate(R.layout.item_search, parent, false))
    }

    override fun onBindViewHolder(holder: BookHolder, position: Int) {
        val book = mData!![position]
        holder.nameTv.text = book.title
        holder.authorTv.text = book.author
        Glide.with(mContext).load(ApiConstant.BASE_IMAGE_URL.plus(book.cover)).into(holder.cover)
        holder.itemView.setOnClickListener { ChapterActivity.launch(mContext, book) }
    }

    fun setData(mData: List<Book>) {
        this.mData = mData
    }

    override fun getItemCount(): Int {
        return mData?.size ?: 0
    }

    inner class BookHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nameTv: TextView = view.findViewById(R.id.name_tv)
        var authorTv: TextView = view.findViewById(R.id.author_tv)
        var cover: ImageView = view.findViewById(R.id.cover_iv)

    }
}
