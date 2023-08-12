package step1

import io.kotest.core.spec.style.FunSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class PersonKoTestFunSpec : FunSpec({

    test("이름 붙인 인자") {
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
            it.age shouldBe 33
            it.nickname shouldBe "kycho"
        }
    }

    test("널 타입") {
        val person = Person("조경현", 33, null)
        person.name shouldBe "조경현"
        person.age shouldBe 33
        person.nickname shouldBe null
    }

    test("기본 인자") {
        val person = Person("조경현", 33)
        person.name shouldBe "조경현"
        person.age shouldBe 33
        person.nickname shouldBe "kycho"
    }

    context("데이터 클래스") {
        val person = Person("조경현", 33, "kycho")

        test("equals()") {
            val person2 = Person("조경현", 33, "kycho")
            person shouldBe person2
        }

        test("copy()") {
            val copiedPerson = person.copy()

            copiedPerson.name shouldBe "조경현"
            copiedPerson.age shouldBe 33
            copiedPerson.nickname shouldBe "kycho"
        }

        test("componentN()") {
            val (name, age, nickname) = person

            name shouldBe "조경현"
            age shouldBe 33
            nickname shouldBe "kycho"
        }
    }
})
