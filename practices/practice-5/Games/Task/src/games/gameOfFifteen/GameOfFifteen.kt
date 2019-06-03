package games.gameOfFifteen

import board.Direction
import board.GameBoard
import board.createGameBoard
import games.game.Game

/*
 * Implement the Game of Fifteen (https://en.wikipedia.org/wiki/15_puzzle).
 * When you finish, you can play the game by executing 'PlayGameOfFifteen'.
 */
fun newGameOfFifteen (initializer: GameOfFifteenInitializer = RandomGameInitializer ()): Game =
        GameImpl (initializer)

class GameImpl (private val initializer: GameOfFifteenInitializer): Game {
    private val board = createGameBoard <Int?> (4)
    private val solutionBoard = createGameBoard <Int?> (4)

    override fun initialize () {
        board.filter {
            it == null
        } .  forEachIndexed { index, cell ->
            board [cell] = initializer.initialPermutation.  getOrNull (index)
            solutionBoard [cell] = (1..15).  toList ().  getOrNull (index)
        }
    }

    override fun canMove (): Boolean = board.any {it == null }


    override fun hasWon (): Boolean {
        return board.getAllCells ().  all { cell ->
            board [cell] == solutionBoard [cell]
        }
    }

    override fun processMove (direction: Direction) {
        val toMoveCell = board.find {it == null }
        val fromMoveCell = with (board) {
            // Extension function access implemented in squareBoardImpl through with
            toMoveCell?.getNeighbour(direction.reversed ())
        }
        // move impossible case: There is always a place to move,
        // If the neighbor cell corresponding to the corresponding direction is null
        fromMoveCell ?.  let {
            board [toMoveCell !!] = board [fromMoveCell]
            board [fromMoveCell] = null
        }
    }

    override fun get (i: Int, j: Int): Int?  = board.  run { get (getCell (i, j)) }
}