package racingcar.domain

import java.util.concurrent.ThreadLocalRandom

fun interface RandomNumberGenerator {
    fun getNumber(range: IntRange): Int
}

object ThreadLocalRandomNumberGenerator : RandomNumberGenerator {
    override fun getNumber(range: IntRange): Int {
        return ThreadLocalRandom.current().nextInt(range.first, range.last)
    }
}

object RangeRandomNumberGenerator : RandomNumberGenerator {
    override fun getNumber(range: IntRange): Int {
        return range.random()
    }
}
