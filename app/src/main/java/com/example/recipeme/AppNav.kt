package com.example.recipeme

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavigation(navController: NavHostController) {
    val viewModel: MainViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.CategoriesScreen.route
    ) {

        //Categories grid screen
        composable(route = Screen.CategoriesScreen.route) {
            CategoryScreen(
                screenState = viewModel.categoriesState,
                navigateToDetailsScreen = {
                    navController.currentBackStackEntry?.savedStateHandle?.set("category", it)
                    navController.navigate(Screen.CategoryDetailsScreen.route)

            })
        }

        //Category details screen
        composable(route = Screen.CategoryDetailsScreen.route) {
            val category = navController.previousBackStackEntry?.savedStateHandle?.get<Category>("category")?: Category(-1, "","","")
            CategoryDetailsScreen(category = category)
        }
    }
}