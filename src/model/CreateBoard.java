package model;

import java.beans.PropertyChangeListener;
import java.util.List;

public interface CreateBoard {

    /**
     * A property name for the mutation of mySequenceIndex.
     */
    String PROPERTY_SEQUENCE_INDEX = "sequence index";

    /**
     * A property name for the mutation of myFrozenBlocks.
     */
    String PROPERTY_FROZEN_BLOCKS = "frozen blocks";

    /**
     * A property name for the mutation of myGameOver.
     */
    String PROPERTY_GAME_OVER = "game over";

    /**
     * A property name for the mutation of myCurrentPiece.
     */
    String PROPERTY_CURRENT_PIECE = "current piece";

    /**
     * A property name for the mutation of myDrop.
     */
    String PROPERTY_DROP = "drop";

    /**
     * A property name for the mutation of myNextPiece.
     */
    String PROPERTY_NEXT_PIECE = "next piece";

    /**
     * Add a PropertyChangeListener to the listener list. The listener is registered for
     * all properties.
     *
     * @param theListener The PropertyChangeListener to be added
     */
    void addPropertyChangeListener(PropertyChangeListener theListener);


    /**
     * Add a PropertyChangeListener for a specific property. The listener will be invoked only
     * when a call on firePropertyChange names that specific property.
     *
     * @param thePropertyName The name of the property to listen on.
     * @param theListener The PropertyChangeListener to be added
     */
    void addPropertyChangeListener(String thePropertyName, PropertyChangeListener theListener);

    /**
     * Remove a PropertyChangeListener from the listener list. This removes a
     * PropertyChangeListener that was registered for all properties.
     *
     * @param theListener The PropertyChangeListener to be removed
     */
    void removePropertyChangeListener(PropertyChangeListener theListener);

    /**
     * Remove a PropertyChangeListener for a specific property. If listener was added more than
     * once to the same event source for the specified property, it will be notified one less
     * time after being removed.
     *
     * @param thePropertyName The name of the property that was listened on.
     * @param theListener The PropertyChangeListener to be removed
     */
    void removePropertyChangeListener(String thePropertyName,
                                      PropertyChangeListener theListener);

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
     * Sets a non random sequence of pieces to loop through.
     *
     * @param thePieces the List of non random TetrisPieces.
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
