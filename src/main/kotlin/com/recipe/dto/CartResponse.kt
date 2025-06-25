package com.recipe.dto

import java.time.ZonedDateTime

data class CartResponse (
    val id: Long = 0,
    var totalInCents: Int = 0,
    val createdAt: ZonedDateTime
)
