/*
 * TCSS 305 – Winter 2023
 * Tetris Project – Sprint 1
 */
package model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.Serial;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class creates the main display that includes the
 * game board, next piece region and the scoreboard.
 *
 * @author Simran Narwal
 * @author Jack Chen
 * @author Koji Yoshiyama
 * @author Minh Vu
 * @version Winter 2023
 */

public class GameBoardGUI extends JFrame {

    /**
     * Still unsure if this is required, but added just in case.
     */
    @Serial
    private static final long serialVersionUID = 54048875904583676L;

    /**
     * This constant is the most used dimension value for panels.
     */
    private static final int DIMENSION_100 = 100;

    /**
     * This constant is a defining dimension value.
     */
    private static final int DIMENSION_175 = 175;

    /**
     * This constant is another defining dimension value.
     */
    private static final int DIMENSION_20 = 20;

    /**
     * This is a constructor that calls the createLayout() method.
     */
    public GameBoardGUI() {
    }

//    public static void main(final String[] theArgs) {
//        final JFrame layout = new JFrame();
//        layout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        layout.setSize(500, 500);
//        layout.setLayout(new BorderLayout(10, 10));
//        layout.setResizable(false);
//        createLayout(layout);
//        layout.setVisible(true);
//    }

    /**
     * This method creates the JPanel game board GUI.
     */
    public void createLayout() {
        // creating the panels
        final JPanel panel1 = new JPanel();
        final JPanel panel2 = new JPanel();
        final JPanel panel3 = new JPanel();
        final JPanel panel4 = new JPanel();
        final JPanel panel5 = new JPanel();
        final JPanel panel6 = new JPanel();
// labeling each panel to know which panel is meant for what
        final JLabel tetrisLabel = new JLabel("TETRIS");
        panel2.add(tetrisLabel);
        final JLabel footerLabel = new JLabel("Footer");
        panel3.add(footerLabel);
        final JLabel gamePanelLabel = new JLabel("Game Panel");
        panel4.add(gamePanelLabel);
        final JLabel nextPieceLabel = new JLabel("Next Piece Panel");
        panel5.add(nextPieceLabel);
        final JLabel gameInfoLabel = new JLabel("Game Controls");
        panel6.add(gameInfoLabel);

        // changing the color of each panel as per assignment specification
        panel1.setBackground(Color.gray);
        panel2.setBackground(Color.gray);
        panel3.setBackground(Color.gray);
        panel4.setBackground(Color.red);
        panel5.setBackground(Color.blue);
        panel6.setBackground(Color.green);

        // setting panel sizes
        panel1.setPreferredSize(new Dimension(DIMENSION_175, DIMENSION_100));
        panel2.setPreferredSize(new Dimension(DIMENSION_100, DIMENSION_100));
        panel3.setPreferredSize(new Dimension(DIMENSION_100, DIMENSION_20));
        panel4.setPreferredSize(new Dimension(DIMENSION_100, DIMENSION_100));
        panel5.setPreferredSize(new Dimension(DIMENSION_100, DIMENSION_100));
        panel6.setPreferredSize(new Dimension(DIMENSION_100, DIMENSION_100));

        // adding panels to the JFrame
        this.add(panel1, BorderLayout.EAST);
        this.add(panel2, BorderLayout.NORTH);
        this.add(panel3, BorderLayout.SOUTH);
        this.add(panel4, BorderLayout.CENTER);

        // creating a nested panel for next piece/controls
        panel1.setLayout(new GridLayout(2, 1));
        panel1.add(panel5);
        panel1.add(panel6);
    }
}
