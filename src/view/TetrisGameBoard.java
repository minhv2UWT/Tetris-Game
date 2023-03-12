package view;

import model.Block;

import java.awt.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;

import static model.CreateBoard.*;


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
    private List<Block[]> myBlocksOnBoard;
    private boolean myGameOver;

    public TetrisGameBoard() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(COLUMNS * BLOCK_SIZE, ROWS * BLOCK_SIZE));
        myBlocksOnBoard = new LinkedList<Block[]>();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (PROPERTY_CURRENT_PIECE.equals(evt.getPropertyName())) {
            myBlocksOnBoard = (List<Block[]>) evt.getNewValue();
            repaint();
        }
        if (PROPERTY_FROZEN_BLOCKS.equals(evt.getPropertyName())) {
            myBlocksOnBoard = (List<Block[]>) evt.getNewValue();
            repaint();
        }
        if (PROPERTY_GAME_OVER.equals(evt.getPropertyName())) {
            myGameOver = (Boolean) evt.getNewValue();
            if(checkGameOver(myGameOver)) {
                JOptionPane.showMessageDialog(null, "GAME OVER YOU SUCK!!!", "GAME OVER LOL", JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }

    @Override
    protected void paintComponent(Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        drawUpdatedBoard(g2d);
        drawGrid(g2d);

    }

    private boolean checkGameOver(boolean theGameOver) {
        System.out.println("I'm called!!!" + this.getClass());
        Block[] firstRow = myBlocksOnBoard.get(0);
        for (int i = 2; i < firstRow.length - 3; i++) {
            if (firstRow[i] != null) {
                myGameOver = theGameOver;
                break;
            }
        }
        return myGameOver;
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

    private void drawUpdatedBoard(Graphics2D g2d) {
        g2d.setColor(Color.GRAY);
        for (int i = 0; i < myBlocksOnBoard.size(); i++) {
            Block[] row = myBlocksOnBoard.get(i);
            for (int column = 0; column < row.length; column++) {
                Block blocks = row[column];
                if (blocks != null) {
                    g2d.setColor(getColor(blocks));
                    g2d.fillRect(column * BLOCK_SIZE, (ROWS - 1 - i) * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
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


