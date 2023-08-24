package racingcar.adapter.view

import racingcar.view.ViewModel
import racingcar.view.ViewModel.ViewType

class StringViewModel(val value: String) : ViewModel {
    override fun getViewType(): ViewType {
        return ViewType.STRING_VIEW
    }
}
