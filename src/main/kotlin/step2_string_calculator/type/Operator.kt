package step2_string_calculator.type

enum class Operator(
    private val operator: String,
    val calculation: (Double, Double) -> Double
) {
    PLUS("+", { a, b -> a + b }),
    MINUS("-", { a, b -> a - b }),
    MULTIPLY("*", { a, b -> a * b }),
    DIVIDE("/", { a, b ->
        require(b != 0.0) { "0으로 나눌 수 없습니다." }
        a / b
    }),
    ;

    companion object {
        fun of(operator: String?): Operator {
            return enumValues<Operator>().firstOrNull { it.operator == operator }
                ?: throw Exception("지원하지 않는 연산자입니다.")
        }
    }
}
