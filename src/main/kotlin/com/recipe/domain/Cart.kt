package com.recipe.domain

import jakarta.persistence.*
import java.time.ZonedDateTime

@Entity
@Table(name = "carts")
data class Cart(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "total_in_cents", nullable = false)
    var totalInCents: Int = 0,

    @Column(name = "created_at", nullable = false)
    val createdAt: ZonedDateTime = ZonedDateTime.now(),

    @OneToMany(mappedBy = "cart", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val cartItems: MutableList<CartItem> = mutableListOf()
)
