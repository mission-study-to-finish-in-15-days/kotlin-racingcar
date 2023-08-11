import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PersonStringTest : StringSpec({

    "이름 있는 인자".config(enabled = true, invocations = 2) {
        val perple = listOf(
            Person("김종인", 33, "종인막"),
            Person("김종인", 33, nickname = "종인막"),
            Person(name = "김종인", nickname = "종인막", age = 33),
        )

        perple.forEach {
            it.name shouldBe "김종인"
        }
    }

    "널 타입" {
        val person = Person("김종인", 33, null)
        person.name shouldBe "김종인"
        person.age shouldBe 33
        person.nickname shouldBe null
    }

    "기본 인자" {
        val person = Person("김종인", 33)
        person.name shouldBe "김종인"
        person.age shouldBe 33
        person.nickname shouldBe "종인막"
    }

    "데이터 클래스" {
        val person1 = Person("김종인", 33)
        val person2 = Person("김종인", 33)
        person1 shouldBe person2
    }

})
