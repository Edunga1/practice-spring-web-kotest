package com.example.kotestcallbacks

import io.kotest.core.spec.style.DescribeSpec
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
class DemoApplicationTests : DescribeSpec({

    it("contextLoads") {
    }

    describe("something") {
        context("ctx 1") {
            it("assert 1") {}
            it("assert 2") {}
        }
    }

    describe("something else") {
        context("ctx 2") {
            it("assert 3") {}
        }
        context("ctx 3") {
            it("assert 4") {}
        }
    }
})
