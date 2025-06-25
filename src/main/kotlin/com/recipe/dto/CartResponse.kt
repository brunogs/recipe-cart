package com.recipe.dto

import java.time.ZonedDateTime

data class CartResponse (
    val id: Long = 0,
    var totalInCents: Int = 0,
    val createdAt: ZonedDateTime,
    val items: List<CartItemResponse> = listOf(),
)

data class CartItemResponse (
    val recipeId: Long,
    val recipeName: String,
    val recipeDescription: String,
    val quantity: Int,
    val totalPrice: Int,
    val unitPrice: Int,
)
