import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class PersonShouldTest : ShouldSpec({
    should("데이터 클래스 componentN") {
        val person = Person(name = "문구화", age = 28)

        person.component1() shouldBe "문구화"
        person.component2() shouldBe 28
        person.component3() shouldBe "구아과"
    }
})