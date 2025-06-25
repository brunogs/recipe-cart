package com.recipe.controllers

import com.recipe.service.RecipeService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RecipeController (
    private val recipeService: RecipeService
) {

    @GetMapping("/recipes/")
    fun getAll() = recipeService.getAll()

}
