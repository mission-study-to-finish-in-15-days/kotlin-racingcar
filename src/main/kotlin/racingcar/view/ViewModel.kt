package racingcar.view

interface ViewModel {
    fun getViewType(): ViewType

    enum class ViewType {
        STRING_VIEW,
    }
}
