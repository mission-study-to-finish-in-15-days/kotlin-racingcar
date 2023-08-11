import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class PersonFreeTest : FreeSpec({

    "이름 있는 인자" - {
        "이름 있는 경우" {
            val perple = listOf(
                Person("김종인", 33, "종인막"),
                Person("김종인", 33, _nickname = "종인막"),
                Person(_name = "김종인", _nickname = "종인막", _age = 33),
            )

            perple.forEach {
                it.name shouldBe "김종인"
            }
        }
    }

    "널 타입" - {
        val person = Person("김종인", 33, null)
        person.name shouldBe "김종인"
        person.age shouldBe 33
        person.nickname shouldBe null
    }

    "기본 인자" - {
        val person = Person("김종인", 33)
        person.name shouldBe "김종인"
        person.age shouldBe 33
        person.nickname shouldBe "종인막"
    }

    "데이터 클래스" - {
        val person1 = Person("김종인", 33)
        val person2 = Person("김종인", 33)
        person1 shouldBe person2
    }
})
