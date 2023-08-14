package step1_kotlin_basic

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class PersonTestByStringSpec : StringSpec({
    "이름 붙인 인자가 잘 동작하는지 확인한다" {
        val people = listOf(
            Person.of("김효진", 28, "hyo"),
            Person.of(name = "김효진", age = 28, nickName = "hyo"),
            Person.of(name = "김효진", 28, "hyo"),
        )

        people.forAll {
            it.officialProfile.name shouldBe "김효진"
            it.officialProfile.age shouldBe 28
            it.nickName shouldBe "hyo"
        }
    }

    "nickName이 null 타입인지 확인한다" {
        val person = Person.of(age = 28, nickName = null)
        person.nickName shouldBe null
    }

    "기본 인자가 잘 동작하는지 확인한다" {
        val person = Person.of(age = 28)
        person.officialProfile.name shouldBe "김효진"
        person.officialProfile.age shouldBe 28
        person.nickName shouldBe null
    }

    "데이터 클래스의 동작을 확인한다" {
        val person1 = Person.of(age = 28)
        val person2 = Person.of(age = 28)

        person1 shouldBe person2
    }
})
