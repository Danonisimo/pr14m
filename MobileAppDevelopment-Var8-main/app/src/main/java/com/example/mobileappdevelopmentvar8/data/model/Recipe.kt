package com.example.mobileappdevelopmentvar8.data.model

data class Recipe(
    val id: Int,
    val name: String,
    val ingredients: List<String>? = null,
    val caloriesPerServing: Int? = null,
    val isDeleted: Boolean? = null,
    val deletedOn: String? = null
)
