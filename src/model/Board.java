/*
 * TCSS 305
 *
 * An implementation of the classic game "Tetris".
 */

package model;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.wallkicks.WallKick;
import view.NextPiece;
import view.ScorePanel;
import view.TetrisGameBoard;


/**
 * Represents a Tetris board. Board objects communicate with clients via Observer pattern.
 * <p>Clients can expect Board objects to call norifyObservers with four different
 * data types:</p>
 * <dl>
 * <dt>{@code List<Block[]>}</dt>
 * <dd>Represents the non-moving pieces on the Board. i.e. Frozen Blocks</dd>
 * <dt>{@link model.MovableTetrisPiece MovableTerisPiece}</dt>
 * <dd>Represents current moving Piece.</dd>
 * <dt>{@link model.TetrisPiece TertisPiece}</dt>
 * <dd>Represents next Piece.</dd>
 * <dt>{@code Integer[]}</dt>
 * <dd>The size of the array represents the number of rows of Frozen Blocks removed.</dd>
 * <dt>{@code Boolean}</dt>
 * <dd>When true, the game is over. </dd>
 * </dl>
 *
 * @author Charles Bryan
 * @author Alan Fowler
 * @version 1.3
 */
public class Board implements CreateBoard {

    // Class constants

    /**
     * Default width of a Tetris game board.
     */
    private static final int DEFAULT_WIDTH = 10;

    /**
     * Default height of a Tetris game board.
     */
    private static final int DEFAULT_HEIGHT = 20;

    // Instance fields
    /**
     * Width of the game board.
     */
    private final int myWidth;

    /**
     * Height of the game board.
     */
    private final int myHeight;

    /**
     * The frozen blocks on the board.
     */
    private final List<Block[]> myFrozenBlocks;
    private final ScorePanel myScorePanel;
    private final NextPiece myNextPiecePanel;


    /**
     * The game over state.
     */
    private boolean myGameOver;

    /**
     * Contains a non-random sequence of TetrisPieces to loop through.
     */
    private List<TetrisPiece> myNonRandomPieces;

    /**
     * The current index in the non-random piece sequence.
     */
    private int mySequenceIndex;

    /**
     * Piece that is next to play.
     */
    private TetrisPiece myNextPiece;

    /**
     * Piece that is currently movable.
     */
    private MovableTetrisPiece myCurrentPiece;

    /**
     * A flag to indicate when moving a piece down is part of a drop operation.
     * This is used to prevent the Board from notifying observers for each incremental
     * down movement in the drop.
     */
    private boolean myDrop;

    /**
     * This field stores all the property change listeners.
     */
    private final PropertyChangeSupport myPCS;
    private final TetrisGameBoard myGameBoard;

    // Constructors

    /**
     * Default Tetris board constructor.
     * Creates a standard size tetris game board.
     */
    public Board() {
        this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    /**
     * Tetris board constructor for non-default sized boards.
     *
     * @param theWidth  Width of the Tetris game board.
     * @param theHeight Height of the Tetris game board.
     */
    public Board(final int theWidth, final int theHeight) {
        super();
        myWidth = theWidth;
        myHeight = theHeight;
        myFrozenBlocks = new LinkedList<Block[]>();

        myNonRandomPieces = new ArrayList<TetrisPiece>();
        mySequenceIndex = 0;
        myGameBoard = new TetrisGameBoard();
        myScorePanel = new ScorePanel();
        myNextPiecePanel = new NextPiece();
        myPCS = new PropertyChangeSupport(this);

        myPCS.addPropertyChangeListener(myGameBoard);
        myPCS.addPropertyChangeListener(myScorePanel);
        myPCS.addPropertyChangeListener(myNextPiecePanel);
        /*  myNextPiece and myCurrentPiece
         *  are initialized by the newGame() method.
         */
    }

    @Override
    public void addPropertyChangeListener(final PropertyChangeListener theListener) {
        myPCS.addPropertyChangeListener(theListener);
    }

    @Override
    public void addPropertyChangeListener(final String thePropertyName,
                                          final PropertyChangeListener theListener) {
        myPCS.addPropertyChangeListener(thePropertyName, theListener);
    }

    @Override
    public void removePropertyChangeListener(final PropertyChangeListener theListener) {
        myPCS.removePropertyChangeListener(theListener);
    }

    @Override
    public void removePropertyChangeListener(final String thePropertyName,
                                             final PropertyChangeListener theListener) {
        myPCS.removePropertyChangeListener(thePropertyName, theListener);
    }

    // public queries

    @Override
    public int getWidth() {
        return myWidth;
    }

    @Override
    public int getHeight() {
        return myHeight;
    }


    @Override
    public void newGame() {
        mySequenceIndex = 0;
        myFrozenBlocks.clear();
        for (int h = 0; h < myHeight; h++) {
            myFrozenBlocks.add(new Block[myWidth]);
        }

        myGameOver = false;
        myCurrentPiece = nextMovablePiece(true);
        myDrop = false;
        myPCS.firePropertyChange(PROPERTY_SEQUENCE_INDEX, null, mySequenceIndex);
        myPCS.firePropertyChange(PROPERTY_FROZEN_BLOCKS, null, myFrozenBlocks);
        myPCS.firePropertyChange(PROPERTY_GAME_OVER, null, myGameOver);
        myPCS.firePropertyChange(PROPERTY_CURRENT_PIECE, null, myCurrentPiece);
        myPCS.firePropertyChange(PROPERTY_DROP, null, myDrop);
    }

    @Override
    public void setPieceSequence(final List<TetrisPiece> thePieces) {
        myNonRandomPieces = new ArrayList<TetrisPiece>(thePieces);
        mySequenceIndex = 0;
        myCurrentPiece = nextMovablePiece(true);
    }

    @Override
    public void step() {
        /*
         * Calling the down() method from here should be sufficient
         * to advance the board by one 'step'.
         * However, more code could be added to this method
         * to implement additional functionality
         */
        down();
    }

    @Override
    public void down() {

        if (!move(myCurrentPiece.down())) {
            // the piece froze, so clear lines and update current piece
            addPieceToBoardData(myFrozenBlocks, myCurrentPiece);
            checkRows();
            if (!myGameOver) {
                myCurrentPiece = nextMovablePiece(false);
            }
            myPCS.firePropertyChange(PROPERTY_CURRENT_PIECE, null, myCurrentPiece);
        }
    }

    @Override
    public void left() {
        if (myCurrentPiece != null) {
            move(myCurrentPiece.left());
        }
    }

    @Override
    public void right() {
        if (myCurrentPiece != null) {
            move(myCurrentPiece.right());
        }
    }

    @Override
    public void rotateCW() {
        if (myCurrentPiece != null) {
            if (myCurrentPiece.getTetrisPiece() == TetrisPiece.O) {
                move(myCurrentPiece.rotateCW());
            } else {
                final MovableTetrisPiece cwPiece = myCurrentPiece.rotateCW();
                final Point[] offsets = WallKick.getWallKicks(cwPiece.getTetrisPiece(),
                        myCurrentPiece.getRotation(),
                        cwPiece.getRotation());
                for (final Point p : offsets) {
                    final Point offsetLocation = cwPiece.getPosition().transform(p);
                    final MovableTetrisPiece temp = cwPiece.setPosition(offsetLocation);
                    if (move(temp)) {
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void rotateCCW() {
        if (myCurrentPiece != null) {
            if (myCurrentPiece.getTetrisPiece() == TetrisPiece.O) {
                move(myCurrentPiece.rotateCCW());
            } else {
                final MovableTetrisPiece ccwPiece = myCurrentPiece.rotateCCW();
                final Point[] offsets = WallKick.getWallKicks(ccwPiece.getTetrisPiece(),
                        myCurrentPiece.getRotation(),
                        ccwPiece.getRotation());
                for (final Point p : offsets) {
                    final Point offsetLocation = ccwPiece.getPosition().transform(p);
                    final MovableTetrisPiece temp = ccwPiece.setPosition(offsetLocation);
                    if (move(temp)) {
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void drop() {
        if (!myGameOver) {
            myDrop = true;
            while (isPieceLegal(myCurrentPiece.down())) {
                down();  // move down as far as possible
            }
            myDrop = false;
            down();  // move down one more time to freeze in place
        }
    }


    @Override
    public String toString() {
        final List<Block[]> board = getBoard();
        board.add(new Block[myWidth]);
        board.add(new Block[myWidth]);
        board.add(new Block[myWidth]);
        board.add(new Block[myWidth]);
        if (myCurrentPiece != null) {
            addPieceToBoardData(board, myCurrentPiece);
        }
        final StringBuilder sb = new StringBuilder();
        for (int i = board.size() - 1; i >= 0; i--) {
            final Block[] row = board.get(i);
            sb.append('|');
            for (final Block c : row) {
                if (c == null) {
                    sb.append(' ');
                } else {
                    sb.append('*');
                }
            }
            sb.append("|\n");
            if (i == this.myHeight) {
                sb.append(' ');
                for (int j = 0; j < this.myWidth; j++) {
                    sb.append('-');
                }
                sb.append('\n');
            }
        }
        sb.append('|');
        for (int w = 0; w < myWidth; w++) {
            sb.append('-');
        }
        sb.append('|');
        return sb.toString();
    }


    // private helper methods

    /**
     * Helper function to check if the current piece can be shifted to the
     * specified position.
     *
     * @param theMovedPiece the position to attempt to shift the current piece
     * @return True if the move succeeded
     */
    private boolean move(final MovableTetrisPiece theMovedPiece) {
        boolean result = false;
        final MovableTetrisPiece oldCurrentPiece = myCurrentPiece;
        if (isPieceLegal(theMovedPiece)) {
            myCurrentPiece = theMovedPiece;
            result = true;
            if (!myDrop) {
                myPCS.firePropertyChange(PROPERTY_CURRENT_PIECE,
                        oldCurrentPiece, myCurrentPiece);
            }
        }
        return result;
    }

    /**
     * Helper function to test if the piece is in a legal state.
     * <p>
     * Illegal states:
     * - points of the piece exceed the bounds of the board
     * - points of the piece collide with frozen blocks on the board
     *
     * @param thePiece MovableTetrisPiece to test.
     * @return Returns true if the piece is in a legal state; false otherwise
     */
    private boolean isPieceLegal(final MovableTetrisPiece thePiece) {
        boolean result = true;

        for (final Point p : thePiece.getBoardPoints()) {
            if (p.x() < 0 || p.x() >= myWidth) {
                result = false;
            }
            if (p.y() < 0) {
                result = false;
            }
        }
        return result && !collision(thePiece);
    }

    /**
     * Adds a movable Tetris piece into a list of board data.
     * Allows a single data structure to represent the current piece
     * and the frozen blocks.
     *
     * @param theFrozenBlocks Board to set the piece on.
     * @param thePiece        Piece to set on the board.
     */
    private void addPieceToBoardData(final List<Block[]> theFrozenBlocks,
                                     final MovableTetrisPiece thePiece) {
        for (final Point p : thePiece.getBoardPoints()) {
            setPoint(theFrozenBlocks, p, thePiece.getTetrisPiece().getBlock());
        }
    }

    /**
     * Checks the board for complete rows.
     */
    private void checkRows() {
        final List<Integer> completeRows = new ArrayList<>();
        for (final Block[] row : myFrozenBlocks) {
            boolean complete = true;
            for (final Block b : row) {
                if (b == null) {
                    complete = false;
                    break;
                }
            }
            if (complete) {
                completeRows.add(myFrozenBlocks.indexOf(row));
            }
        }
        // loop through list backwards removing items by index
        if (!completeRows.isEmpty()) {
            for (int i = completeRows.size() - 1; i >= 0; i--) {
                final Block[] row = myFrozenBlocks.get(completeRows.get(i));
                myFrozenBlocks.remove(row);
                myFrozenBlocks.add(new Block[myWidth]);
            }
        }
        myPCS.firePropertyChange(PROPERTY_FROZEN_BLOCKS, null, myFrozenBlocks);

    }

    /**
     * Helper function to copy the board.
     *
     * @return A new copy of the board.
     */
    private List<Block[]> getBoard() {
        final List<Block[]> board = new ArrayList<Block[]>();
        for (final Block[] row : myFrozenBlocks) {
            board.add(row.clone());
        }
        return board;
    }

    /**
     * Determines if a point is on the game board.
     *
     * @param theBoard Board to test.
     * @param thePoint Point to test.
     * @return True if the point is on the board otherwise false.
     */
    private boolean isPointOnBoard(final List<Block[]> theBoard, final Point thePoint) {
        return thePoint.x() >= 0 && thePoint.x() < myWidth && thePoint.y() >= 0
                && thePoint.y() < theBoard.size();
    }

    /**
     * Sets a block at a board point.
     *
     * @param theBoard Board to set the point on.
     * @param thePoint Board point.
     * @param theBlock Block to set at board point.
     */
    private void setPoint(final List<Block[]> theBoard,
                          final Point thePoint,
                          final Block theBlock) {
        if (isPointOnBoard(theBoard, thePoint)) {
            final Block[] row = theBoard.get(thePoint.y());
            row[thePoint.x()] = theBlock;
        } else if (!myGameOver) {
            myGameOver = true;
            myPCS.firePropertyChange(PROPERTY_GAME_OVER, false, true);
        }
    }

    /**
     * Returns the block at a specific board point.
     *
     * @param thePoint the specific Point to check
     * @return the Block type at point or null if no block exists.
     */
    private Block getPoint(final Point thePoint) {
        Block b = null;
        if (isPointOnBoard(myFrozenBlocks, thePoint)) {
            b = myFrozenBlocks.get(thePoint.y())[thePoint.x()];
        }
        return b;
    }

    /**
     * Helper function to determine of a movable block has collided with set
     * blocks.
     *
     * @param theTest movable TetrisPiece to test for collision.
     * @return Returns true if any of the blocks has collided with a set board
     * block.
     */
    private boolean collision(final MovableTetrisPiece theTest) {
        boolean res = false;
        for (final Point p : theTest.getBoardPoints()) {
            if (getPoint(p) != null) {
                res = true;
            }
        }
        return res;
    }

    /**
     * Gets the next MovableTetrisPiece.
     *
     * @param theRestart Restart the non random cycle.
     * @return A new MovableTetrisPiece.
     */
    private MovableTetrisPiece nextMovablePiece(final boolean theRestart) {

        if (myNextPiece == null || theRestart) {
            prepareNextMovablePiece();
        }

        final TetrisPiece next = myNextPiece;

        int startY = myHeight - 1;
        if (myNextPiece == TetrisPiece.I) {
            startY--;
        }

        prepareNextMovablePiece();
        return new MovableTetrisPiece(
                next,
                new Point((myWidth - myNextPiece.getWidth()) / 2, startY));
    }

    /**
     * Prepares the Next movable piece for preview.
     */
    private void prepareNextMovablePiece() {
        final TetrisPiece oldNextPiece = myNextPiece;
        final int oldSequenceIndex = mySequenceIndex;
        final boolean share = myNextPiece != null;
        if (myNonRandomPieces == null || myNonRandomPieces.isEmpty()) {
            myNextPiece = TetrisPiece.getRandomPiece();
        } else {
            mySequenceIndex %= myNonRandomPieces.size();
            myNextPiece = myNonRandomPieces.get(mySequenceIndex++);
        }
        if (share && !myGameOver) {
            myPCS.firePropertyChange(PROPERTY_NEXT_PIECE, null, myNextPiece);
            myPCS.firePropertyChange(PROPERTY_SEQUENCE_INDEX,
                    oldSequenceIndex, mySequenceIndex);

        }
    }


    // Inner classes

    /**
     * A class to describe the board data to registered Observers.
     * The board data includes the current piece and the frozen blocks.
     */
    protected final class BoardData {

        /**
         * The board data to pass to observers.
         */
        private final List<Block[]> myBoardData;

        /**
         * Constructor of the Board Data object.
         */
        protected BoardData() {
            myBoardData = getBoard();
            myBoardData.add(new Block[myWidth]);
            myBoardData.add(new Block[myWidth]);
            myBoardData.add(new Block[myWidth]);
            myBoardData.add(new Block[myWidth]);
            if (myCurrentPiece != null) {
                addPieceToBoardData(myBoardData, myCurrentPiece);
            }
        }

        /**
         * Copy and return the board's data.
         *
         * @return Copy of the Board Data.
         */
        protected List<Block[]> getBoardData() {
            final List<Block[]> board = new ArrayList<Block[]>();
            for (final Block[] row : myBoardData) {
                board.add(row.clone());
            }
            return board;
        }

    } // end inner class BoardData


}
