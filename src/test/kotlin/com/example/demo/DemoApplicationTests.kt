package com.example.demo

import io.kotest.core.spec.style.DescribeSpec
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
class DemoApplicationTests : DescribeSpec({

    it("contextLoads") {
    }

    describe("디스크라이브 1") {
        context("컨텍스트 1") {
            it("어써트 1") {}
            it("어써트 2") {}
        }
    }

    describe("디스크라이브 2") {
        context("컨텍스트 2") {
            it("어써트 3") {}
        }
        context("컨텍스트 3") {
            it("어써트 4") {}
        }
    }
})
