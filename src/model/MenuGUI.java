/*
 * TCSS 305 – Winter 2023
 * Tetris Project – Sprint 1
 */
package model;

import static java.lang.System.out;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.Serial;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


/**
 * This class creates the MenuGUI, and combines it with a GameBoardGUI object.
 *
 * @author Simran Narwal
 * @author Jack Chen
 * @author Koji Yoshiyama
 * @author Minh Vu
 * @version Winter 2023
 */

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
     * Still unsure if this is required, but added just in case.
     */
    @Serial
    private static final long serialVersionUID = -2286457356758598552L;

    /**
     * Made into a field as it is sed more than once.
     */
    private static final String ABOUT_US = "About Us";

    /**
     * Made into a field as it is sed more than once.
     */
    private static final String EXIT = "Exit";

    /**
     * Creating string holding "<br><br>".
     */
    private static final String BR_BR = "<br><br>";

    /**
     * Creates a menu option that displays, "About Tetris".
     */
    private final JMenuItem myAboutItem = new JMenuItem("About Tetris");

    /**
     * Creates a menu option that displays, "About Us".
     */
    private final JMenuItem myAboutUsItem = new JMenuItem(ABOUT_US);

    /**
     * Creates a menu option that displays, "How to Play".
     */
    private final JMenuItem myHowToPlayItem = new JMenuItem("How to Play");

    /**
     * Creates a menu option that displays, "FAQs".
     */
    private final JMenuItem myFAQItem = new JMenuItem("FAQs");

    /**
     * Creates a menu option that displays, "Exit".
     */
    private final JMenuItem myExitItem = new JMenuItem(EXIT);

    /**
     * Creates a menu option that displays, "New Game".
     */
    private final JMenuItem myNewGameItem = new JMenuItem("New Game");

    /**
     * Creates a menu option that displays, "Save".
     */
    private final JMenuItem mySaveItem = new JMenuItem("Save");

    /**
     * Creates a menu option that displays, "Load".
     */
    private final JMenuItem myLoadItem = new JMenuItem("Load");

    /**
     * Creates a menu option that displays, "Delete".
     */
    private final JMenuItem myDeleteItem = new JMenuItem("Delete");

    /**
     * Creates a menu option that displays, "Layout 1".
     */
    private final JMenuItem myLayout1 = new JMenuItem("Layout 1");

    /**
     * Creates a menu option that displays, "Layout 2".
     */
    private final JMenuItem myLayout2 = new JMenuItem("Layout 2");

    /**
     * This is the game board GUI that will be merged with the menu bar.
     */
    private GameBoardGUI myFrame;

    /**
     * This method is a constructor that calls the createMenuGUI() method.
     */
    public MenuGUI() {
        super();
        createMenuGUI();
    }

    /**
     * This method creates the MenuGUI and inserts it into a GameBoardGUI object.
     */
    private void createMenuGUI() {
        gameBoardGUISetUp();
        // Creating Menu Bar
        final JMenuBar menuBar = new JMenuBar();

        // Creating Menu Options
        final JMenu fileMenu = new JMenu("File");
        final JMenu aboutMenu = new JMenu("About ");
        final JMenu helpMenu = new JMenu("Help");
        final JMenu exitMenu = new JMenu(EXIT);
        final JMenu optionsMenu = new JMenu("Options");

        // Set ShortKey to Menu Options
        fileMenu.setMnemonic(KeyEvent.VK_F);
        aboutMenu.setMnemonic(KeyEvent.VK_A);
        helpMenu.setMnemonic(KeyEvent.VK_H);
        exitMenu.setMnemonic(KeyEvent.VK_X);
        optionsMenu.setMnemonic(KeyEvent.VK_O);

        // adding Items to options
        optionsMenu.add(myLayout1);
        optionsMenu.add(myLayout2);

        // Set ShortKey to File Menu Items
        myNewGameItem.setMnemonic(KeyEvent.VK_N);
        mySaveItem.setMnemonic(KeyEvent.VK_S);
        myLoadItem.setMnemonic(KeyEvent.VK_L);
        myDeleteItem.setMnemonic(KeyEvent.VK_D);

        // Creating performance for these layouts
        myLayout1.addActionListener(this);
        myLayout2.addActionListener(this);

        // Adding Items to Help Menu
        helpMenu.add(myHowToPlayItem);
        helpMenu.add(myFAQItem);

        //Adding Items to Exit Menu
        exitMenu.add(myExitItem);

        // Creating performances of file menu items
        myNewGameItem.addActionListener(e -> out.println("New game"));
        mySaveItem.addActionListener(e -> out.println("Game Saved"));
        myLoadItem.addActionListener(e -> out.println("Game Loaded"));
        myDeleteItem.addActionListener(e -> out.println("Game Deleted"));

        // Adding Items to file menu
        fileMenu.add(myNewGameItem);
        fileMenu.addSeparator();
        fileMenu.add(mySaveItem);
        fileMenu.add(myLoadItem);
        fileMenu.add(myDeleteItem);

        // Adding Items to about us menu
        aboutMenu.add(myAboutUsItem);
        aboutMenu.add(myAboutItem);

        // Creating Performance for about Items
        myAboutUsItem.addActionListener(this);
        myAboutItem.addActionListener(this);
        myHowToPlayItem.addActionListener(this);
        myFAQItem.addActionListener(this);
        myExitItem.addActionListener(this);

        // Adding options to menu bar
        menuBar.add(fileMenu);
        menuBar.add(aboutMenu);
        menuBar.add(optionsMenu);
        menuBar.add(helpMenu);
        menuBar.add(exitMenu);

        // -------------------------------------------------------------------
        //creating layouts using createLayout from BorderLayoutPractice class
        myFrame.createLayout();
        // --------------------------------------------------------------------

        // Set Frame menu bar
        myFrame.setJMenuBar(menuBar);
        myFrame.setVisible(true);
    }

    /**
     * This is a helper method for createMenuGUI that sets up te gameBoardGUI.
     */
    private void gameBoardGUISetUp() {
        //GUI window using BorderLayoutPractice
        myFrame = new GameBoardGUI();

        // Creating GUI window
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(DIMENSION_500, DIMENSION_500);
        myFrame.setLayout(new BorderLayout(DIMENSION_10, DIMENSION_10));
        myFrame.setResizable(false);
        myFrame.setLocationRelativeTo(null);

        // Adding icon and title
        final ImageIcon icon = new ImageIcon("TetrisIcon.PNG");
        myFrame.setIconImage(icon.getImage());
        myFrame.setTitle("Tetris");
    }

    /**
     * This method pulls up the image in the class AboutUs when
     * the "About Tetris" button is clicked.
     *
     * @param theEvent the event to be processed
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        actionPerformedHelper(theEvent);
        if (theEvent.getSource().equals(myAboutUsItem)) {
            new AboutUs();
        }
        if (theEvent.getSource().equals(myLayout1)) {
            myFrame.createLayout();
        }
        if (theEvent.getSource().equals(myHowToPlayItem)) {
            new HowToPlay();
        }
        if (theEvent.getSource().equals(myFAQItem)) {
            new FAQs();
        }
        if (theEvent.getSource().equals(myExitItem)) {
            new ExitOption();
        }
    }

    /**
     * This is a helper method for actionPerformed() to reduce complexity.
     *
     * @param theEvent the event to be processed
     */
    private void actionPerformedHelper(final ActionEvent theEvent) {
        if (theEvent.getSource().equals(myAboutItem)) {
            JOptionPane.showMessageDialog(null,
                    "<html>"
                            + "Tetris is a classic puzzle video game where "
                            + "players manipulate different shaped blocks"
                            + BR_BR
                            + " to create complete horizontal lines The "
                            + "game was created in 1984 and has since become"
                            + BR_BR
                            + " a popular and iconic game enjoyed by "
                            + "millions of players worldwide.",
                    ABOUT_US, JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
