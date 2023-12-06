package day01

import readInput

fun main() {
    val solver = Day01()
    val input = readInput("src/day01/input")
    println("Part 1: ${solver.part1(input)}")
    println("Part 2: ${solver.part2(input)}")
}

class Day01 {

    fun part1(input: List<String>): Long {
        val elfCarry = mutableListOf<Long>()
        var weight = 0L
        input.forEach {
            if(it.isNotBlank()) {
                weight += it.toLong()
            } else {
                elfCarry.add(weight)
                weight = 0L
            }
        }
        return elfCarry.max()
    }

    fun part2(input: List<String>): Long {
        val elfCarry = mutableListOf<Long>()
        var weight = 0L
        input.forEach {
            if(it.isNotBlank()) {
                weight += it.toLong()
            } else {
                elfCarry.add(weight)
                weight = 0L
            }
        }
        return elfCarry.sortedDescending ().take(3).sum()
    }

}