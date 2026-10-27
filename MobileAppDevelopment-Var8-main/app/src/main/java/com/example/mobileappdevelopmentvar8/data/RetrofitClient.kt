package com.example.mobileappdevelopmentvar8.data

import com.example.mobileappdevelopmentvar8.data.service.ProductsApiService
import com.example.mobileappdevelopmentvar8.data.service.RecipeDeleteApiService
import com.example.mobileappdevelopmentvar8.data.service.RecipesApiService
import com.example.mobileappdevelopmentvar8.data.service.UsersApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.InetSocketAddress
import java.net.Proxy

object RetrofitClient {
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val proxy = Proxy(Proxy.Type.HTTP, InetSocketAddress("10.207.106.59", 3128))

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        //.proxy(proxy)
        .build()

    val retrofitClient = Retrofit.Builder()
        .baseUrl("https://dummyjson.com/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val recipesApiService: RecipesApiService by lazy {
        retrofitClient.create(RecipesApiService::class.java)
    }

    val usersApiService: UsersApiService by lazy {
        retrofitClient.create(UsersApiService::class.java)
    }

    val productsApiService: ProductsApiService by lazy {
        retrofitClient.create(ProductsApiService::class.java)
    }

    val recipeDeleteApiService: RecipeDeleteApiService by lazy {
        retrofitClient.create(RecipeDeleteApiService::class.java)
    }
}
