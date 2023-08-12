sealed interface Calculatable {
    fun calculrate(a: Int, b: Int): Int
}

object PlusCalc : Calculatable {
    override fun calculrate(a: Int, b: Int): Int {
        return a + b
    }
}

object MinusCalc : Calculatable {
    override fun calculrate(a: Int, b: Int): Int {
        return a - b
    }
}

object MultipleCalc : Calculatable {
    override fun calculrate(a: Int, b: Int): Int {
        return a * b
    }
}

object DivideCalc : Calculatable {
    override fun calculrate(a: Int, b: Int): Int {
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

        val trimInput = inputString.trim()
        val toCharArray = trimInput.toCharArray()
        val toMutableList = toCharArray.toMutableList()
        val filteredBlankList = toMutableList.filter { ' ' != it }.toMutableList()
        filteredBlankList.add(0, '+')

        return divideAndCalc(filteredBlankList).toString()
    }

    private fun divideAndCalc(charMutableList: MutableList<Char>, prevInt: Int = 0): Int {
        if (charMutableList.size == 0) return prevInt

        val symbolC = charMutableList.removeAt(0)
        val symbolType: SymbolType = SymbolType.symbolOf(symbolC)
        val numberChar = charMutableList.removeAt(0)
        if (numberRegex.matches(numberChar.toString())) {
            return divideAndCalc(charMutableList, CalcSelector.select(symbolType).calculrate(prevInt, numberChar.toString().toInt()))
        }

        throw IllegalArgumentException("정상적이지 않는 문자열 $charMutableList")
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
            return values().firstOrNull { it.c == c }
                ?: throw IllegalArgumentException("사칙 연산 기호 이외에는 들어오면 안됩니다.(c=$c)")
        }
    }
}
