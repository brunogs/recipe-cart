package com.recipe.service

import com.recipe.domain.Cart
import com.recipe.domain.CartItem
import com.recipe.dto.AddRecipe
import com.recipe.dto.CartResponse
import com.recipe.errors.CartNotFoundException
import com.recipe.errors.RecipeNotFoundException
import com.recipe.repository.CartItemRepository
import com.recipe.repository.CartRepository
import com.recipe.repository.RecipeRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CartService(
    private val cartRepository: CartRepository,
    private val recipeRepository: RecipeRepository,
    private val cartItemRepository: CartItemRepository
) {

    @Transactional
    fun create() : CartResponse {
        val cart = cartRepository.save(Cart())
        return CartResponse(cart.id, cart.totalInCents, cart.createdAt)
    }

    @Transactional
    fun addRecipe(id: Long, request: AddRecipe) {
        val cart = cartRepository.findById(id).orElseThrow { CartNotFoundException(id) }
        val recipe = recipeRepository.findById(request.recipeId).orElseThrow { RecipeNotFoundException(request.recipeId) }

        val cartITem = CartItem(cart, recipe, request.quantity)
        cartItemRepository.save(cartITem)
    }

    @Transactional
    fun deleteRecipe(cartId: Long, recipeId: Long) {
        val rowsAffected = cartItemRepository.deleteAllByCartIdAndRecipeId(cartId, recipeId)
        if (rowsAffected == 0) {
            throw RecipeNotFoundException(recipeId)
        }
    }
}
