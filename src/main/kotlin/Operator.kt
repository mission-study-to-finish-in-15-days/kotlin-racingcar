sealed interface Operator {
    fun compute(x: Int, y: Int): Int
}

object PlusOperator : Operator {
    override fun compute(x: Int, y: Int): Int {
        return x + y
    }
}

object SubtractOperator : Operator {
    override fun compute(x: Int, y: Int): Int {
        return x - y
    }
}

object MultiplyOperator : Operator {
    override fun compute(x: Int, y: Int): Int {
        return x * y
    }
}

object DivideOperator : Operator {
    override fun compute(x: Int, y: Int): Int {
        return x / y
    }
}
