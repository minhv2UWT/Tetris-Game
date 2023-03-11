package view;

import model.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;

import static model.CreateBoard.PROPERTY_CURRENT_PIECE;

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
    /**
     * The Next Piece
     */
    private TetrisPiece myNextPiece;
    /**
     * current point position.
     */
    private Point myCurrentPosition;
    /*
    * Instantiate random rotation.
     */
    private Rotation myRotation;


    /**
     * The current state of the game board.
     * 0 = empty, 1 = occupied.
     */
    private int[][] myBoardState = new int[ROWS][COLUMNS];

    public TetrisGameBoard() {

        myNextPiece = TetrisPiece.getRandomPiece();
        myRotation = Rotation.random();
        myCurrentPosition = new Point(0, 0);
        myCurrentPiece = new MovableTetrisPiece(myNextPiece, myCurrentPosition, myRotation);

        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(COLUMNS * BLOCK_SIZE, ROWS * BLOCK_SIZE));

    }

    /**
     * Updates the state of the game board with the given board state.
     *
     * @param boardState The new board state to display.
     */
    public void setBoardState(int[][] boardState) {
        myBoardState = boardState;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the blocks on the game board.
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                if (myBoardState[row][col] == 1) {
                    g.setColor(Color.WHITE);
                    g.fillRect(col * BLOCK_SIZE, row * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                    g.setColor(Color.BLACK);
                    g.drawRect(col * BLOCK_SIZE, row * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                }
            }
        }


        // Draw the grid lines.
        g.setColor(Color.RED);
        for (int row = 0; row <= ROWS; row++) {
            g.drawLine(0, row * BLOCK_SIZE, COLUMNS * BLOCK_SIZE, row * BLOCK_SIZE);
        }
        for (int col = 0; col <= COLUMNS; col++) {
            g.drawLine(col * BLOCK_SIZE, 0, col * BLOCK_SIZE, ROWS * BLOCK_SIZE);
        }
        if (myCurrentPiece != null) {
            TetrisPiece piece = myCurrentPiece.getTetrisPiece();
            int[][] points = piece.getPointsByRotation(myCurrentPiece.getRotation());
            for (int i = 0; i < points.length; i++) {
                int x = points[i][0] + myCurrentPiece.getPosition().x();
                int y = points[i][1] + myCurrentPiece.getPosition().y();
                g.setColor(getColor(myCurrentPiece.getTetrisPiece().getBlock()));
                g.fillRect(x * BLOCK_SIZE, y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
            }
        }
    }

    private void step() {

    }

    private Color getColor(Block block) {
        Color blockColor = Color.BLACK;
        switch (block) {
            case I:
                blockColor = Color.CYAN;
            case J:
                blockColor = Color.BLUE;
            case L:
                blockColor = Color.ORANGE;
            case O:
                blockColor = Color.YELLOW;
            case S:
                blockColor = Color.GREEN;
            case T:
                blockColor = Color.decode("#A020F0"); // purple
            case Z:
                blockColor = Color.RED;
        }
        return blockColor;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (PROPERTY_CURRENT_PIECE.equals(evt.getNewValue())) {

        }

    }

}
