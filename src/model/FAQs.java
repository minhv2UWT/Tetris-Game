/*
 * TCSS 305 – Winter 2023
 * Tetris Project – Sprint 1
 */
package model;

import java.io.Serial;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * This class creates the content within the "FAQ" tab in the MenuGUI.
 *
 * @author Simran Narwal
 * @author Jack Chen
 * @author Koji Yoshiyama
 * @author Minh Vu
 * @version Winter 2023
 */

public class FAQs extends JFrame {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * This constant is made to avoid magic numbers.
     */
    private static final int BOUNDS_SIZE = 600;

    /**
     * This constant is made to avoid magic numbers.
     */
    private static final int SIZE_WIDTH = 500;

    /**
     * This constant is made to avoid magic numbers.
     */
    private static final int SIZE_HEIGHT = 450;

    /**
     * This method is a constructor that calls the
     * createFrequentlyAskedQuestions() method.
     */
    FAQs() {
        createFrequentlyAskedQuestions();
    }

    /**
     * This method creates a window to answer a series of FAQs.
     */
    public void createFrequentlyAskedQuestions() {
        final JFrame frame = new JFrame();
        final JTextArea text = new JTextArea("Frequently Asked Questions");
        text.append(System.lineSeparator());
        text.append(System.lineSeparator());
        text.append("1.");
        text.append(System.lineSeparator());
        text.append(" Question: Why play?");
        text.append(System.lineSeparator());
        text.append(" Answer: IT'S THE BEST GAME EVER!");
        text.append(System.lineSeparator());
        text.append(System.lineSeparator());
        text.append("2.");
        text.append(System.lineSeparator());
        text.append(" Question: When was the first game of Tetris created?");
        text.append(System.lineSeparator());
        text.append(" Answer: 1984!");
        text.append(System.lineSeparator());
        text.append(System.lineSeparator());
        text.append("3.");
        text.append(System.lineSeparator());
        text.append(" Question: My pieces won't fall?");
        text.append(System.lineSeparator());
        text.append(" Answer: There may be a chance there "
                + "is a lag in the system."
                + " Try restarting!");
        text.append(System.lineSeparator());
        text.append(System.lineSeparator());
        text.append("4.");
        text.append(System.lineSeparator());
        text.append(" Question: Can I change the "
                + "background color of my screen?");
        text.append(System.lineSeparator());
        text.append(" Answer: Unfortunately,we are unable to support that"
                + " request at this time!");
        text.append(System.lineSeparator());
        text.append(System.lineSeparator());
        text.append("5.");
        text.append(System.lineSeparator());
        text.append(" Question: Can I play with other players?");
        text.append(System.lineSeparator());
        text.append(" Answer: Unfortunately,this game of Tetris is"
                + " to be played individually!");
        text.append(System.lineSeparator());
        text.append(System.lineSeparator());
        text.append("Is your question not here?");
        text.append(System.lineSeparator());
        text.append("Try searching the web. Good Luck :)");

        text.setBounds(0, 0, BOUNDS_SIZE, BOUNDS_SIZE);
        frame.add(text);

        frame.setSize(SIZE_WIDTH, SIZE_HEIGHT);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
