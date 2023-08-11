package step1

import io.kotest.assertions.assertSoftly
import io.kotest.assertions.withClue
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.FreeSpec
import io.kotest.core.test.TestCase
import io.kotest.core.test.TestCaseOrder
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldNotContain
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe

class PersonTest : FreeSpec({

    "이름 붙인 인자" - {
        /**
         * 코틀린의 Named Argument 와 Default Param 은 다른 언어처럼 내장된 기능이 아닌 코틀린 컴파일러의 전처리(생성자 오버로딩)으로 동작된다.
         * 즉 코틀린 코드로 선언된 라이브러리라고 해도, 코틀린 컴파일러를 거치지 않으면 Person("김지원", 28), Person(28) 같이 유연한 생성자 매개변수를 유지할 수 없다.
         *
         * 만약 자바에서도 똑같이 유연하게 생성자를 종류별로 만들어 두고 싶다면 함수에 @JvmOverloads 어노테이션을 붙이면 된다.
         */

        val person1: Person = Person("김지원", 28, "지원킴")
        val personX: Person = Person("김지원TT", 28, "지원킴TT")

        val people: List<Person> = listOf(
            person1,
            Person("김지원", 28, nickname = "지원킴"),
            Person(name = "김지원", nickname = "지원킴", age = 28),
        )

        assertSoftly {
            people.forAll { it.name shouldBe "김지원" }
            people shouldContain person1
            people shouldNotContain personX
        }
    }

    "널 타입" - {
        /**
         * 코틀린은 Nullable, Non-Nullable 타입이 구분되어 사용된다.
         * 코틀린의 Null 타입은 Optional 같이 래퍼 객체를 활용하는게 아니라, 코틀린 컴파일러가 컴파일 시점에 Null 체크를 해주는 것이다.
         *
         * 실제 자바코드로 변환하여 보면 @NotNull 어노테이션이 달려있는데, 이는 컴파일러 힌트이지 런타임에는 아무런 의미가 없기 때문에, 실행시 NullPointerException 이 발생할 수 있다.
         */
        val person: Person = Person("김지원", 28, null) // ?: fail("person 객체가 null 입니다.")

        assertSoftly {
            person.name shouldBe "김지원"
            person.name should { str -> str.matches("[_a-zA-Z가-힣]+".toRegex()) }
            person.age shouldBe 28
            person.nickname shouldBe null
        }
    }

    "기본 인자" - {
        val person: Person = Person("김지원", 28)

        assertSoftly {
            person.name shouldBe "김지원"
            person.age shouldBe 28
            person.nickname shouldBe "nick_${person.name}"
        }
    }

    "데이터 클래스" - {
        /**
         * data class 사용시 주의 사항 (* 참고로 override 해서 아래 메서드들을 직접 구현할 수 있음 )
         * - copy 는 얕은 복사이다.
         * - toString(), toEquals() 를 사용할 때 객체간의 연관관계가 있다면 무한 재귀 되지 않도록 주의해야한다.
         * - hashcode() 메서드는 생성자에 있는 모든 필드를 이용하여 복합 해쉬를 만든다.
         * - componentN() 은 아래 예시처럼 객체 분해에 사용된다. 다만 타입이나 이름이 아닌 생성자 순서로 사용해야해서 불편하다.
         * - data 키워드와 abstract, open, sealed, inner 는 함께 사용할 수 없다. 물론 상속도 불가능. 애초에 함께 사용할 일이 없기도 하고
         */
        val person1: Person = Person("김지원", 28)
        val person2: Person = Person("김지원", 28)

        // 순서 변경이 안되서  잘 안쓰지만, 테스트에선 유용해요
        val (name: String, age: Int, nickname: String?) = person2

        assertSoftly {
            withClue("person1 과 person2는 같아야 한다.") { person1 shouldBe person2 }
            name shouldBe person2.name
            age shouldBe person2.age
            nickname shouldBe person2.nickname
        }
    }
}) {

    /**
     * 테스트 전처리
     * @see [ https://kotest.io/docs/framework/lifecycle-hooks.html ]
     */
    override suspend fun beforeEach(testCase: TestCase) {}

    /**
     * 테스트 인스턴스 생성 방식
     * @see [ https://kotest.io/docs/framework/isolation-mode.html ]
     */
    override fun isolationMode() = IsolationMode.SingleInstance

    /**
     * 테스트 순서 지정 (* 기본적으로 코루틴 - 비동기로 실행됨, 다만 순서는 테스트 컨텍스트 복잡해짐 + 테스트 실행시간이 늘어나므로 비추천 )
     * @see [ https://kotest.io/docs/framework/test-ordering.html ]
     */
    override fun testCaseOrder() = TestCaseOrder.Sequential
}
