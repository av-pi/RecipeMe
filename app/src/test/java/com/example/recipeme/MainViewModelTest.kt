package com.example.recipeme

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@ExperimentalCoroutinesApi
class MainViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    lateinit var viewModel: MainViewModel
    lateinit var apiService: ApiService

    @Before
    fun setUp() {
        viewModel = MainViewModel()
        apiService = Retrofit
            .Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Test
    fun shouldFetchRecipePreviewsSuccessfully_whenFetchRecipeByCategoryIsCalled() = runTest {

        // Arrange
        val expectedResponse = listOf<RecipePreview>(
            RecipePreview("Mbuzi Choma (Roasted Goat)",
                "https://www.themealdb.com/images/media/meals/cuio7s1555492979.jpg",
                52968)
        )

        // Act
        val categoryName = "Goat"
        val actualResponse = apiService.getRecipePreviews(categoryName).meals

        // Assert
        assertEquals(expectedResponse, actualResponse)
    }

    @Test
    fun shouldFetchRecipeSuccessfully_whenFetchByRecipeIdIsCalled() = runTest {
        // Arrange
        val expectedValue = listOf<Recipe>(
            Recipe(
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
        )

        // Act
        val recipeId = "52959"
        val actualValue = apiService.getRecipeById(recipeId).meals

        // Assert
        assertEquals(expectedValue[0], actualValue[0])
        assertEquals(expectedValue[0].getIngredientsList(), actualValue[0].getIngredientsList())
    }

}