package com.example.recipeme

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

/**
 * Manages the navigation to every screen in the application
 *
 * @param navController The navigation controller instance responsible for moving between screens
 */
@Composable
fun AppNavigation(navController: NavHostController) {
    val viewModel: MainViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.CategoriesScreen.route
    ) {

        // Categories grid screen
        composable(route = Screen.CategoriesScreen.route) {
            CategoryScreen(
                screenState = viewModel.categoriesState,
                navigateToDetailsScreen = {
                    navController.currentBackStackEntry?.savedStateHandle?.set("category", it)
                    navController.navigate(Screen.CategoryDetailsScreen.route)
                },
                navigateToRecipesPreviewScreen = { category ->
                    viewModel.fetchRecipesByCategory(category.strCategory)
                    navController.navigate(Screen.RecipePreviewsScreen.route)
                })
        }

        // Recipe previews screen showing all recipes of a category
        composable(route = Screen.RecipePreviewsScreen.route) {
            RecipePreviewsScreen(screenStateFlow = viewModel.recipePreviewStateFlow,
                navigateToRecipeScreen = {recipe ->
                    viewModel.fetchRecipeById(recipe.idMeal.toString())
                    navController.navigate(Screen.RecipeScreen.route)

                })
        }

        // Category details screen
        composable(route = Screen.CategoryDetailsScreen.route) {
            val category = navController.previousBackStackEntry?.savedStateHandle?.get<Category>("category")?: Category(-1, "","","")
            CategoryDetailsScreen(category = category)
        }

        // Recipe screen
        composable(route = Screen.RecipeScreen.route) {
            RecipeScreen(recipeStateFlow = viewModel.recipeStateFlow)
        }
    }
}