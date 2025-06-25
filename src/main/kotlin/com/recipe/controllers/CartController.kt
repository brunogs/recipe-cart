package com.recipe.controllers

import com.recipe.dto.CartResponse
import com.recipe.service.CartService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CartController(
    private val cartService: CartService
) {

    @PostMapping("/carts")
    fun create() : CartResponse {
        //in real-world we'll create it exclusively to the user session
        return cartService.create()
    }
}