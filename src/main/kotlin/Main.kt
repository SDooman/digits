import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val times =
        (0 until 100)
            .map {
                measureTimeMillis {
                    Digit.solve(
                        target = 469,
                        numbers = setOf(5, 7, 11, 13, 19, 23)
                    )
                }
            }

    println(times.average())
}
