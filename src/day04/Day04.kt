package day04

import readInput

fun main() {
    val solver = Day04()
    val input = readInput("src/day04/input")
    println("Part 1: ${solver.part1(input)}")
    println("Part 2: ${solver.part2(input)}")
}

class Day04 {

    fun part1(input: List<String>): Int {
        val fullyContains = input.filter {
            val leftRange = it.substringBefore(",").split("-").map { it.toInt()}
            val rightRange = it.substringAfter(",").split("-").map { it.toInt()}

            (leftRange[0] >= rightRange[0] && leftRange[1] <= rightRange[1]) ||
                    (leftRange[0] <= rightRange[0] && leftRange[1] >= rightRange[1])
        }
        return fullyContains.size
    }

    fun part2(input: List<String>): Int {
        val partialContains = input.filter {
            val leftRange = it.substringBefore(",").split("-").map { it.toInt()}
            val rightRange = it.substringAfter(",").split("-").map { it.toInt()}


            (leftRange[0]>= rightRange[0] && leftRange[0] <=rightRange[1]) ||
            (leftRange[1]>= rightRange[0] && leftRange[1] <=rightRange[1])||
            (rightRange[0]>= leftRange[0] && rightRange[0] <=leftRange[1]) ||
            (rightRange[1]>= leftRange[0] && rightRange[1] <=leftRange[1])
        }
        return partialContains.size
    }

}