package com.jlapps.data.remote.mapper

import com.jlapps.data.remote.model.ProductPayload
import com.jlapps.domain.entity.Product

object ProductPayloadMapper {

    fun map(cacheData: List<ProductPayload>) = cacheData.map { map(it) }

    private fun map(productPayload: ProductPayload) = Product(
        name = productPayload.name,
        imageURL = productPayload.imageURL,
        description = productPayload.description
    )

    fun mapProductToProductCache(products : List<Product>) = products.map { map(it) }

    private fun map(product: Product) = ProductPayload(
        name = product.name,
        imageURL = product.imageURL,
        description = product.description
    )

}