package com.recipe.domain

import org.junit.jupiter.api.Assertions.*
import java.time.ZonedDateTime
import kotlin.test.Test

class CartItemTest {

    @Test
    fun `should calculate totalPrice correctly`() {
        // Given
        val cart = createTestCart()
        val recipe = createTestRecipe(priceInCents = 1500)
        val quantity = 3
        val cartItem = CartItem(cart, recipe, quantity)

        // When
        val totalPrice = cartItem.getTotalPrice()

        // Then
        assertEquals(4500, totalPrice) // 1500 * 3 = 4500
    }

    @Test
    fun `should calculate unitPrice correctly`() {
        // Given
        val cart = createTestCart()
        val recipe = createTestRecipe(priceInCents = 2000)
        val cartItem = CartItem(cart, recipe, 1)

        // When
        val unitPrice = cartItem.getUnitPrice()

        // Then
        assertEquals(2000, unitPrice)
    }


    private fun createTestCart(): Cart {
        return Cart(
            id = 1L,
            createdAt = ZonedDateTime.now()
        )
    }

    private fun createTestRecipe(priceInCents: Int = 1200): Recipe {
        return Recipe(
            id = 1L,
            name = "Test Recipe",
            priceInCents = priceInCents,
            description = "Test Description"
        )
    }


}