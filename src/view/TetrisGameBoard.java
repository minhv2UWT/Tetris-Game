package view;

import model.Board;


import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TetrisGameBoard extends JPanel implements PropertyChangeListener {
    private static int UNIT_SIZE = 25;
    Board myBoard;
    public TetrisGameBoard() {
        // other constructor code
        myBoard = new Board();
        this.setPreferredSize(new Dimension(getWidth(),getHeight()));
        this.setBackground(Color.black);
        myBoard.addPropertyChangeListener(this);

    }



    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("board")) {
            // repaint the panel to reflect the new board state
            repaint();
        }
    }

    @Override
    public void paintComponent(Graphics theGraphic) {
        super.paintComponent(theGraphic);
        draw(theGraphic);
    }
    public void draw(Graphics theGraphic) {
//        for (int i = 0; i <= getHeight()/UNIT_SIZE;i++) {
//            theGraphic.drawLine(i*UNIT_SIZE,0,i*UNIT_SIZE,getHeight());
//            theGraphic.drawLine(0,i*UNIT_SIZE,getWidth(),i*UNIT_SIZE);
//        }
        Color randomColor = new Color(
                (int) (Math.random() * 256),
                (int) (Math.random() * 256),
                (int) (Math.random() * 256)
        );
        theGraphic.setColor(randomColor);

        // Fill the entire panel with the random color
        theGraphic.fillRect(0, 0, getWidth(), getHeight());
    }

}
