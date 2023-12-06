package com.example.recipeme

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Data class which is the parsed JSON response of all categories from the backend into Kotlin objects
 *
 * @param idCategory The unique id of the category
 * @param strCategory The name of the category
 * @param strCategoryThumb Link to the thumbnail of the category
 * @param strCategoryDescription Text description of the category
 */
@Parcelize
data class Category(
    val idCategory: Int,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String
): Parcelable

/**
 * Data class used in parsing the JSON response of the categories to Kotlin objects
 *
 * @param categories The list of categories returned in the JSON response parsed as a list
 */
data class CategoriesResponse(val categories: List<Category>)
