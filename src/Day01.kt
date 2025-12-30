fun main() {
    val max = 100
    fun part1(input: List<String>): Int {
        var position = 50
        var zeros = 0
        input.forEach {
            val direction = it[0]
            val number = it.substring(1).toInt()
            if (direction == 'L') {
                position -= number
            } else if (direction == 'R') {
                position += number
            } else throw IllegalArgumentException("Invalid direction: $direction")
            position %= max
            if (position == 0) zeros++
        }
        return zeros
    }

    fun part2(input: List<String>): Int {
        var position = 50
        var zeros = 0
        input.forEach {
            val prevPosition = position
            val direction = it[0]
            val number = it.substring(1).toInt()
            if (direction == 'L') {
                position -= number
            } else if (direction == 'R') {
                position += number
            } else throw IllegalArgumentException("Invalid direction: $direction")
            var moveOverZero = 0
            while (position < 0) {
                position += max
                moveOverZero++
            }
            while (position >= max) {
                position -= max
                moveOverZero++
            }
            val equalZero = if (position == 0) 1 else 0
            val moveOrEqual = if (moveOverZero > 0) moveOverZero else equalZero
            val startZero = if (moveOrEqual > 0 && prevPosition == 0) -1 else 0
            zeros += moveOrEqual + startZero
        }
        return zeros
    }

    val testInput = readInput("Day01_test")
    check(part1(testInput) == 3)
    check(part2(testInput) == 6)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
