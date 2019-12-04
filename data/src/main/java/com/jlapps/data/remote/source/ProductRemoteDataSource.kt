package com.jlapps.data.remote.source

import com.jlapps.domain.entity.Product
import io.reactivex.Single

interface ProductRemoteDataSource {

    fun getProducts() : Single<List<Product>>

}