package step02

enum class OperationType(private val value: String) {
    PLUS("+"),
    MINUS("-"),
    DIVIDE("/"),
    TIMES("*");

    companion object {
        infix fun from(value: String): OperationType? = OperationType.values().firstOrNull { it.value == value }
    }
}
