package view;

import model.*;
import model.Point;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
    private boolean myGameOver;


    /**
     * The current state of the game board.
     * 0 = empty, 1 = occupied.
     */
    private int[][] myBoardState = new int[ROWS][COLUMNS];

    public TetrisGameBoard() {

        createPiece();
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
    protected void paintComponent(Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        // for better graphics display
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        // Draw the blocks on the game board.
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                if (myBoardState[row][col] == 1) {
                    g2d.setColor(Color.GRAY);
                    g2d.fillRect(col * BLOCK_SIZE, row * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                    g2d.setColor(Color.BLACK);
                    g2d.drawRect(col * BLOCK_SIZE, row * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                }
            }
        }

        if (myCurrentPiece != null) {
            TetrisPiece piece = myCurrentPiece.getTetrisPiece();
            int[][] points = piece.getPointsByRotation(myCurrentPiece.getRotation());
            g2d.setColor(getColor(myCurrentPiece.getTetrisPiece().getBlock()));
            for (int i = 0; i < points.length; i++) {
                int x = points[i][0] + myCurrentPiece.getPosition().x();
                int y = points[i][1] + myCurrentPiece.getPosition().y();
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

    public void createPiece() {
        myNextPiece = TetrisPiece.getRandomPiece();
        myRotation = Rotation.random();
        myCurrentPosition = new Point(3, -3);
        myCurrentPiece = new MovableTetrisPiece(myNextPiece, myCurrentPosition, myRotation);
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

    private void addCurrentPieceToBoard() {
        int[][] points = myCurrentPiece.getTetrisPiece().getPointsByRotation(myCurrentPiece.getRotation());
        for (int i = 0; i < points.length; i++) {
            int x = points[i][0] + myCurrentPosition.x();
            int y = points[i][1] + myCurrentPosition.y();
            myBoardState[y][x] = 1;
        }
    }

    private boolean isValidMove(MovableTetrisPiece piece) {
        int[][] points = piece.getTetrisPiece().getPointsByRotation(piece.getRotation());
        for (int i = 0; i < points.length; i++) {
            int x = points[i][0] + piece.getPosition().x();
            int y = points[i][1] + piece.getPosition().y();
            if (x < 0 || x >= COLUMNS || y >= ROWS || (y >= 0 && myBoardState[y][x] == 1)) {
                return false;
            }
        }
        return true;
    }

    private void step() {
        Point newPosition = new Point(myCurrentPosition.x(), myCurrentPosition.y() + 1);
        MovableTetrisPiece newPiece = new MovableTetrisPiece(myCurrentPiece.getTetrisPiece(),
                newPosition, myCurrentPiece.getRotation());
        if (isValidMove(newPiece)) {
            myCurrentPosition = newPosition;
            myCurrentPiece = newPiece;
        } else {
            // The piece has hit something, so add it to the game board and create a new piece.
            addCurrentPieceToBoard();
            createPiece();
        }
    }
    private void moveRight() {
        Point newPosition = new Point(myCurrentPosition.x(), myCurrentPosition.y() + 1);
        MovableTetrisPiece newPiece = new MovableTetrisPiece(myCurrentPiece.getTetrisPiece(),
                newPosition, myCurrentPiece.getRotation());
        if (isValidMove(newPiece)) {
            myCurrentPosition = newPosition;
            myCurrentPiece = newPiece;
        } else {
            // The piece has hit something, so add it to the game board and create a new piece.
            addCurrentPieceToBoard();
            createPiece();
        }
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (PROPERTY_CURRENT_PIECE.equals(evt.getPropertyName())) {

            step();
            repaint();
        } else if (CreateBoard.PROPERTY_GAME_OVER.equals(evt.getPropertyName())) {


        } else if (CreateBoard.PROPERTY_NEXT_PIECE.equals(evt.getPropertyName())) {
        }
    }
}


