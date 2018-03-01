package com.doctorlh.readbykotlin.bean

import java.io.Serializable

/**
 * Created by Administrator on 2017/12/29 0029.
 */

class ChapterResult : Serializable {

    var mixToc: MixToc? = null
    var ok: Boolean = false

    class MixToc : Serializable {
        var chapters: List<Chapter>? = null
    }
}
