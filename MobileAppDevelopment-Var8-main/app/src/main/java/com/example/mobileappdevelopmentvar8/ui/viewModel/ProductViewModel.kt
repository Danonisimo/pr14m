package com.example.mobileappdevelopmentvar8.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileappdevelopmentvar8.data.RetrofitClient
import com.example.mobileappdevelopmentvar8.data.model.Product
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    fun getProduct() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.productsApiService.getProduct()

                Log.d(
                    "До редактирования:",
                    "ID: ${response.id} | " +
                            "Название: ${response.title} | " +
                            "Описание: ${response.description} | " +
                            "Категория: ${response.category} | " +
                            "Тэги: ${response.tags}"
                )
            } catch (e: Exception) {
                Log.e("RetrofitError", e.message.toString())
            }
        }
    }

    fun updateProduct(product: Product) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.productsApiService.updateProduct(product)

                Log.d(
                    "После редактирования:",
                    "ID: ${response.id} | " +
                            "Название: ${response.title} | " +
                            "Описание: ${response.description} | " +
                            "Категория: ${response.category} | " +
                            "Тэги: ${response.tags}"
                )
            } catch (e: Exception) {
                Log.e("RetrofitError", e.message.toString())
            }
        }
    }
}
