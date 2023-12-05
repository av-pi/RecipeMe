package com.example.recipeme

data class RecipePreview(
    val strMeal: String,
    val strMealThumb: String,
    val idMeal: Int
)

data class RecipePreviewResponse(val recipePreviews: List<RecipePreview>)
