package com.recipe.dto

import com.recipe.domain.Recipe

data class RecipeResponse(
    val id: Long = 0,
    val name: String,
    val priceInCents: Int,
    val description: String? = null,
    val products: List<ProductResponse> = listOf(),
) {
    constructor(recipe: Recipe, products: List<ProductResponse>) : this(
        recipe.id,
        recipe.name,
        recipe.priceInCents,
        recipe.description,
        products,
    )
}

data class ProductResponse(
    val name: String,
    val quantity: Int,
)