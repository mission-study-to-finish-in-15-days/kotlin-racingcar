package step01

import io.kotest.core.spec.style.FreeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import step01.*

class PersonKoTest : FreeSpec({
    "FreeSpec" - {
        "이름붙인 인자 테스트(Person 내 필드의 순서와 상관없이 이름으로 구분 가능)" {
            val persons =
                listOf(
                    Person(),
                    Person(
                        personInfo = PersonInfo(
                            name = Name(value = "정연재"),
                            age = Age(value = 28)
                        ),
                        nickname = NickName(value = "연재막")
                    ),
                    Person(
                        nickname = NickName("연재막"),
                        personInfo = PersonInfo(
                            age = Age(value = 28),
                            name = Name(value = "정연재")
                        )
                    )
                )
            persons.forAll {
                it.personInfo.name.value shouldBe "정연재"
                it.nickname.value shouldBe "연재막"
                it.personInfo.age.value shouldBe 28
            }
        }

        "널 타입 테스트 (nickname 은 nullable 필드)" {
            val person = Person(
                PersonInfo(
                    name = Name("정연재"),
                    age = Age(28)
                ),
                nickname = NickName(null)
            )

            person.personInfo.name.value shouldBe "정연재"
            person.nickname.value shouldBe null
            person.personInfo.age.value shouldBe 28
        }

        "기본 인자 테스트(생성자의 기본 인자를 지정 가능)" {
            val person = Person()
            person.personInfo.name.value shouldBe "정연재"
            person.nickname.value shouldBe "연재막"
            person.personInfo.age.value shouldBe 28
        }

        "데이터 클래스(1) - 구조분해" {
            val person = Person(
                PersonInfo(
                    name = Name("정연재"),
                    age = Age(28)
                ),
                nickname = NickName("연재막")
            )
            val (personInfo, nickname) = person
            println("$personInfo, $nickname")
        }

        "데이터 클래스(2) - copy()" {
            val person1 = Person(
                PersonInfo(
                    name = Name("정연재"),
                    age = Age(28)
                ),
                nickname = NickName("연재막")
            )
            val person2 = person1.copy(nickname = NickName("연재짱"))

            person2.personInfo.name.value shouldBe "정연재"
            person2.nickname.value shouldBe "연재짱"
            person2.personInfo.age.value shouldBe 28
        }

        "데이터 클래스(3) - 속성" {
            val person1 = Person(
                PersonInfo(
                    name = Name("정연재"),
                    age = Age(28)
                ),
                nickname = NickName("연재막")
            )
            val person2 = Person(
                PersonInfo(
                    name = Name("정연재"),
                    age = Age(28)
                ),
                nickname = NickName("연재막")
            )
            (person1 == person2) shouldBe true
            person1 shouldBe person2
        }
    }
})
