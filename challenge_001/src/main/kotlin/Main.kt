fun main(){
    val (actual, movements) = readln().split(" ")
    val result = Combination(actual, movements)
    result.decode()
    result.printActual()
}