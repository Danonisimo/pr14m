package com.example.mobileappdevelopmentvar8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobileappdevelopmentvar8.ui.theme.MobileAppDevelopmentVar8Theme
import com.example.mobileappdevelopmentvar8.data.model.Recipe
import com.example.mobileappdevelopmentvar8.ui.viewModel.RecipeUiState
import com.example.mobileappdevelopmentvar8.ui.viewModel.RecipeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileAppDevelopmentVar8Theme {
                val recipeViewModel: RecipeViewModel = viewModel()
                val state by recipeViewModel.uiState.collectAsState()
                Scaffold(
                    topBar = { TopAppBar(title = { Text("Вариант 8 — Рецепты") }) }
                ) { padding -> RecipeContent(state, recipeViewModel::fetchRecipes, Modifier.padding(padding)) }
            }
        }
    }
}

@Composable
private fun RecipeContent(state: RecipeUiState, onRetry: () -> Unit, modifier: Modifier) {
    when (state) {
        RecipeUiState.Loading -> Column(modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) { CircularProgressIndicator() }
        is RecipeUiState.Error -> Column(modifier.fillMaxSize().padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Text("Ошибка загрузки: ${state.message}")
            Button(onClick = onRetry, modifier = Modifier.padding(top = 12.dp)) { Text("Повторить") }
        }
        is RecipeUiState.Success -> LazyColumn(modifier.fillMaxSize(), contentPadding = PaddingValues(12.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(state.recipes, key = { it.id }) { recipe -> RecipeCard(recipe) }
        }
    }
}

@Composable
private fun RecipeCard(recipe: Recipe) {
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(recipe.name, style = MaterialTheme.typography.titleLarge)
            Text("Ингредиенты: ${recipe.ingredients?.joinToString(", ") ?: "не указаны"}")
            Text("Калории: ${recipe.caloriesPerServing ?: "не указано"} ккал на порцию", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
