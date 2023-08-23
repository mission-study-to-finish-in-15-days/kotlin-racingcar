package view

import domain.distance.Distance

sealed interface DistanceDisplay {
    fun display(distance: Distance): Display
}


object DashDistanceDisplay : DistanceDisplay {
    private const val SYMBOL = "-"
    override fun display(distance: Distance): Display {
        return Display(IntArray(distance.value).joinToString("") { SYMBOL })
    }
}

@JvmInline
value class Display(private val value: String) {
    override fun toString(): String {
        return value
    }
}
