import io.kotest.core.spec.style.FeatureSpec
import io.kotest.core.spec.style.FreeSpec
import io.kotest.core.spec.style.StringSpec
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

class PersonFeatureTest : FeatureSpec({

    feature("이름 있는 인자") {
        scenario("이름 있는 경우") {
            val perple = listOf(
                Person("김종인", 33, "종인막"),
                Person("김종인", 33, nickname = "종인막"),
                Person(name = "김종인", nickname = "종인막", age = 33),
            )

            perple.forEach {
                it.name shouldBe "김종인"
            }
        }
    }

    feature("널 타입") {
        val person = Person("김종인", 33, null)
        person.name shouldBe "김종인"
        person.age shouldBe 33
        person.nickname shouldBe null
    }

    xfeature("실행 안될 피쳐") {
        val person = Person("김종인", 33)
        person.name shouldBe "김종인"
        person.age shouldBe 33
        person.nickname shouldBe "종인막"
    }

    xfeature("데이터 클래스") {
        scenario("실행 안될 시나리오") {
            val person1 = Person("김종인", 33)
            val person2 = Person("김종인", 33)
            person1 shouldBe person2
        }
    }

})
