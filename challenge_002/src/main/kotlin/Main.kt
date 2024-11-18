package org.example

import java.io.File

fun main() {
    val userPath = System.getProperty("user.dir")
    val filePath = "$userPath\\src\\main\\kotlin\\attemps.txt"
    println(readFile(filePath))

}

fun readFile(path:String):String{
    var good = 0
    var wrong = 0
    var prevLetter = false
    var prevNum = false
    var prevChar = ' '
    var regex = Regex("[A-Z]")

    loopLine@ for ( item in File(path).readLines()) {
        // Caso 1 - No puede tener mayúsculas
        if (regex.containsMatchIn(item)) {
            wrong++
            continue
        }

        resetVariables@ run{
            prevChar=' '
            prevNum=false
            prevLetter=false
        }

       loopChar@ for(caracter in item) {
           // Caso 2 - No digitos despues de letras
           if (prevLetter && caracter.isDigit()) {
               wrong++
               continue@loopLine
           }

           // Caso 3 - Digito igual o creciente
           // Caso 4 - Letra igual o creciente
           if (prevNum || prevLetter) {
               if (prevChar.code > caracter.code) {
                   wrong++
                   continue@loopLine
               }
           }

           //Actualizacion de variables
           resetLoopVariables@ run {
               if (caracter.isLetter()) prevLetter=true
               if (caracter.isDigit()) prevNum=true
               prevChar = caracter
           }

       }
        good++
    }
    return "${good}true ${wrong}false"
}