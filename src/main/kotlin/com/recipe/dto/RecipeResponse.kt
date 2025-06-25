package com.recipe.dto

import com.recipe.domain.Recipe

data class RecipeResponse(
    val id: Long = 0,
    val name: String,
    val priceInCents: Int,
    val description: String? = null,
) {
    constructor(recipe: Recipe) : this(
        recipe.id,
        recipe.name,
        recipe.priceInCents,
        recipe.description
    )
}
