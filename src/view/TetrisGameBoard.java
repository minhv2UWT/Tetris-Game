package view;



import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TetrisGameBoard extends JPanel implements PropertyChangeListener {

    /**
     * Number of rows in the gameBoard.
     */
    private static final int ROWS = 20;

    /**
     * Number of columns in the gameBoard.
     */
    private static final int COLUMNS = 10;
private Dimension myDimension;
    /**
     * This is a constructor for TetrisGameBoard.
     */
    public TetrisGameBoard(Dimension dimension) {
myDimension = dimension;
        this.setBackground(Color.BLACK);
    }


    @Override
    public void propertyChange(final PropertyChangeEvent theEvt) {
        if (theEvt.getPropertyName().equals(theEvt.getPropertyName())) {
            // repaint the panel to reflect the new board state
            repaint();
        }
    }

    @Override
    public void paintComponent(final Graphics theGraphic) {
        super.paintComponent(theGraphic);
        // all the method state change checks from the board class that
        // have state changes which affect the GameBoard
        draw(theGraphic);
//        checkRows(theGraphic);
//        down(theGraphic);
//        setPoint(theGraphic);
//        move(theGraphic);
//        newGame(theGraphic);

    }

    private void draw(final Graphics theGraphic) {
        final Graphics2D g2d = (Graphics2D) theGraphic;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.RED);



        for(int row = 0; row <= 20; row++) {
            for (int col = 0; col <= 10; col++) {
                g2d.draw(new Rectangle2D.Double(col * getWidth()/10, row * getHeight()/20,
                        20*getWidth()/10, 20*getHeight()/20));
            }
        }
    }

    // isn't the point of an observer design pattern to not have to check when states are changed?
    // propertyChange() method will get all the properties that are changed as
    // they change, then we repaint the board, isn't that all we need?
    // should we just choose the color of each piece first and then when the board is
    // repainted after a state change it just remembers the corresponding colors?
    private void checkRows(final Graphics theGraphic) {
        final Graphics2D g2d = (Graphics2D) theGraphic;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        // myBoard.checkRows();------- how to call this method without instantiating a new board??
 //     if (/*checkRows has been mutated*/) {
            //set color - not sure why, do we need to set color each time a state change occurs?
            //g2d.setColor(); to whatever the color of each piece should be
//      }
    }

    private void move(final Graphics theGraphic) {
        final Graphics2D g2d = (Graphics2D) theGraphic;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        // if the property ends up changing then set colors
            if(true) {
               // g2d.setColor();
            }
    }
    private void setPoint(final Graphics theGraphic) {
        final Graphics2D g2d = (Graphics2D) theGraphic;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        // if the property ends up changing then set colors
        if(true) {
            // g2d.setColor();
        }
    }

    private void down(final Graphics theGraphic) {
        final Graphics2D g2d = (Graphics2D) theGraphic;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        // if the property ends up changing then set colors
        if(true) {
            // g2d.setColor();
        }
    }

    private void newGame(final Graphics theGraphic) {
        final Graphics2D g2d = (Graphics2D) theGraphic;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        // if the property ends up changing then set colors
        if(true) {
            // g2d.setColor();
        }
    }

}
