package com.recipe.service

import com.recipe.RecipeApplicationTests
import com.recipe.dto.AddRecipe
import com.recipe.errors.CartNotFoundException
import com.recipe.errors.RecipeNotFoundException
import com.recipe.service.CartService
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired

@Tag("integration")
class CartServiceIntegrationTest : RecipeApplicationTests() {

    @Autowired
    private lateinit var cartService: CartService

    @Nested
    inner class CartCreationTests {

        @Test
        fun `should create new cart successfully`() {
            // When
            val cartResponse = cartService.create()

            // Then
            assertTrue(cartResponse.id > 0)
            assertEquals(0, cartResponse.totalInCents)
            assertTrue(cartResponse.items.isEmpty())
        }
    }

    @Nested
    inner class CartRetrievalTests {

        @Test
        fun `should get existing cart by id`() {
            // Given - Create a cart first
            val createdCart = cartService.create()

            // When
            val cartResponse = cartService.getById(createdCart.id!!)

            // Then
            assertEquals(createdCart.id, cartResponse.id)
            assertEquals(0, cartResponse.totalInCents)
            assertTrue(cartResponse.items.isEmpty())
        }
    }

    @Nested
    inner class AddRecipeTests {

        @Test
        fun `should throw cart not found exception for invalid cart id`() {
            // Given
            val invalidCartId = 333L
            val addRecipeRequest = AddRecipe(recipeId = 1L, quantity = 1)

            // When & Then
            assertThrows<CartNotFoundException> {
                cartService.addRecipe(invalidCartId, addRecipeRequest)
            }
        }

        @Test
        fun `should throw recipe not found exception for invalid recipe id`() {
            // Given
            val cart = cartService.create()
            val invalidRecipeId = 999L
            val addRecipeRequest = AddRecipe(recipeId = invalidRecipeId, quantity = 1)

            // When & Then
            assertThrows<RecipeNotFoundException> {
                cartService.addRecipe(cart.id!!, addRecipeRequest)
            }
        }

        @Test
        fun `should add valid recipe to cart successfully`() {
            // Given
            val cart = cartService.create()
            val validRecipeId = 3L
            val addRecipeRequest = AddRecipe(recipeId = validRecipeId, quantity = 1)

            // When
            cartService.addRecipe(cart.id!!, addRecipeRequest)

            // Then - Verify the recipe was added
            val updatedCart = cartService.getById(cart.id!!)
            assertEquals(1, updatedCart.items.size)
            assertEquals(validRecipeId, updatedCart.items[0].recipeId)
            assertEquals(1, updatedCart.items[0].quantity)
            assertTrue(updatedCart.totalInCents > 0)
        }

        @Test
        fun `should add multiple recipes to cart`() {
            // Given
            val cart = cartService.create()
            val recipe1Id = 1L // Assuming recipes with these IDs exist
            val recipe2Id = 2L

            // When
            cartService.addRecipe(cart.id!!, AddRecipe(recipeId = recipe1Id, quantity = 2))
            cartService.addRecipe(cart.id!!, AddRecipe(recipeId = recipe2Id, quantity = 1))

            // Then
            val updatedCart = cartService.getById(cart.id!!)
            assertEquals(2, updatedCart.items.size)
            
            val recipe1Item = updatedCart.items.firstOrNull { it.recipeId == recipe1Id }
            val recipe2Item = updatedCart.items.firstOrNull { it.recipeId == recipe2Id }
            
            assertEquals(2, recipe1Item!!.quantity)
            assertEquals(1, recipe2Item!!.quantity)
        }

        @Test
        fun `should increase quantity when adding same recipe twice`() {
            // Given
            val cart = cartService.create()
            val recipeId = 1L

            // When
            cartService.addRecipe(cart.id!!, AddRecipe(recipeId = recipeId, quantity = 2))
            cartService.addRecipe(cart.id!!, AddRecipe(recipeId = recipeId, quantity = 3))

            // Then
            val updatedCart = cartService.getById(cart.id!!)
            assertEquals(1, updatedCart.items.size)
            assertEquals(5, updatedCart.items[0].quantity) // 2 + 3 = 5
        }
    }

    @Nested
    inner class DeleteRecipeTests {

        @Test
        fun `should throw recipe not found exception for invalid recipe id`() {
            // Given
            val cart = cartService.create()
            val invalidRecipeId = 999L

            // When & Then
            assertThrows<RecipeNotFoundException> {
                cartService.deleteRecipe(cart.id!!, invalidRecipeId)
            }
        }

        @Test
        fun `should delete recipe from cart successfully`() {
            // Given
            val cart = cartService.create()
            val recipeId = 2L
            
            // Add recipe first
            cartService.addRecipe(cart.id!!, AddRecipe(recipeId = recipeId, quantity = 2))
            
            // Verify it was added
            var cartResponse = cartService.getById(cart.id!!)
            assertEquals(1, cartResponse.items.size)

            // When
            cartService.deleteRecipe(cart.id!!, recipeId)

            // Then
            cartResponse = cartService.getById(cart.id!!)
            assertEquals(0, cartResponse.items.size)
            assertEquals(0, cartResponse.totalInCents)
        }

        @Test
        fun `should delete only specified recipe when multiple recipes exist`() {
            // Given
            val cart = cartService.create()
            val recipe1Id = 1L
            val recipe2Id = 2L
            
            // Add multiple recipes
            cartService.addRecipe(cart.id!!, AddRecipe(recipeId = recipe1Id, quantity = 1))
            cartService.addRecipe(cart.id!!, AddRecipe(recipeId = recipe2Id, quantity = 2))
            
            // Verify both were added
            var cartResponse = cartService.getById(cart.id!!)
            assertEquals(2, cartResponse.items.size)

            // When - Delete only recipe2
            cartService.deleteRecipe(cart.id!!, recipe2Id)

            // Then - Only recipe1 should remain
            cartResponse = cartService.getById(cart.id!!)
            assertEquals(1, cartResponse.items.size)
            assertEquals(recipe1Id, cartResponse.items[0].recipeId)
        }
    }
}
