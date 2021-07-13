package othello;

public class OthelloModel {
    /** 2-Dimensional array for the board */
    private int[][] board = new int[8][8];

    /** Player 1 Chip Count */
    private int player1ChipCount = 0;
    /** Player 2 Chip Count */
    private int player2ChipCount = 0;

    /**
     * Returns the contents of a given square
     * 
     * @param row row in the board
     * @param col column in the board
     * @return 0 for empty, 1 for black, 2 for white
     */
    public int getSquare(int row, int col) {
        return 0;
    }

    /**
     * Contains all the layouts for testing portions of the code.
     * 
     * @param mode the board layout selected
     */
    public void prepareBoard(int mode) {

    }

    /**
     * Checks if a player can make a valid move to this particular square
     * 
     * @param row    row on the board
     * @param col    column on the board
     * @param player whichever player is trying to move, 1 for black, 2 for white
     * @return true if move is valid, false if not
     */
    public boolean canMove(int row, int col, int player) {
        return false;
    }

    /**
     * The player is attempting the move here. Returns number of chips captured, or
     * 0 if the move is illegal <br>
     * if the move is legal, it will update the OthelloModel's board array to flip
     * the appropriate chips to the new color
     * 
     * @param row    row on the board
     * @param col    column on the board
     * @param player whichever player is trying to move, 1 for black, 2 for white
     * @return number of chips captured if the move is legal, 0 if the move is
     *         illegal
     */
    public int tryMove(int row, int col, int player) {
        return 0;
    }

    /**
     * Checks if the current player has any valid move, anywhere on the board
     * 
     * @param player the current player, 1 for black, 2 for white
     * @return true if there is a valid move, false if not
     */
    public boolean moveTest(int player) {
        return true;
    }

    /**
     * Return total number of pieces the specified player has on the board.
     * 
     * @param player the current player, 1 for black, 2 for white
     * @return
     */
    public int chipCount(int player) {
        if (player == 1)
            return player1ChipCount;
        return player2ChipCount;
    }
}
