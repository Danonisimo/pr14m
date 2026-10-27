package com.example.mobileappdevelopmentvar8.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileappdevelopmentvar8.data.RetrofitClient
import com.example.mobileappdevelopmentvar8.data.model.Recipe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<RecipeUiState>(RecipeUiState.Loading)
    val uiState: StateFlow<RecipeUiState> = _uiState.asStateFlow()

    init {
        fetchRecipes()
    }

    fun fetchRecipes() {
        viewModelScope.launch {
            _uiState.value = RecipeUiState.Loading
            try {
                val response = RetrofitClient.recipesApiService.getRecipes()
                _uiState.value = RecipeUiState.Success(response.recipes)
            } catch (e: Exception) {
                Log.e("RecipeViewModel", "Не удалось загрузить рецепты", e)
                _uiState.value = RecipeUiState.Error(e.message ?: "Неизвестная ошибка")
            }
        }
    }
}

sealed interface RecipeUiState {
    data object Loading : RecipeUiState
    data class Success(val recipes: List<Recipe>) : RecipeUiState
    data class Error(val message: String) : RecipeUiState
}
