package org.example
import java.io.File

fun main() {
    runCount(readFile(getFilePath("trace.txt")))
}

fun readFile(path:String): List<String>{
    return File(path).readLines()
}

fun getFilePath(fileName:String):String{
    val userPath = System.getProperty("user.dir")
    return "$userPath\\src\\main\\kotlin\\$fileName"
}

fun runCount(list: List<String>) {
    var actual_pos = 0
    var min_pos = 0
    var iterations = 0
    var last_iteration = 0
    var instruction = mutableListOf<Int>()

    for (line in list.indices) {
        actual_pos = 0
        instruction = list[line].split(" ").map{it.toInt()}.toMutableList()

        while (actual_pos in min_pos until instruction.size) {
            actual_pos += instruction[actual_pos].also {
                instruction[actual_pos] = instruction[actual_pos]+1
            }
            if (line == list.size-1) last_iteration++
            iterations++
        }
    }
    println("Total iterations: $iterations")
    println("Last iteration $last_iteration")
    println("submit $iterations-$last_iteration")
}