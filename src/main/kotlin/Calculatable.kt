sealed interface Calculatable {
    fun calcultate(a: Int, b: Int): Int
}

object PlusCalc : Calculatable {
    override fun calcultate(a: Int, b: Int): Int {
        return a + b
    }
}

object MinusCalc : Calculatable {
    override fun calcultate(a: Int, b: Int): Int {
        return a - b
    }
}

object MultipleCalc : Calculatable {
    override fun calcultate(a: Int, b: Int): Int {
        return a * b
    }
}

object DivideCalc : Calculatable {
    override fun calcultate(a: Int, b: Int): Int {
        return a / b
    }
}

object CalcSelector {
    fun select(symbolType: SymbolType): Calculatable {
        return when (symbolType) {
            SymbolType.PLUS -> PlusCalc
            SymbolType.MINUS -> MinusCalc
            SymbolType.MULTIPLE -> MultipleCalc
            SymbolType.DIVIDE -> DivideCalc
        }
    }
}

object StringCalcurator {
    private val numberRegex: Regex = "\\d".toRegex()

    fun calculate(inputString: String? = null): String {
        require(inputString != null) { "null 이면 안됩니다." }

        return divideAndCalc(inputString.toCharArray().toMutableList()).toString()
    }

    private fun divideAndCalc(charMutableList: MutableList<Char>): Int {
        var prevInt: Int = 0
        var prevSymbol: SymbolType = SymbolType.PLUS

        for (c in charMutableList) {
            val strC = c.toString()
            when {
                numberRegex.matches(strC) -> prevInt = CalcSelector.select(prevSymbol).calcultate(prevInt, strC.toInt())
                strC.isBlank() -> continue
                else -> prevSymbol = SymbolType.symbolOf(c)
            }
        }
        return prevInt
    }

}

enum class SymbolType(private val c: Char) {
    PLUS('+'),
    MINUS('-'),
    MULTIPLE('*'),
    DIVIDE('/'),
    ;

    companion object {
        fun symbolOf(c: Char): SymbolType {
            return values().firstOrNull { it.c == c } ?: throw IllegalArgumentException("사칙 연산 기호 이외에는 들어오면 안됩니다.(c=$c)")
        }
    }
}
