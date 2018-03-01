package com.doctorlh.readbykotlin.bean

import java.io.Serializable

/**
 * Created by Administrator on 2017/12/29 0029.
 */

class NovelResult : Serializable {
    var ok: Boolean = false
    var chapter: Novel? = null
}
