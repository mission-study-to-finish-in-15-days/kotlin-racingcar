package step1

import io.kotest.core.spec.style.ExpectSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class PersonTestByBehaviorSpec : ExpectSpec({
    context("Person 객체로 이름 붙인 인자 테스트") {
        expect("서로 다르게 생성된 세 객체의 이름이 모두 같다.") {
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
    }

    context("Person 객체로 null 타입 테스트") {
        expect("nickName이 null 타입인지 확인한다.") {
            val person = Person.of(age = 28, nickName = null)
            person.nickName shouldBe null
        }
    }

    context("Person 객체로 기본 인자 테스트") {
        expect("기본 인자가 잘 동작하는지 확인한다.") {
            val person = Person.of(age = 28)
            person.officialProfile.name shouldBe "김효진"
            person.officialProfile.age shouldBe 28
            person.nickName shouldBe null
        }
    }

    context("Person 객체로 데이터 클래스의 기본 특성 테스트") {
        expect("서로 다르게 생성된 두 객체가 같아야한다.") {
            val person1 = Person.of(age = 28)
            val person2 = Person.of(age = 28)
            person1 shouldBe person2
        }
    }
})
