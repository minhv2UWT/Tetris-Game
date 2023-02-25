/*
 * TCSS 305 – Winter 2023
 * Tetris Project – Sprint 1
 */
package model;


import java.io.Serial;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * This class creates the functionality within the "Exit" tab in the MenuGUI.
 * @author Simran Narwal
 * @author Jack Chen
 * @author Koji Yoshiyama
 * @author Minh Vu
 * @version Winter 2023
 */

public class ExitOption extends JFrame {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * This method is a constructor that calls the createExitOption() method.
     */
     public ExitOption() {
        super();
        createExitOption();
    }

    /**
     * This method creates a dialog console to confirm if the user wishes to
     * exit the game.
     */

    private void createExitOption() {
        final JFrame frame = new JFrame();
        frame.setVisible(true);

        final int selection = JOptionPane.showConfirmDialog(frame,
                "Are you sure you want to "
                        + "exit?", "Confirm", JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (selection == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
