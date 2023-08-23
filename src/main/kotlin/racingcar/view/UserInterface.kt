package racingcar.view

import racingcar.view.ViewModel.ViewType

interface UserInterface {
    fun getViewType(): ViewType

    fun getInt(message: ViewModel): Int

    fun getStrings(message: ViewModel): List<String>

    fun display(message: ViewModel)
}
