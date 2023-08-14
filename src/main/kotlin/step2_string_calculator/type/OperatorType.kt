package step2_string_calculator.type

enum class OperatorType(
    private val operator: String
) {
    PLUS("+"),
    MINUS("-"),
    MULTIPLY("*"),
    DIVIDE("/"),
    ;

    companion object {
        fun of(operator: String?): OperatorType {
            return enumValues<OperatorType>().firstOrNull { it.operator == operator }
                ?: throw Exception("지원하지 않는 연산자입니다.")
        }
    }
}
