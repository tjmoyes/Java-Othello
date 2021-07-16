/*
 * File Name:   OthelloModel.java
 * Author:      Tyson Moyes
 * Assignment:  Assignment 1 Part 2
 * Date:        June 26th 2021
 * Professor:   Daniel Cormier
 * Purpose:     OthelloModel.java contains all the logic for the Othello game, which is manipulated by the Controller in OthelloViewController
 */

package othello;

/**
 * OthelloModel contains all the logic for the Othello game
 * 
 * @author Tyson Moyes
 * @version 1.0
 * @since 1.8.0_29
 */
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
        return board[row][col];
    }

    /**
     * Contains all the layouts for testing portions of the code.
     * 
     * @param mode the board layout selected
     */
    public void prepareBoard(int mode) {
        switch (mode) {
        // I had to add this formatter off section or VSCode would turn my 2D array into
        // absolute disgusting filth
        // @formatter:off
            // NORMAL GAME
            case 0:
                board = new int[][] 
                {
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 2, 1, 0, 0, 0},
                    {0, 0, 0, 2, 1, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0}
                };
                break;

            // CORNER TEST
            case 1:
                board = new int[][] 
                {
                    {2, 0, 0, 0, 0, 0, 0, 1},
                    {0, 1, 0, 0, 0, 0, 2, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 1, 0, 0, 0, 0, 1, 0},
                    {2, 0, 0, 0, 0, 0, 0, 2}
                };
                break;

            // SIDE TESTS
            case 2:
                board = new int[][]
                {
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 2, 2, 2, 2, 2, 2, 0},
                    {0, 2, 1, 1, 1, 1, 2, 0},
                    {0, 2, 1, 0, 0, 1, 1, 0},
                    {0, 2, 1, 0, 0, 1, 2, 0},
                    {0, 2, 1, 1, 1, 1, 2, 0},
                    {0, 2, 2, 2, 2, 2, 2, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0}
                };
                break;
            
            // 1x CAPTURE TEST
            case 3:
                board = new int[][]
                {
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 1, 1, 1, 1, 1, 1, 0},
                    {0, 1, 1, 1, 1, 1, 1, 0},
                    {0, 1, 2, 2, 2, 1, 1, 0},
                    {0, 1, 2, 0, 2, 1, 1, 0},
                    {0, 1, 2, 2, 2, 1, 1, 0},
                    {0, 1, 1, 1, 1, 1, 1, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0}
                };
                break;
            
            // 2x CAPTURE TEST
            case 4:
                board = new int[][]
                {
                    {1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 2, 2, 2, 1, 2, 1, 1},
                    {1, 2, 2, 2, 2, 2, 1, 1},
                    {1, 2, 2, 0, 2, 2, 1, 1},
                    {1, 2, 2, 2, 2, 1, 1, 1},
                    {1, 2, 1, 2, 2, 2, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1}
                };
                break;
            
            // Empty Board
            case 5:
                board = new int[][]
                {
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0}
                };
                break;

            // Inner Square Test
            case 6:
                board = new int[][]
                {
                    {2, 2, 2, 2, 2, 2, 2, 2},
                    {2, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 2, 2, 2, 2, 0, 2},
                    {2, 0, 2, 1, 1, 2, 0, 2},
                    {2, 0, 2, 1, 1, 2, 0, 2},
                    {2, 0, 2, 2, 2, 2, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 2},
                    {2, 2, 2, 2, 2, 2, 2, 2}
                };
                break;
            // Up arrow Test
            case 7:
                board = new int[][]
                {
                    {0, 0, 0, 1, 1, 0, 0, 0},
                    {0, 0, 1, 1, 1, 1, 0, 0},
                    {0, 1, 0, 1, 1, 0, 1, 0},
                    {1, 0, 0, 1, 1, 0, 0, 1},
                    {0, 0, 0, 1, 1, 0, 0, 0},
                    {0, 0, 0, 1, 1, 0, 0, 0},
                    {0, 0, 0, 1, 1, 0, 0, 0},
                    {0, 0, 0, 1, 1, 0, 0, 0}
                };
                break;
            default:
                System.err.println("BAD MODE. This should NEVER happen");
                break;
            }
        // @formatter:on

        // update player chip initialized count
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 1)
                    player1ChipCount++;
                else if (board[i][j] == 2)
                    player2ChipCount++;
                else
                    continue;
            }
        }
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
        if(player == 1 && board[row][col] == 0 && board[row][col] != 1)
            return true;
        else if(player == 2 && board[row][col] == 0 && board[row][col] != 2)
            return true;
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
