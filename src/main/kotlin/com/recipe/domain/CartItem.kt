package com.recipe.domain

import jakarta.persistence.*

@Entity
@Table(name = "cart_items")
data class CartItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    val cart: Cart,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    val recipe: Recipe? = null,

    @Column(nullable = false)
    val quantity: Int = 1
) {

    constructor(cart: Cart, recipe: Recipe, quantity: Int) : this(
        null,
        cart,
        recipe,
        quantity
    )

    fun getTotalPrice(): Int {
        return recipe?.let { it.priceInCents * quantity } ?: 0
    }

    fun getUnitPrice(): Int {
        return recipe?.priceInCents ?: 0
    }
}
