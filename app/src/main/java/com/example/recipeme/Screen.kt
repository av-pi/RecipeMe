package com.example.recipeme

/**
 * Sealed class of singletons storing instances to the routes to each screen of the application.
 * Prevents bugs and conformation to a single naming convention for each screen.
 *
 * @param route The route name for each screen in the application
 */
sealed class Screen(val route: String) {
    object CategoriesScreen: Screen("categories_screen")
    object CategoryDetailsScreen: Screen("category_details_screen")
    object RecipePreviewsScreen: Screen("recipe_previews_screen")
    object RecipeScreen: Screen("recipe_screen")
}