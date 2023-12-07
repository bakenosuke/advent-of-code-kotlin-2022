package day10

import readInput
import kotlin.math.abs

fun main() {
    val solver = Day10()
    val input = readInput("src/day10/input")
    println("Part 1: ${solver.part1(input)}")
    println("Part 2: ${solver.part2(input)}")
}

class Day10 {

    fun part1(input: List<String>): Int {
        var x = 1
        var cycle = 1
        var sumOfSignals = 0
        input.forEach {
            cycle++
            if (cycle % 40 == 20) {
                sumOfSignals += (x * cycle)
            }
            if (it == "noop") return@forEach

            cycle++
            val move = it.substringAfter(" ").toInt()
            x += move
            if (cycle % 40 == 20) {
                sumOfSignals += (x * cycle)
            }
        }

        return sumOfSignals
    }

    fun part2(input: List<String>): String {
        var x = 1
        val crt = mutableListOf<String>()

        input.forEach {
            if (abs(((crt.size ) % 40) - x) <= 1) {
                crt.add("#")
            } else {
                crt.add(".")
            }
            if (it == "noop") {
                return@forEach
            }

            if (abs(((crt.size) % 40) - x) <= 1) {
                crt.add("#")
            } else {
                crt.add(".")
            }
            val move = it.substringAfter(" ").toInt()
            x += move
        }

        println(crt.take(40).joinToString(""))
        println(crt.drop(40).take(40).joinToString(""))
        println(crt.drop(80).take(40).joinToString(""))
        println(crt.drop(120).take(40).joinToString(""))
        println(crt.drop(160).take(40).joinToString(""))
        println(crt.drop(200).take(40).joinToString(""))
        return crt.joinToString("")
    }

}