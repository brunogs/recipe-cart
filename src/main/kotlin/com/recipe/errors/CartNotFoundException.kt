package com.recipe.errors

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class CartNotFoundException(cartId: Long) : Exception("Cart with id $cartId not found") {
}