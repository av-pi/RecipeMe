package com.example.recipeme

sealed class Screen(val route: String) {
    object CategoriesScreen: Screen("categoriesscreen")
    object CategoryDetailsScreen: Screen("categorydetailsscreen")
}