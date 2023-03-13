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

    private int myScore;
    private int myLevel;
    private int myLines;
    private final List<Block[]> myFrozenBlocks;
    private JLabel scoreText;
    private JLabel currentLevel;
    private JLabel upLevel;
    private JLabel linesCleared;
    public ScorePanel() {
        this.setBackground(Color.darkGray);
        setUpComponent();
        myFrozenBlocks = new LinkedList<Block[]>();
    }

    private void setUpComponent() {
        scoreText = new JLabel();
        this.add(scoreText);
        scoreText.setText("Current Score: " + myScore);
        scoreText.setForeground(Color.WHITE);
        scoreText.setFont(new Font("Arial", Font.BOLD, 12));


        currentLevel = new JLabel();
        currentLevel.setPreferredSize(new Dimension(100, 100));
        this.add(currentLevel);
        currentLevel.setText("Current Level: " + getLevel());
        currentLevel.setForeground(Color.WHITE);
        currentLevel.setFont(new Font("Arial", Font.PLAIN, 12));


        upLevel = new JLabel();
        this.add(upLevel);
        upLevel.setText("Lines Till Next Level: ");
        upLevel.setForeground(Color.WHITE);
        upLevel.setFont(new Font("Arial", Font.PLAIN, 12));
        linesCleared = new JLabel();
        linesCleared.setPreferredSize(new Dimension(100, 100));
        this.add(linesCleared);
        linesCleared.setText("Lines Cleared: " + getLines());
        linesCleared.setForeground(Color.WHITE);
        linesCleared.setFont(new Font("Arial", Font.PLAIN, 12));
    }

    public int getLevel() {
        return myLevel;
    }

    public int getLines() {
        return myLines;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(PROPERTY_FROZEN_BLOCKS)) {
            final List<Block[]> myFrozenBlocks = (List<Block[]>) evt.getNewValue();

            for (int row = 0; row < myFrozenBlocks.size(); row++) {
                boolean checkRow = true;
                int blockClear = 0;
                int rowClear = 0;
                for (int column = 0; column < myFrozenBlocks.get(row).length; column++) {
                    if (myFrozenBlocks.get(row)[column] != null) {
                        checkRow = false;
                        break;
                    }
                    blockClear++;
                    if(blockClear == 10) {
                        rowClear++;
                    }
                }
            }myScore++;

        }

    }
}