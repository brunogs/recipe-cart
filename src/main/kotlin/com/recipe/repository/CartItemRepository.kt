package com.recipe.repository

import com.recipe.domain.CartItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.stereotype.Repository

@Repository
interface CartItemRepository : JpaRepository<CartItem, Long> {

    @Modifying
    fun deleteAllByCartIdAndRecipeId(cartId: Long, recipeId: Long): Int
}
