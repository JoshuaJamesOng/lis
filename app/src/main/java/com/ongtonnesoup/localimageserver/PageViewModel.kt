package com.ongtonnesoup.localimageserver

import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

class PageViewModel(
    private val repository: PageRepository,
    private val mapper: UiPageMapper
) : ViewModel() {

    private val _updates = BehaviorSubject.create<UiPage>()
    private val _disposables = CompositeDisposable()

    fun updates(): Observable<UiPage> {
        return _updates.hide()
            .doOnSubscribe { loadPage() }
            .doOnTerminate { _disposables.clear() }
    }

    private fun loadPage() {
        repository.getData()
            .doOnSubscribe { _disposables.add(it) }
            .map { page -> mapper.map(page) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { page -> _updates.onNext(page) }
    }

}