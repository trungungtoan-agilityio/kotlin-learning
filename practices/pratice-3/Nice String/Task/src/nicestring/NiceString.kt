package nicestring

/**
 * A string is nice if at least two of the following conditions are satisfied:
 * - It doesn't contain substrings bu, ba or be;
 * - It contains at least three vowels (vowels are a, e, i, o and u);
 * - It contains a double letter (at least two similar letters following one another), like b in "abba"
 */
fun String.isNice(): Boolean {

    // Check black list
    // Contains at least three vowels (vowels are a, e, i, o and u)
    // Contains a double letter
    val notBadSubString = setOf("bu", "ba", "be").none{this.contains(it)}
    val hasThreeVowels = count{it in "aeiou"} >= 3
    val hasDouble = zipWithNext().any { it.first == it.second }
    return listOf(notBadSubString, hasDouble, hasThreeVowels).count {it} >= 2
}


fun String.isNicePractice(): Boolean {

    // Check black list
    // Contains at least three vowels (vowels are a, e, i, o and u)
    // Contains a double letter
    var validCount = 0
    if (!onBlackList(this))
        validCount++
    if (threeVowels(this))
        validCount++
    if (doubleLetter(this))
        validCount++
    return validCount >= 2
}

fun onBlackList(letter: String): Boolean {
    val blackList = arrayOf("bu", "ba", "be")
    for (b in blackList) {
        if (letter.contains(b)) return true
    }
    return false
}

fun threeVowels(letter: String): Boolean {
    val vowels = arrayOf("a", "e", "i", "o", "u")
    var countVowel = 0
    for (l in letter) {
        if (vowels.contains(l.toString())) countVowel++
    }

    return countVowel >= 3
}

fun doubleLetter(letter: String): Boolean {
    for (i in 0 until letter.length) {
        if (i < letter.length - 1 && letter.elementAt(i) == letter.elementAt(i+1))
            return true
    }
    return false
}