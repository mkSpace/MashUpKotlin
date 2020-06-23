package com.example.kotlinexample.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.kotlinexample.BaseActivity
import com.example.kotlinexample.R
import com.example.kotlinexample.detail.DetailFragment
import com.example.kotlinexample.di.DaggerViewModelFactory
import com.example.kotlinexample.rx.observeOnMain
import com.example.kotlinexample.rx.subscribeWithErrorLogger
import com.example.kotlinexample.search.SearchFragment
import javax.inject.Inject

class MainActivity : BaseActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

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
            Step.MAIN -> if (supportFragmentManager.primaryNavigationFragment !is MainFragment) MainFragment() else null
            Step.SEARCH -> if (supportFragmentManager.primaryNavigationFragment !is SearchFragment) SearchFragment() else null
            Step.DETAIL -> if (supportFragmentManager.primaryNavigationFragment !is DetailFragment) DetailFragment() else null
        }?.apply { arguments = bundle }

        fragment?.let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, it)
                .setPrimaryNavigationFragment(it)
                .addToBackStack(it.javaClass.simpleName)
                .commit()
        }
    }
}
