package com.recipe

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
	fromApplication<RecipeApplication>().with(TestcontainersConfiguration::class).run(*args)
}
