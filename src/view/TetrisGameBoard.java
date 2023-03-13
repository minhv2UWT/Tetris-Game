/*
 * TCSS 305 – Winter 2023
 * Group Project – Tetris
 */

package view;

import static model.CreateBoard.PROPERTY_CURRENT_PIECE;
import static model.CreateBoard.PROPERTY_FROZEN_BLOCKS;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serial;
import java.util.LinkedList;
import java.util.List;
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

public class TetrisGameBoard extends JPanel implements PropertyChangeListener {

    /**
     * This is the serialVersionUID.
     */
    @Serial
    private static final long serialVersionUID = 123456789L;


    /**
     * Number of rows.
     */
    private static final int ROWS = 20;

    /**
     * Number of columns.
     */
    private static final int COLUMNS = 10;

    /**
     * Size of a grid cell.
     */
    private static final int UNIT = 28;

    /**
     * My movable Piece.
     */
    private List<Block[]> myBlocksOnBoard;

    /**
     * This method is the constructor for the TetrisGameBoard class.
     */
    public TetrisGameBoard() {
        super();
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(COLUMNS * UNIT, ROWS * UNIT));
        myBlocksOnBoard = new LinkedList<>();
    }

    /**
     * This method receives the property changes in the model so the
     * game board can update accordingly.
     *
     * @param theEvt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvt) {
        if (PROPERTY_CURRENT_PIECE.equals(theEvt.getPropertyName())) {
            myBlocksOnBoard = (List<Block[]>) theEvt.getNewValue();
            repaint();
        }
        if (PROPERTY_FROZEN_BLOCKS.equals(theEvt.getPropertyName())) {
            myBlocksOnBoard = (List<Block[]>) theEvt.getNewValue();
            repaint();
        }
    }

    /**
     * This method, called by propertyChange, repaints the game board
     * which includes the pieces and the grid.
     * @param theGraphics the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        setRenderingHints(g2d);
        drawUpdatedBoard(g2d);
        drawGrid(g2d);
    }

    /**
     * This private helper method was made to satisfy the law of
     * demeter and makes the graphics sharper.
     * @param theGraphics the current graphic that is being drawn.
     */
    private void setRenderingHints(final Graphics2D theGraphics) {
        theGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                     RenderingHints.VALUE_ANTIALIAS_ON);
    }

    /**
     * This is a helper method that is called in paintComponent that draws the grid.
     * @param theG2d this is the graphic that is being drawn.
     */
    private void drawGrid(final Graphics2D theG2d) {
        theG2d.setColor(Color.WHITE);
        for (int i = 0; i <= ROWS; i++) {
            for (int column = 0; column <= COLUMNS; column++) {
                theG2d.drawLine(0, i * UNIT, COLUMNS * UNIT, i * UNIT);
                theG2d.drawLine(column * UNIT, 0, column * UNIT, ROWS * UNIT);
            }
        }
    }

    /**
     * This is a helper method that is called in paintComponent that draws the pieces.
     * @param theG2d this is the graphic that is being drawn.
     */
    private void drawUpdatedBoard(final Graphics2D theG2d) {
        for (int row = 0; row < myBlocksOnBoard.size(); row++) {
            final Block[] rowBlocks = myBlocksOnBoard.get(row);
            for (int column = 0; column < rowBlocks.length; column++) {
                if (myBlocksOnBoard.get(row)[column] != null) {
                    theG2d.setColor(getColor(myBlocksOnBoard.get(row)[column]));
                    final int upsideDown = ROWS - (1 + row);
                    theG2d.fillRect(column * UNIT, upsideDown * UNIT, UNIT, UNIT);
                }
            }
        }
    }

    /**
     * This method finds out each piece's corresponding color.
     * @param theBlock the piece sent in that will receive a color assignment.
     * @return the color that the piece should be.
     */
    private Color getColor(final Block theBlock) {
        return switch (theBlock) {
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


