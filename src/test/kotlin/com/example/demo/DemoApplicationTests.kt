package com.example.demo

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldHaveSize
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.transaction.annotation.Transactional

@Transactional
@AutoConfigureMockMvc
@SpringBootTest
class DemoApplicationTests(
    private val mvc: MockMvc,
    private val repository: FruitRepository
) : DescribeSpec({
    describe("/fruits/{name}") {
        fun request(name: String) = mvc.get("/fruits/$name")

        context("fruit exists") {
            val fruit = Fruit(name = "apple", color = "red", price = 200)

            val subject by lazy {
                repository.save(fruit)
                request(fruit.name)
            }

            it("responds with status 200") {
                subject.andExpect { status { isOk() } }
            }

            it("responds with fruit") {
                subject.andExpect {
                    jsonPath("$.name") { value("apple") }
                    jsonPath("$.color") { value("red") }
                    jsonPath("$.price") { value(200) }
                }
            }
        }

        context("fruit does not exist") {
            val subject by lazy { request("apple") }

            it("responds with status 404") {
                // previous fruit is rolled back. so it does not exist
                repository.findAll() shouldHaveSize 0
                subject.andExpect { status { isNotFound() } }
            }
        }
    }
})
