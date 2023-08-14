package step3

import kotlin.random.Random

class NoClassRacingCar {
}

fun main(args: Array<String>) {
    println("자동차 대수는 몇 대인가요?")
    val carCount = readln().toInt()
    println("시도할 횟수는 몇 회인가요?")
    val roundCount = readln().toInt()

    println("실행 결과")

    val carDistances = IntArray(carCount)
    for (round in 0 until roundCount) {
        println("$round Round")
        for (carNumber in 0 until carCount) {
            val nextInt = Random.nextInt(10)
            var distance = carDistances.getOrElse(carNumber) { 0 }
            if (nextInt >= 4) distance++
            carDistances[carNumber] = distance
            println("$carNumber Car ($nextInt) ${IntArray(distance).joinToString("") { "-" }}")
        }
        println(carDistances.contentToString())
    }

}
