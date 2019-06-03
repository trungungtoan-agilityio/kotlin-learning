package games.gameOfFifteen

/*
 * This function should return the parity of the permutation.
 * true - the permutation is even
 * false - the permutation is odd
 * https://en.wikipedia.org/wiki/Parity_of_a_permutation

 * If the game of fifteen is started with the wrong parity, you can't get the correct result
 *   (numbers sorted in the right order, empty cell at last).
 * Thus the initial permutation should be correct.
 */
fun isEven (permutation: List <Int>): Boolean {
    // pair of original: (i, j) (i <j)
    // permutation of i: Pi
    val listOfOriginToPermutation = permutation.  map { Pi ->
        permutation.indexOf (Pi) + 1 to Pi
    }
    with (listOfOriginToPermutation) {
        val test = flatMap { (i, Pi) ->
            filter { (j, _) ->
                i <j
            } .  map { (_, Pj) ->
                Pi to Pj
            }
        } .  count { (Pi, Pj) ->
            Pi> Pj
        } .  takeIf { numOfInverted ->
            numOfInverted% 2 == 0
        } ?.  let {
            return true
        }
        return false
    }
}