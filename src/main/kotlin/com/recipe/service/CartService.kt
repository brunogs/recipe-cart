package com.recipe.service

import com.recipe.domain.Cart
import com.recipe.domain.CartItem
import com.recipe.domain.Recipe
import com.recipe.dto.AddRecipe
import com.recipe.dto.CartItemResponse
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
    private val cartItemRepository: CartItemRepository,
) {

    @Transactional
    fun create() : CartResponse {
        val cart = cartRepository.save(Cart())
        return CartResponse(cart.id, 0, cart.createdAt)
    }

    @Transactional(readOnly = true)
    fun getById(id: Long): CartResponse {
        val cart = cartRepository.findById(id).orElseThrow { CartNotFoundException(id) }
        val items = cart.cartItems
        val total = items.sumOf { it.getTotalPrice() }
        val cartItems = items.map { CartItemResponse(
            recipeId = it.recipe!!.id,
            recipeName = it.recipe!!.name,
            recipeDescription = it.recipe!!.description ?: "",
            quantity = it.quantity,
            totalPrice = it.getTotalPrice(),
            unitPrice = it.getUnitPrice(),
        ) }

        return CartResponse(
            cart.id,
            total,
            cart.createdAt,
            cartItems,
        )
    }

    @Transactional
    fun addRecipe(id: Long, request: AddRecipe) {
        val cart = cartRepository.findById(id).orElseThrow { CartNotFoundException(id) }
        val recipe = recipeRepository.findById(request.recipeId).orElseThrow { RecipeNotFoundException(request.recipeId) }

        val cartItemResult = cartItemRepository.findByCartIdAndRecipeId(id, recipe.id)

        cartItemResult.ifPresentOrElse(
            { updateCartItemQuantity(it, request.quantity) },
            { addNewCartItem(cart, recipe, request.quantity) }
        )
    }

    private fun updateCartItemQuantity(cartItem: CartItem, increaseQuantity: Int) {
        val update = cartItem.copy(quantity = cartItem.quantity + increaseQuantity)
        cartItemRepository.save(update)
    }

    private fun addNewCartItem(cart: Cart, recipe: Recipe, quantity: Int) {
        val cartITem = CartItem(cart, recipe, quantity)
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
