package com.example.mobileappdevelopmentvar8.data.service

import com.example.mobileappdevelopmentvar8.data.model.Recipe
import retrofit2.http.DELETE
import retrofit2.http.Path

interface RecipeDeleteApiService {
    @DELETE("recipes/{id}")
    suspend fun deleteRecipe(@Path("id") id: Int): Recipe
}
