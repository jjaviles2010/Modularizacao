package com.jlapps.data.local.source

import com.jlapps.domain.entity.Product
import io.reactivex.Single

interface ProductCacheDataSource {

    fun getProducts() : Single<List<Product>>

    fun insertData(products: List<Product>)

    fun updateData(products: List<Product>)

}