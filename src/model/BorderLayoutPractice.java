
package model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BorderLayoutPractice extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 54048875904583676L;

    JFrame frame;

    public BorderLayoutPractice() {

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

    public void createLayout() {
//
//        // importing the tetris game font
//        Font font = null;
//        try {
//            font = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/emulogic.ttf"));
//        }
//        catch (final IOException | FontFormatException e) {
//            e.printStackTrace();
//        }
//
//        // creating a large font and a small font
//        final Font headerFont;
//        final Font smallFont;
//        if (font != null) {
//            headerFont = font.deriveFont(Font.BOLD).deriveFont(60f);
//            smallFont = font.deriveFont(Font.BOLD).deriveFont(10f);
//        }
//        else {
//            headerFont = null;
//            smallFont = null;
//        }

        // creating the panels
        final JPanel panel1 = new JPanel();
        final JPanel panel2 = new JPanel();
        final JPanel panel3 = new JPanel();
        final JPanel panel4 = new JPanel();
        final JPanel panel5 = new JPanel();
        final JPanel panel6 = new JPanel();
// labeling each panel to know which panel is meant for what
        final JLabel tetrisLabel = new JLabel("TETRIS");
//        tetrisLabel.setFont(headerFont);
        panel2.add(tetrisLabel);
        final JLabel footerLabel = new JLabel("Footer");
//        footerLabel.setFont(smallFont);
        panel3.add(footerLabel);
        final JLabel gamePanelLabel = new JLabel("Game Panel");
//        gamePanelLabel.setFont(smallFont);
        panel4.add(gamePanelLabel);
        final JLabel nextPieceLabel = new JLabel("Next Piece Panel");
//        nextPieceLabel.setFont(smallFont);
        panel5.add(nextPieceLabel);
        final JLabel gameInfoLabel = new JLabel("Game Controls");
//        gameInfoLabel.setFont(smallFont);
        panel6.add(gameInfoLabel);

        // changing the color of each panel as per assignment specification
        panel1.setBackground(Color.gray);
        panel2.setBackground(Color.gray);
        panel3.setBackground(Color.gray);
        panel4.setBackground(Color.red);
        panel5.setBackground(Color.blue);
        panel6.setBackground(Color.green);

        // setting panel sizes
        panel1.setPreferredSize(new Dimension(175, 100));
        panel2.setPreferredSize(new Dimension(100, 100));
        panel3.setPreferredSize(new Dimension(150, 20));
        panel4.setPreferredSize(new Dimension(100, 25));
        panel5.setPreferredSize(new Dimension(100, 175));
        panel6.setPreferredSize(new Dimension(100, 175));

        // adding panels to the Jframe
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
