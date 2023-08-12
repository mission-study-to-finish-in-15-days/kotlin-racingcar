sealed interface Calculable {
    fun calculate(a: Number, b: Number): Number
}

object PlusCalculator : Calculable {
    override fun calculate(a: Number, b: Number): Number {
        return a + b
    }
}

object MinusCalculator : Calculable {
    override fun calculate(a: Number, b: Number): Number {
        return a - b
    }
}

object MultipleCalculator : Calculable {
    override fun calculate(a: Number, b: Number): Number {
        return a * b
    }
}

object DivideCalculator : Calculable {
    override fun calculate(a: Number, b: Number): Number {
        return a / b
    }
}

@JvmInline
value class Number(private val value: Int)  {
    operator fun plus(from: Number): Number {
        return Number(this.value + from.value)
    }

    operator fun minus(from: Number): Number {
        return Number(this.value - from.value)
    }

    operator fun times(from: Number): Number {
        return Number(this.value * from.value)
    }

    operator fun div(from: Number): Number {
        return Number(this.value / from.value)
    }

    override fun toString(): String {
        return "$value"
    }
}

object CalculableSelector {
    fun select(symbolType: SymbolType): Calculable {
        return when (symbolType) {
            SymbolType.PLUS -> PlusCalculator
            SymbolType.MINUS -> MinusCalculator
            SymbolType.MULTIPLE -> MultipleCalculator
            SymbolType.DIVIDE -> DivideCalculator
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

    fun popSymbolAndNumber(): SymbolNumber {
        return SymbolNumber(
            symbolType = popSymbol(),
            number = popNumber(),
        )
    }

    private fun popSymbol(): SymbolType {
        return SymbolType.charOf(
            symbolAndNumArray.removeAt(0)
        )
    }

    private fun popNumber(): Number {
        return Number(symbolAndNumArray
            .removeAt(0)
            .digitToInt())
    }

    val size: Int get() = symbolAndNumArray.size
}

data class SymbolNumber(
    val symbolType: SymbolType,
    val number: Number,
)

object StringCalculator {

    fun calculate(inputString: String? = null): String {
        requireNotNull(inputString) { "null 이면 안됩니다." }

        return divideAndCalculate(SymbolAndNumberQueue(inputString)).toString()
    }

    private fun divideAndCalculate(queue: SymbolAndNumberQueue, result: Number = Number(0)): Number {
        if (queue.size == 0) return result

        val (symbolType: SymbolType, number: Number) = queue.popSymbolAndNumber()
        val calculable: Calculable = CalculableSelector.select(symbolType)
        return divideAndCalculate(queue, calculable.calculate(result, number))
    }
}

enum class SymbolType(private val symbolChar: Char) {
    PLUS('+'),
    MINUS('-'),
    MULTIPLE('*'),
    DIVIDE('/'),
    ;

    companion object {
        fun charOf(inputChar: Char): SymbolType {
            return values().firstOrNull { it.symbolChar == inputChar }
                ?: throw IllegalArgumentException("사칙 연산 기호 이외에는 들어오면 안됩니다.(c=$inputChar)")
        }
    }
}
