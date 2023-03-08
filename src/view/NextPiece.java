package view;

import model.Block;
import model.Point;
import model.TetrisPiece;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class NextPiece extends JPanel implements PropertyChangeListener {
    private TetrisPiece myNextPiece;

    public NextPiece() {
        setBackground(Color.BLACK);
        setBorder(BorderFactory.createLineBorder(Color.RED));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("nextPiece".equals(evt.getPropertyName())) {
            myNextPiece = (TetrisPiece) evt.getNewValue();
            repaint();
        }
    }
    private Color getColor(Block block) {
        Color blockColor = Color.BLACK;
        switch (block) {
            case I: blockColor = Color.CYAN;
            case J: blockColor = Color.BLUE;
            case L: blockColor = Color.ORANGE;
            case O: blockColor = Color.YELLOW;
            case S: blockColor = Color.GREEN;
            case T: blockColor = Color.decode("#A020F0"); // purple
            case Z: blockColor = Color.RED;
        }
        return blockColor;
    }
}
