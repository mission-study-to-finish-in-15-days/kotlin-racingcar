package domain.game

@JvmInline
value class Round(private val roundCount: Int) {
    init {
        require(roundCount > 0) { "RoundCount=$roundCount 는 0보다 커야 합니다." }
    }

    fun forEachRound(function: (Int) -> Unit) {
        repeat(roundCount) {
            function(it)
        }
    }
}
