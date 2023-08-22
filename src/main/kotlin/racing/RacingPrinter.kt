package racing

object RacingPrinter {
    fun printSimulation(result: List<List<Int>>) {
        println()
        println("실행 결과")

        printResult(result)
    }

    private fun printResult(result: List<List<Int>>) {
        result.forEach {
            printRound(it)
            println()
        }
    }

    private fun printRound(roundResult: List<Int>) {
        roundResult.forEach { println("-".repeat(it)) }
    }
}
