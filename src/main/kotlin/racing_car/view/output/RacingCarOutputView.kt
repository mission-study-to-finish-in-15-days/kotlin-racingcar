package racing_car.view.output


object RacingCarOutputView {

    fun printResultTitle() {
        println("\n실행 결과")
    }

    fun printPosition(position: List<Int>) {
        position.forEach { println("-".repeat(it)) }
        println()
    }
}
