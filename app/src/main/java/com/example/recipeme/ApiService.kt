package com.example.recipeme

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val retrofit = Retrofit
    .Builder()
    .baseUrl("https://www.themealdb.com/api/json/v1/1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val recipeService: ApiService = retrofit.create(ApiService::class.java)

/**
 * API service used by viewmodels to fetch data
 */
interface ApiService {

    /**
     * Function signature for fetching all categories from backend
     *
     * @return Returns a CategoryResponse instance managed in the viewmodel
     */
    @GET("categories.php")
    suspend fun getCategories(): CategoriesResponse

    /**
     * Function signature for fetching recipe previews of a specific category
     *
     * @param category The name of the category whose recipes must be previewed
     */
    @GET("filter.php")
    suspend fun getRecipePreviews(@Query("c") category: String): RecipePreviewResponse

}