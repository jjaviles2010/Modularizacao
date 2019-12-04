package com.jlapps.data.remote.api

import com.jlapps.data.remote.model.ProductPayload
import io.reactivex.Single
import retrofit2.http.GET

interface ProductAPI {
    @GET("/v2/5de6d57e3700005f00092640")
    fun getProducts(): Single<List<ProductPayload>>
}
