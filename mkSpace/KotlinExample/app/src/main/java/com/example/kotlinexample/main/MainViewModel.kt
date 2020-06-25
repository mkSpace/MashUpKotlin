package com.example.kotlinexample.main

import android.os.Bundle
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.kotlinexample.BaseSchedulerProvider
import com.example.kotlinexample.BaseViewModel
import com.example.kotlinexample.search.SearchRepository
import io.reactivex.Flowable
import io.reactivex.processors.BehaviorProcessor

class MainViewModel @ViewModelInject constructor(
    schedulerProvider: BaseSchedulerProvider,
    searchRepository: SearchRepository
) : ViewModel() {

    private val currentStepWithBundleProcessor =
        BehaviorProcessor.createDefault(Step.MAIN to Bundle())

    val currentStepWithBundle: Flowable<Pair<Step, Bundle>> =
        currentStepWithBundleProcessor.distinctUntilChanged()

    fun navigateNextStep(step: Step, bundle: Bundle) {
        currentStepWithBundleProcessor.offer(step to bundle)
    }

    val items = searchRepository.getRepositories()
}