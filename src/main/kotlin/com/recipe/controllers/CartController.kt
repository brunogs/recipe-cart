package com.recipe.controllers

import com.recipe.dto.AddRecipe
import com.recipe.dto.CartResponse
import com.recipe.service.CartService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
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

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/carts/{id}/add_recipe")
    fun addRecipes(
        @PathVariable id: Long,
        @RequestBody request: AddRecipe
    ) {
        cartService.addRecipe(id, request)
    }
}