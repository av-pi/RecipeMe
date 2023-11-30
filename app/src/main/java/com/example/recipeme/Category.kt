package com.example.recipeme

data class Category(
    val idCategory: Int,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String
)

data class CategoriesResponse(val categories: List<Category>)
