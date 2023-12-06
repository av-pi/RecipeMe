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

}