package games.game2048

/*
 * This function moves all the non-null elements to the beginning of the list
 * (by removing nulls) and merges equal elements.
 * The parameter 'merge' specifies the way how to merge equal elements:
 * it returns a new element that should be present in the resulting list
 * instead of two merged elements.
 *
 * If the function 'merge("a")' returns "aa",
 * then the function 'moveAndMergeEqual' transforms the input in the following way:
 *   a, a, b -> aa, b
 *   a, null -> a
 *   b, null, a, a -> b, aa
 *   a, a, null, a -> aa, a
 *   a, null, a, a -> aa, a
 *
 * You can find more examples in 'TestGame2048Helper'.
 *
 * Solution
 *  1. Remove Null
 *  2. Push value to a new list and compare 2 adjust values
 *         IF the same    : Replace with merge
 *         IF difference  : Insert values
*/
fun <T : Any> List<T?>.moveAndMergeEqual(merge: (T) -> T): List<T> {
    return this.asSequence () // Optimization for the call chain
            .filterNotNull() // (1)
            .fold(mutableListOf()) { result, element ->
//                if (result.lastOrNull() == element) {
//                    result[result.lastIndex] = merge(element)
//                } else {
//                    result.add(element)
//                }
//                result
                when (element) {
                    result.  lastOrNull () -> {
                        result [result.  lastIndex ] = merge (element)
                        result
                    }
                    else -> {
                        result.add (element)
                        result
                    }
                }
            }
}


