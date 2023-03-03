package view;

import model.CreateBoard;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TetrisBoardGUI extends JPanel implements PropertyChangeListener {

    private CreateBoard myBoard;
    public TetrisBoardGUI(CreateBoard theBoard) {
        super();
        myBoard = theBoard;
        //myBoard.addPropertyChangeListener(this);
    }
    public void paintComponent(Graphics theGraphics) {

    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
