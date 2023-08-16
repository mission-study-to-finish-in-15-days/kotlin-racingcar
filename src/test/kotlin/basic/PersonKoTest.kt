package basic
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class PersonKoTest : StringSpec({

    "이름 붙인 인자" {
        val people = listOf(
            Person("신윤철", 28, "윤코니"),
            Person("신윤철", 28, _nickname = "윤코니"),
            Person(
                _name =  "신윤철",
                _nickname = "윤코니",
                _age = 28
            )
        )

        people.forAll {
            it.name shouldBe "신윤철"
        }
    }

    "널 타입" {
        val person = Person("신윤철", 28, null)
        person.name shouldBe "신윤철"
        person.age shouldBe 28
        person.nickname shouldBe null
    }

    "기본 인자" {
        val person = Person("신윤철", 28)
        person.name shouldBe "신윤철"
        person.age shouldBe 28
        person.nickname shouldBe "윤코니"
    }
    "데이터 클래스" {
        val person1 = Person("신윤철", 28)
        val person2 = Person("신윤철", 28)
        person1 shouldBe person2
    }
})
