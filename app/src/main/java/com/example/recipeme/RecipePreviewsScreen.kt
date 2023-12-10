package com.example.recipeme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.flow.StateFlow

/**
 * Composable controlling what the user sees on the screen based on success of fetching recipe previews
 *
 * @param screenStateFlow The stateflow from MainViewModel
 */
@Composable
fun RecipePreviewsScreen(screenStateFlow: StateFlow<MainViewModel.RecipePreviewState>,
                         navigateToRecipeScreen: (RecipePreview) -> Unit) {

    val screenState by screenStateFlow.collectAsState()

    Surface(
        modifier = Modifier.background(MaterialTheme.colorScheme.surface)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.secondary)
        ) {
            when {
                // Data being fetched from the backend
                screenState.loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                // Data fetch failed or something went wrong
                screenState.error != null -> {
                    Text(text = "ERROR OCCURRED!")
                }

                // Successfully fetched data
                else -> {
                    LazyColumn {
                        items(screenState.list) {recipe ->
                            RecipePreviewItem(recipe = recipe,
                                navigateToRecipeScreen)
                        }
                    }
                }
            }
        }
    }


}

/**
 * Composable representing each row in the recipe previews list shown to the user
 *
 * @param recipe The recipe whose preview must be shown to the user
 */
@Composable
fun RecipePreviewItem(recipe: RecipePreview,
                      navigateToRecipeScreen: (RecipePreview) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navigateToRecipeScreen(recipe)
            }
            .background(MaterialTheme.colorScheme.secondary),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically) {

        // Recipe Thumbnail
        Box(modifier = Modifier
            .size(100.dp)
            .padding(8.dp)
            .clip(CircleShape)
            .background(Color.Gray)) {

            Image(
                modifier = Modifier.fillMaxSize(1f),
                painter = rememberAsyncImagePainter(recipe.strMealThumb),
                contentDescription = "Image of ${recipe.strMeal}")
        }

        // Recipe name
        Text(
            modifier = Modifier
                .padding(start = 8.dp),
            text = recipe.strMeal,
            textAlign = TextAlign.Left,
            style = MaterialTheme.typography.titleMedium
        )
    }
}