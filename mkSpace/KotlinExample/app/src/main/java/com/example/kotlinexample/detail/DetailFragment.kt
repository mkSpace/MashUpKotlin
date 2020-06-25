package com.example.kotlinexample.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.kotlinexample.BaseFragment
import com.example.kotlinexample.R
import com.example.kotlinexample.rx.observeOnMain
import com.example.kotlinexample.rx.subscribeWithErrorLogger
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.FragmentComponent
import kotlinx.android.synthetic.main.fragment_detail.*
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : BaseFragment() {

    private val detailViewModel: DetailViewModel by viewModels()

    @Inject lateinit var adapter: DetailAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailRecyclerView.adapter = adapter
        toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        detailViewModel.ownerName
            .observeOnMain()
            .subscribeWithErrorLogger { toolbar.title = it }
            .addToDisposables()

        detailViewModel.items
            .observeOnMain()
            .subscribeWithErrorLogger(adapter::submitList)
            .addToDisposables()
    }
}