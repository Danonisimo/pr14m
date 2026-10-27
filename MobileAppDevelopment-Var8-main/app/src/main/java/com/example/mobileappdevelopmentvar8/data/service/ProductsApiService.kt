package com.example.mobileappdevelopmentvar8.data.service

import com.example.mobileappdevelopmentvar8.data.model.Product
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface ProductsApiService {
    @GET("products/48")
    suspend fun getProduct(): Product

    @PUT("products/48")
    suspend fun updateProduct(@Body product: Product): Product
}
