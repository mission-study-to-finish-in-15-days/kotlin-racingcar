package racing_car.view.output


object RacingCarOutputView {

    fun outputResultTitle() {
        println("\n실행 결과")
    }

    fun outputPosition(position: List<Int>) {
        position.forEach { println("-".repeat(it)) }
        println()
    }
}
