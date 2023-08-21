package calculator.type

enum class ExpressionRegex(val regex: Regex) {
    NUMBER(regex = "([1-9][0-9]*)".toRegex()),
    OPERATOR(regex = "[+\\-*/]".toRegex()),
    TOKEN(regex = "${NUMBER.regex}|${OPERATOR.regex}".toRegex()),
    EXPRESSION(regex = "${NUMBER.regex}(${OPERATOR.regex}${NUMBER.regex})*".toRegex()),
}
