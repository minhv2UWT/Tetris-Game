package model;

import java.io.Serial;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class AboutUs extends JFrame {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 4235325245485188290L;

    /**
     * This constant is the dimension for the window.
     */
    private static final int DIMENSION_510 = 510;

    /**
     * This constant is the dimension for the window.
     */
    private static final int DIMENSION_500 = 500;

    /**
     * This method is a constructor that calls the createAboutUs() method.
     */
    AboutUs() {
        createAboutUs();
    }

    /**
     * This method creates the image window that displays when
     * "About Tetris" is clicked in the menu.
     */
    private void createAboutUs() {
        final ImageIcon image = new ImageIcon("Tetris.PNG");
        final String br = "<br><br>";
        final JLabel label = new JLabel("<html>" + "Developers:" + br + "1. Minh"
                + br + "2. Simran" + br + "3. Koji"
                + br + "4. Jack");
        label.setIcon(image);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        this.setSize(DIMENSION_510, DIMENSION_500);
        this.setVisible(true);
        this.add(label);
        this.setIconImage(image.getImage());
        this.setTitle("About Us");

    }
}
