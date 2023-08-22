class Printer {
    fun print(cars: List<Car>) {
        cars.forEach { car ->
            println("-".repeat(car.distance))
        }
        println()
    }
}
