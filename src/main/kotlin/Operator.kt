enum class Operator {
    PLUS,
    MINUS,
    TIMES,
    DIVIDED_BY;

    override fun toString() = when (this) {
        PLUS -> "+"
        MINUS -> "-"
        TIMES -> "*"
        DIVIDED_BY -> "/"
    }
}