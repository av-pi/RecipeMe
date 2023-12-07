package com.example.recipeme

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    /** State members for categories */
    private val _categoriesState = mutableStateOf(CategoryState())
    val categoriesState: State<CategoryState> = _categoriesState

    /** State members for recipe previews */
    private val _recipePreviewStateFlow = MutableStateFlow(RecipePreviewState())
    val recipePreviewStateFlow = _recipePreviewStateFlow.asStateFlow()

    /** State members for recipes */
    private val _recipeStateFlow = MutableStateFlow(RecipeState())
    val recipeStateFlow = _recipeStateFlow.asStateFlow()


    init {
        fetchCategories()
    }

    /**
    / Makes network call to get all categories
    */
    private fun fetchCategories() {
        viewModelScope.launch {

            try {
                val response: CategoriesResponse = recipeService.getCategories()
                _categoriesState.value = _categoriesState.value.copy(
                    list = response.categories,
                    loading = false,
                    error = null
                )
            } catch (e: Exception) {
                _categoriesState.value = _categoriesState.value.copy(
                    loading = false,
                    error = "Error fetching categories ${e.message}"
                )
            }
        }
    }

    /**
     * Fetches all recipe previews of a specific category
     *
     * @param categoryName The specific category whose recipes must be previewed
     *
     * This function updates the MainViewModel's RecipePreview state
     */
    fun fetchRecipesByCategory(categoryName: String) {
        viewModelScope.launch {
            try {
                val response: RecipePreviewResponse = recipeService.getRecipePreviews(categoryName)
                _recipePreviewStateFlow.value = _recipePreviewStateFlow.value.copy(
                    list = response.meals,
                    loading = false,
                    error = null
                )
            } catch (e: Exception) {
                    _recipePreviewStateFlow.value = _recipePreviewStateFlow.value.copy(
                        loading = false,
                        error = "Error fetching $categoryName recipes! \n ${e.message}"
                    )
            }
        }
    }

    /**
     * Fetches a specific recipe from the backend
     *
     * @param id The id of the recipe to be fetched
     *
     * This function updates the Viewmodel's recipe state flow
     */
    fun fetchRecipeById(id: String) {
        viewModelScope.launch {
            try {
                val response: RecipeResponse = recipeService.getRecipeById(id)
                _recipeStateFlow.value = _recipeStateFlow.value.copy(
                    recipe = response.meals[0],
                    loading = false,
                    error = null
                )

            } catch (e: Exception) {
                _recipeStateFlow.value = _recipeStateFlow.value.copy(
                    loading = false,
                    error = "Error fetching the recipe! \n ${e.message}"
                )
            }
        }
    }

    /**
     * Class that represents the state of the retrieved categories
     */
    data class CategoryState(
        val loading: Boolean = true,
        val list: List<Category> = emptyList(),
        val error: String? = null
    )

    /**
     * Class that represents the state of the retrieved recipe previews in a category
     */
    data class RecipePreviewState(
        val loading: Boolean = true,
        val list: List<RecipePreview> = emptyList(),
        val error: String? = null
    )

    /**
     * Class that represents the state of the retrieved recipe
     */
    data class RecipeState(
        val loading: Boolean = true,
        val recipe: Recipe? = null,
        val error: String? = null
    )
}