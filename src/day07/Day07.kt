package day07

import readInput

fun main() {
    val solver = Day07()
    val input = readInput("src/day07/input")
    println("Part 1: ${solver.part1(input)}")
    println("Part 2: ${solver.part2(input)}")
}

class Day07 {

    data class Folder(
        val name: String,
        var size: Long = 0,
        var parent: Folder? = null,
        val children: MutableList<Folder> = mutableListOf(),
    )

    fun part1(input: List<String>): Long {
        val rootFolder = input.buildFolders()
        val results = rootFolder.findFoldersSmallerThan(100000)
        return results.sumOf { it.size }
    }

    fun List<String>.buildFolders(): Folder {
        val rootFolder = Folder("/")
        var currentFolder = rootFolder
        val lines = toMutableList()
        while (lines.isNotEmpty()) {
            val line = lines.removeFirst()
            when  {
                line == "$ cd /" -> {
                    // Do nothing
                }

                line == "$ ls" -> {
                    while(lines.isNotEmpty() && !lines.first().startsWith("$")) {
                        val nextLine = lines.removeFirst()
                        if(!nextLine.startsWith("dir")) {
                            val size = nextLine.substringBefore(" ").toLong()
                            var folder: Folder? = currentFolder
                            while (folder != null) {
                                folder.size += size
                                folder = folder.parent
                            }
                        }
                    }
                }

                line.startsWith("$ cd") -> {
                    // cd
                    val folder = line.substringAfter("$ cd ")
                    currentFolder = when (folder) {
                        ".." -> currentFolder.parent!!
                        else -> {
                            val newFolder = Folder(name = folder, parent = currentFolder)

                            currentFolder.children.add(newFolder)
                            newFolder
                        }
                    }

                }
            }
        }
        return rootFolder
    }

    fun Folder.findFoldersSmallerThan(size: Long): List<Folder> {
        val result = mutableListOf<Folder>()
        if (this.size <= size) {
            result.add(this)
        }
        children.forEach {
            result.addAll(it.findFoldersSmallerThan(size))
        }
        return result
    }

    fun part2(input: List<String>): Long {
        val rootFolder = input.buildFolders()

        val usedSpace = rootFolder.size
        val neededSpace = 30000000L
        val totalSpace = 70000000L
        val availableSpace = totalSpace - usedSpace

        val threshold = neededSpace - availableSpace

        val targets = rootFolder.findFoldersBiggerThan(threshold)

        return targets.minOf { it.size }
    }

    fun Folder.findFoldersBiggerThan(size: Long): List<Folder> {
        val result = mutableListOf<Folder>()
        if (this.size >= size) {
            result.add(this)
        }
        children.forEach {
            result.addAll(it.findFoldersBiggerThan(size))
        }
        return result
    }

}