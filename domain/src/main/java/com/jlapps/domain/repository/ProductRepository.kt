package com.jlapps.domain.repository

import com.jlapps.domain.entity.Product
import io.reactivex.Single

interface ProductRepository {

    fun getProducts(forceUpdate: Boolean): Single<List<Product>>

}