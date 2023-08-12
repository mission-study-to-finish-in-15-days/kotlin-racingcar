package step2

object Calculator {

    fun plus(input: String): Int {
        val inputSplit = input.split(" ")
        return inputSplit
            .filter { it != "+" }
            .sumOf { it.toInt() }
    }
}
