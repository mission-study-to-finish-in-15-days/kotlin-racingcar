import io.kotest.core.spec.style.FunSpec
import io.kotest.core.spec.style.ShouldSpec

class PersonShouldTest : ShouldSpec({
    should("이름 있는 인자 ") {
        testFunction()
    }

    context("context 이름 있는 인자") {
        should("context.should 이름 있는 인자") {
            testFunction()
        }

        xshould("context.xtest 실행 하지 않는 테스트") {
            testFunction()
        }
    }

    xcontext("xcontext 실행하지 않는") {
        should("should 실행하지 않는") {
            testFunction()
        }
    }

})

