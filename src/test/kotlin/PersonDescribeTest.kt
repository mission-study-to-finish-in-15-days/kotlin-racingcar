import io.kotest.core.spec.style.DescribeSpec

class PersonDescribeTest : DescribeSpec({
    it("이름 있는 인자 ") {
        testFunction()
    }

    context("context 이름 있는 인자") {
        it("context.it 이름 있는 인자") {
            testFunction()
        }

        xit("context.xtest 실행 하지 않는 테스트") {
            testFunction()
        }
    }

    xcontext("xcontext 실행하지 않는") {
        it("it 실행하지 않는") {
            testFunction()
        }
    }
})
