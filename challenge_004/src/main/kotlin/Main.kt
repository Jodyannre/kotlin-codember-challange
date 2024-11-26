package org.example
import java.io.File

fun main() {
    getNodes(readFile(getFilePath("nodos.txt")).toString())

}

fun getNodes(file: String) {
    var nodeGroups  = file
        .trimStart('[').trimEnd(']')
        .split("],")
        .map { it.trim('[', ']').split(",").toMutableSet() }
        .toMutableList()
    
    var toRemove = mutableSetOf<Int>()

    nodeGroups.forEachIndexed { i, currentSet ->
        nodeGroups.forEachIndexed { j, innerSet ->
            if (i != j && currentSet.any { it in innerSet }) {
                currentSet.addAll(innerSet)
                toRemove.add(j)
            }
        }
    }
    deleteElements(nodeGroups,toRemove)
    println(getCleanedList(nodeGroups))
}

fun getCleanedList(list:MutableList<MutableSet<String>>): String {
    return list
        .filter { it.size < 3 }
        .joinToString(",") { it.joinToString(",") }
}

fun deleteElements(nodeGroups:MutableList<MutableSet<String>>, toRemove: Set<Int>) {
    toRemove.sortedDescending().forEach { nodeGroups.removeAt(it) }
}

fun readFile(path:String): List<String>{
    return File(path).readLines()
}

fun getFilePath(fileName:String):String{
    val userPath = System.getProperty("user.dir")
    return "$userPath\\src\\main\\kotlin\\$fileName"
}