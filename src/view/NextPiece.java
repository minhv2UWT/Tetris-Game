package view;

import model.*;
import model.Point;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


import static model.CreateBoard.PROPERTY_NEXT_PIECE;


public class NextPiece extends JPanel implements PropertyChangeListener {
    private static final int ROWS = 4;

    /**
     * Number of columns in the game board.
     */
    private static final int COLUMNS = 4;

    /**
     * The width of each block in pixels.
     */
    private static final int BLOCK_SIZE = 30;

    private TetrisPiece myNextPiece;


    public NextPiece() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(COLUMNS * BLOCK_SIZE, ROWS * BLOCK_SIZE));
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (PROPERTY_NEXT_PIECE.equals(evt.getPropertyName())) {
            myNextPiece = (TetrisPiece) evt.getNewValue();
            repaint();
        }
    }


    @Override
    protected void paintComponent(Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        // for better graphics display
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        if (myNextPiece != null) {
            Point[] points = myNextPiece.getPoints();
            g2d.setColor(getColor(myNextPiece.getBlock()));
            for (Point point : points) {
                int x = point.x();
                int y = point.y();
                g2d.fillRect(x * BLOCK_SIZE, y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
            }
        }

        // Draw the grid lines.
        g2d.setColor(Color.RED);
        for (int row = 0; row <= ROWS; row++) {
            g2d.drawLine(0, row * BLOCK_SIZE, COLUMNS * BLOCK_SIZE, row * BLOCK_SIZE);
        }
        for (int col = 0; col <= COLUMNS; col++) {
            g2d.drawLine(col * BLOCK_SIZE, 0, col * BLOCK_SIZE, ROWS * BLOCK_SIZE);
        }
    }

    private Color getColor(Block block) {
        Color blockColor = Color.BLACK;
        switch (block) {
            case I:
                blockColor = Color.CYAN;
                break;
            case J:
                blockColor = Color.BLUE;
                break;
            case L:
                blockColor = Color.ORANGE;
                break;
            case O:
                blockColor = Color.YELLOW;
                break;
            case S:
                blockColor = Color.GREEN;
                break;
            case T:
                blockColor = Color.decode("#A020F0"); // purple
                break;
            case Z:
                blockColor = Color.RED;
                break;
        }
        return blockColor;
    }


}
