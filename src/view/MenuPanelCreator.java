package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuPanelCreator {

    private static final int DIMENSION_175 = 175;
    private static final int DIMENSION_100 = 100;
    private static final int DIMENSION_20 = 20;

    public static JPanel createPanel1() {
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(2, 1));
        panel1.add(createPanel5());
        panel1.add(createPanel6());
        return panel1;
    }

    public static JPanel createPanel2() {
        JPanel panel2 = createPanel(Color.gray,
                new Dimension(DIMENSION_175, DIMENSION_100));
        panel2.add(createLabel("TETRIS"));
        return panel2;
    }

    public static JPanel createPanel3() {
        JPanel panel3 = createPanel(Color.gray,
                new Dimension(DIMENSION_175, DIMENSION_20));
        panel3.add(createLabel("Footer"));
        return panel3;
    }

    public static JPanel createPanel4() {
        JPanel panel4 = createPanel(Color.red,
                new Dimension(DIMENSION_100, DIMENSION_100));
        panel4.add(createLabel("Game Panel"));
        return panel4;
    }

    public static JPanel createPanel5() {
        JPanel panel5 = createPanel(Color.blue,
                new Dimension(DIMENSION_100, DIMENSION_100));
        panel5.add(createLabel("Next Piece Panel"));
        return panel5;
    }

    public static JPanel createPanel6() {
        JPanel panel6 = createPanel(Color.green,
                new Dimension(DIMENSION_100, DIMENSION_100));
        panel6.add(createLabel("Game Controls"));
        return panel6;
    }

    private static JPanel createPanel(final Color theColor, final Dimension theSize) {
        final JPanel panel = new JPanel();
        panel.setBackground(theColor);
        panel.setPreferredSize(theSize);
        return panel;
    }

    private static JLabel createLabel(final String theText) {
        return new JLabel(theText);
    }
}
