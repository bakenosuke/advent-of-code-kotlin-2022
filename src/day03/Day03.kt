package day03

import readInput

fun main() {
    val solver = Day03()
    val input = readInput("src/day03/input")
    println("Part 1: ${solver.part1(input)}")
    println("Part 2: ${solver.part2(input)}")
}

class Day03 {

    fun part1(input: List<String>): Int {
        val results = input.map {
            val left = it.substring(0, it.length / 2).toCharArray().distinct()
            val right = it.substring(it.length / 2, it.length).toCharArray().distinct()

            val common = left.filter {
                right.contains(it)
            }

            common.sumOf { it.score() }
        }

        return results.sum()
    }

    fun Char.score(): Int {
        val code = this.code - 'a'.code + 1
        return if (code > 0) {
            code
        } else {
            code + 58
        }
    }

    fun part2(input: List<String>): Int {
        val results = (0..<(input.size / 3)).map {
            val first = input[it * 3].toCharArray().distinct()
            val second = input[it * 3 + 1].toCharArray().distinct()
            val third = input[it * 3 + 2].toCharArray().distinct()

            val common = first.filter {
                second.contains(it) && third.contains(it)
            }
            common.sumOf { it.score() }
        }

        return results.sum()
    }

}