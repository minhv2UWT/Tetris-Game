package model;

import javax.swing.JFrame;

public class CreateGUI extends JFrame {

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
