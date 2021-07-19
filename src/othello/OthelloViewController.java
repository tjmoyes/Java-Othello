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
    /** A tiles, default white */
    private Color tileA = Color.WHITE;
    /** B tiles, default black */
    private Color tileB = Color.BLACK;

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

    // TODO: Fix icon paths before submission
    // Icons
    /** Player 1 Icon */
    private final ImageIcon player1Icon = new ImageIcon(getClass().getResource("graphics/black.png"));
    /** Player 2 Icon */
    private final ImageIcon player2Icon = new ImageIcon(getClass().getResource("graphics/white.png"));
    /** Up Arrow Icon */
    private final ImageIcon upIcon = new ImageIcon(getClass().getResource("graphics/uparrow.png"));
    /** Down Arrow Icon */
    private final ImageIcon downIcon = new ImageIcon(getClass().getResource("graphics/downarrow.png"));
    /** Left Arrow Icon */
    private final ImageIcon leftIcon = new ImageIcon(getClass().getResource("graphics/leftarrow.png"));
    /** Right Arrow Icon */
    private final ImageIcon rightIcon = new ImageIcon(getClass().getResource("graphics/rightarrow.png"));
    /** Checkmark Icon */
    private final ImageIcon checkMarkIcon = new ImageIcon(getClass().getResource("graphics/checkmark.png"));

    private JTextField chatBarField = new JTextField();

    // Menus
    /** The Menu Bar */
    private final JMenuBar menuBar = new JMenuBar();
    /** File Menu */
    private final JMenu file = new JMenu("File");
    /** Game Menu */
    private final JMenu game = new JMenu("Game");
    /** Help Menu */
    private final JMenu help = new JMenu("Help");

    // File Menu Items
    private JMenuItem newGame = new JMenuItem("New Game");
    private JMenuItem loadGame = new JMenuItem("Load");
    private JMenuItem saveGame = new JMenuItem("Save");
    private JMenuItem exit = new JMenuItem("Exit");

    // Game Menu Items
    private JMenu boardColours = new JMenu("Board Colours");
    private JMenu debugScenarios = new JMenu("Debug Scenarios");

    // Board colours RadioMenuItems
    private JRadioButtonMenuItem normalColours = new JRadioButtonMenuItem("Normal Colours");
    private JRadioButtonMenuItem canadianColours = new JRadioButtonMenuItem("Canadian Colours");
    private JRadioButtonMenuItem customColours = new JRadioButtonMenuItem("Custom Colours");

    /** Board Colours ButtonGroup */
    private ButtonGroup colourGroup = new ButtonGroup();

    // Debug Scenarios RadioMenuItems
    private JRadioButtonMenuItem normalGame = new JRadioButtonMenuItem("Normal Game");
    private JRadioButtonMenuItem cornerTest = new JRadioButtonMenuItem("Corner Test");
    private JRadioButtonMenuItem sideTests = new JRadioButtonMenuItem("Side Tests");
    private JRadioButtonMenuItem oneXCaptureTest = new JRadioButtonMenuItem("1x Capture Test");
    private JRadioButtonMenuItem twoXCaptureTest = new JRadioButtonMenuItem("2x Capture Test");
    private JRadioButtonMenuItem emptyBoard = new JRadioButtonMenuItem("Empty Board");
    private JRadioButtonMenuItem innerSquareTest = new JRadioButtonMenuItem("Inner Square Test");
    private JRadioButtonMenuItem upArrowOrientationTest = new JRadioButtonMenuItem("Up Arrow Orientation Test");

    /** Debug Scenarios ButtonGroup */
    private ButtonGroup debugGroup = new ButtonGroup();

    // Help Menu Item
    private JMenuItem about = new JMenuItem("About");

    // Color Chooser for Custom Color
    private JColorChooser cc1;
    private JColorChooser cc2;
    private JLabel color1Preview = new JLabel("  ");
    private JLabel color2Preview = new JLabel("  ");

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
        createMenu(c);
        createBoard(c);
        createCommands(c);
        createChatInputArea(c);
        pane.add(board, BorderLayout.WEST);
        pane.add(commands, BorderLayout.EAST);
        pane.add(chatInputPanel, BorderLayout.SOUTH);
    }

    /**
     * createMenu creates and adds all elements required for the menu bar
     * 
     * @param c Controller object for action listening
     */
    private void createMenu(Controller c) {
        menuBar.add(file);
        menuBar.add(game);
        menuBar.add(help);

        // Add the action listeners
        newGame.addActionListener(c);
        exit.addActionListener(c);
        about.addActionListener(c);
        normalColours.addActionListener(c);
        canadianColours.addActionListener(c);
        customColours.addActionListener(c);
        normalGame.addActionListener(c);
        cornerTest.addActionListener(c);
        sideTests.addActionListener(c);
        oneXCaptureTest.addActionListener(c);
        twoXCaptureTest.addActionListener(c);
        emptyBoard.addActionListener(c);
        innerSquareTest.addActionListener(c);
        upArrowOrientationTest.addActionListener(c);

        // Set the action commands
        newGame.setActionCommand("New Game");
        exit.setActionCommand("Exit");
        about.setActionCommand("About");
        normalColours.setActionCommand("Normal Colours");
        canadianColours.setActionCommand("Canadian Colours");
        customColours.setActionCommand("Custom Colours");
        normalGame.setActionCommand("0");
        cornerTest.setActionCommand("1");
        sideTests.setActionCommand("2");
        oneXCaptureTest.setActionCommand("3");
        twoXCaptureTest.setActionCommand("4");
        emptyBoard.setActionCommand("5");
        innerSquareTest.setActionCommand("6");
        upArrowOrientationTest.setActionCommand("7");

        // Add to the file menu
        file.add(newGame);
        loadGame.setEnabled(false);
        saveGame.setEnabled(false);
        file.add(loadGame);
        file.add(saveGame);
        file.add(exit);

        // Submenu for Board Colours
        // Create ButtonGroup and add items to the group
        normalColours.setSelected(true);
        colourGroup.add(normalColours);
        colourGroup.add(canadianColours);
        colourGroup.add(customColours);

        // Add all the items to the board colours submenu
        boardColours.add(normalColours);
        boardColours.add(canadianColours);
        boardColours.add(customColours);

        // Submenu for Debug Scenarios
        // Create ButtonGroup and add items to the group
        normalGame.setSelected(true);
        debugGroup.add(normalGame);
        debugGroup.add(cornerTest);
        debugGroup.add(sideTests);
        debugGroup.add(oneXCaptureTest);
        debugGroup.add(twoXCaptureTest);
        debugGroup.add(emptyBoard);
        debugGroup.add(innerSquareTest);
        debugGroup.add(upArrowOrientationTest);

        // Add all the items to the debug scenarios submenu
        debugScenarios.add(normalGame);
        debugScenarios.add(cornerTest);
        debugScenarios.add(sideTests);
        debugScenarios.add(oneXCaptureTest);
        debugScenarios.add(twoXCaptureTest);
        debugScenarios.add(emptyBoard);
        debugScenarios.add(innerSquareTest);
        debugScenarios.add(upArrowOrientationTest);

        // Add the GAME menu items
        game.add(boardColours);
        game.add(debugScenarios);

        // Add the HELP menu item
        help.add(about);

        // Set the JFrame's menu bar
        super.setJMenuBar(menuBar);
    }

    /**
     * createBoard creates all elements of the Board pane, which is on the left side
     * of the UI.
     * <p>
     * Using a nested for loop, it traverses the 10x10 GridLayout and adds the
     * necessary JLabel elements
     */
    private void createBoard(Controller c) {
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
                    // Top and bottom row borders for the board
                    if (i == 0 && (j != 0 && j != 9)) {
                        columnIndicator.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
                    } else if (i == 9 && (j != 0 && j != 9)) {
                        columnIndicator.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.GRAY));
                    }

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

                        // If it's the first or last row, should add the external 1px border for the
                        // board.
                        if (j == 0)
                            rowIndicator.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.GRAY));
                        else if (j == 9)
                            rowIndicator.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.GRAY));
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

                        // Alternating the square colors
                        if ((i - 1 + j - 1) % 2 == 0) {
                            squares[i - 1][j - 1].setBackground(tileA);
                        } else {
                            squares[i - 1][j - 1].setBackground(tileB);
                        }
                        // Add the square to the board
                        board.add(squares[i - 1][j - 1]);
                    }
                }
            }
        }

        // Add Starting Tokens with the controller.
        c.addTokens();

        // Add the green border
        squares[3][3].setBorder(BorderFactory.createLineBorder(Color.GREEN, 5));

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
        JLabel player1Info = new JLabel(Integer.toString(c.getPlayerChipCount(2)), player1Icon, JLabel.CENTER);
        playerInfoArea.add(player1Info);

        JLabel player2Text = new JLabel();
        player2Text.setFont(player2Text.getFont().deriveFont(15f));
        player2Text.setText("Player 2 Pieces: ");
        playerInfoArea.add(player2Text);
        // Icon and token count label
        JLabel player2Info = new JLabel(Integer.toString(c.getPlayerChipCount(2)), player2Icon, JLabel.CENTER);
        playerInfoArea.add(player2Info);

        northSection.add(playerInfoArea, BorderLayout.EAST);

        // Chat output area fills out the remainder of the right side of the Frame
        JPanel chatOutputArea = new JPanel(new BorderLayout());
        chatOutputArea.setPreferredSize(new Dimension(450, 0));
        chatOutputArea.setBackground(blueBG);
        chatOutputArea.setBorder(BorderFactory.createMatteBorder(5, 0, 5, 0, Color.GRAY));

        JTextArea chatOutput = new JTextArea();
        chatOutput.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        chatOutput.setText("Player 1 initialized with " + c.getPlayerChipCount(1)
                + " pieces\nPlayer 2 initialized with " + c.getPlayerChipCount(2) + " pieces");
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
        chatBarField.setBackground(Color.WHITE);
        chatBarField.setEditable(true);
        chatBarField.setHorizontalAlignment(JTextField.LEFT);
        chatInputPanel.add(chatBarField, BorderLayout.WEST);

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
        private OthelloModel model = new OthelloModel();
        private boolean validMovesEnabled = false;

        /**
         * When a button or checkbox is acted upon, this method is called.
         * 
         * @param e the action event
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            String btnPressed = e.getActionCommand();
            switch (btnPressed) {
            case "New Game":
                int selectedScenario = Integer.parseInt(debugGroup.getSelection().getActionCommand());
                newGame(selectedScenario);
                break;
            case "Normal Colours":
            case "Canadian Colours":
            case "Custom Colours":
                changeColours(btnPressed);
                break;
            case "About":
                showAbout();
                break;

            case "Show Valid Moves":
                showValidMoves();
                break;

            // These buttons are inside the "Custom Colours" JOptionPane
            case "Color 1 Button":
                JOptionPane.showConfirmDialog(null, cc1, "Choose a color", JOptionPane.YES_NO_CANCEL_OPTION);
                color1Preview.setBackground(cc1.getColor());
                break;

            case "Color 2 Button":
                JOptionPane.showConfirmDialog(null, cc2, "Choose a color", JOptionPane.YES_NO_CANCEL_OPTION);
                color2Preview.setBackground(cc2.getColor());
                break;
            }
        }

        /**
         * Creates a new game based on the desired mode
         * 
         * @param mode
         */
        private void newGame(int mode) {
            System.out.println("New Game, Mode = " + mode);
            model.prepareBoard(mode);

            addTokens();
        }

        private void updateBoard() {

        }

        /**
         * Change the colours of the board
         * 
         * @param colourChoice the selected Colour option
         */
        private void changeColours(String colourChoice) {
            // The JColorChooser for Custom Colours needs a base point, so use the current
            // tileA and tileB colours.
            cc1 = new JColorChooser(tileA);
            cc2 = new JColorChooser(tileB);

            // NORMAL COLOURS means black and white tiles
            if (colourChoice == "Normal Colours") {
                tileA = Color.WHITE;
                tileB = Color.BLACK;
            }

            // CANADIAN COLOURS means white and red tiles
            else if (colourChoice == "Canadian Colours") {
                tileA = Color.WHITE;
                tileB = Color.RED;
            }

            // CUSTOM COLOURS means the user gets to select two colours using JColorChooser
            else {
                JPanel colorChoosePanel = new JPanel(new BorderLayout());
                JPanel color1ChoicePanel = new JPanel(new BorderLayout());
                JPanel color2ChoicePanel = new JPanel(new BorderLayout());
                JButton color1Btn = new JButton("Color 1");
                JButton color2Btn = new JButton("Color 2");
                color1Btn.addActionListener(this);
                color2Btn.addActionListener(this);

                color1Btn.setActionCommand("Color 1 Button");
                color2Btn.setActionCommand("Color 2 Button");
                color1Preview.setBackground(tileA);
                color2Preview.setBackground(tileB);
                color1Preview.setOpaque(true);
                color2Preview.setOpaque(true);
                color1Preview.setPreferredSize(new Dimension(50, 50));
                color2Preview.setPreferredSize(new Dimension(50, 50));

                color1ChoicePanel.add(color1Btn, BorderLayout.WEST);
                color1ChoicePanel.add(color1Preview, BorderLayout.EAST);
                color2ChoicePanel.add(color2Btn, BorderLayout.WEST);
                color2ChoicePanel.add(color2Preview, BorderLayout.EAST);

                colorChoosePanel.add(color1ChoicePanel, BorderLayout.NORTH);
                colorChoosePanel.add(color2ChoicePanel, BorderLayout.SOUTH);
                JOptionPane.showMessageDialog(null, colorChoosePanel, "Choose Colours",
                        JOptionPane.INFORMATION_MESSAGE);

                // Now that the colours have been selected, set the tileA and tileB values based
                // on their respective JColorChooser values
                tileA = cc1.getColor();
                tileB = cc2.getColor();

            }

            // Loop through the board and set the background colors for the squares
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if ((i + j) % 2 == 0)
                        squares[i][j].setBackground(tileA);
                    else
                        squares[i][j].setBackground(tileB);
                }
            }
        }

        /**
         * Adds the tokens to the board based on the values from OthelloModel's
         * getSquare method
         */
        private void addTokens() {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (model.getSquare(i, j) == 1)
                        squares[i][j].setIcon(player1Icon);
                    else if (model.getSquare(i, j) == 2)
                        squares[i][j].setIcon(player2Icon);
                    else {
                        squares[i][j].setIcon(null);
                    }
                }
            }
        }

        /**
         * Shows the JOptionPane when the About button is selected
         */
        private void showAbout() {
            JOptionPane.showMessageDialog(null, "Othello Game\nby Tyson Moyes\n\nJuly 2021", "About",
                    JOptionPane.INFORMATION_MESSAGE);
        }

        private int getPlayerChipCount(int playerNum) {
            if (playerNum == 1) {
                return model.chipCount(1);
            } else {
                return model.chipCount(2);
            }
        }

        private void showValidMoves() {
            if (validMovesEnabled) {
                validMovesEnabled = false;
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (squares[i][j].getIcon() == checkMarkIcon) {
                            squares[i][j].setIcon(null);
                        }
                    }
                }

            } else {
                validMovesEnabled = true;
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (model.canMove(i, j, model.getCurrentPlayer()))
                            squares[i][j].setIcon(checkMarkIcon);
                    }
                }
            }
        }
    }
}