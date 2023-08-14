package dataclass

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class PersonTest : AnnotationSpec() {
    @Test
    fun `이름 붙은 인자를 이용하여 객체를 생성할 수 있다`() {
        val people = listOf(
            Person(),
            Person(
                nameInfo = NameInfo(
                    name = Name("김준우"),
                    nickName = NickName("junuuu"),
                ),
                age = Age(26)
            ),
            Person(
                NameInfo(
                    Name("김준우"),
                    nickName = NickName("junuuu"),
                ),
                age = Age(26)
            )
        )

        people.forAll {
            val nameInfo = it.nameInfo
            nameInfo.getName() shouldBe "김준우"
        }
    }

    @Test
    fun `null 타입 다루어보기`() {
        val person = Person(
            NameInfo(Name("김준우"), NickName(null)),
            Age(26)
        )

        person.getName() shouldBe "김준우"
        person.getAge() shouldBe 26
        person.getNickname() shouldBe null
    }

    @Test
    fun `기본인자 default-arguments 다루어보기`() {
        val person = Person()

        person.getName() shouldBe "김준우"
    }

    @Test
    fun `데이터 클래스 다루어보기`() {
        val person1 = Person()
        val person2 = Person()

        person1 shouldBe person2
    }
}
