package com.example.recipeme

/**
 * Data class which is the parsed JSON response of recipe previews from the backend into Kotlin objects
 *
 * @param idMeal The unique id of the recipe
 * @param strMeal The name of the recipe
 * @param strMealThumb Link to the thumbnail of the recipe
 */
data class RecipePreview(
    val strMeal: String,
    val strMealThumb: String,
    val idMeal: Int
)

/**
 * Data class used in parsing the JSON response of recipe previews to Kotlin objects
 *
 * @param meals The list of recipe previews returned in the JSON response parsed as a list
 */
data class RecipePreviewResponse(val meals: List<RecipePreview>)
