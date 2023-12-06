package day06

import readInput

fun main() {
    val solver = Day06()
    val input = readInput("src/day06/input")
    println("Part 1: ${solver.part1(input)}")
    println("Part 2: ${solver.part2(input)}")
}

class Day06 {

    fun part1(input: List<String>): Int {
        val buffer = mutableListOf<Char>()
        input[0].toCharArray().forEachIndexed { i, char ->
            if (!buffer.contains(char) && buffer.size == 3 && buffer.size == buffer.distinct().size) {
                return i + 1
            }
            buffer.add(char)
            if (buffer.size == 4) {
                buffer.removeAt(0)
            }
        }
        return 0
    }

    fun part2(input: List<String>): Int {
        val buffer = mutableListOf<Char>()
        input[0].toCharArray().forEachIndexed { i, char ->
            if (!buffer.contains(char) && buffer.size == 13 && buffer.size == buffer.distinct().size) {
                return i + 1
            }
            buffer.add(char)
            if (buffer.size == 14) {
                buffer.removeAt(0)
            }
        }
        return 0
    }

}