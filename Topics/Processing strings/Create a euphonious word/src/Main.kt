fun main() {
    val discordantWord = readln()
    val minInsertions = minCharactersForEuphonious(discordantWord)

    println("$minInsertions")
}

fun isVowel(char: Char): Boolean {
    return char in "aeiouy"
}

fun minCharactersForEuphonious(word: String): Int {
    var minInsertions = 0
    var consecutiveCount = 1 // Count of consecutive vowels or consonants

    for (i in 1 until word.length) {
        val currentChar = word[i]
        val prevChar = word[i - 1]

        if (isVowel(currentChar) == isVowel(prevChar)) {
            consecutiveCount++
            if (consecutiveCount >= 3) {
                minInsertions++
                consecutiveCount = 1
            }
        } else {
            consecutiveCount = 1
        }
    }

    return minInsertions
}
