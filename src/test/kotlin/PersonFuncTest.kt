import io.kotest.core.spec.style.FunSpec

class PersonFuncTest : FunSpec({
    test("이름 있는 인자 ") {
        testFunction()
    }

    context("context 이름 있는 인자") {
        test("context.test 이름 있는 인자") {
            testFunction()
        }

        xtest("context.xtest 실행 하지 않는 테스트") {
            testFunction()
        }
    }

    xcontext("xcontext 실행하지 않는") {
        test("test 실행하지 않는") {
            testFunction()
        }
    }

})

