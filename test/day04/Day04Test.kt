package day04

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import readInput

class Day04Test {

    private val solver = Day04()

    @Test
    fun `should match part 1 example` () {
        val input = readInput("test/day04/part1")
        val expected = 2

        val result = solver.part1(input)
        result shouldBe expected
    }

    @Test
    fun `should match part 2 example` () {
        val input = readInput("test/day04/part1")
        val expected = 4

        val result = solver.part2(input)
        result shouldBe expected
    }

}