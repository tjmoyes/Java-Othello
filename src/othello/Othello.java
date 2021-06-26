/*
 * File Name:   Othello.java
 * Author:      Tyson Moyes
 * Assignment:  Assignment 1 Part 1
 * Date:        June 22nd 2021
 * Professor:   Daniel Cormier
 * Purpose:     Driver for the Othello program. Contains the main method which creates a new OthelloSplashScreen and displays it.
 */

package othello;

import javax.swing.*;
import java.awt.*;

/**
 * Othello is the driver class for the program.
 * 
 * @author Tyson Moyes
 * @version 1.0
 * @since 1.8.0_291
 */
public class Othello {

    /**
     * Main method. Run the program, create a Splash screen and call its
     * showSplashWindow method
     * 
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        int duration = 5000;

        OthelloSplashScreen splash = new OthelloSplashScreen(duration);
        splash.showSplashWindow();

        // Run the OVC
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                OthelloViewController vc = new OthelloViewController();
                vc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                vc.setResizable(false);

                vc.setLocationRelativeTo(null);

                vc.setVisible(true);
            }
        });
    }
}
