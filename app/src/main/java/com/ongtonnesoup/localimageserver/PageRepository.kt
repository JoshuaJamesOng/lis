package com.ongtonnesoup.localimageserver

import android.content.Context
import io.reactivex.Single

private val FILENAME = "page.html"

class PageRepository(private val context: Context) {

    fun getData(): Single<Page> = Single.fromCallable {
        val html = read(FILENAME)
        Page(html)
    }

    private fun read(file: String): String {
        return context.assets.open(file).bufferedReader().use {
            it.readText()
        }
    }

}