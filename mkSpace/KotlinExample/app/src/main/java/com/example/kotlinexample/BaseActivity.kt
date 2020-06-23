package com.example.kotlinexample

import androidx.annotation.CallSuper
import com.example.kotlinexample.di.DaggerViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {

    private val disposables by lazy { CompositeDisposable() }

    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }

    protected fun Disposable.addToDisposables(): Disposable = addTo(disposables)
}
