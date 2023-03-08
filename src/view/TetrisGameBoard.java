package view;


import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TetrisGameBoard extends JPanel implements PropertyChangeListener {
    private static final int ROWS = 20;
    private static final int COLUMNS = 10;


    public TetrisGameBoard() {
        this.setBackground(Color.BLACK);
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(evt.getPropertyName())) {
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
        final Graphics2D g2d = (Graphics2D) theGraphic;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.RED);
        int width = getWidth();
        int height = getHeight();
        int boxWidth = width / COLUMNS;
        int boxHeight = height / ROWS;

        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                int x = column * boxWidth;
                int y = row * boxHeight;
                g2d.drawRect(x, y, boxWidth, boxHeight);
            }
        }
    }

}
