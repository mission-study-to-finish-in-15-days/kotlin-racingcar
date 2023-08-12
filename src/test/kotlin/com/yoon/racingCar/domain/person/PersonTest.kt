package com.yoon.racingCar.domain.person

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class PersonTest : StringSpec({
    "이름 붙인 인자" {
        val people = listOf(
            Person("김윤지", 28, "윤지막"),
            Person("김윤지", 29, _nickname = "윤지막"),
            Person(_name = "김윤지", _age = 28)
        )

        people.forAll {
            it.name shouldBe "김윤지"
        }
    }

   "널 타입" {
        val person = Person("김윤지", 28, null)
        person.name shouldBe "김윤지"
        person.age shouldBe 28
        person.nickname shouldBe null
    }

    "기본 인자" {
        val person = Person("김윤지", 28)
        person.name shouldBe "김윤지"
        person.age shouldBe 28
        person.nickname shouldBe "윤지막"
    }

     "데이터 클래스" {
         val person1 = Person("김윤지", 28)
         val person2 = Person("김윤지", 28)
         person1 shouldBe person2
     }
})