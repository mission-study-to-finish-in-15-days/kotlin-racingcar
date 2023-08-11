import io.kotest.core.spec.style.*
import io.kotest.matchers.shouldBe

class PersonAnnotationTest : AnnotationSpec() {

    @Test
    fun `이름 있는 인자`() {
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
