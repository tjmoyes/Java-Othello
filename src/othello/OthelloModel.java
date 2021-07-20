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

    // Some class constants for your use:
    public static final int NORMAL = 0;
    public static final int CORNER_TEST = 1;
    public static final int OUTER_TEST = 2;
    public static final int TEST_CAPTURE = 3;
    public static final int TEST_CAPTURE2 = 4;
    public static final int UNWINNABLE = 5;
    public static final int INNER_TEST = 6;
    public static final int ARROW = 7;

    public static final int EMPTY = 0;
    public static final int BLACK = 1;
    public static final int WHITE = 2;

    public OthelloModel() {
        prepareBoard(NORMAL);
    }

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
            case NORMAL:
                board = new int[][] 
                {
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 2, 1, 0, 0, 0},
                    {0, 0, 0, 1, 2, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0}
                };
                break;

            // CORNER TEST
            case CORNER_TEST:
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
            case OUTER_TEST:
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
            case TEST_CAPTURE:
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
            case TEST_CAPTURE2:
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
            case UNWINNABLE:
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
            case INNER_TEST:
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
            case ARROW:
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
        int tempRow = row, tempCol = col;

        // Move is only valid if this square is empty
        if (board[row][col] != EMPTY) {
            return false;
        } else {
            // NORTH
            try {
                /*
                 * Checking NORTH of empty space
                 * 
                 * if P1 - board[row][col] must be 0, with at least 1 P2 token at
                 * board[row-n][col], and finally a P1 token at board[n][col]
                 * 
                 * if P2 - board[row][col] must be 0, with at least 1 P1 token at
                 * board[row-n][col], and finally a P2 token at board[n][col]
                 */
                tempRow--;
                while ((player == 1 && board[tempRow][tempCol] == WHITE)
                        || (player == 2 && board[tempRow][tempCol] == BLACK)) {
                    tempRow--;
                    if (board[tempRow][tempCol] == player) {
                        return true;
                    }
                }

                // invalid, reset the tempRow variable
                tempRow = row;
            } catch (ArrayIndexOutOfBoundsException oobe) {
                tempRow = row;
            }

            // EAST
            try {
                /*
                 * Checking EAST of empty space
                 * 
                 * if P1 - board[row][col] must be 0, with at least 1 P2 token at
                 * board[row][col+n], and finally a P1 token at board[row][n]
                 * 
                 * if P2 - board[row][col] must be 0, with at least 1 P1 token at
                 * board[row][col+n], and finally a P2 token at board[row][n]
                 */
                tempCol++;
                while ((player == 1 && board[tempRow][tempCol] == WHITE)
                        || (player == 2 && board[tempRow][tempCol] == BLACK)) {
                    tempCol++;
                    if (board[tempRow][tempCol] == player) {
                        return true;
                    }
                }

                // invalid move, reset the tempCol variable
                tempCol = col;
            } catch (ArrayIndexOutOfBoundsException oobe) {
                tempCol = col;
            }

            // SOUTH
            try {
                /*
                 * Checking SOUTH of empty space
                 * 
                 * if P1 - board[row][col] must be 0, with at least 1 P2 token at
                 * board[row+n][col], and finally a P1 token at board[n][col]
                 * 
                 * if P2 - board[row][col] must be 0, with at least 1 P1 token at
                 * board[row+n][col], and finally a P2 token at board[n][col]
                 */
                tempRow++;
                while ((player == 1 && board[tempRow][tempCol] == WHITE)
                        || (player == 2 && board[tempRow][tempCol] == BLACK)) {
                    tempRow++;
                    if (board[tempRow][tempCol] == player) {
                        return true;
                    }
                }

                // Invalid move, reset the tempRow variable
                tempRow = row;
            } catch (ArrayIndexOutOfBoundsException oobe) {
                tempRow = row;
            }

            // WEST
            try {
                /*
                 * Checking WEST of empty space
                 * 
                 * if P1 - board[row][col] must be 0, with at least 1 P2 token at
                 * board[row][col-n], and finally a P1 token at board[row][n]
                 * 
                 * if P2 - board[row][col] must be 0, with at least 1 P1 token at
                 * board[row][col-n], and finally a P2 token at board[row][col-n]
                 */
                tempCol--;
                while ((player == 1 && board[tempRow][tempCol] == WHITE)
                        || (player == 2 && board[tempRow][tempCol] == BLACK)) {
                    tempCol--;
                    if (board[tempRow][tempCol] == player) {
                        return true;
                    }
                }

                // invalid move, reset the tempCol variable
                tempCol = col;
            } catch (ArrayIndexOutOfBoundsException oobe) {
                tempRow = row;
                tempCol = col;
            }

            // NORTHEAST
            try {
                /*
                 * Checking NORTHEAST of empty space
                 * 
                 * if P1 - board[row][col] must be 0, with at least 1 P2 token at
                 * board[row-n][col+m], and finally a P1 token at board[n][m]
                 * 
                 * if P2 - board[row][col] must be 0, with at least 1 P1 token at
                 * board[row-n][col+m], and finally a P2 token at board[n][m]
                 */
                tempRow--;
                tempCol++;
                while ((player == 1 && board[tempRow][tempCol] == WHITE)
                        || (player == 2 && board[tempRow][tempCol] == BLACK)) {
                    tempRow--;
                    tempCol++;
                    if (board[tempRow][tempCol] == player) {
                        return true;
                    }
                }

                // invalid move, reset tempRow and tempCol
                tempRow = row;
                tempCol = col;
            } catch (ArrayIndexOutOfBoundsException oobe) {
                tempRow = row;
                tempCol = col;
            }

            // NORTHWEST
            try {
                /*
                 * Checking NORTHWEST of empty space
                 * 
                 * if P1 - board[row][col] must be 0, with at least 1 P2 token at
                 * board[row-n][col-m], and finally a P1 token at board[n][m]
                 * 
                 * if P2 - board[row][col] must be 0, with at least 1 P1 token at
                 * board[row-n][col-m], and finally a P2 token at board[n][m]
                 */
                tempRow--;
                tempCol--;
                while ((player == 1 && board[tempRow][tempCol] == WHITE)
                        || (player == 2 && board[tempRow][tempCol] == BLACK)) {
                    tempRow--;
                    tempCol--;
                    if (board[tempRow][tempCol] == player) {
                        return true;
                    }
                }

                // invalid move, reset tempRow and tempCol
                tempRow = row;
                tempCol = col;

            } catch (ArrayIndexOutOfBoundsException oobe) {
                tempRow = row;
                tempCol = col;
            }
        }

        // SOUTHEAST
        try {
            /*
             * Checking SOUTHEAST of empty space
             * 
             * if P1 - board[row][col] must be 0, with at least 1 P2 token at
             * board[row+n][col+m], and finally a P1 token at board[n][m]
             * 
             * if P2 - board[row][col] must be 0, with at least 1 P1 token at
             * board[row+n][col+m], and finally a P2 token at board[n][m]
             */
            tempRow++;
            tempCol++;
            while ((player == 1 && board[tempRow][tempCol] == WHITE)
                    || (player == 2 && board[tempRow][tempCol] == BLACK)) {
                tempRow++;
                tempCol++;
                if (board[tempRow][tempCol] == player) {
                    return true;
                }
            }

            // Invalid move, reset tempRow and tempCol
            tempRow = row;
            tempCol = col;
        } catch (ArrayIndexOutOfBoundsException oobe) {
            tempRow = row;
            tempCol = col;
        }

        // SOUTHWEST
        try {
            /*
             * Checking SOUTHWEST of empty space
             * 
             * if P1 - board[row][col] must be 0, with at least 1 P2 token at
             * board[row+n][col-m], and finally a P1 token at board[n][m]
             * 
             * if P2 - board[row][col] must be 0, with at least 1 P1 token at
             * board[row+n][col-m], and finally a P2 token at board[n][m]
             */
            tempRow++;
            tempCol--;
            while ((player == 1 && board[tempRow][tempCol] == WHITE)
                    || (player == 2 && board[tempRow][tempCol] == BLACK)) {
                tempRow++;
                tempCol--;
                if (board[tempRow][tempCol] == player) {
                    return true;
                }
            }

            // Invalid move, reset tempRow and tempCol
            tempRow = row;
            tempCol = col;
        } catch (ArrayIndexOutOfBoundsException oobe) {
            tempRow = row;
            tempCol = col;
        }

        // No valid move found for this square
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
        int tempRow = row, tempCol = col, capturedPieces = 0, jumpedSpaces = 0;
        ;

        // NORTH
        try {
            tempRow--;
            while ((player == 1 && board[tempRow][tempCol] == 2) || (player == 2 && board[tempRow][tempCol] == 1)) {
                jumpedSpaces++;
                tempRow--;
                if (board[tempRow][tempCol] == player) {
                    board[row][col] = player;
                    // backtrack through to flip the captured pieces
                    while (jumpedSpaces > 0) {
                        tempRow++;
                        jumpedSpaces--;
                        board[tempRow][tempCol] = player;
                        capturedPieces++;
                    }
                    break;
                }
            }

            // Can't capture this way, reset temps
            tempRow = row;
            jumpedSpaces = 0;
        } catch (ArrayIndexOutOfBoundsException oobe) {
            // Out of bounds, can't capture, reset temps
            tempRow = row;
            jumpedSpaces = 0;
        }

        // NORTHEAST
        try {
            tempRow--;
            tempCol++;
            while ((player == 1 && board[tempRow][tempCol] == 2) || (player == 2 && board[tempRow][tempCol] == 1)) {
                jumpedSpaces++;
                tempRow--;
                tempCol++;
                if (board[tempRow][tempCol] == player) {
                    board[row][col] = player;
                    // backtrack through to flip the captured pieces
                    while (jumpedSpaces > 0) {
                        tempRow++;
                        tempCol--;
                        jumpedSpaces--;
                        board[tempRow][tempCol] = player;
                        capturedPieces++;
                    }
                    break;
                }
            }

            // Can't capture this way, reset temps
            tempRow = row;
            tempCol = col;
            jumpedSpaces = 0;
        } catch (ArrayIndexOutOfBoundsException oobe) {
            // Out of bounds, can't capture, reset temps
            tempRow = row;
            tempCol = col;
            jumpedSpaces = 0;
        }

        // EAST
        try {
            tempCol++;
            while ((player == 1 && board[tempRow][tempCol] == 2) || (player == 2 && board[tempRow][tempCol] == 1)) {
                jumpedSpaces++;
                tempCol++;
                if (board[tempRow][tempCol] == player) {
                    board[row][col] = player;
                    // backtrack through to flip the captured pieces
                    while (jumpedSpaces > 0) {
                        tempCol--;
                        jumpedSpaces--;
                        board[tempRow][tempCol] = player;
                        capturedPieces++;
                    }
                    break;
                }
            }

            // Can't capture this way, reset temps
            tempCol = col;
            jumpedSpaces = 0;
        } catch (ArrayIndexOutOfBoundsException oobe) {
            // Out of bounds, can't capture, reset temps
            tempCol = col;
            jumpedSpaces = 0;
        }

        // SOUTHEAST
        try {
            tempRow++;
            tempCol++;
            while ((player == 1 && board[tempRow][tempCol] == 2) || (player == 2 && board[tempRow][tempCol] == 1)) {
                jumpedSpaces++;
                tempRow++;
                tempCol++;
                if (board[tempRow][tempCol] == player) {
                    board[row][col] = player;
                    // backtrack through to flip the captured pieces
                    while (jumpedSpaces > 0) {
                        tempRow--;
                        tempCol--;
                        jumpedSpaces--;
                        board[tempRow][tempCol] = player;
                        capturedPieces++;
                    }
                    break;
                }
            }

            // Can't capture this way, reset temps
            tempRow = row;
            tempCol = col;
            jumpedSpaces = 0;
        } catch (ArrayIndexOutOfBoundsException oobe) {
            // Out of bounds, can't capture, reset temps
            tempRow = row;
            tempCol = col;
            jumpedSpaces = 0;
        }

        // SOUTH
        try {
            tempRow++;
            while ((player == 1 && board[tempRow][tempCol] == 2) || (player == 2 && board[tempRow][tempCol] == 1)) {
                jumpedSpaces++;
                tempRow++;
                if (board[tempRow][tempCol] == player) {
                    board[row][col] = player;
                    // backtrack through to flip the captured pieces
                    while (jumpedSpaces > 0) {
                        tempRow--;
                        jumpedSpaces--;
                        board[tempRow][tempCol] = player;
                        capturedPieces++;
                    }
                    break;
                }
            }

            // Can't capture this way, reset temps
            tempRow = row;
            jumpedSpaces = 0;
        } catch (ArrayIndexOutOfBoundsException oobe) {
            // Out of bounds, can't capture, reset temps
            tempRow = row;
            jumpedSpaces = 0;
        }

        // SOUTHWEST
        try {
            tempRow++;
            tempCol--;
            while ((player == 1 && board[tempRow][tempCol] == 2) || (player == 2 && board[tempRow][tempCol] == 1)) {
                jumpedSpaces++;
                tempRow++;
                tempCol--;
                if (board[tempRow][tempCol] == player) {
                    board[row][col] = player;
                    // backtrack through to flip the captured pieces
                    while (jumpedSpaces > 0) {
                        tempRow--;
                        tempCol++;
                        jumpedSpaces--;
                        board[tempRow][tempCol] = player;
                        capturedPieces++;
                    }
                    break;
                }
            }

            // Can't capture this way, reset temps
            tempRow = row;
            tempCol = col;
            jumpedSpaces = 0;
        } catch (ArrayIndexOutOfBoundsException oobe) {
            // Out of bounds, can't capture, reset temps
            tempRow = row;
            tempCol = col;
            jumpedSpaces = 0;
        }

        // WEST
        try {
            tempCol--;
            while ((player == 1 && board[tempRow][tempCol] == 2) || (player == 2 && board[tempRow][tempCol] == 1)) {
                jumpedSpaces++;
                tempCol--;
                if (board[tempRow][tempCol] == player) {
                    board[row][col] = player;
                    // backtrack through to flip the captured pieces
                    while (jumpedSpaces > 0) {
                        tempCol++;
                        jumpedSpaces--;
                        board[tempRow][tempCol] = player;
                        capturedPieces++;
                    }
                    break;
                }
            }

            // Can't capture this way, reset tempRow
            tempCol = col;
            jumpedSpaces = 0;
        } catch (ArrayIndexOutOfBoundsException oobe) {
            // Out of bounds, can't capture, reset temps
            tempCol = col;
            jumpedSpaces = 0;
        }

        // NORTHWEST
        try {
            tempRow--;
            tempCol--;
            while ((player == 1 && board[tempRow][tempCol] == 2) || (player == 2 && board[tempRow][tempCol] == 1)) {
                jumpedSpaces++;
                tempRow--;
                tempCol--;
                if (board[tempRow][tempCol] == player) {
                    board[row][col] = player;
                    // backtrack through to flip the captured pieces
                    while (jumpedSpaces > 0) {
                        tempRow++;
                        tempCol++;
                        jumpedSpaces--;
                        board[tempRow][tempCol] = player;
                        capturedPieces++;
                    }
                    break;
                }
            }

            // Can't capture this way, reset tempRow
            tempRow = row;
            tempCol = col;
            jumpedSpaces = 0;
        } catch (ArrayIndexOutOfBoundsException oobe) {
            // Out of bounds, can't capture, reset temps
            tempRow = row;
            tempCol = col;
            jumpedSpaces = 0;
        }

        // will return 0 if no legal moves exist
        return capturedPieces;
    }

    /**
     * Checks if the current player has any valid move, anywhere on the board
     * 
     * @param player the current player, 1 for black, 2 for white
     * @return true if there is a valid move, false if not
     */
    public boolean moveTest(int player) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (canMove(i, j, player)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Return total number of pieces the specified player has on the board.
     * 
     * @param player the current player, 1 for black, 2 for white
     * @return
     */
    public int chipCount(int player) {
        player1ChipCount = 0;
        player2ChipCount = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == BLACK) {
                    player1ChipCount++;
                } else if (board[i][j] == WHITE) {
                    player2ChipCount++;
                }
            }
        }

        if (player == BLACK) {
            return player1ChipCount;
        } else {
            return player2ChipCount;
        }
    }

}