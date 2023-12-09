package day11

import readInput
import java.math.BigInteger

fun main() {
    val solver = Day11()
    val input = readInput("src/day11/input")
    println("Part 1: ${solver.part1(input)}")
    println("Part 2: ${solver.part2(input)}")
}

class Day11 {

    data class Monkey(
        val number: Int,
        var items: MutableList<BigInteger> = mutableListOf(),
        var itemsInspected: BigInteger = BigInteger.ZERO,
        val operation: Operation,
        val test: BigInteger,
        val trueTarget: Int,
        val falseTarget: Int,
    ) {
        data class Operation(
            val type: OperationType,
            val value: BigInteger
        ) {
            enum class OperationType {
                SQUARE, ADD, MULTIPLY
            }
        }
    }

    fun part1(input: List<String>): BigInteger {
        var monkies = parseMonkies(input)

        repeat(20) {
            println("Round ${it + 1}")
            monkies.forEach {
                println("Monkey ${it.number} has ${it.items}")
            }
            monkies.playARound()
        }

        val top2 = monkies.sortedByDescending { it.itemsInspected }.take(2)

        return top2[0].itemsInspected * top2[1].itemsInspected
    }

    fun List<Monkey>.playARound() {
        forEach { monkey ->
            monkey.items.forEach { item ->
                monkey.itemsInspected++
                val itemWorry = when (monkey.operation.type) {
                    Monkey.Operation.OperationType.SQUARE -> (item * item) / BigInteger("3")
                    Monkey.Operation.OperationType.ADD -> (item + monkey.operation.value) / BigInteger("3")
                    Monkey.Operation.OperationType.MULTIPLY -> (item * monkey.operation.value) / BigInteger("3")
                }
                if (itemWorry % monkey.test == BigInteger.ZERO) {
                    this[monkey.trueTarget].items.add(itemWorry)
                } else {
                    this[monkey.falseTarget].items.add(itemWorry)
                }
            }
            monkey.items.clear()
        }
    }

    fun parseMonkies(input: List<String>): List<Monkey> {
        val monkies = mutableListOf<Monkey>()
        var i = 0
        while (i < input.size) {
            if (input[i].startsWith("Monkey")) {
                val number = input[i].replace("Monkey ", "").replace(":", "").toInt()
                val items =
                    input[i + 1].substringAfter("Starting items:").split(",").map { it.trim().toBigInteger() }
                        .toMutableList()
                val operation = input[i + 2].parseOperation()
                val test = input[i + 3].substringAfter("Test: divisible by ").trim().toBigInteger()
                val trueTarget = input[i + 4].substringAfter("If true: throw to monkey ").trim().toInt()
                val falseTarget = input[i + 5].substringAfter("If false: throw to monkey ").trim().toInt()
                monkies.add(
                    Monkey(
                        number = number,
                        items = items,
                        operation = operation,
                        test = test,
                        trueTarget = trueTarget,
                        falseTarget = falseTarget
                    )
                )

                i += 6
            } else {
                i++
            }
        }
        return monkies
    }

    fun String.parseOperation(): Monkey.Operation {
        val tokens = substringAfter("Operation: new = ").trim().split(" ").map { it.trim() }

        return when {
            tokens[2] == "old" -> Monkey.Operation(Monkey.Operation.OperationType.SQUARE, BigInteger.ZERO)
            tokens[1] == "+" -> Monkey.Operation(Monkey.Operation.OperationType.ADD, tokens[2].toBigInteger())
            tokens[1] == "*" -> Monkey.Operation(Monkey.Operation.OperationType.MULTIPLY, tokens[2].toBigInteger())
            else -> throw IllegalArgumentException("Unknown operation type: ${this}")
        }
    }

    fun part2(input: List<String>): BigInteger {
        var monkies = parseMonkies(input)

        repeat(10_000) {
            println("Round ${it + 1}")
            monkies.playARound2()
        }

        val top2 = monkies.sortedByDescending { it.itemsInspected }.take(2)

        return top2[0].itemsInspected * top2[1].itemsInspected
    }

    fun List<Monkey>.playARound2() {
        var lcm = BigInteger.ONE
        forEach { monkey ->
            lcm *= monkey.test
        }

        forEach { monkey ->
            monkey.items.forEach { item ->
                monkey.itemsInspected++
                val itemWorry = when (monkey.operation.type) {
                    Monkey.Operation.OperationType.SQUARE -> (item * item)
                    Monkey.Operation.OperationType.ADD -> (item + monkey.operation.value)
                    Monkey.Operation.OperationType.MULTIPLY -> (item * monkey.operation.value)
                } % lcm
                if (itemWorry % monkey.test == BigInteger.ZERO) {
                    this[monkey.trueTarget].items.add(itemWorry)
                } else {
                    this[monkey.falseTarget].items.add(itemWorry)
                }
            }
            monkey.items.clear()
        }
    }
}