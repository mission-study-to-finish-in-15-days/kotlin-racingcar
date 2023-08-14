package step2_string_calculator.util

fun String.extractNumberAndOperatorToQueue(input: String): ArrayDeque<String> {
    val queue = ArrayDeque<String>()

    for (it in input) {
        if (OperationUtil.isOperatorOrNull(queue.lastOrNull()) && OperationUtil.isOperator(it.toString())) {
            throw IllegalArgumentException("식의 첫 자리가 연산자이거나, 연속된 연산자가 입력되었습니다.")
        }
        if (OperationUtil.isNumber(queue.lastOrNull()) && OperationUtil.isNumber(it.toString())) {
            createNDigitNumber(queue, it)
            continue
        }
        queue.add((it.toString()))
    }
    return queue
}

private fun createNDigitNumber(queue: ArrayDeque<String>, character: Char) {
    val lastNumber = queue.readLastAndRemove().toInt()
    queue.add((lastNumber * 10 + character.toString().toInt()).toString())
}


fun String.trimAll(): String {
    return this.replace(" ", "")
}

class StringUtil
