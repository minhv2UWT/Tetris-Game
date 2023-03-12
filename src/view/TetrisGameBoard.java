package view;

import model.*;
import model.Point;
import model.Block;

import java.awt.*;

import java.awt.geom.RectangularShape;
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
    private RectangularShape myPiece;
    private List<Block[]> myFrozenBlocks;


    public TetrisGameBoard() {

        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(COLUMNS * BLOCK_SIZE, ROWS * BLOCK_SIZE));
        myFrozenBlocks = new LinkedList<Block[]>();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (PROPERTY_CURRENT_PIECE.equals(evt.getPropertyName())) {
            myFrozenBlocks = (List<Block[]>) evt.getNewValue();
            repaint();
        }
        if (PROPERTY_FROZEN_BLOCKS.equals(evt.getPropertyName())) {
            myFrozenBlocks = (List<Block[]>) evt.getNewValue();
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        if (myCurrentPiece != null) {
            drawCurrentPiece(g2d);
        }
        drawFrozenBlock(g2d);
        drawGrid(g2d);

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

    private void drawFrozenBlock(Graphics2D g2d) {
        g2d.setColor(Color.GRAY);
        for (int rowNumber = 0; rowNumber < myFrozenBlocks.size(); rowNumber++) {
            Block[] row = myFrozenBlocks.get(rowNumber);
            for (int column = 0; column < row.length; column++) {
                if (row[column] != null) {
                    int x = column * BLOCK_SIZE;
                    int y = (ROWS - 1 - rowNumber) * BLOCK_SIZE;
                    g2d.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
                }
            }
        }
    }


    private void drawCurrentPiece(Graphics2D g2d) {

        for (int i = 0; i < myFrozenBlocks.size(); i++) {
            for (int j = 0; j < myFrozenBlocks.get(i).length; j++) {
                if (myFrozenBlocks.get(i)[j] != null) {
                    g2d.setColor(getColor(myFrozenBlocks.get(i)[j]));
                    myPiece.setFrame(i * BLOCK_SIZE, j * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
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


