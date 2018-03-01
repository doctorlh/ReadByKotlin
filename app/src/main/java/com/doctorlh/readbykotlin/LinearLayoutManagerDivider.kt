package com.doctorlh.readbykotlin

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * time:2016/11/25
 * description: 线性分割线
 *
 * @author
 */
class LinearLayoutManagerDivider(private val leftSpace: Int, private val topSpace: Int, private val rightSpace: Int, private val bottomSpace: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View,
                                parent: RecyclerView, state: RecyclerView.State?) {
        outRect.left = leftSpace
        outRect.right = rightSpace
        outRect.top = topSpace
        outRect.bottom = bottomSpace
    }
}
