class Car {
    var distance: Int = 0
        private set
    fun move() {
        val randomNumber = (0..9).random()
        if (randomNumber >= 4) {
            distance += 1
        }
    }
}
