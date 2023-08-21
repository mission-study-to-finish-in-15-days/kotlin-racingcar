package person

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class PersonFunTest : FunSpec({
    test("데이터 클래스 toString") {
        val person1 = Person(_name = "문구화", _age = 28, _nickname = "구아과")
        val person2 = Person("문구화", 28)

        person1.toString() shouldBe person2.toString()
    }

    test("데이터 클래스 hashCode") {
        val person1 = Person(_name = "문구화", _age = 28, _nickname = "구아과")
        val person2 = Person("문구화", 28)

        person1.hashCode() shouldBe person2.hashCode()
    }

    test("데이터 클래스 copy") {
        val person1 = Person(_name = "문구화", _age = 28, _nickname = "구아과")
        val person2 = person1.copy()

        person1 shouldBe person2
    }
})
