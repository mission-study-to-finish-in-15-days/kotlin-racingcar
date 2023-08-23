package racingcar.service

import java.util.*

interface UserInterface {
    fun getInt(message: String): Int

    fun getStrings(message: String): List<String>

    fun print(message: String)
}

class ConsoleUserInterface : UserInterface {

    private val scanner = Scanner(System.`in`)
    override fun print(message: String) {
        println(message + "\n")
    }

    override fun getInt(message: String): Int {
        println(message)
        return scanner.nextInt()
    }

    override fun getStrings(message: String): List<String> {
        println(message)
        return scanner.next()
            .split(",")
            .map { it.trim() }
    }
}
