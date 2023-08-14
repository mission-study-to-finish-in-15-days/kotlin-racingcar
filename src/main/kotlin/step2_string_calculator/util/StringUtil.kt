package step2_string_calculator.util

fun String.extractNumberAndOperatorToQueue(): ArrayDeque<String> {
    val queue = ArrayDeque<String>()

    for (it in this) {
        validateOperator(queue, it)
        if (validateAndCreateNumber(queue, it)) continue
        queue.add((it.toString()))
    }
    return queue
}

private fun validateAndCreateNumber(queue: ArrayDeque<String>, it: Char): Boolean {
    if (OperationUtil.isNumber(queue.lastOrNull()) && OperationUtil.isNumber(it.toString())) {
        createNDigitNumber(queue, it)
        return true
    }
    return false
}

private fun validateOperator(queue: ArrayDeque<String>, it: Char) {
    if (OperationUtil.isOperatorOrNull(queue.lastOrNull()) && OperationUtil.isOperator(it.toString())) {
        throw IllegalArgumentException("식의 첫 자리가 연산자이거나, 연속된 연산자가 입력되었습니다.")
    }
}

private fun createNDigitNumber(queue: ArrayDeque<String>, character: Char) {
    val lastNumber = queue.removeLast().toInt()
    queue.add((lastNumber * 10 + character.toString().toInt()).toString())
}

fun String.trimAll(): String {
    return this.replace(" ", "")
}

class StringUtil
