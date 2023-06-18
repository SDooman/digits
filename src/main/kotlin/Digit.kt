object Digit {

    fun solve(target: Int, numbers: Set<Int>) = solveInternal(target = target, numbers = numbers)

    private fun solveInternal(target: Int, numbers: Set<Int>): Expr? {
        if (target in numbers) {
            return Expr.IntExpr(target)
        }

        val pairsToSearch =
            Operator
                .values()
                .flatMap { op ->
                    val numbersToSearch =
                        when (op) {
                            Operator.PLUS,
                            Operator.MINUS,
                            Operator.DIVIDED_BY -> numbers
                            Operator.TIMES -> numbers.filter { target % it == 0 }.toSet()
                        }

                    numbersToSearch.map { n ->
                        val newTarget =
                            when (op) {
                                Operator.PLUS -> target - n
                                Operator.MINUS -> target + n
                                Operator.TIMES -> target / n
                                Operator.DIVIDED_BY -> target * n
                            }
                        Triple(n, op, newTarget)
                    }
                }
                .shuffled()

        for ((number, operator, newTarget) in pairsToSearch) {
            val newNumbers = numbers.minus(number)
            val solution = solveInternal(target = newTarget, numbers = newNumbers)

            if (solution != null) {
                return Expr.BinaryExpr(
                    left = solution,
                    operator = operator,
                    right = Expr.IntExpr(number)
                )
            }
        }

        return null
    }
}