package org.example

import java.io.File

fun main() {
    val result = mutableListOf<Int>()
    val numbers = readFile(getFilePath("./numbers.txt")).get(0).split(",")

    numbers.forEach {
        if (calculatePrime(it)) result.add(it.toInt())
    }

    println(result.sorted())
}



fun calculatePrime(string: String): Boolean {
    val number = string.toInt()

    /* Primer filtro */
    if (!isPrime(number)) return false

    /* Segundo Filtro */
    var numberConverted = 0
    for (i in string) {
        numberConverted+=i.digitToInt()
    }
    return isPrime(numberConverted)
}

fun isPrime(number: Int): Boolean{
    if (number in 0..3) return true
    for (i in number downTo 1){
        if (number != i && i != 1 && number % i == 0) return false
    }
    return true
}

fun readFile(path:String): List<String>{
    return File(path).readLines()
}

fun getFilePath(fileName:String):String{
    val userPath = System.getProperty("user.dir")
    return "$userPath\\src\\main\\kotlin\\$fileName"
}

