package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.Block;
import model.Point;
import model.TetrisPiece;

public class TetrisNextPiece extends JPanel implements PropertyChangeListener {
    private TetrisPiece nextPiece =null;
    private Point[] myPoint =null;
    private Block block=null;
    private final JPanel[][] panels = new JPanel[4][4];

    public TetrisNextPiece() {
        setPreferredSize(new Dimension(100, 100));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setLayout(new GridLayout(4, 4, 1, 1));
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                JPanel panel = new JPanel();
                panel.setBackground(Color.BLACK);
                panels[i][j] = panel;
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                add(panels[i][j]);
            }
        }
    }
    // Override the paintComponent method to paint the Tetris piece
    @Override
    protected void paintComponent(Graphics g) {
        System.out.println("paint");
        super.paintComponent(g);
        for (Point p : this.myPoint) {
            System.out.println(p);
            // Set the background color of each block in the Tetris piece
            panels[p.x()][p.y()].setBackground(getBlockColor(nextPiece));
        }
        // Add the updated panels to the panel
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                add(panels[i][j]);
            }
        }
        // Repaint the panel
        System.out.println(true);
    }
    // A helper method to return the color for each block type
    private Color getBlockColor(TetrisPiece piece) {
        switch (piece.getBlock()) {
            case I:
                return Color.CYAN;
            case J:
                return Color.BLUE;
            case L:
                return Color.ORANGE;
            case O:
                return Color.YELLOW;
            case S:
                return Color.GREEN;
            case T:
                return Color.PINK;
            case Z:
                return Color.RED;
            default:
                return Color.WHITE;
        }
    }

    public Point[] getMyPoint() {
        return myPoint;
    }

    public void setMyPoint(Point[] myPoint) {
        this.myPoint = myPoint;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("myNextPiece")) {
            System.out.println(evt.getPropertyName());
            this.nextPiece = (TetrisPiece) evt.getNewValue();
            Block block1 = this.nextPiece.getBlock();
            Point[] points = this.nextPiece.getPoints();
            setBlock(block1);
            setMyPoint(points);
            if (this.block != null && this.myPoint != null) {
                // Remove all components from the panel
                // Set the background color of each block in the Tetris piece
                repaint();
            }
        }
    }

}
