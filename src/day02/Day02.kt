package day02

import readInput

fun main() {
    val solver = Day02()
    val input = readInput("src/day02/input")
    println("Part 1: ${solver.part1(input)}")
    println("Part 2: ${solver.part2(input)}")
}

class Day02 {

    fun part1(input: List<String>): Int {
        val scores = input.map {
            val opponent = it.substringBefore(" ").toMove()
            val mine = it.substringAfter(" ").toMove()

            val winScore = when{
                opponent == mine -> 3
                opponent == Move.ROCK && mine == Move.PAPER -> 6
                opponent == Move.PAPER && mine == Move.SCISSORS -> 6
                opponent == Move.SCISSORS && mine == Move.ROCK -> 6
                mine == Move.ROCK && opponent == Move.PAPER -> 0
                mine == Move.PAPER && opponent == Move.SCISSORS -> 0
                mine == Move.SCISSORS && opponent == Move.ROCK -> 0
                else -> 0
            }
             mine.score +winScore
        }
        return  scores.sum()
    }

    fun String.toMove(): Move {
        return when (this) {
            "A", "X" -> return Move.ROCK
            "B", "Y" -> return Move.PAPER
            "C", "Z" -> return Move.SCISSORS
            else -> throw IllegalArgumentException("Invalid move")
        }
    }

    fun String.toOutcome(): Outcome {
        return when (this) {
             "X" -> return Outcome.LOSE
            "Y" -> return Outcome.DRAW
            "Z" -> return Outcome.WIN
            else -> throw IllegalArgumentException("Invalid outcome")
        }
    }

    enum class Move(val score: Int) {
        ROCK(1), PAPER(2), SCISSORS(3)
    }

    enum class Outcome() {
        WIN, LOSE, DRAW
    }

    fun part2(input: List<String>): Int {
        val scores = input.map {
            val opponent = it.substringBefore(" ").toMove()
            val outcome = it.substringAfter(" ").toOutcome()
            val mine = when(outcome){
                Outcome.WIN -> when(opponent) {
                    Move.ROCK -> Move.PAPER
                    Move.PAPER -> Move.SCISSORS
                    Move.SCISSORS -> Move.ROCK
                }
                Outcome.LOSE -> when(opponent) {
                    Move.ROCK -> Move.SCISSORS
                    Move.PAPER -> Move.ROCK
                    Move.SCISSORS -> Move.PAPER
                }
                Outcome.DRAW -> opponent
            }

            mine.score + when(outcome){
                Outcome.WIN -> 6
                Outcome.LOSE -> 0
                Outcome.DRAW -> 3
            }
        }
        return  scores.sum()
    }

}