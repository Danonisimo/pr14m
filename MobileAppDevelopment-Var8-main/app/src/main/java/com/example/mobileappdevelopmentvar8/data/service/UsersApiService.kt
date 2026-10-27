package com.example.mobileappdevelopmentvar8.data.service

import com.example.mobileappdevelopmentvar8.data.model.User
import retrofit2.http.Body
import retrofit2.http.POST

interface UsersApiService {
    @POST("users/add")
    suspend fun createUser(@Body user: User): User
}
