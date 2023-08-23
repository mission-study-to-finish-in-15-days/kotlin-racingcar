import java.util.Scanner
import kotlin.random.Random

class NoClassRacingCarMain

fun main() {
    val systemResourceAsStream = ClassLoader.getSystemResourceAsStream("noclass-input.txt")
        ?: throw IllegalArgumentException("input.txt 파일이 존재 하지 않습니다.")
//    val scanner = Scanner(System.`in`)
    val scanner = Scanner(systemResourceAsStream)
    println("자동차 대수는 몇 대인가요?")
    val carCount = scanner.nextInt().also { println(it) }
    println("시도할 횟수는 몇 회인가요?")
    val roundCount = scanner.nextInt().also { println(it) }

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
