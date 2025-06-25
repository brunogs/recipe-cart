package com.recipe.service

import com.recipe.dto.ProductResponse
import com.recipe.repository.RecipeRepository
import com.recipe.dto.RecipeResponse
import org.springframework.stereotype.Service

@Service
class RecipeService (
    private val recipeRepository: RecipeRepository,
) {

    fun getAll() : List<RecipeResponse> {
        return recipeRepository.findAll().map {
            val products = it.recipeItems.map { item ->
                ProductResponse(
                    item.product.name,
                    item.quantity
                )
            }
            RecipeResponse(it, products)
        }
    }

}
