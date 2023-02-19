/*
 * TCSS 305
 * 
 * An implementation of the classic game "Tetris".
 */

package model;

import java.util.Random;

/**
 * Enumeration of the TetrisPiece types.
 * 
 * @author Charles Bryan
 * @author Alan Fowler
 * @version 1.3
 */
public enum TetrisPiece {

    /** The 'I' TetrisPiece. */
    I(4, 1,
      Block.I ,
      new int[][][] 
         {{{0, 2}, {1, 2}, {2, 2}, {3, 2}},
          {{2, 3}, {2, 2}, {2, 1}, {2, 0}},
          {{0, 1}, {1, 1}, {2, 1}, {3, 1}},
          {{1, 3}, {1, 2}, {1, 1}, {1, 0}}},
      new Point(0, 2), new Point(1, 2), new Point(2, 2), new Point(3, 2) 
      ),

    /** The 'J' TetrisPiece. */
    J(3, 2,
      Block.J,
      new int[][][]
         {{{0, 2}, {0, 1}, {1, 1}, {2, 1}},
          {{1, 2}, {2, 2}, {1, 1}, {1, 0}},
          {{0, 1}, {1, 1}, {2, 1}, {2, 0}},
          {{1, 2}, {1, 1}, {0, 0}, {1, 0}}},
      new Point(0, 2), new Point(0, 1), new Point(1, 1), new Point(2, 1)),

    /** The 'L' TetrisPiece. */
    L(3, 2,
      Block.L,
      new int[][][]
         {{{2, 2}, {0, 1}, {1, 1}, {2, 1}},
          {{1, 2}, {1, 1}, {1, 0}, {2, 0}},
          {{0, 1}, {1, 1}, {2, 1}, {0, 0}},
          {{0, 2}, {1, 2}, {1, 1}, {1, 0}}},           
      new Point(2, 2), new Point(0, 1), new Point(1, 1), new Point(2, 1)),

    /** The 'O' TetrisPiece. */
    O(3, 2,
      Block.O,
      new int[][][]
        {{{1, 2}, {2, 2}, {1, 1}, {2, 1}},
            {{1, 2}, {2, 2}, {1, 1}, {2, 1}},
            {{1, 2}, {2, 2}, {1, 1}, {2, 1}},
            {{1, 2}, {2, 2}, {1, 1}, {2, 1}}},
      new Point(1, 2), new Point(2, 2), new Point(1, 1), new Point(2, 1)),

    /** The 'S' TetrisPiece. */
    S(3, 2,
      Block.S,
      new int[][][]
         {{{1, 2}, {2, 2}, {0, 1}, {1, 1}},
          {{1, 2}, {1, 1}, {2, 1}, {2, 0}},
          {{1, 1}, {2, 1}, {0, 0}, {1, 0}},
          {{0, 2}, {0, 1}, {1, 1}, {1, 0}}},     
      new Point(1, 2), new Point(2, 2), new Point(0, 1), new Point(1, 1)),

    /** The 'T' TetrisPiece. */
    T(3, 2,
      Block.T,
      new int[][][]
         {{{1, 2}, {0, 1}, {1, 1}, {2, 1}},
          {{1, 2}, {1, 1}, {2, 1}, {1, 0}},
          {{0, 1}, {1, 1}, {2, 1}, {1, 0}},
          {{1, 2}, {0, 1}, {1, 1}, {1, 0}}},
      new Point(1, 2), new Point(0, 1), new Point(1, 1), new Point(2, 1)),

    /** The 'Z' TetrisPiece. */
    Z(3, 2,
      Block.Z,
      new int[][][]
         {{{0, 2}, {1, 2}, {1, 1}, {2, 1}},
          {{2, 2}, {1, 1}, {2, 1}, {1, 0}},
          {{0, 1}, {1, 1}, {1, 0}, {2, 0}},
          {{1, 2}, {0, 1}, {1, 1}, {0, 0}}},
      new Point(0, 2), new Point(1, 2), new Point(1, 1), new Point(2, 1));

    
    // Other class constants

    /**
     * A Random Object.
     */
    private static final Random RANDOM = new Random();

    
    // instance fields
    /**
     * The width of the TetrisPiece.
     */
    private final int myWidth;

    /**
     * The height of the TetrisPiece.
     */
    private final int myHeight;

    /**
     * The 4 Points of the TetrisPiece.
     */
    private final Point[] myPoints;
    
    /**
     * The 4 arrays of 4 points. 
     */
    private final int[][][] myPointsByRotation;

    /**
     * Block type of the TetrisPiece.
     */
    private final Block myBlock;

    /**
     * The TetrisPiece constructor.
     * 
     * @param theWidth width of the TetrisPiece.
     * @param theHeight height of the TetrisPiece.
     * @param theBlock Block type of the TetrisPiece.
     * @param thePointsByRotation The 4 different point arrays based on rotation. 
     * @param thePoints the initial position of the blocks of the TetrisPiece.
     */
    TetrisPiece(final int theWidth, final int theHeight,
                final Block theBlock, 
                final int[][][] thePointsByRotation, 
                final Point... thePoints) {
        myPointsByRotation = thePointsByRotation.clone();
        myWidth = theWidth;
        myHeight = theHeight;
        myBlock = theBlock;
        myPoints = thePoints.clone();
    }

    /**
     * Return the width of the TetrisPiece.
     * 
     * @return the width of the TetrisPiece.
     */
    public int getWidth() {
        return myWidth;
    }

    /**
     * Return the height of the TetrisPiece.
     * 
     * @return the height of the TetrisPiece.
     */
    public int getHeight() {
        return myHeight;
    }

    /**
     * Return the Block type of the TetrisPiece.
     * 
     * @return The Block type of the TetrisPiece.
     */
    public Block getBlock() {
        return myBlock;
    }
    
    /**
     * Returns the Points of the TetrisPiece.
     * 
     * @return the Points of the TetrisPiece.
     */
    public Point[] getPoints() {
        return myPoints.clone();
    }
    /**
     * Returns the Points of the TetrisPiece based on the Rotation. 
     * 
     * @param theRotation return the points for this Piece based on this rotation
     * @return the Points of the TetrisPiece.
     */    
    public int[][] getPointsByRotation(final Rotation theRotation) {
        return myPointsByRotation[theRotation.ordinal()].clone();
    }

    /**
     * Get a random TetrisPiece.
     * 
     * @return a random TetrisPiece.
     */
    public static TetrisPiece getRandomPiece() {
        return values()[RANDOM.nextInt(values().length)];
    }
}
