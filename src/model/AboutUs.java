/*
 * TCSS 305 – Winter 2023
 * Tetris Project – Sprint 1
 */
package model;

import java.io.Serial;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

 /**
 * @author Simran Narwal
 * @author Jack Chen
 * @author Koji Yoshiyama
 * @author Minh Vu
 * @version Winter 2023
 */

/**This class is creating the content within the AboutUs
 tab located in the MenuGUI.*/
public class AboutUs extends JFrame {
    /**
     * Still unsure if this is required, but added just in case.
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
    public AboutUs() {
        super();
        createAboutUs();
    }

    /**
     * This method creates the image window that displays when
     * "About Tetris" is clicked in the menu.
     */
    private void createAboutUs() {
        final ImageIcon image = new ImageIcon("Tetris.PNG");
        final String breaking = "<br><br>";
        final JLabel label = new JLabel("<html>" + "Developers:"
                + breaking + "1. Minh"
                + breaking + "2. Simran" + breaking + "3. Koji"
                + breaking + "4. Jack");
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
