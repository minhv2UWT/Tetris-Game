package view;

import model.*;
import model.Point;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static model.CreateBoard.PROPERTY_NEXT_PIECE;


public class NextPiece extends JPanel implements PropertyChangeListener {
    private static final int ROWS = 5;

    /**
     * Number of columns in the game board.
     */
    private static final int COLUMNS = 5;

    /**
     * The width of each block in pixels.
     */
    private static final int BLOCK_SIZE = 40;
    private TetrisPiece myNextPiece;

    public NextPiece() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(COLUMNS * BLOCK_SIZE, ROWS * BLOCK_SIZE));


    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (PROPERTY_NEXT_PIECE.equals(evt.getPropertyName())) {
            System.out.println("I'm called");
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
            drawNextPiece(g2d);
        }
        System.out.println("aaa");
        drawGrid(g2d);
    }

    private void drawNextPiece(Graphics2D g2d) {
        System.out.println("drawn");

        for (Point p : myNextPiece.getPoints()) {
            g2d.setColor(getColor(myNextPiece.getBlock()));
            g2d.fillRect(p.x() * BLOCK_SIZE, p.y() * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);

        }
    }

    private void drawGrid(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        for (int i = 0; i <= ROWS; i++) {
            for (int j = 0; j <= COLUMNS; j++) {
                g2d.drawLine(0, i * BLOCK_SIZE, COLUMNS * BLOCK_SIZE, i * BLOCK_SIZE);
                g2d.drawLine(j * BLOCK_SIZE, 0, j * BLOCK_SIZE, ROWS * BLOCK_SIZE);
            }
        }
    }

    private Color getColor(Block block) {
        return switch (block) {
            case I -> Color.CYAN;
            case J -> Color.BLUE;
            case L -> Color.ORANGE;
            case O -> Color.YELLOW;
            case S -> Color.GREEN;
            case T -> Color.decode("#A020F0"); // purple
            case Z -> Color.RED;
        };
    }


}
