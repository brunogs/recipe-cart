package com.recipe.errors

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class RecipeNotFoundException(recipeId: Long) : Exception("Recipe with id $recipeId not found") {
}
