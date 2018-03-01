package com.doctorlh.readbykotlin.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Administrator on 2017/12/29 0029.
 */
class Book : Serializable {

    @SerializedName("_id")
    var bookId: String? = null

    var title: String? = null

    var cover: String? = null

    var author: String? = null

    var lastRead: Int = 0

    override fun equals(obj: Any?): Boolean {
        if (obj == null) {
            return false
        }
        if (obj is Book) {
            val book = obj as Book?
            return book!!.bookId === this.bookId
        }
        return super.equals(obj)
    }

}
