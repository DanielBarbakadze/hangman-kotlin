class Hangman {
    private var playersList = mutableListOf<Player>()
    private var triedLetters = charArrayOf()

    fun registerPlayer() {
        print("Enter Name: ")
        val player = Player(readLine(), 2)
        println("\nHello ${player.name}, Let’s play Hangman!")
        print("Enter Word: ")
        val word = GuessWord(readLine()?.toUpperCase())
        println("\nGame is starting…")
        start(player, word)
    }

    private fun start(player: Player, word: GuessWord) {
        var playing = true
        while (player.lives!! > 0 && playing) {
            println("\nLives remaining: ${player.lives}")
            print("Enter Character: ")
            var currentLetter = readLine()?.toUpperCase()
            if (currentLetter?.length == 1) {
                while (!isNewLetter(currentLetter!!.single())) {
                    print("You already tried this character\nEnter Character: ")
                    currentLetter = readLine()?.toUpperCase()
                }
                if (word.containsLetter(currentLetter)) {
                    if (word.getGameWord() == word.text) {
                        println("Congratulations! Want to play again? (Y/N/H):")
                        finish(player)
                        playing = false
                    } else {
                        println("Yes, it is there!!!\nCurrent Word is: ${word.getGameWord()}")
                    }
                } else {
                    player.lives = player.lives?.minus(1)
                    println("There is no such character")
                }
            } else {
                println("Please, enter a valid character!")
            }
        }
        if (player.lives!! == 0) {
            println("Sorry, you lost… The word was: ${word.text}\nWant to play again? (Y/N/H):")
            finish(player)
        }
    }
    private fun finish(player: Player) {
        playersList.add(player)
        triedLetters = charArrayOf()
        when (readLine()?.toUpperCase()) {
            "Y" -> registerPlayer()
            "N" -> println("Thanks for playing!")
            "H" -> printPlayersResults()
            else -> println("Invalid Character. Game ended.")
        }
    }
    private fun printPlayersResults() {
        playersList.sortByDescending { it.lives }
        playersList.forEachIndexed { index, it -> if (index < 5) println(it.getResult()) }
    }
    private fun isNewLetter(letter: Char): Boolean {
        return if (triedLetters.contains(letter)) false
        else {
            triedLetters = (triedLetters.joinToString("") + letter).toCharArray()
            true
        }
    }
}
