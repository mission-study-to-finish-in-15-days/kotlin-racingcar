package com.yoon.racingCar.domain.person

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class PersonBehaviorSpec: BehaviorSpec({

    Given("person") {
        val sut = Person("김윤지", 20)

        When("introduce 가 없는 경우를 확인하면") {
            Then("name 으로 반환된다.") {
                val actual = sut.introduce

                actual shouldBe sut.name
            }
        }

        When("introduce 을 다시 설정하면") {
            sut.setIntroduce("니하오. 워쓸찐율쯸")
            Then("자신의 소갯말에 반영된다.") {
                val actual = sut.introduce

                actual shouldBe "니하오. 워쓸찐율쯸"
            }
        }
    }
})

