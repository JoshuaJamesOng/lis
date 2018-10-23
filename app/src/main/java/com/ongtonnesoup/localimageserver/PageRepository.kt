package com.ongtonnesoup.localimageserver

import android.content.Context
import io.reactivex.Single
import org.jsoup.Jsoup

private const val FILENAME = "page.html"

class PageRepository(
    private val context: Context,
    private val configuration: LocalImageServer.Configuration
) {

    fun getData(): Single<Page> = Single.fromCallable {
        val rawHtml = read(FILENAME)
        val htmlWithLocalImages = transform(rawHtml)
        Page(htmlWithLocalImages)
    }

    private fun read(file: String): String {
        return context.assets.open(file).bufferedReader().use {
            it.readText()
        }
    }

    private fun transform(rawHtml: String): String {
        val document = Jsoup.parse(rawHtml)
        val imageTags = document.getElementsByTag("img")
        imageTags
            .forEach { imageTag ->
                val src = imageTag.attr("src")
                val newSrc = getLocalUrlForImage(src)
                imageTag.attr("src", newSrc)
            }
        return document.toString()
    }

    private fun getLocalUrlForImage(src: String): String {
        return "http://${configuration.hostname}:${configuration.port}/image/?src=$src"
    }

}