package com.jlapps.modularizacao

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.jlapps.modularizacao.databinding.ActivityMainBinding
import com.jlapps.modularizacao.extensions.visible
import com.jlapps.modularizacao.viewmodel.ViewState
import org.koin.android.ext.android.inject

import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    private val mainListAdapter : MainListAdapter by inject()

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.viewModel = viewModel

        binding.lifecycleOwner = this

        setupRecyclerView()
        setupViewModel()
    }


    private fun setupViewModel() {
        viewModel.getProducts()

        viewModel.state.observe(this, Observer { state ->
            when (state) {
                is ViewState.Success -> {
                    mainListAdapter.products = state.data
                    setVisibilities(showList = true)
                }
                is ViewState.Loading -> {
                    setVisibilities(showProgressBar = true)
                }
                is ViewState.Failed -> {
                    binding.tvMessage.text = state.throwable.message
                    setVisibilities(showMessage = true)
                }
            }
        })
    }


    private fun setupRecyclerView() = with(binding.recyclerView) {
        layoutManager = LinearLayoutManager(context)
        adapter = mainListAdapter
    }

    private fun setVisibilities(
        showProgressBar: Boolean = false,
        showList: Boolean = false,
        showMessage: Boolean = false
    ) {
        binding.progressBar.visible(showProgressBar)
        binding.recyclerView.visible(showList)
        binding.tvMessage.visible(showMessage)
    }


}


