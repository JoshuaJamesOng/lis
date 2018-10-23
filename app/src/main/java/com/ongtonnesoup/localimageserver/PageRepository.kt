package com.ongtonnesoup.localimageserver

import io.reactivex.Single

class PageRepository {

    fun getData(): Single<Page> = Single.fromCallable { Page("<html><h1>Hello world</h1></html>") }

}