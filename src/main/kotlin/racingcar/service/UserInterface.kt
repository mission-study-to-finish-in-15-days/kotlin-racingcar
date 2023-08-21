package racingcar.service

import java.util.Scanner

interface UserInterface {
    fun getInt(message: String): Int

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
}
