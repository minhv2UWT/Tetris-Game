
package model;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.Serial;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuGUI extends JFrame implements ActionListener {

    /**
     * This constant sets the size of the gameBoardGUI.
     */
    private static final int DIMENSION_500 = 500;

    /**
     * This constant helps set the gameBoardGUI layout.
     */
    private static final int DIMENSION_10 = 10;

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = -2286457356758598552L;

    /**
     * I made this JMenuItem a field because the variable is used in multiple methods.
     */
    private final JMenuItem myAboutItem = new JMenuItem("About Tetris");

    /**
     * This method is a constructor that calls the createMenuGUI() method.
     */
    public MenuGUI() {
        createMenuGUI();
    }

    /**
     * This class creates the MenuGUI and inserts it into a GameBoardGUI object.
     */
    private void createMenuGUI() {
        //GUI window using BorderLayoutPractice
        final GameBoardGUI frame = new GameBoardGUI();

        // Creating GUI window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(DIMENSION_500, DIMENSION_500);
        frame.setLayout(new BorderLayout(DIMENSION_10, DIMENSION_10));
        frame.setResizable(false);

        // Creating Menu Bar
        final JMenuBar menuBar = new JMenuBar();

        // Creating Menu Options
        final JMenu fileMenu = new JMenu("File");
        final JMenu aboutMenu = new JMenu("About ");
        final JMenu helpMenu = new JMenu("Help");
        final JMenu exitMenu = new JMenu("Exit");

        // Set ShortKey to Menu Options
        fileMenu.setMnemonic(KeyEvent.VK_F);
        aboutMenu.setMnemonic(KeyEvent.VK_A);
        helpMenu.setMnemonic(KeyEvent.VK_H);
        exitMenu.setMnemonic(KeyEvent.VK_X);

        // Creating Menu Items for File Menu
        final JMenuItem newGameItem = new JMenuItem("New Game");
        final JMenuItem saveItem = new JMenuItem("Save");
        final JMenuItem loadItem = new JMenuItem("Load");
        final JMenuItem deleteItem = new JMenuItem("Delete");

        // Creating Menu Items for About Menu
        final JMenuItem aboutUsItem = new JMenuItem("About Us");
       // final JMenuItem aboutItem = new JMenuItem("About Tetris");

        // Set ShortKey to File Menu Items
        newGameItem.setMnemonic(KeyEvent.VK_N);
        saveItem.setMnemonic(KeyEvent.VK_S);
        loadItem.setMnemonic(KeyEvent.VK_L);
        deleteItem.setMnemonic(KeyEvent.VK_D);

        // Creating performances of file menu items
        newGameItem.addActionListener(this);
        saveItem.addActionListener(this);
        loadItem.addActionListener(this);
        deleteItem.addActionListener(this);

        // Adding Items to file menu
        fileMenu.add(newGameItem);
        fileMenu.add(saveItem);
        fileMenu.add(loadItem);
        fileMenu.add(deleteItem);

        // Adding Items to about us menu
        aboutMenu.add(aboutUsItem);
        aboutMenu.add(myAboutItem);

        // Creating Performance for about Items
        aboutUsItem.addActionListener(this);
        myAboutItem.addActionListener(this);

        // Adding options to menu bar
        menuBar.add(fileMenu);
        menuBar.add(aboutMenu);
        menuBar.add(helpMenu);
        menuBar.add(exitMenu);

        // -------------------------------------------------------------------
        //creating layouts using createLayout from BorderLayoutPractice class
        frame.createLayout();
        // --------------------------------------------------------------------

        // Set Frame menu bar
        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
    }

    /**
     * This method pulls up the image in the class AboutUs when
     * the "About Tetris" button is clicked.
     * @param theE the event to be processed
     */
    @Override
    public void actionPerformed(final ActionEvent theE) {
        if (theE.getSource() == myAboutItem) {
            new AboutUs();
        }

    }
}
