package racingcar

object ResultView {

    fun showCurrentRound(round: Int){
      println("현재 라운드는 $round 회차 입니다.")
    }

    fun showResult(cars: List<Car>) {
        cars.forEach{
            showCarPosition(it.getPosition())
            newLine()
        }
    }

    private fun showCarPosition(position: Position){
        repeat(position.value){
            print("-")
        }
    }

    private fun newLine(){
        println()
    }
}
