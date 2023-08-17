package step3_simple_racing_car.util

object RandomDecisionUtil {
    fun isUpper(
        threshold: Int? = MEDIUM_THRESHOLD
    ): Boolean = (0..9).random() >= threshold!!

    private const val MEDIUM_THRESHOLD = 4
}

