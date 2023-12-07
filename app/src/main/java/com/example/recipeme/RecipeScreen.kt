package com.example.recipeme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.flow.StateFlow

/**
 * Composable for checking current state of recipe screen.
 * Based on the state, displays loader, error or the complete recipe.
 *
 * @param recipeStateFlow The state of the recipe retrieved
 *
 * The composable utilises statehoisting to pass in the stored
 * state from the viewmodel to ensure separation of concerns
 */
@Composable
fun RecipeScreen(recipeStateFlow: StateFlow<MainViewModel.RecipeState>) {

    val recipeState by recipeStateFlow.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        when {
            recipeState.loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            recipeState.error != null -> {
                Text(text = "Could not retrieve the desired recipe!")
            }

            else -> {
                RecipeDisplay(recipe = recipeState.recipe!!)
            }
        }
    }
}

/**
 * Preview function for the recipe display
 */
@Preview(showBackground = true)
@Composable
fun PreviewRecipeDisplay() {
    val recipe = Recipe(
        idMeal = "52959",
        strMeal = "Baked salmon with fennel & tomatoes",
        strDrinkAlternate = null,
        strCategory = "Seafood",
        strArea = "British",
        strInstructions = "Heat oven to 180C/fan 160C/gas 4. Trim the fronds from the fennel and set aside. Cut the fennel bulbs in half, then cut each half into 3 wedges. Cook in boiling salted water for 10 mins, then drain well. Chop the fennel fronds roughly, then mix with the parsley and lemon zest.\r\n\r\nSpread the drained fennel over a shallow ovenproof dish, then add the tomatoes. Drizzle with olive oil, then bake for 10 mins. Nestle the salmon among the veg, sprinkle with lemon juice, then bake 15 mins more until the fish is just cooked. Scatter over the parsley and serve.",
        strMealThumb = "https://www.themealdb.com/images/media/meals/1548772327.jpg",
        strTags = "Paleo,Keto,HighFat,Baking,LowCarbs",
        strYoutube = "https://www.youtube.com/watch?v=xvPR2Tfw5k0",
        strIngredient1 = "Fennel",
        strIngredient2 = "Parsley",
        strIngredient3 = "Lemon",
        strIngredient4 = "Cherry Tomatoes",
        strIngredient5 = "Olive Oil",
        strIngredient6 = "Salmon",
        strIngredient7 = "Black Olives",
        strIngredient8 = "",
        strIngredient9 = "",
        strIngredient10 = "",
        strIngredient11 = "",
        strIngredient12 = "",
        strIngredient13 = "",
        strIngredient14 = "",
        strIngredient15 = "",
        strIngredient16 = "",
        strIngredient17 = "",
        strIngredient18 = "",
        strIngredient19 = "",
        strIngredient20 = "",
        strMeasure1 = "2 medium",
        strMeasure2 = "2 tbs chopped",
        strMeasure3 = "Juice of 1",
        strMeasure4 = "175g",
        strMeasure5 = "1 tbs",
        strMeasure6 = "350g",
        strMeasure7 = "to serve",
        strMeasure8 = "",
        strMeasure9 = "",
        strMeasure10 = "",
        strMeasure11 = "",
        strMeasure12 = "",
        strMeasure13 = "",
        strMeasure14 = "",
        strMeasure15 = "",
        strMeasure16 = "",
        strMeasure17 = "",
        strMeasure18 = "",
        strMeasure19 = "",
        strMeasure20 = "",
        strSource = "https://www.bbcgoodfood.com/recipes/7745/baked-salmon-with-fennel-and-tomatoes",
        strImageSource = null,
        strCreativeCommonsConfirmed = null,
        dateModified = null,
        null
    )
    
    RecipeDisplay(recipe = recipe)
}

/**
 * Composable that displays the entire recipe retrieved from the backend
 *
 * @param recipe The recipe to be rendered
 */
// TODO: Significantly improve upon the UI
@Composable
fun RecipeDisplay(recipe: Recipe) {
    val backgroundColor = Color(0xFFF8F5F2) // Light background
    val titleColor = Color(0xFF333333) // Dark title
    val secondaryColor = Color(0xFF999999) // Gray text

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = backgroundColor
    ) {
        Column(modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(1f)
        ) {
            // Recipe Image
            Card(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(8.dp),
                elevation =  CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                )
            ) {
                Box(modifier = Modifier
                    .size(100.dp)
                    .padding(8.dp)
                    .clip(CircleShape)
                    ) {

                    Image(painter = rememberAsyncImagePainter(recipe.strMealThumb),
                    contentDescription = "Image thumbnail of the recipe"
                )
                }
            }

            // Recipe Title and Category
            Text(
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
                text = recipe.strMeal,
                style = MaterialTheme.typography.displayMedium,
                color = titleColor,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = recipe.strCategory,
                style = MaterialTheme.typography.bodyMedium,
                color = secondaryColor
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(1.dp)
                    .verticalScroll(rememberScrollState())

            ) {

                // Ingredients
                Card(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth(1f),
                    shape = RoundedCornerShape(8.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 10.dp
                    )
                ) {
                    Column(modifier = Modifier
                        .padding(16.dp)) {
                        Text(
                            text = "Ingredients:",
                            style = MaterialTheme.typography.bodyMedium,
                            color = titleColor,
                            fontWeight = FontWeight.Bold
                        )

                        for (i in recipe.getIngredientsList()) {
                            Text(
                                text = "${i.quantity} ${i.name}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = secondaryColor
                            )
                        }
                    }
                }

                // Instructions
                Card(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth(1f)
                        .wrapContentHeight(),
                    shape = RoundedCornerShape(8.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 10.dp
                    )
                ) {
                    Column(modifier = Modifier
                        .padding(16.dp)
                        .wrapContentHeight()
                    ) {
                        Text(
                            text = "Instructions:",
                            style = MaterialTheme.typography.displayMedium,
                            color = titleColor,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = recipe.strInstructions,
                            style = MaterialTheme.typography.bodyMedium,
                            color = secondaryColor,
                        )
                    }
                }

                // Source and Links

                // TODO: Fix bug go to youtube icon button not showing.
                // TODO: Handle taking user to youtube upon click
                Row(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth(1f),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Source: ${recipe.strSource}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = secondaryColor
                    )
                    if (recipe.strYoutube.isNotEmpty()) {
                        IconButton(onClick = { /* Open Youtube Link */ }) {
                            Icon(
                                imageVector = Icons.Outlined.ExitToApp,
                                contentDescription = "View on YouTube",
                                tint = Color.DarkGray
                            )
                        }
                    }
                }
            }
        }
    }
}