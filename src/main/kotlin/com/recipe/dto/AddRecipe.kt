package com.recipe.dto

data class AddRecipe (
    val recipeId: Long,
    val quantity: Int = 1,
) {
    constructor(recipeId: Long) : this(recipeId, 1)
}
