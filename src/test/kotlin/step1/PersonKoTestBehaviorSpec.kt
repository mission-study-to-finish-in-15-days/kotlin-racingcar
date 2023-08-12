package step1

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class PersonKoTestBehaviorSpec : BehaviorSpec({

    given("data 클래스인 person 객체가 하나 존재하고") {
        val person = Person("조경현", 33)

        `when`("copy 했을때") {
            val copiedPerson = person.copy()

            then("비교하면 똑같다!!") {
                person shouldBe copiedPerson
            }
        }
    }
})
