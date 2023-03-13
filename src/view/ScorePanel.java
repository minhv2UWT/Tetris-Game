/*
 * TCSS 305 – Winter 2023
 * Tetris Project – Sprint 1
 */
package view;

import static model.CreateBoard.FROZEN_BLOCKS;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Block;

/**
 * This class creates the game board where Tetris is played.
 * @author Koji Yoshiyama
 * @author Minh Vu
 * @author Simran Narwal
 * @author Jack Chen
 * @version Winter 2023
 */

public class ScorePanel extends JPanel implements PropertyChangeListener {

    /**
     * This constant is made to satisfy checkstyle.
     */
    private static final String ARIAL = "Arial";

    /**
     * This constant is made to avoid magic numbers.
     */
    private static final int MAGIC_12 = 12;

    /**
     * This constant is made to avoid magic numbers.
     */
    private static final int MAGIC_100 = 100;

    /**
     * This constant is made to avoid magic numbers.
     */
    private static final int MAGIC_10 = 10;

    /**
     * This field stores the user's current score.
     */
    private int myScore;

    /**
     * This field stores the current level in the game.
     */
    private int myLevel;

    /**
     * This field stores the amount of lines that have been cleared.
     */
    private int myLines;

    /**
     * This method is the constructor for ScorePanel.
     */
    public ScorePanel() {
        this.setBackground(Color.darkGray);
        setUpComponent();
    }

    /**
     * This method creates the visual aspect of the score panel.
     */
    private void setUpComponent() {
        final JLabel scoreText = new JLabel();
        this.add(scoreText);
        scoreText.setText("Current Score: " + myScore);
        scoreText.setForeground(Color.WHITE);
        scoreText.setFont(new Font(ARIAL, Font.BOLD, MAGIC_12));
        final JLabel currentLevel = new JLabel();
        currentLevel.setPreferredSize(new Dimension(MAGIC_100, MAGIC_100));
        this.add(currentLevel);
        currentLevel.setText("Current Level: " + getLevel());
        currentLevel.setForeground(Color.WHITE);
        currentLevel.setFont(new Font(ARIAL, Font.PLAIN, MAGIC_12));
        final JLabel upLevel = new JLabel();
        this.add(upLevel);
        upLevel.setText("Lines Till Next Level: ");
        upLevel.setForeground(Color.WHITE);
        upLevel.setFont(new Font(ARIAL, Font.PLAIN, MAGIC_12));
        final JLabel linesCleared = new JLabel();
        linesCleared.setPreferredSize(new Dimension(MAGIC_100, MAGIC_100));
        this.add(linesCleared);
        linesCleared.setText("Lines Cleared: " + getLines());
        linesCleared.setForeground(Color.WHITE);
        linesCleared.setFont(new Font(ARIAL, Font.PLAIN, MAGIC_12));
    }

    /**
     * This method is a getter for the current level.
     * @return int of the current level.
     */
    public int getLevel() {
        return myLevel;
    }

    /**
     * This method is a getter for the lines.
     * @return int of the current lines.
     */
    public int getLines() {
        return myLines;
    }

    /**
     * This method gets property changes that are used to update the score panel.
     * @param theEvt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvt) {
        if (theEvt.getPropertyName().equals(FROZEN_BLOCKS)) {
            final List<Block[]> frozenBlocks = (List<Block[]>) theEvt.getNewValue();

            for (final Block[] frozenBlock : frozenBlocks) {
                boolean checkRow = true;
                int blockClear = 0;
                int rowClear = 0;
                for (Block block : frozenBlock) {
                    if (block != null) {
                        checkRow = false;
                        break;
                    }
                    blockClear++;
                    if (blockClear == MAGIC_10) {
                        rowClear++;
                    }
                }
            }
            myScore++;

        }

    }
}