package com.recipe.service

import com.recipe.repository.RecipeRepository
import com.recipe.dto.RecipeResponse
import org.springframework.stereotype.Service

@Service
class RecipeService (
    private val recipeRepository: RecipeRepository,
) {

    fun getAll() : List<RecipeResponse> {
        return recipeRepository.findAll().map { RecipeResponse(it) }
    }

}
