package step1

import io.kotest.core.spec.style.BehaviorSpec

class PersonBehaviorTest : BehaviorSpec({
    given("이름 있는 인자 ") {
        testFunction()
    }

    given("context 이름 있는 인자") {
        `when`("context.given 이름 있는 인자") {
            then("이름이 일치 한다.") {
                testFunction()
            }
        }

        xand("xand 실행 하지 않는 테스트") {
            testFunction()
        }
    }

    xgiven("xcontext 실행하지 않는") {
        and("given 실행하지 않는") {
            testFunction()
        }
    }
})
