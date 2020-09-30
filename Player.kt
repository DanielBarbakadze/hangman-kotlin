class Player(var name: String?, var lives: Int?) {
    fun getResult(): String {
        return "$name - $lives Lives"
    }
}