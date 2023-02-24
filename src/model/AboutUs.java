package model;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class AboutUs extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 4235325245485188290L;
    JFrame newFrame = new JFrame();
    ImageIcon image;
    JLabel label;
    AboutUs() {
        image = new ImageIcon("Tetris.PNG");
        label = new JLabel("<html>" + "<br><br>" + "About Tetris" + "<br><br>"
                + "Tetris is really fun I guess hehe :) :):)");
        label.setIcon(image);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);



        this.setSize(600,600);
        this.setVisible(true);
        this.add(label);

        this.setIconImage(image.getImage());

        this.setTitle("About");

    }
}
