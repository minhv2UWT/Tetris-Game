/*
 * TCSS 305 – Winter 2023
 * Group Project – Tetris
 */

package model;

import java.beans.PropertyChangeListener;
import java.util.List;

/**
 * This class creates the game board where Tetris is played.
 * @author Koji Yoshiyama
 * @author Minh Vu
 * @author Simran Narwal
 * @author Jack Chen
 * @version Winter 2023
 */

public interface CreateBoard {

    /**
     * A property name for changes to frozen blocks.
     */
    String FROZEN_BLOCKS = "frozen block";

    /**
     * A property name for changes to the sequence index.
     */
    String SEQUENCE_INDEX = "sequence index";

    /**
     * A property name for changes to game over.
     */
    String GAME_OVER = "game over";

    /**
     * A property name for changes to current piece.
     */
    String CURRENT_PIECE = "current piece";

    /**
     * A property name for changes that affect the NextPiece.
     */
    String NEXT_PIECE = "NextPiece Mutation";

    /**
     * Add a PropertyChangeListener to the listener list.
     * The listener is registered for
     * all properties.
     *
     * @param theListener The PropertyChangeListener to be added
     */
    void addPropertyChangeListener(PropertyChangeListener theListener);

    /**
     * Get the width of the board.
     *
     * @return Width of the board.
     */
    int getWidth();

    /**
     * Get the height of the board.
     *
     * @return Height of the board.
     */
    int getHeight();

    /**
     * Resets the board for a new game.
     * This method must be called before the first game
     * and before each new game.
     */
    void newGame();

    /**
     * Sets a non-random sequence of pieces to loop through.
     *
     * @param thePieces the List of non-random TetrisPieces.
     */
    void setPieceSequence(List<TetrisPiece> thePieces);

    /**
     * Advances the board by one 'step'.
     * <p>
     * This could include
     * - moving the current piece down 1 line
     * - freezing the current piece if appropriate
     * - clearing full lines as needed
     */
    void step();

    /**
     * Try to move the movable piece down.
     * Freeze the Piece in position if down tries to move into an illegal state.
     * Clear full lines.
     */
    void down();

    /**
     * Try to move the movable piece left.
     */
    void left();

    /**
     * Try to move the movable piece right.
     */
    void right();

    /**
     * Try to rotate the movable piece in the clockwise direction.
     */
    void rotateCW();

    /**
     * Try to rotate the movable piece in the counter-clockwise direction.
     */
    void rotateCCW();

    /**
     * Drop the piece until piece is set.
     */
    void drop();

    @Override
    String toString();
}