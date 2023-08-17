package racingcar.domain

import java.util.concurrent.ThreadLocalRandom

fun interface RandomNumberUtil {
    fun getRandomNumber(origin: Int, bound: Int): Int
}

object ThreadLocalRandomNumberUtil : RandomNumberUtil {
    override fun getRandomNumber(origin: Int, bound: Int): Int {
        return ThreadLocalRandom.current().nextInt(origin, bound)
    }
}
