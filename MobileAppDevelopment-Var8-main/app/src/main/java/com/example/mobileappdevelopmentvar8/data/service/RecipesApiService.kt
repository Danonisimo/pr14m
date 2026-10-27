package com.example.mobileappdevelopmentvar8.data.service

import com.example.mobileappdevelopmentvar8.data.model.RecipeResponse
import retrofit2.http.GET

interface RecipesApiService {
    @GET("recipes")
    suspend fun getRecipes(): RecipeResponse
}
