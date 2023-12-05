package com.example.recipeme

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

/*
Composable for checking current state of categories screen.
Based on the state, displays loader, error or categories grid.
 */
@Composable
fun CategoryScreen(modifier: Modifier = Modifier,
                   screenState: State<MainViewModel.RecipeState>,
                   navigateToDetailsScreen: (Category) -> Unit) {

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
                CategoryGrid(categories = categoriesScreenState.list, navigateToDetailsScreen)
            }
        }
    }
}

/*
Composable for the grid of recipe categories
 */
@Composable
fun CategoryGrid(categories: List<Category>,
                 navigateToDetailsScreen: (Category) -> Unit) {

    //Category grid
    LazyVerticalGrid(
        GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize()
    ) {
        items(categories) {
            category ->
            CategoryItem(category = category, navigateToDetailsScreen)
        }
    }
}

/*
Composable for each item in the recipe categories grid
 */
@Composable
fun CategoryItem(category: Category,
                 navigateToDetailsScreen: (Category) -> Unit) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
            .clickable {
                navigateToDetailsScreen(category)
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        //Category image
        Image(
            //Coil composable for jetpack compose added in dependencies
            painter = rememberAsyncImagePainter(category.strCategoryThumb),
            contentDescription = "Image of food category",
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
        )

        //Category name
        Text(
            text = category.strCategory,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}