package day11

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import readInput

class Day11Test {

    private val solver = Day11()

    @Test
    fun `should match part 1 example`() {
        val input = readInput("test/day11/part1")
        val expected = 10605

        val result = solver.part1(input).toLong()
        result shouldBe expected
    }

    @Test
    fun `should match part 2 example`() {
        val input = readInput("test/day11/part1")
        val expected = 2713310158

        val result = solver.part2(input).toLong()
        result shouldBe expected
    }

}