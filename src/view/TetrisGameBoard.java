package view;

import model.*;
import model.Point;
import model.Block;

import java.awt.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;

import static model.CreateBoard.PROPERTY_CURRENT_PIECE;
import static model.CreateBoard.PROPERTY_FROZEN_BLOCKS;


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
    private MovableTetrisPiece myCurrentPiece;
    private final List<Block[]> myFrozenBlocks;
    /**
     * The current state of the game board.
     * 0 = empty, 1 = occupied.
     */

    public TetrisGameBoard() {

        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(COLUMNS * BLOCK_SIZE, ROWS * BLOCK_SIZE));
        myFrozenBlocks = new ArrayList<Block[]>();
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (PROPERTY_CURRENT_PIECE.equals(evt.getPropertyName())) {
            myCurrentPiece = (MovableTetrisPiece) evt.getNewValue();
            repaint();
        }
        if(PROPERTY_FROZEN_BLOCKS.equals(evt.getPropertyName())) {
//            Block[] newBlock = (Block[]) evt.getNewValue();
//            myFrozenBlocks.add(newBlock);
//            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        // for better graphics display
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        // Draw the blocks on the game board.
        g2d.setColor(Color.WHITE);

        if (myCurrentPiece != null) {
            TetrisPiece piece = myCurrentPiece.getTetrisPiece();
            int[][] points = piece.getPointsByRotation(myCurrentPiece.getRotation());
            g2d.setColor(getColor(myCurrentPiece.getTetrisPiece().getBlock()));
            for (int i = 0; i < points.length; i++) {
                int x = points[i][0] + myCurrentPiece.getPosition().x();
                int y = ROWS - 1 - points[i][1] - myCurrentPiece.getPosition().y();
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


