package view;

import model.Block;

import java.awt.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;

import static model.CreateBoard.*;


public class TetrisGameBoard extends JPanel implements PropertyChangeListener {

    /**
     * Number of rows in the game board.
     */
    private static final int ROWS = 20;

    /**
     * Number of columns in the game board.
     */
    private static final int COLUMNS = 10;

    /**
     * The width of each block in pixels.
     */
    private static final int BLOCK_SIZE = 28;
    /**
     * my Movable Piece
     */
    private List<Block[]> myBlocksOnBoard;
    private boolean myGameOver;

    public TetrisGameBoard() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(COLUMNS * BLOCK_SIZE, ROWS * BLOCK_SIZE));
        myBlocksOnBoard = new LinkedList<Block[]>();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (PROPERTY_CURRENT_PIECE.equals(evt.getPropertyName())) {
            myBlocksOnBoard = (List<Block[]>) evt.getNewValue();
            repaint();
        }
        if (PROPERTY_FROZEN_BLOCKS.equals(evt.getPropertyName())) {
            myBlocksOnBoard = (List<Block[]>) evt.getNewValue();
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        drawUpdatedBoard(g2d);
        drawGrid(g2d);
    }

    private void drawGrid(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        for (int i = 0; i <= ROWS; i++) {
            for (int column = 0; column <= COLUMNS; column++) {
                g2d.drawLine(0, i * BLOCK_SIZE, COLUMNS * BLOCK_SIZE, i * BLOCK_SIZE);
                g2d.drawLine(column * BLOCK_SIZE, 0, column * BLOCK_SIZE, ROWS * BLOCK_SIZE);
            }
        }
    }

    private void drawUpdatedBoard(Graphics2D g2d) {
        for (int row = 0; row < myBlocksOnBoard.size(); row++) {
            for (int column = 0; column < myBlocksOnBoard.get(row).length; column++) {
                if (myBlocksOnBoard.get(row)[column] != null) {
                    g2d.setColor(getColor(myBlocksOnBoard.get(row)[column]));
                    g2d.fillRect(column * BLOCK_SIZE, (ROWS - 1 - row) * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                }
            }
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


