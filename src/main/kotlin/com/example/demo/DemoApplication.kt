package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class DemoApplication

@RestController
class FruitsController(
    private val repository: FruitRepository
) {
    @GetMapping("/fruits/{name}")
    fun getFruit(@PathVariable name: String): ResponseEntity<Map<String, Any>> {
        val fruit = repository.findByName(name) ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(
            mapOf(
                "name" to fruit.name,
                "color" to fruit.color,
                "price" to fruit.price,
            )
        )
    }
}

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}
