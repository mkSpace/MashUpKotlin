package com.example.kotlinexample.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProviders
import com.example.kotlinexample.BaseFragment
import com.example.kotlinexample.R
import com.example.kotlinexample.rx.observeOnMain
import com.example.kotlinexample.rx.subscribeWithErrorLogger
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : BaseFragment() {

    private lateinit var adapter: MainAdapter

    private lateinit var mainViewModel: MainViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        adapter = MainAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab.setOnClickListener {
            mainViewModel.navigateNextStep(Step.SEARCH, Bundle())
        }
        mainRepositories.adapter = adapter
        toolbar.title = context?.getString(R.string.app_name)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mainViewModel.items
            .observeOnMain()
            .subscribeWithErrorLogger {
                mainRepositories.isVisible = it.isNotEmpty()
                mainEmptyText.isVisible = it.isEmpty()
                adapter.submitList(it)
            }
            .addToDisposables()
    }
}