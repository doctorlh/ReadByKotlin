package com.doctorlh.readbykotlin

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    fun main(args: Array<String>) {

        loop@ for (i in 1..100) {
            if (i > 50) break@loop
        }

        listOf(1, 2, 3).forEach {
            if (it == 3) return@forEach
        }

        listOf(1, 2, 3).forEach(fun(value: Int) {
            if (value == 3) return
        })
    }
}
