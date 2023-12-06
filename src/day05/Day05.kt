package day05

import readInput

fun main() {
    val solver = Day05()
    val input = readInput("src/day05/input")
    println("Part 1: ${solver.part1(input)}")
    println("Part 2: ${solver.part2(input)}")
}

class Day05 {

    fun part1(input: List<String>): String {
        val stacks = input.readStacks()
        val blankLineIndex = input.indexOf("")
        val moveLines = input.subList(blankLineIndex + 1, input.size)

        moveLines.forEach {
            move(it, stacks)
        }
        return stacks.map { it.firstOrNull() }.filterNotNull().joinToString("")
    }

    fun List<String>.readStacks(): List<MutableList<Char>> {
        val blankLineIndex = this.indexOf("")
        val size = this[blankLineIndex - 1].split(" ").filter { it.isNotBlank() }.size
        val stackLines = subList(0, blankLineIndex - 1)
        val stacks = (0..<size).map { mutableListOf<Char>() }
        stackLines.forEach {
            (0..<size).map { index ->
                try {
                    val char = it[index * 4 + 1]
                    if (char != ' ') {
                        stacks[index].add(it[index * 4 + 1])
                    }
                } catch (e: StringIndexOutOfBoundsException) {
                }
            }
        }
        return stacks
    }

    fun move(input: String, stacks: List<MutableList<Char>>) {
        val tokens = input.split(" ")

        val number = tokens[1].toInt()
        val from = tokens[3].toInt()
        val to = tokens[5].toInt()

        (0..<number).forEach {
            stacks[to - 1].add(0, stacks[from - 1].removeAt(0))
        }
    }

    fun part2(input: List<String>): String {
        val stacks = input.readStacks()
        val blankLineIndex = input.indexOf("")
        val moveLines = input.subList(blankLineIndex + 1, input.size)

        moveLines.forEach {
            move2(it, stacks)
        }
        return stacks.map { it.firstOrNull() }.filterNotNull().joinToString("")
    }

    fun move2(input: String, stacks: List<MutableList<Char>>) {
        val tokens = input.split(" ")

        val number = tokens[1].toInt()
        val from = tokens[3].toInt()
        val to = tokens[5].toInt()

        val tempStack = mutableListOf<Char>()
        (0..<number).forEach {
            tempStack.add(0, stacks[from - 1].removeAt(0))
        }
        (0..<number).forEach {
            stacks[to - 1].add(0, tempStack.removeAt(0))
        }
    }

}