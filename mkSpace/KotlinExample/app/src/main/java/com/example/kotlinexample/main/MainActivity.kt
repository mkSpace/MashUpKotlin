package com.example.kotlinexample.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.kotlinexample.BaseActivity
import com.example.kotlinexample.BaseFragment
import com.example.kotlinexample.Injection
import com.example.kotlinexample.R
import com.example.kotlinexample.detail.DetailFragment
import com.example.kotlinexample.rx.observeOnMain
import com.example.kotlinexample.rx.subscribeWithErrorLogger
import com.example.kotlinexample.search.SearchFragment

class MainActivity : BaseActivity() {

    private val mainViewModel by viewModels<MainViewModel> {
        Injection.provideMainViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindViewModel()
    }

    private fun bindViewModel() {
        mainViewModel.currentStepWithBundle
            .observeOnMain()
            .subscribeWithErrorLogger { (step, bundle) ->
                navigate(step, bundle)
            }
            .addToDisposables()
    }

    private fun navigate(step: Step, bundle: Bundle) {
        val fragment = when (step) {
            Step.MAIN ->
                MainFragment()
            Step.SEARCH ->
                SearchFragment()
            Step.DETAIL ->
                DetailFragment()
        }.apply { arguments = bundle }

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .setPrimaryNavigationFragment(fragment)
            .addToBackStack(fragment.javaClass.simpleName)
            .commit()
    }
}
