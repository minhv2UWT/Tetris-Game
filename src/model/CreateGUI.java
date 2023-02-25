/*
 * TCSS 305 – Winter 2023
 * Tetris Project – Sprint 1
 */
package model;

import javax.swing.JFrame;
import java.io.Serial;

/**
 * This class only houses a main method that starts the program.
 *
 * @author Simran Narwal
 * @author Jack Chen
 * @author Koji Yoshiyama
 * @author Minh Vu
 * @version Winter 2023
 */


public class CreateGUI extends JFrame {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * This main method kick-starts the whole program.
     * @param theArgs an array of strings representing
     * the command-line arguments
     */
    public static void main(final String[] theArgs) {
        // MenuGUI creates the menu and instantiates a gameBoardGUI object.
        // After the menu is created, it is put into the GameBoardGUI
        // via frame.setJMenuBar(menuBar);
        new MenuGUI();
    }
}
