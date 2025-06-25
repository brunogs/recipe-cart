package com.recipe.service

import com.recipe.domain.Cart
import com.recipe.dto.CartResponse
import com.recipe.repository.CartRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CartService(
    private val cartRepository: CartRepository
) {

    @Transactional
    fun create() : CartResponse {
        val cart = cartRepository.save(Cart())
        return CartResponse(cart.id, cart.totalInCents, cart.createdAt)
    }
}
