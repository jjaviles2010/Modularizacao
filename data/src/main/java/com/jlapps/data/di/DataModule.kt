package com.jlapps.data.di

import com.jlapps.data.repository.ProductRepositoryImpl
import com.jlapps.domain.repository.ProductRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<ProductRepository> {
        ProductRepositoryImpl(
            productCacheDataSource = get(),
            productRemoteDataSource = get()
        )
    }
}

val dataModules = listOf(remoteDataSourceModule, repositoryModule, cacheDataModule)
