package step3

import io.kotest.core.spec.style.StringSpec

class InputViewKtTest : StringSpec({

    "dd" {
        println(IntArray(1).map { "-" }.joinToString(""))
    }

})
