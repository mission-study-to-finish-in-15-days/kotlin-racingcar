import io.kotest.core.spec.style.ShouldSpec

class PersonShouldTest : ShouldSpec({

    // NOTE: _name, _age, _nickname private 처리로 인한 Deprecated
    /*
    should("데이터 클래스 componentN") {
        val (_name, _age, _nickname) = Person(_name = "문구화", _age = 28)

        _name shouldBe "문구화"
        _age shouldBe 28
        _nickname shouldBe "구아과"
    }
    */
})
