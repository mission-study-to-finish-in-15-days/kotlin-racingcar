package step1

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PersonKoTest : StringSpec({

    "이름 붙인 인자" {
        val people = listOf(
            Person("조경현", 33, "kycho"),
            Person("조경현", 33, nickname = "kycho"),
            Person(
                name = "조경현",
                nickname = "kycho",
                age = 33
            )
        )

        people.forAll {
            it.name shouldBe "조경현"
        }
    }

    "널 타입" {
        val person = Person("조경현", 33, null)
        person.name shouldBe "조경현"
        person.age shouldBe 33
        person.nickname shouldBe null
    }

    "기본 인자" {
        val person = Person("조경현", 33)
        person.name shouldBe "조경현"
        person.age shouldBe 33
        person.nickname shouldBe "kycho"
    }


    "데이터 클래스" {
        val person1 = Person("조경현", 33)
        val person2 = Person("조경현", 33)
        person1 shouldBe person2
    }
})