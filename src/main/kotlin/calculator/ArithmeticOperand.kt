package calculator

@JvmInline
value class ArithmeticOperand(
    val value: Long,
) {
    companion object {
        fun of(stringValue: String): ArithmeticOperand {
            requireNotNull(stringValue.toLongOrNull()) { "사칙연산의 피연산자는 숫자여야합니다." }
            return ArithmeticOperand(stringValue.toLong())
        }
    }
}
