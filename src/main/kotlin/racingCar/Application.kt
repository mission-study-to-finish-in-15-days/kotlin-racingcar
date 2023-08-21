import java.util.*

fun main() {
    Application().run()
}
class Application {
    fun run() {
        val carCount = getInt("자동차 대수는 몇 대인가요?\n")
        val tryCount = getInt("시도할 횟수는 몇 회인가요?\n")
        val cars: List<Car> = (0..carCount).map { Car() }
        val printer = Printer()
        println("실행 결과")
        (0..tryCount).forEach {
            cars.forEach { it.move() }
            printer.print(cars)
        }
    }
    private fun getInt(message: String): Int {
        println(message)
        val scanner = Scanner(System.`in`)
        return scanner.nextInt()
    }
}
