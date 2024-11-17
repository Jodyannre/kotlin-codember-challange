class Combination(actual: String, private val movements: String) {
    val actual = actual.map{ it.digitToInt() }.toMutableList()
    private val X_MAX = this.actual.size-1
    private var x = 0

    companion object {
        private const val Y_MAX = 9
        private const val Y_MIN = 0
        private const val X_MIN = 0
        private const val U = 'U'
        private const val D = 'D'
        private const val L = 'L'
        private const val R = 'R'
        private const val invalid = "Invalid action"
    }

    fun decode() {
        for (action in movements) {
            when (action) {
                U -> up()
                D -> down()
                L -> left()
                R -> right()
                else -> println(invalid)
            }
        }
    }

    private fun up() {
        actual[x] = if (actual[x] + 1 > Y_MAX) Y_MIN else actual[x] + 1
    }

    private fun down() {
        actual[x] = if (actual[x] - 1 < Y_MIN) Y_MAX else actual[x] - 1
    }

    private fun left() {
        x = if (x-1 < X_MIN) X_MAX else x-1
    }

    private fun right() {
        x = if (x+1 > X_MAX) X_MIN else x+1
    }

    fun printActual(){
        println(actual.joinToString(""))
    }

}