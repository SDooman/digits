sealed class Expr {

    abstract val value: Int

    data class IntExpr(override val value: Int) : Expr() {
        override fun toString() = value.toString()
    }

    data class BinaryExpr(
        private val left: Expr,
        private val operator: Operator,
        private val right: Expr
    ): Expr() {

        override val value get() =
            when (operator) {
                Operator.PLUS -> left.value + right.value
                Operator.MINUS -> left.value - right.value
                Operator.TIMES -> left.value * right.value
                Operator.DIVIDED_BY -> {
                    check(right.value != 0 && (left.value % right.value != 0))
                    left.value / right.value
                }
            }

        override fun toString() = "($left $operator $right)"
    }
}