class GuessWord(var text: String?) {
    private var gameWord: CharArray? = text?.length?.let { "_".repeat(it).toCharArray() }

    fun getGameWord(): String? {
        return gameWord?.let { String(it) }
    }

    fun containsLetter(givenLetter: String): Boolean {
        val ind: Int? = text?.indexOf(givenLetter)

        if (gameWord?.let { String(it) } != text) {
            if (ind != -1 && ind != null) {
                text?.forEachIndexed { i, it ->
                    if (it == givenLetter.single()) gameWord?.set(i, givenLetter.single())
                }
            }
        }

        return ind != -1
    }
}