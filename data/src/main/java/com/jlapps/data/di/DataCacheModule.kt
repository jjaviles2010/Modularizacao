package com.jlapps.data.di

import com.jlapps.data.local.database.ProductDataBase
import com.jlapps.data.local.source.ProductCacheDataSource
import com.jlapps.data.local.source.ProductCacheDataSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val cacheDataModule = module {
    single { ProductDataBase.createDataBase(androidContext()) }
    factory<ProductCacheDataSource> { ProductCacheDataSourceImpl(productDao = get()) }
}