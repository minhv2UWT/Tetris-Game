package view;

import model.Block;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedList;
import java.util.List;
import static model.CreateBoard.PROPERTY_FROZEN_BLOCKS;

public class ScorePanel extends JPanel implements PropertyChangeListener {

    int score;
    int level = 1;
    int myLines;
    private List<Block[]> myBlocksOnBoard;
    private final List<Block[]> myFrozenBlocks;


//int nextLevel;

    public ScorePanel() {
        this.setBackground(Color.darkGray);
        setUpComponent();
        myFrozenBlocks = new LinkedList<>();
    }

    private void setUpComponent() {
        JLabel scoreText = new JLabel();
        this.add(scoreText);
        scoreText.setText("Current Score: " + score);
        scoreText.setForeground(Color.WHITE);
        scoreText.setFont(new Font("Arial", Font.BOLD, 12));


        JLabel currentLevel = new JLabel();
        currentLevel.setPreferredSize(new Dimension(100, 100));
        this.add(currentLevel);
        currentLevel.setText("Current Level: " + getLevel());
        currentLevel.setForeground(Color.WHITE);
        currentLevel.setFont(new Font("Arial", Font.PLAIN, 12));


        JLabel upLevel = new JLabel();
        this.add(upLevel);
        upLevel.setText("Lines Till Next Level: ");
        upLevel.setForeground(Color.WHITE);
        upLevel.setFont(new Font("Arial", Font.PLAIN, 12));


        JLabel linesCleared = new JLabel();
        linesCleared.setPreferredSize(new Dimension(100, 100));
        this.add(linesCleared);
        linesCleared.setText("Lines Cleared: " + getLines());
        linesCleared.setForeground(Color.WHITE);
        linesCleared.setFont(new Font("Arial", Font.PLAIN, 12));
    }

    public int getLevel() {
        return level;
    }

    public int getLines() {
        return myLines;
    }

    @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if (PROPERTY_FROZEN_BLOCKS.equals(evt.getPropertyName())) {
                myBlocksOnBoard = (List<Block[]>) evt.getNewValue();
                repaint();
                if (myBlocksOnBoard.size() == 1) {
                    score += 40 * level;
                }
            }
    }
}


