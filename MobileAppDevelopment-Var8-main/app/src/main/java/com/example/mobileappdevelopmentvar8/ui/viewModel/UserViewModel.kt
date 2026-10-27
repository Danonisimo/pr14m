package com.example.mobileappdevelopmentvar8.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileappdevelopmentvar8.data.RetrofitClient
import com.example.mobileappdevelopmentvar8.data.model.User
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    fun addUser(user: User) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.usersApiService.createUser(user)

                Log.d(
                    "UserLog",
                    "ID: ${response.id} | " +
                            "Имя: ${response.firstName} | " +
                            "Фамилия: ${response.lastName} | " +
                            "Пол: ${response.gender} | " +
                            "Адрес: ${response.address.address}"
                )

            } catch (e: Exception) {
                Log.e("RetrofitError", e.message.toString())
            }
        }
    }
}
