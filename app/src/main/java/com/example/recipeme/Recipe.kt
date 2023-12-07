package com.example.recipeme

import java.util.Date

data class Recipe(
    val idMeal: String,
    val strMeal: String,
    val strDrinkAlternate: String?,
    val strCategory: String,
    val strArea: String,
    val strInstructions: String,
    val strMealThumb: String,
    val strTags: String,
    val strYoutube: String,
    val strIngredient1: String,
    val strIngredient2: String,
    val strIngredient3: String,
    val strIngredient4: String,
    val strIngredient5: String,
    val strIngredient6: String,
    val strIngredient7: String,
    val strIngredient8: String,
    val strIngredient9: String,
    val strIngredient10: String,
    val strIngredient11: String,
    val strIngredient12: String,
    val strIngredient13: String,
    val strIngredient14: String,
    val strIngredient15: String,
    val strIngredient16: String,
    val strIngredient17: String,
    val strIngredient18: String,
    val strIngredient19: String,
    val strIngredient20: String,
    val strMeasure1: String,
    val strMeasure2: String,
    val strMeasure3: String,
    val strMeasure4: String,
    val strMeasure5: String,
    val strMeasure6: String,
    val strMeasure7: String,
    val strMeasure8: String,
    val strMeasure9: String,
    val strMeasure10: String,
    val strMeasure11: String,
    val strMeasure12: String,
    val strMeasure13: String,
    val strMeasure14: String,
    val strMeasure15: String,
    val strMeasure16: String,
    val strMeasure17: String,
    val strMeasure18: String,
    val strMeasure19: String,
    val strMeasure20: String,
    val strSource: String,
    val strImageSource: String?,
    val strCreativeCommonsConfirmed: String?,
    val dateModified: Date?,
    @Transient private var ingredientsList: MutableList<Ingredient>? = null
) {

    /**
     * Getter function that parses all ingredients and their measures into a single list
     */
    fun getIngredientsList(): MutableList<Ingredient> {

        if (ingredientsList == null) {
            ingredientsList = mutableListOf()
        }

        if (ingredientsList!!.isEmpty()) {
            if (strIngredient1.isNotEmpty()) ingredientsList!!.add(Ingredient(strIngredient1, strMeasure1))
            if (strIngredient2.isNotEmpty()) ingredientsList!!.add(Ingredient(strIngredient2, strMeasure2))
            if (strIngredient3.isNotEmpty()) ingredientsList!!.add(Ingredient(strIngredient3, strMeasure3))
            if (strIngredient4.isNotEmpty()) ingredientsList!!.add(Ingredient(strIngredient4, strMeasure4))
            if (strIngredient5.isNotEmpty()) ingredientsList!!.add(Ingredient(strIngredient5, strMeasure5))
            if (strIngredient6.isNotEmpty()) ingredientsList!!.add(Ingredient(strIngredient6, strMeasure6))
            if (strIngredient7.isNotEmpty()) ingredientsList!!.add(Ingredient(strIngredient7, strMeasure7))
            if (strIngredient8.isNotEmpty()) ingredientsList!!.add(Ingredient(strIngredient8, strMeasure8))
            if (strIngredient9.isNotEmpty()) ingredientsList!!.add(Ingredient(strIngredient9, strMeasure9))
            if (strIngredient10.isNotEmpty()) ingredientsList!!.add(Ingredient(strIngredient10, strMeasure10))
            if (strIngredient11.isNotEmpty()) ingredientsList!!.add(Ingredient(strIngredient11, strMeasure11))
            if (strIngredient12.isNotEmpty()) ingredientsList!!.add(Ingredient(strIngredient12, strMeasure12))
            if (strIngredient13.isNotEmpty()) ingredientsList!!.add(Ingredient(strIngredient13, strMeasure13))
            if (strIngredient14.isNotEmpty()) ingredientsList!!.add(Ingredient(strIngredient14, strMeasure14))
            if (strIngredient15.isNotEmpty()) ingredientsList!!.add(Ingredient(strIngredient15, strMeasure15))
            if (strIngredient16.isNotEmpty()) ingredientsList!!.add(Ingredient(strIngredient16, strMeasure16))
            if (strIngredient17.isNotEmpty()) ingredientsList!!.add(Ingredient(strIngredient17, strMeasure17))
            if (strIngredient18.isNotEmpty()) ingredientsList!!.add(Ingredient(strIngredient18, strMeasure18))
            if (strIngredient19.isNotEmpty()) ingredientsList!!.add(Ingredient(strIngredient19, strMeasure19))
            if (strIngredient20.isNotEmpty()) ingredientsList!!.add(Ingredient(strIngredient20, strMeasure20))
        }

        return ingredientsList!!
    }
}

data class Ingredient(val name: String, val quantity: String)

data class RecipeResponse(val meals: List<Recipe>)