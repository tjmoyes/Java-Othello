/*
 * File Name:   OthelloViewController.java
 * Author:      Tyson Moyes
 * Assignment:  Assignment 1 Part 1
 * Date:        June 26th 2021
 * Professor:   Daniel Cormier
 * Purpose:     OthelloViewController.java contains the OthelloViewController and Controller inner class.
 *              These contain all the UI element creation and action handling for the Othello game. 
 * Class List:  OthelloViewController
 *              Controller (inner class)
 */

package othello;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * OthelloViewController contains all the code for creating the UI elements and
 * adding them to the program's Frame. It also has an inner class
 * {@link Controller} for handling the action events from the buttons and the
 * checkbox.
 * 
 * @author Tyson Moyes
 * @version 1.2
 * @since 1.8.0_29
 */
public class OthelloViewController extends JFrame {
    /****************************************************************************************
     * VARIABLES
     ***************************************************************************************/
    /** Serial version UID generated from serialver */
    private static final long serialVersionUID = 3646672638732074113L;
    /** Text for the titlebar */
    private static final String FRAME_TITLE = "Tyson Moyes' Othello Client";

    // Board variables
    /** 2-dimensional array for the board squares */
    private JLabel[][] squares = new JLabel[8][8];
    /** Dimension for the board squares */
    private final Dimension squareSize = new Dimension(60, 60);
    /** Char array for the row indicators, used in createBoard() */
    private final char[] ROWS = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H' };

    // Colors
    /** For the Grey BG Areas */
    private final Color greyBG = new Color(220, 220, 220);
    /** For the Blue BG Areas */
    private final Color blueBG = new Color(175, 175, 255);
    /** White tiles */
    private final Color whiteTile = Color.WHITE;
    /** Black tiles */
    private final Color blackTile = Color.BLACK;

    // Buttons
    private JButton up, down, left, right, move, submit;
    /** Dimension for the command buttons (up, down, left, right, move) */
    private final Dimension commandBtnSize = new Dimension(40, 40);
    // Checkbox
    private final JCheckBox showValidMoves = new JCheckBox("Show Valid Moves");

    // UI panels
    /** Main UI Pane to which all UI elements will be appended */
    private final JPanel pane = new JPanel(new BorderLayout());
    /** Board UI pane */
    private final JPanel board = new JPanel(new GridLayout(10, 10));
    /** commands UI Pane */
    private final JPanel commands = new JPanel(new BorderLayout());
    /** Chat input Pane */
    private final JPanel chatInputPanel = new JPanel(new BorderLayout());

    // Icons
    /** Player 1 Icon */
    private final ImageIcon player1Icon = new ImageIcon(getClass().getResource("/graphics/black.png"));
    /** Player 2 Icon */
    private final ImageIcon player2Icon = new ImageIcon(getClass().getResource("/graphics/white.png"));
    /** Up Arrow Icon */
    private final ImageIcon upIcon = new ImageIcon(getClass().getResource("/graphics/uparrow.png"));
    /** Down Arrow Icon */
    private final ImageIcon downIcon = new ImageIcon(getClass().getResource("/graphics/downarrow.png"));
    /** Left Arrow Icon */
    private final ImageIcon leftIcon = new ImageIcon(getClass().getResource("/graphics/leftarrow.png"));
    /** Right Arrow Icon */
    private final ImageIcon rightIcon = new ImageIcon(getClass().getResource("/graphics/rightarrow.png"));

    /****************************************************************************************
     * METHODS
     ***************************************************************************************/

    /**
     * OthelloViewController constructor creates the View Controller
     * <p>
     * Calls the super constructor, calls the {@link #createGUI} method and adds to
     * the frame
     */
    public OthelloViewController() {
        super(FRAME_TITLE);
        createGUI();

        super.add(pane);
        super.setContentPane(pane);
        super.pack();
    }

    /**
     * createGUI calls the methods to create each section of the UI, then adds them
     * to the pane.
     * 
     * @see #createBoard
     * @see #createCommands
     * @see #createChatInputArea
     */
    private void createGUI() {
        // Create an instance of the Controller inner class that we can pass to the
        // createCommands and createChatInputArea methods for action handling
        Controller c = new Controller();
        createBoard();
        createCommands(c);
        createChatInputArea(c);
        pane.add(board, BorderLayout.WEST);
        pane.add(commands, BorderLayout.EAST);
        pane.add(chatInputPanel, BorderLayout.SOUTH);
    }

    /**
     * createBoard creates all elements of the Board pane, which is on the left side
     * of the UI.
     * <p>
     * Using a nested for loop, it traverses the 10x10 GridLayout and adds the
     * necessary JLabel elements
     */
    private void createBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                // Header or footer row? Should have grey BG
                if (i == 0 || i == 9) {
                    JLabel columnIndicator = new JLabel();
                    columnIndicator.setOpaque(true);
                    columnIndicator.setBackground(greyBG);
                    columnIndicator.setHorizontalAlignment(JLabel.CENTER);
                    columnIndicator.setVerticalAlignment(JLabel.CENTER);
                    columnIndicator.setFont(columnIndicator.getFont().deriveFont(20f));

                    // Check the current column loop index. If not the first or last column in the
                    // row, the
                    // square should have the column number printed to it
                    if (j != 0 && j != 9) {
                        columnIndicator.setText("" + j);
                    }
                    // Add to the board
                    board.add(columnIndicator);
                }

                // Not a header or footer row
                else {
                    // is it the first or last column? These should have the row letters printed,
                    // with a grey background
                    if (j == 0 || j == 9) {
                        JLabel rowIndicator = new JLabel();
                        rowIndicator.setOpaque(true);
                        rowIndicator.setBackground(greyBG);
                        rowIndicator.setHorizontalAlignment(JLabel.CENTER);
                        rowIndicator.setVerticalAlignment(JLabel.CENTER);
                        rowIndicator.setFont(rowIndicator.getFont().deriveFont(20f));
                        rowIndicator.setText("" + ROWS[i - 1]);
                        board.add(rowIndicator);
                    }

                    // This is the actual board, this should be alternating white & black tiles
                    else {

                        // since I'm using a nested loop that goes to 10 (GridLayout size), but squares
                        // array is only 8x8, I have to decrement i and j by 1 for each square of the
                        // board
                        squares[i - 1][j - 1] = new JLabel();
                        squares[i - 1][j - 1].setOpaque(true);
                        squares[i - 1][j - 1].setPreferredSize(squareSize);
                        squares[i - 1][j - 1].setHorizontalAlignment(JLabel.CENTER);
                        squares[i - 1][j - 1].setVerticalAlignment(JLabel.CENTER);

                        // Border logic
                        if (i - 1 == 0) {
                            // Header row
                            if (j - 1 == 0) {
                                // Header row, first column. Border should be on left & top
                                squares[i - 1][j - 1]
                                        .setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, Color.GRAY));
                            } else if (j - 1 == 7) {
                                // Header row, last column. Border should be on right & top
                                squares[i - 1][j - 1]
                                        .setBorder(BorderFactory.createMatteBorder(1, 0, 0, 1, Color.GRAY));
                            } else {
                                // Header row, middle columns. Border should only be on top
                                squares[i - 1][j - 1]
                                        .setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.GRAY));
                            }
                        } else if (i - 1 == 7) {
                            // Footer Row
                            if (j - 1 == 0) {
                                // Footer row, first column. Border should be on left & bottom
                                squares[i - 1][j - 1]
                                        .setBorder(BorderFactory.createMatteBorder(0, 1, 1, 0, Color.GRAY));
                            } else if (j - 1 == 7) {
                                // Footer row, last column. Border should be on right & bottom
                                squares[i - 1][j - 1]
                                        .setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.GRAY));
                            } else {
                                // Footer row, middle columns. Border should only be on bottom
                                squares[i - 1][j - 1]
                                        .setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
                            }
                        } else {
                            // Middle rows
                            if (j - 1 == 0) {
                                // Middle rows, left column. Border should only be on the left
                                squares[i - 1][j - 1]
                                        .setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.GRAY));
                            } else if (j - 1 == 7) {
                                // Middle rows, right column. Border should only be on the right.
                                squares[i - 1][j - 1]
                                        .setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.GRAY));
                            } // Middle row middle columns have no border
                        }

                        // Alternating the square colors
                        if ((i - 1 + j - 1) % 2 == 0) {
                            squares[i - 1][j - 1].setBackground(whiteTile);
                        } else {
                            squares[i - 1][j - 1].setBackground(blackTile);
                        }
                        // Add the square to the board
                        board.add(squares[i - 1][j - 1]);
                    }
                }
            }
        }

        // We need to add the starting tokens. Black has 2 tokens at index [3,4] (D5)
        // and [4,3] (E4). Black has 2 tokens at index [3,3] (D4) and [4,4] (E5)
        squares[3][4].setIcon(player1Icon);
        squares[4][3].setIcon(player1Icon);

        squares[3][3].setIcon(player2Icon);
        squares[4][4].setIcon(player2Icon);

        // Finally, add the thick border
        board.setBorder(BorderFactory.createLineBorder(Color.GRAY, 5));
    }

    /**
     * createCommands creates all the elements on the right-hand side of the
     * program.
     * 
     * @param c the Controller object created in {@link #createGUI}
     */
    private void createCommands(Controller c) {
        // Set the background for the whole area
        commands.setBackground(greyBG);

        JPanel northSection = new JPanel(new BorderLayout());
        northSection.setBackground(greyBG);
        // Panel for the northmost part of this, contains the Show Valid Moves checkbox
        JPanel validMovesPanel = new JPanel(new BorderLayout());
        showValidMoves.setActionCommand("Show Valid Moves");
        showValidMoves.addActionListener(c);
        validMovesPanel.add(showValidMoves);
        validMovesPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, Color.GRAY));
        northSection.add(validMovesPanel, BorderLayout.NORTH);

        // Create the movement buttons and add them to a gridlayout
        JPanel movementArea = new JPanel(new GridLayout(3, 3, 3, 3));
        movementArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        movementArea.setBackground(greyBG);

        up = new JButton();
        up.setBackground(Color.WHITE);
        up.setPreferredSize(commandBtnSize);
        up.setIcon(upIcon);
        up.addActionListener(c);
        up.setActionCommand("up");

        down = new JButton();
        down.setBackground(Color.WHITE);
        down.setPreferredSize(commandBtnSize);
        down.setIcon(downIcon);
        down.addActionListener(c);
        down.setActionCommand("down");

        left = new JButton();
        left.setBackground(Color.WHITE);
        left.setPreferredSize(commandBtnSize);
        left.setIcon(leftIcon);
        left.addActionListener(c);
        left.setActionCommand("left");

        right = new JButton();
        right.setBackground(Color.WHITE);
        right.setPreferredSize(commandBtnSize);
        right.setIcon(rightIcon);
        right.addActionListener(c);
        right.setActionCommand("right");

        move = new JButton("move");
        move.setBackground(Color.WHITE);
        move.setFont(move.getFont().deriveFont(10f));
        move.setMargin(new Insets(0, 0, 0, 0));
        move.setPreferredSize(commandBtnSize);
        move.addActionListener(c);
        move.setActionCommand("move");

        movementArea.add(new JLabel());
        movementArea.add(up);
        movementArea.add(new JLabel());
        movementArea.add(left);
        movementArea.add(move);
        movementArea.add(right);
        movementArea.add(new JLabel());
        movementArea.add(down);
        movementArea.add(new JLabel());

        northSection.add(movementArea, BorderLayout.WEST);

        // The Player Info area displays each player's token icon and number of tokens
        // (or at least it will)
        JPanel playerInfoArea = new JPanel(new GridLayout(2, 2));
        playerInfoArea.setBackground(greyBG);
        playerInfoArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JLabel player1Text = new JLabel();
        player1Text.setFont(player1Text.getFont().deriveFont(15f));
        player1Text.setText("Player 1 Pieces: ");
        playerInfoArea.add(player1Text);
        // This is the actual icon and token count.
        JLabel player1Info = new JLabel("2", player1Icon, JLabel.CENTER);
        playerInfoArea.add(player1Info);

        JLabel player2Text = new JLabel();
        player2Text.setFont(player2Text.getFont().deriveFont(15f));
        player2Text.setText("Player 2 Pieces: ");
        playerInfoArea.add(player2Text);
        // Icon and token count label
        JLabel player2Info = new JLabel("2", player2Icon, JLabel.CENTER);
        playerInfoArea.add(player2Info);

        northSection.add(playerInfoArea, BorderLayout.EAST);

        // Chat output area fills out the remainder of the right side of the Frame
        JPanel chatOutputArea = new JPanel(new BorderLayout());
        chatOutputArea.setPreferredSize(new Dimension(450, 0));
        chatOutputArea.setBackground(blueBG);
        chatOutputArea.setBorder(BorderFactory.createMatteBorder(5, 0, 5, 0, Color.GRAY));

        JTextArea chatOutput = new JTextArea();
        chatOutput.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        // TODO: Will need to update this with proper piece initialized count
        chatOutput.setText("Player 1 initialized with 2 pieces\nPlayer 2 initialized with 2 pieces");
        chatOutput.setOpaque(false);
        chatOutput.setWrapStyleWord(true);
        chatOutput.setEditable(false);
        chatOutputArea.add(chatOutput, BorderLayout.CENTER);

        commands.add(northSection, BorderLayout.NORTH);
        commands.add(chatOutputArea, BorderLayout.CENTER);

        commands.setBorder(BorderFactory.createMatteBorder(5, 0, 0, 5, Color.GRAY));
    }

    /**
     * createChatInputArea creates the text field and submit button and adds them to
     * the chat Panel
     * 
     * @param c The Controller object created in {@link #createGUI}
     */
    private void createChatInputArea(Controller c) {
        // Create a text field and add it to the left-side of the chat Panel
        JTextField textField = new JTextField();
        textField.setBackground(Color.WHITE);
        // I'm not sure exactly what I'm missing here, but the text field isn't editable
        // for me
        textField.setEditable(true);
        textField.setHorizontalAlignment(JTextField.LEFT);
        chatInputPanel.add(textField, BorderLayout.WEST);

        // Create a submit button with black background and red text and add it to the
        // right-side of the chat Panel
        submit = new JButton("Submit");
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.RED);
        submit.addActionListener(c);
        submit.setActionCommand("submit");
        chatInputPanel.add(submit, BorderLayout.EAST);

        // Add the border to the left, right, and bottom
        chatInputPanel.setBorder(BorderFactory.createMatteBorder(0, 5, 5, 5, Color.GRAY));
    }

    /****************************************************************************************
     * INNER CLASS
     ***************************************************************************************/

    /**
     * Controller implements ActionListener and handles the action events from the
     * buttons created by {@link OthelloViewController}
     */
    private class Controller implements ActionListener {
        /**
         * When a button or checkbox is acted upon, this method is called.
         * 
         * @param e the action event
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            String btnPressed = e.getActionCommand();
            System.out.println(btnPressed);
        }
    }
}