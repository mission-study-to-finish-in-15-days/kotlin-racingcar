package racingcar.domain

import java.util.concurrent.ThreadLocalRandom

fun interface RandomNumberGenerator {
    fun getNumber(origin: Int, bound: Int): Int
}

object ThreadLocalRandomNumberGenerator : RandomNumberGenerator {
    override fun getNumber(origin: Int, bound: Int): Int {
        return ThreadLocalRandom.current().nextInt(origin, bound)
    }
}
