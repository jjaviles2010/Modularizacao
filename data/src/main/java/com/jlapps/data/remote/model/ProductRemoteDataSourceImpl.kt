package com.jlapps.data.remote.model

import com.jlapps.data.remote.api.ProductAPI
import com.jlapps.data.remote.mapper.ProductPayloadMapper
import com.jlapps.data.remote.source.ProductRemoteDataSource
import com.jlapps.domain.entity.Product
import io.reactivex.Single

class ProductRemoteDataSourceImpl(private val productAPI: ProductAPI) : ProductRemoteDataSource {
    override fun getProducts(): Single<List<Product>> {
        return productAPI.getProducts().map { ProductPayloadMapper.map(it) }
    }

}