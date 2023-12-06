package day08

import readInput
import kotlin.math.max

fun main() {
    val solver = Day08()
    val input = readInput("src/day08/input")
    println("Part 1: ${solver.part1(input)}")
    println("Part 2: ${solver.part2(input)}")
}

class Day08 {

    data class Cell(
        val height: Int,
        var visible: Boolean = false
    )

    fun part1(input: List<String>): Int {
        val map = input.map { it.toCharArray().map { Cell(height = it.digitToInt()) } }
        val cols = map[0].size
        var visible = 0
        // Top to Bottom
        (0..<cols).forEach { col ->
            var highestSoFar = -1
            map.indices.forEach { row ->
                val cell = map[row][col]
                if (cell.height > highestSoFar) {
                    highestSoFar = cell.height
                    if (!cell.visible) {
                        cell.visible = true
                        visible++
                    }
                }
            }
        }
        // Bottom to Top
        (0..<cols).forEach { col ->
            var highestSoFar = -1
            map.indices.reversed().forEach { row ->
                val cell = map[row][col]
                if (cell.height > highestSoFar) {
                    highestSoFar = cell.height
                    if (!cell.visible) {
                        cell.visible = true
                        visible++
                    }
                }
            }
        }

        // Left to Right
        map.forEach { row ->
            var highestSoFar = -1
            row.forEach { cell ->
                if (cell.height > highestSoFar) {
                    highestSoFar = cell.height
                    if (!cell.visible) {
                        cell.visible = true
                        visible++
                    }
                }
            }
        }
        // Right to Left
        map.forEach { row ->
            var highestSoFar = -1
            row.reversed().forEach { cell ->
                if (cell.height > highestSoFar) {
                    highestSoFar = cell.height
                    if (!cell.visible) {
                        cell.visible = true
                        visible++
                    }
                }
            }
        }
        return visible
    }

    fun part2(input: List<String>): Int {
        val map = input.map { it.toCharArray().map { it.digitToInt() } }
        var highestScore = 0
        map.forEachIndexed { row, rowItems ->
            rowItems.forEachIndexed { col, cell ->
                highestScore = max(highestScore, calculateScore(row, col, map))
            }
        }

        return highestScore
    }

    fun calculateScore(row: Int, col: Int, map: List<List<Int>>): Int {
        val myHeight = map[row][col]
        // Look up
        var up = 0
        if (row > 1) {
            run outer@{
                ((row - 1) downTo 0).forEach {
                    val otherCell = map[it][col]
                    up++
                    if (otherCell >= myHeight) {
                        return@outer
                    }
                }
            }
        }

        // Look down
        var down = 0
        run outer@{
            ((row + 1)..<map.size).forEach {
                val otherCell = map[it][col]
                down++
                if (otherCell >= myHeight) {
                    return@outer
                }
            }
        }

        // Look left
        var left = 0
        run outer@{
            if (col > 1) {
                ((col - 1) downTo 0).forEach {
                    val otherCell = map[row][it]
                    left++
                    if (otherCell >= myHeight) {
                        return@outer
                    }
                }
            }
        }

        // Look right
        var right = 0
        run outer@{
            ((col + 1)..<map[0].size).forEach {
                val otherCell = map[row][it]
                right++
                if (otherCell >= myHeight) {
                    return@outer
                }
            }
        }

        return up * down * left * right
    }

}