package day03

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import readInput

class Day03Test {

    private val solver = Day03()

    @Test
    fun `should match part 1 example` () {
        val input = readInput("test/day03/part1")
        val expected = 157

        val result = solver.part1(input)
        result shouldBe expected
    }

    @Test
    fun `should match part 2 example` () {
        val input = readInput("test/day03/part1")
        val expected = 70

        val result = solver.part2(input)
        result shouldBe expected
    }

}