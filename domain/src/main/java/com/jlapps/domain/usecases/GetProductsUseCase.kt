package com.jlapps.domain.usecases

import com.jlapps.domain.entity.Product
import com.jlapps.domain.repository.ProductRepository
import io.reactivex.Scheduler
import io.reactivex.Single

class GetProductsUseCase(
    private val productRepository: ProductRepository,
    private val scheduler: Scheduler
) {
    fun execute(): Single<List<Product>> {
        return productRepository.getProducts()
            .observeOn(scheduler)
    }
}