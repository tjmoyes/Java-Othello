/*
 * File Name:   OthelloSplashScreen.java
 * Author:      Tyson Moyes
 * Assignment:  Assignment 1 Part 1
 * Date:        June 26th 2021
 * Professor:   Daniel Cormier
 * Purpose:     This file contains the OthelloSplashScreen class and all the code required to load the Splash Screen at the start of the program.
 */

package othello;

import javax.swing.*;
import java.awt.*;

/**
 * OthelloSplashScreen contains all the code required to display the splash
 * screen for the Othello program
 * 
 * @author Tyson Moyes (and Svillen Ranev and Daniel Cormier, from Lab 2 code)
 * @version 1.2
 * @since 1.8.0_291
 */
public class OthelloSplashScreen extends JWindow {
    /** Splash screen duration */
    private final int duration;
    /** Serial version ID generated from serialver */
    private static final long serialVersionUID = 9054548635007527755L;

    /**
     * Constructor for OthelloSplashScreen.
     * <p>
     * Set the duration of the splash screen
     * 
     * @param duration length of time splash screen is displayed.
     */
    public OthelloSplashScreen(int duration) {
        this.duration = duration;
    }

    /**
     * showSplashWindow creates and displays the splash screen
     */
    public void showSplashWindow() {
        JPanel content = new JPanel(new BorderLayout());

        content.setBackground(Color.GRAY);

        // image dimension is 775 x 125. Add 10 for padding
        int width = 775 + 10;
        int height = 125 + 10;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;
        setBounds(x, y, width, height);

        // Get the splash screen
        JLabel label = new JLabel(new ImageIcon(getClass().getResource("/graphics/splashscreen.png")));
        // add splash screen to the Panel
        content.add(label, BorderLayout.CENTER);

        setContentPane(content);

        setVisible(true);

        try {
            // This is where I will put the code when we are actually loading the program, I
            // presume. For now, sleep.
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            // something has gone wrong, bad bad bad
            System.err.println("Error with splash screen load area");
            e.printStackTrace();
        }

        // destroy the Splash Screen now that the program has loaded
        dispose();
    }
}
