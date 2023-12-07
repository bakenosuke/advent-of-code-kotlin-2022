package day09

import readInput

fun main() {
    val solver = Day09()
    val input = readInput("src/day09/input")
    println("Part 1: ${solver.part1(input)}")
    println("Part 2: ${solver.part2(input)}")
}

class Day09 {

    data class Position(var x: Int = 0, var y: Int = 0)

    fun part1(input: List<String>): Int {
        val head = Position()
        val tail = Position()
        val visited = mutableSetOf<Position>()

        input.forEach {
            val direction = it.substringBefore(" ").toDirection()
            val steps = it.substringAfter(" ").toInt()

            repeat(steps) {
                when (direction) {
                    Direction.UP -> head.y++
                    Direction.DOWN -> head.y--
                    Direction.LEFT -> head.x--
                    Direction.RIGHT -> head.x++
                }
                chase(head, tail)
                visited.add(Position(tail.x, tail.y))
            }
        }

        return visited.size
    }

    enum class Direction {
        UP, DOWN, LEFT, RIGHT
    }

    fun String.toDirection(): Direction {
        when (this) {
            "U" -> return Direction.UP
            "D" -> return Direction.DOWN
            "L" -> return Direction.LEFT
            "R" -> return Direction.RIGHT
            else -> throw Exception("Unknown direction $this")
        }
    }

    fun part2(input: List<String>): Int {
        val heads = (0..8).map { Position() }
        val tail = Position()
        val visited = mutableSetOf<Position>()

        input.forEach {
            val direction = it.substringBefore(" ").toDirection()
            val steps = it.substringAfter(" ").toInt()

            repeat(steps) {
                when (direction) {
                    Direction.UP -> heads.first().y++
                    Direction.DOWN -> heads.first().y--
                    Direction.LEFT -> heads.first().x--
                    Direction.RIGHT -> heads.first().x++
                }
                chase(heads[0], heads[1])
                chase(heads[1], heads[2])
                chase(heads[2], heads[3])
                chase(heads[3], heads[4])
                chase(heads[4], heads[5])
                chase(heads[5], heads[6])
                chase(heads[6], heads[7])
                chase(heads[7], heads[8])
                chase(heads[8], tail)
                visited.add(Position(tail.x, tail.y))
            }
        }

        return visited.size
    }

    fun chase(head: Position, tail: Position) {
        if (tail.x - head.x > 1) {
            tail.x--
            if (tail.y > head.y) {
                tail.y--
            } else if (tail.y < head.y) {
                tail.y++
            }
        } else if (head.x - tail.x > 1) {
            tail.x++
            if (tail.y > head.y) {
                tail.y--
            } else if (tail.y < head.y) {
                tail.y++
            }
        } else if (tail.y - head.y > 1) {
            tail.y--
            if (tail.x > head.x) {
                tail.x--
            } else if (tail.x < head.x) {
                tail.x++
            }
        } else if (head.y - tail.y > 1) {
            tail.y++
            if (tail.x > head.x) {
                tail.x--
            } else if (tail.x < head.x) {
                tail.x++
            }
        }
    }

}