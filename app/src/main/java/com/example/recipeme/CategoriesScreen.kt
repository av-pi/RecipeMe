package com.example.recipeme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

/**
 * Composable for checking current state of categories screen.
 * Based on the state, displays loader, error or categories grid.
 *
 * @param screenState The state of the categories retrieved
 * @param navigateToDetailsScreen The lambda function from the navigation block
 *
 * The composable utilises statehoisting to pass in the stored
 * state from the viewmodel to ensure separation of concerns
 */
@Composable
fun CategoryScreen(screenState: State<MainViewModel.CategoryState>,
                   navigateToDetailsScreen: (Category) -> Unit,
                   navigateToRecipesPreviewScreen: (Category) -> Unit) {

    val categoriesScreenState by screenState

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        when {
            categoriesScreenState.loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            categoriesScreenState.error != null -> {
                Text(text = "ERROR OCCURRED!")
            }

            else -> {
                CategoryGrid(categories = categoriesScreenState.list,
                    navigateToDetailsScreen,
                    navigateToRecipesPreviewScreen)
            }
        }
    }
}

/**
 * Composable for the grid of recipe categories
 *
 * @param categories List of categories fetched from the backend
 * @param navigateToDetailsScreen Lambda function managing the movement to category details screen
 */
@Composable
fun CategoryGrid(categories: List<Category>,
                 navigateToDetailsScreen: (Category) -> Unit,
                 navigateToRecipesPreviewScreen: (Category) -> Unit) {

    //Category grid
    LazyVerticalGrid(
        GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize()
    ) {
        items(categories) {
            category ->
            CategoryItem(category = category,
                navigateToDetailsScreen,
                navigateToRecipesPreviewScreen)
        }
    }
}

/**
 * Composable for each item in the recipe categories grid
 *
 * @param category The specific category being displayed in this row
 * @param navigateToDetailsScreen Lambda function managing the movement to category details screen
 */
@Composable
fun CategoryItem(category: Category,
                 navigateToDetailsScreen: (Category) -> Unit,
                 navigateToRecipesPreviewScreen: (Category) -> Unit) {

    Card(
        modifier = Modifier
            .padding(4.dp)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(12.dp)
            )
            .background(color = MaterialTheme.colorScheme.secondary)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.secondary)
                .padding(8.dp)
                .clickable {
                    navigateToRecipesPreviewScreen(category)
                    //navigateToDetailsScreen(category)
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(modifier = Modifier
                .fillMaxSize()
            ) {
                //Category image
                Image(
                    //Coil composable for jetpack compose added in dependencies
                    painter = rememberAsyncImagePainter(category.strCategoryThumb),
                    contentDescription = "Image of food category",
                    modifier = Modifier
                        .fillMaxSize()
                        .aspectRatio(1f)
                        .align(Alignment.Center)
                        .clip(RoundedCornerShape(16.dp))
                )

                //Category name
                Text(
                    text = category.strCategory,
                    color = MaterialTheme.colorScheme.onSecondary,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .align(Alignment.BottomCenter)
                )

                IconButton(onClick = {
                    navigateToDetailsScreen(category)
                },
                    modifier = Modifier.align(Alignment.TopEnd)) {
                    Icon(
                        imageVector = Icons.Outlined.Info,
                        contentDescription = "Information about the category",
                        tint = MaterialTheme.colorScheme.onSecondary
                    )

                }
            }
        }
    }
}
