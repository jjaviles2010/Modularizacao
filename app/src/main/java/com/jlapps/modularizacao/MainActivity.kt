package com.jlapps.modularizacao

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.jlapps.modularizacao.viewmodel.ViewState

import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()
    }


    private fun setupViewModel() {
        viewModel.getProducts()

        viewModel.state.observe(this, Observer { state ->
            when(state) {
                is ViewState.Success -> {
                    Log.i("TAG", "Sucesso")
                }
                is ViewState.Loading -> {
                    Log.i("TAG", "Loading")
                }
                is ViewState.Failed -> {
                    Log.i("TAG", "Failed")
                }
            }
        })
    }
}


