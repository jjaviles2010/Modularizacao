package com.jlapps.modularizacao

import androidx.lifecycle.MutableLiveData
import com.jlapps.domain.entity.Product
import com.jlapps.domain.usecases.GetProductsUseCase
import com.jlapps.modularizacao.viewmodel.BaseViewModel
import com.jlapps.modularizacao.viewmodel.StateMachineSingle
import com.jlapps.modularizacao.viewmodel.ViewState
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.plusAssign


class MainViewModel(
    val useCase: GetProductsUseCase,
    val uiScheduler: Scheduler
): BaseViewModel() {

    val state = MutableLiveData<ViewState<List<Product>>>().apply {
        value = ViewState.Loading
    }

    fun getProducts(forceUpdate: Boolean = false) {
        disposables += useCase.execute(forceUpdate = forceUpdate)
            .compose(StateMachineSingle())
            .observeOn(uiScheduler)
            .subscribe(
                {
                    //onSuccess
                    state.postValue(it)
                },
                {
                    //onError
                }
            )
    }
}
