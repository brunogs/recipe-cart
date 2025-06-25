package com.recipe.domain

import jakarta.persistence.*

@Entity
@Table(name = "recipes")
data class Recipe(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, length = 255)
    val name: String,

    @Column(name = "price_in_cents", nullable = false)
    val priceInCents: Int,

    @Column(columnDefinition = "TEXT")
    val description: String? = null,

    @OneToMany(mappedBy = "recipe", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val recipeItems: List<RecipeItem> = emptyList()
)
