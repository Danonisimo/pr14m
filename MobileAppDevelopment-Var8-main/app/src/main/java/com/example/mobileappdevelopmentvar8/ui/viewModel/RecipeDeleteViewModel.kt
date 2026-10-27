package com.example.mobileappdevelopmentvar8.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileappdevelopmentvar8.data.RetrofitClient
import kotlinx.coroutines.launch

class RecipeDeleteViewModel : ViewModel() {
    fun deleteRecipe() {
        viewModelScope.launch {
            val id = 19
            try {
                val response = RetrofitClient.recipeDeleteApiService.deleteRecipe(id = id)

                Log.d(
                    "RecipeDeleteLog",
                    "ID: ${response.id} | " +
                            "Название: ${response.name} | " +
                            "Удалено: ${response.isDeleted} | " +
                            "Время удаления: ${response.deletedOn}"
                )
            } catch (e: Exception) {
                Log.e("RetrofitError", e.message.toString())
            }
        }
    }
}
