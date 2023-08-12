sealed interface Calculable {
    fun calculate(a: Int, b: Int): Int
}

object PlusCalc : Calculable {
    override fun calculate(a: Int, b: Int): Int {
        return a + b
    }
}

object MinusCalc : Calculable {
    override fun calculate(a: Int, b: Int): Int {
        return a - b
    }
}

object MultipleCalc : Calculable {
    override fun calculate(a: Int, b: Int): Int {
        return a * b
    }
}

object DivideCalc : Calculable {
    override fun calculate(a: Int, b: Int): Int {
        return a / b
    }
}

object CalcSelector {
    fun select(symbolType: SymbolType): Calculable {
        return when (symbolType) {
            SymbolType.PLUS -> PlusCalc
            SymbolType.MINUS -> MinusCalc
            SymbolType.MULTIPLE -> MultipleCalc
            SymbolType.DIVIDE -> DivideCalc
        }
    }
}

data class SymbolAndNumberQueue(private val inputString: String) {
    private val symbolAndNumArray: MutableList<Char>

    init {
        val trimInput = inputString.trim()
        val toCharArray = trimInput.toCharArray()
        val toMutableList = toCharArray.toMutableList()
        val filteredBlankList: MutableList<Char> = toMutableList.filter { ' ' != it }.toMutableList()
        filteredBlankList.add(0, '+')
        symbolAndNumArray = filteredBlankList
    }

    fun pop(): Char {
        return symbolAndNumArray.removeAt(0)
    }

    fun popInt(): Int {
        return symbolAndNumArray
            .removeAt(0)
            .digitToInt()
    }

    val size: Int get() = symbolAndNumArray.size
}

object StringCalculator {

    fun calculate(inputString: String? = null): String {
        require(inputString != null) { "null 이면 안됩니다." }

        return divideAndCalc(SymbolAndNumberQueue(inputString)).toString()
    }

    private val numberRegex: Regex = "\\d".toRegex()
    private fun divideAndCalc(queue: SymbolAndNumberQueue, prevInt: Int = 0): Int {
        if (queue.size == 0) return prevInt

        val symbolC = queue.pop()
        val symbolType: SymbolType = SymbolType.symbolOf(symbolC)
        val numberChar = queue.popInt()
        if (numberRegex.matches(numberChar.toString())) {
            val calculable = CalcSelector.select(symbolType)
            return divideAndCalc(queue, calculable.calculate(prevInt, numberChar))
        }

        throw IllegalArgumentException("정상적이지 않는 문자열 $queue")
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
