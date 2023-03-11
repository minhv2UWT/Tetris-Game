package view;
import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;

import static model.CreateBoard.PROPERTY_FROZEN_BLOCKS;

public class ScorePanel extends JPanel {

    int score;
    int level = 1;
    int myLines;

//int nextLevel;

    public ScorePanel() {


        this.setBackground(Color.darkGray);

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

    public void propertyChange(PropertyChangeEvent evt, final Integer[] theArray) {
        if (evt.getPropertyName().equals(PROPERTY_FROZEN_BLOCKS)) {
            if(theArray.length == 1) {
                score += 40 * level;
                myLines++;
            } else if (theArray.length == 2) {
                score += 100 * level;
                myLines++;
            } else if (theArray.length == 3) {
                score += 300 * level;
                myLines++;
            } else if (theArray.length == 4) {
                score += 1200 * level;
                myLines++;
            }
        }

        if (myLines <= 4) {
            level++;
        } else if(myLines <= 9) {
            level ++;
        } else if(myLines <= 14) {
            level++;
        }
    }

    public int getLevel() {
        return level;
    }

    public int getLines() {
        return myLines;
    }
}
