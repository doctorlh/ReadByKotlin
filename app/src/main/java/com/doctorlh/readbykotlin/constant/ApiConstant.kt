package com.doctorlh.readbykotlin.constant

/**
 * Created by Administrator on 2017/12/29 0029.
 */

object ApiConstant {
    /**
     * "http://api01pbmp.zhuishushenqi.com"
     * "http://api02u58f.zhuishushenqi.com"
     * "http://api03icqj.zhuishushenqi.com"
     * "http://api04ssfv.zhuishushenqi.com"
     * "http://api05iye5.zhuishushenqi.com"
     * "http://api06aa7y.zhuishushenqi.com"
     * "http://api073nwc.zhuishushenqi.com"
     * "http://api08mgip.zhuishushenqi.com"
     * "http://api09ecx7.zhuishushenqi.com"
     * "http://api10e46r.zhuishushenqi.com"
     */
    val BASE_URL = "http://api.zhuishushenqi.com"

    val BASE_CHAPTER_URL = "http://chapter2.zhuishushenqi.com/chapter/"

    val BASE_IMAGE_URL = "http://statics.zhuishushenqi.com"

    val URL_SEARCH = BASE_URL + "/book/fuzzy-search"

    val URL_CHAPTER = BASE_URL + "/mix-atoc/%s?view=chapter"
}
