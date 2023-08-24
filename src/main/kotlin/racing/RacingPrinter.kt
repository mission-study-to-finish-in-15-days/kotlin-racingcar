package racing

object RacingPrinter {
    fun printSimulation(racingResult: RacingResult) {
        printHeader()
        printResult(racingResult)
    }

    fun printSimulationWithName(racingResult: RacingResult) {
        printHeader()
        printResultWithName(racingResult)
        printWinnerNames(racingResult)
    }

    private fun printHeader() {
        println()
        println("실행 결과")
    }

    private fun printResult(racingResult: RacingResult) {
        racingResult.roundResults.forEach {
            printRound(it)
            println()
        }
    }

    private fun printRound(roundResult: RacingRoundResult) {
        return roundResult.value.forEach {
            println("-".repeat(it))
        }
    }

    private fun printResultWithName(racingResult: RacingResult) {
        racingResult.roundResults.forEach {
            printRoundWithName(it, racingResult.carNames)
            println()
        }
    }

    private fun printRoundWithName(roundResult: RacingRoundResult, carNames: List<String>) {
        return roundResult.value.forEachIndexed { index, result ->
            println("${carNames[index]} : ${"-".repeat(result)}")
        }
    }

    private fun printWinnerNames(racingResult: RacingResult) {
        val winnerText = racingResult.winnerNames.joinToString(separator = ", ")
        println("$winnerText 이(가) 최종 우승했습니다.")
    }
}
