/*
 * TCSS 305 – Winter 2023
 * Group Project – Tetris
 */

package view;

import static model.CreateBoard.NEXT_PIECE;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serial;
import javax.swing.JPanel;
import model.Block;
import model.Point;
import model.TetrisPiece;

/**
 * This class creates the game board where Tetris is played.
 * @author Koji Yoshiyama
 * @author Minh Vu
 * @author Simran Narwal
 * @author Jack Chen
 * @version Winter 2023
 */

public class NextPiece extends JPanel implements PropertyChangeListener {

    /**
     * This is the serial version UID for the class.
     */
    @Serial
    private static final long serialVersionUID = 123456789L;

    /**
     * Number of rows in the game board.
     */
    private static final int ROWS = 5;

    /**
     * Number of columns in the game board.
     */
    private static final int COLUMNS = 5;

    /**
     * The width of each block in pixels.
     */
    private static final int BLOCK_SIZE = 40;

    /**
     * This is the piece to be drawn.
     */
    private TetrisPiece myNextPiece;

    /**
     * This method is a constructor for the nextPiece class.
     */
    public NextPiece() {
        super();
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(COLUMNS * BLOCK_SIZE, ROWS * BLOCK_SIZE));
    }

    /**
     * This method looks out for property changes within the model
     * so the next piece panel can be updated.
     * @param theEvt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvt) {
        if (NEXT_PIECE.equals(theEvt.getPropertyName())) {
            myNextPiece = (TetrisPiece) theEvt.getNewValue();
            repaint();
        }

    }

    /**
     * This method is called by propertyChange, and it repaints the nextPiece panel.
     * @param theGraphics the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        setRenderingHints(g2d);
        if (myNextPiece != null) {
            drawNextPiece(g2d);
        }
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
     * This helper method is called by paintComponent,
     * and it is used to draw the next tetris piece.
     * @param theG2d this is the graphic currently being drawn
     */
    private void drawNextPiece(final Graphics2D theG2d) {
        for (final Point p : myNextPiece.getPoints()) {
            theG2d.setColor(getColor(myNextPiece.getBlock()));
            theG2d.fillRect((p.x() + 1) * BLOCK_SIZE, (p.y() + 1) * BLOCK_SIZE,
                         BLOCK_SIZE, BLOCK_SIZE);
        }
    }

    /**
     * This method is called by paintComponent, and it is used
     * to draw the grid where the nextPiece is drawn.
     * @param theG2d this is the graphic currently being drawn
     */
    private void drawGrid(final Graphics2D theG2d) {
        theG2d.setColor(Color.RED);
        for (int i = 0; i <= ROWS; i++) {
            for (int j = 0; j <= COLUMNS; j++) {
                theG2d.drawLine(0, i * BLOCK_SIZE, COLUMNS * BLOCK_SIZE, i * BLOCK_SIZE);
                theG2d.drawLine(j * BLOCK_SIZE, 0, j * BLOCK_SIZE, ROWS * BLOCK_SIZE);
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
