package mastermind

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {
    var rightPoint = 0
    var wrongPoint = 0
    val usedList = BooleanArray(secret.length)
    for (index in 0 until secret.length) {
        if (secret[index] == guess[index]) {
            usedList[index] = true
            rightPoint++
        }
    }

    for (i in 0 until guess.length) {
        if (secret[i] != guess[i]) {
            for (j in 0 until secret.length) {
                if (!usedList[j] && j!=i && guess[j] == secret[i]) {
                    usedList[j] = true
                    wrongPoint++
                    break
                }
            }
        }
    }

    return Evaluation(rightPoint, wrongPoint)
}