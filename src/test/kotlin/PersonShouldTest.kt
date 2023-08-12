import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class PersonShouldTest : ShouldSpec({
    should("데이터 클래스 componentN") {
        val (name, age, nickname) = Person(name = "문구화", age = 28)

        name shouldBe "문구화"
        age shouldBe 28
        nickname shouldBe "구아과"
    }
})
