package racingcar.adapter.view

import racingcar.view.UserInterface
import racingcar.view.ViewModel
import racingcar.view.ViewModel.ViewType
import java.util.Scanner

class ConsoleUserInterface : UserInterface {

    private val scanner = Scanner(System.`in`)
    override fun display(message: ViewModel) {
        if (message !is StringViewModel) throw IllegalArgumentException("Not supported ViewType")

        println(message.value + "\n")
    }

    override fun getInt(message: ViewModel): Int {
        if (message !is StringViewModel) throw IllegalArgumentException("Not supported ViewType")

        println(message.value)
        return scanner.nextInt()
    }

    override fun getStrings(message: ViewModel): List<String> {
        if (message !is StringViewModel) throw IllegalArgumentException("Not supported ViewType")

        println(message.value)
        return scanner.next()
            .split(",")
            .map { it.trim() }
    }

    override fun getViewType(): ViewType {
        return ViewType.STRING_VIEW
    }
}
