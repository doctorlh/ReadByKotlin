package com.doctorlh.readbykotlin.ui


import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.doctorlh.readbykotlin.App
import com.doctorlh.readbykotlin.R
import com.doctorlh.readbykotlin.bean.Book
import com.doctorlh.readbykotlin.bean.Chapter
import java.util.*

/**
 * Created by Administrator on 2017/12/29 0029.
 */

class ChapterAdapter(private val mContext: Context, private val mBook: Book) : RecyclerView.Adapter<ChapterAdapter.ChapterHolder>() {

    private var mData: List<Chapter>? = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterHolder {
        return ChapterHolder(LayoutInflater.from(mContext).inflate(R.layout.item_chapter, parent, false))
    }

    override fun onBindViewHolder(holder: ChapterHolder, position: Int) {
        val chapter = mData!![position]
        holder.titleTv.text = chapter.title
        if (mBook.lastRead === position) {
            holder.titleTv.setTextColor(ContextCompat.getColor(mContext, R.color.colorAccent))
        } else {
            holder.titleTv.setTextColor(ContextCompat.getColor(mContext, R.color.text))
        }
        holder.itemView.setOnClickListener {
            ReadActivity.launch(mContext, chapter.link, chapter.title)
            mBook.lastRead = position
            App.getInstance().mDaoSession.bookDao.update(mBook)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return mData?.size ?: 0
    }

    fun setData(mData: List<Chapter>) {
        this.mData = mData
    }

    class ChapterHolder(view: View) : RecyclerView.ViewHolder(view) {
        var titleTv: TextView = view.findViewById(R.id.title_tv)
    }
}
