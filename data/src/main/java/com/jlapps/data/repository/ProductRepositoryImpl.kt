package com.jlapps.data.repository

import com.jlapps.data.local.source.ProductCacheDataSource
import com.jlapps.data.remote.source.ProductRemoteDataSource
import com.jlapps.domain.entity.Product
import com.jlapps.domain.repository.ProductRepository
import io.reactivex.Single

class ProductRepositoryImpl (
    private val productCacheDataSource: ProductCacheDataSource,
    private val productRemoteDataSource: ProductRemoteDataSource
): ProductRepository {
    override fun getProducts(forceUpdate: Boolean): Single<List<Product>> {
        return if (forceUpdate)
            getProductsRemote(forceUpdate)
        else
            productCacheDataSource.getProducts()
                .flatMap { listJobs ->
                    when {
                        listJobs.isEmpty() -> getProductsRemote(false)
                        else -> Single.just(listJobs)
                    }
                }
    }


    private fun getProductsRemote(isUpdate: Boolean): Single<List<Product>> {
        return productRemoteDataSource.getProducts()
            .flatMap { listJobs ->
                if (isUpdate)
                    productCacheDataSource.updateData(listJobs)
                else
                    productCacheDataSource.insertData(listJobs)
                Single.just(listJobs)
            }
    }



}