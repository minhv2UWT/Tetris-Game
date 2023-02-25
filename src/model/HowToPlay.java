/*
 * TCSS 305 – Winter 2023
 * Tetris Project – Sprint 1
 */
package model;

import java.io.Serial;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * This class creates the content within the "How to Play" tab in the MenuGUI.
 * @author Simran Narwal
 * @author Jack Chen
 * @author Koji Yoshiyama
 * @author Minh Vu
 * @version Winter 2023
 */

public class HowToPlay extends JFrame {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * This constant was made to avoid magic numbers.
     */
    private static final int MAGIC_NUMBER = 450;

    /**
     * This method is a constructor that calls the createHowToPlay() method.
     */
    public HowToPlay() {
        super();
        createHowToPlay();
    }

    /**
     * This method creates a window of text, displaying the instructions on
     * how to play Tetris.
     */
    private void createHowToPlay() {
        final JFrame frame = new JFrame();

        final JTextArea text = new JTextArea("Instructions to Play!");
        text.append(System.lineSeparator());
        text.append("1.      The pieces will automatically come down");
        text.append(System.lineSeparator());
        text.append("2.      To bring piece down faster, click the "
                + "down key on your keyboard");
        text.append(System.lineSeparator());
        text.append("3.      Fit the pieces so they all fill the bottom row");
        text.append(System.lineSeparator());
        text.append("4.      Once the bottom row is filled, "
                + "your score will increase!");

        text.setBounds(0, 0, MAGIC_NUMBER, MAGIC_NUMBER);
        frame.add(text);
        frame.setSize(MAGIC_NUMBER, MAGIC_NUMBER);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
