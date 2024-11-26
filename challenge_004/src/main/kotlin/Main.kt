package org.example

import java.io.File

fun main() {
    getNodes(readFile(getFilePath("nodos.txt")).toString())

}

fun readFile(path:String): List<String>{
    return File(path).readLines()
}

fun getFilePath(fileName:String):String{
    val userPath = System.getProperty("user.dir")
    return "$userPath\\src\\main\\kotlin\\$fileName"
}

fun getNodes(file: String) {
    var newList = file.drop(3).dropLast(3).replace("[","").split("],").toMutableSet().map{ it.split(",").toMutableSet()}.toMutableList()
    var actual: MutableSet<String>? = null
    var deleteElements = mutableSetOf<Int>()
    var i = 0
    while (true){
        actual = newList[i]
        for (j in newList.indices){
            if (actual.contains(newList[j].elementAt(0))
                || actual.contains(newList[j].elementAt(1))){
                actual.add(newList[j].elementAt(0))
                actual.add(newList[j].elementAt(1))
               if (i != j) deleteElements.add(j)
            }
        }
         i++
        if (i > newList.size-1) break
    }
    for (j in deleteElements.sorted().reversed()){
        newList.removeAt(j)
    }

    println(newList.filter { index -> index.size < 3 }.map { it.joinToString(",")}.joinToString(","))
}