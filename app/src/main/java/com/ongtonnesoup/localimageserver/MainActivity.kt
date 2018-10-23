package com.ongtonnesoup.localimageserver

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModelFactory = ViewModelFactory(PageRepository(), UiPageMapper())

    private val viewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(PageViewModel::class.java) }

    private val _disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        viewModel.updates()
            .doOnSubscribe { _disposables.add(it) }
            .subscribe {
                webview.loadData(it.base64EncodedHtml, "text/html", "base64")
            }
    }

    override fun onStop() {
        _disposables.clear()
        super.onStop()
    }
}
