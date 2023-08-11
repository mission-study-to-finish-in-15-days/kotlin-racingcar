import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class PersonKoTest : StringSpec({
    "이름 붙인 인자" {
        val people = listOf(
            Person("문구화", 28, "구아과"),
            Person("문구화", 28, nickname = "구아과"),
            Person(
                name = "문구화",
                nickname = "구아과",
                age = 28
            )
        )

        people.forAll {
            it.name shouldBe "문구화"
        }
    }

    "널 타입" {
        val person = Person("문구화", 28, null)

        person.name shouldBe "문구화"
        person.age shouldBe 28
        person.nickname shouldBe null
    }

    "기본 인자" {
        val person = Person("문구화", 28)

        person.name shouldBe "문구화"
        person.age shouldBe 28
        person.nickname shouldBe "구아과"
    }

    "데이터 클래스" {
        val person1 = Person("문구화", 28)
        val person2 = Person("문구화", 28)

        person1 shouldBe person2
    }
})