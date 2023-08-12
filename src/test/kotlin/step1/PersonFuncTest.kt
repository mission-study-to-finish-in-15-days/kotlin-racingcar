import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import step1.Person

class PersonFuncTest : FunSpec(
    {
        test("데이터 클래스 toString") {
            val person1 = Person(name="신윤철", age=28, nickname = "윤코니")
            val person2 = Person("신윤철", 28)

            person1.toString() shouldBe person2.toString()
        }

        test("데이터 클래스 hashCode") {
            val person1 = Person(name="신윤철", age=28, nickname = "윤코니")
            val person2 = Person("신윤철", 28)

            person1.hashCode() shouldBe person2.hashCode()
        }

        test("데이터 클래스 copy") {
            val person1 = Person(name="신윤철", age=28, nickname = "윤코니")
            val person2 = Person("신윤철", 28)

            person1 shouldBe person2
        }

        test("데이터 클래스 componentN"){
            val person1 = Person(name="신윤철", age=28, nickname = "윤코니")
            val (name, age, nickname) = person1
        }
    }
)