package com.doctorlh.readbykotlin.bean

import java.io.Serializable

/**
 * Created by Administrator on 2017/12/29 0029.
 */

class BookResult : Serializable {

    lateinit var books: List<Book>

    var ok: Boolean = false
}
