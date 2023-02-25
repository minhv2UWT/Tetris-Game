
package model;

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
    private final String myAboutString = "About Us";

    /**
     * Made into a field as it is sed more than once.
     */
    private final String myExitString = "Exit";

    /**
     * Creates a menu option that displays, "About Tetris".
     */
    private final JMenuItem myAboutItem = new JMenuItem("About Tetris");

    /**
     * Creates a menu option that displays, "About Us".
     */
    private final JMenuItem myAboutUsItem = new JMenuItem(myAboutString);

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
    private final JMenuItem myExitItem = new JMenuItem(myExitString);

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
     * This class creates the MenuGUI and inserts it into a GameBoardGUI object.
     */
    private void createMenuGUI() {
        gameBoardGUISetUp();
        // Creating Menu Bar
        final JMenuBar menuBar = new JMenuBar();

        // Creating Menu Options
        final JMenu fileMenu = new JMenu("File");
        final JMenu aboutMenu = new JMenu("About ");
        final JMenu helpMenu = new JMenu("Help");
        final JMenu exitMenu = new JMenu(myExitString);
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
        myNewGameItem.addActionListener(this);
        mySaveItem.addActionListener(this);
        myLoadItem.addActionListener(this);
        myDeleteItem.addActionListener(this);

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
        if (theEvent.getSource() == mySaveItem) {
            System.out.println("Game Saved");
        }
        if (theEvent.getSource() == myLoadItem) {
            System.out.println("Game Loaded");
        }
        if (theEvent.getSource() == myDeleteItem) {
            System.out.println("Game Deleted");
        }
        if (theEvent.getSource() == myNewGameItem) {
            System.out.println("New Game!");
        }
        if (theEvent.getSource() == myAboutUsItem) {
            new AboutUs();
        }
        if (theEvent.getSource() == myLayout1) {
            myFrame.createLayout();
        }
        if (theEvent.getSource() == myHowToPlayItem) {
            new HowToPlay();
        }
        if (theEvent.getSource() == myFAQItem) {
            new FAQs();
        }
        if (theEvent.getSource() == myExitItem) {
            new ExitOption();
        }
    }

    /**
     * This is a helper method for actionPerformed() to reduce complexity.
     *
     * @param theEvent the event to be processed
     */
    private void actionPerformedHelper(final ActionEvent theEvent) {
        final String br = "<br><br>";
        if (theEvent.getSource() == myAboutItem) {
            JOptionPane.showMessageDialog(null,
                    "<html>"
                            + "Tetris is a classic puzzle video game where "
                            + "players manipulate different shaped blocks"
                            + br
                            + " to create complete horizontal lines The "
                            + "game was created in 1984 and has since become"
                            + br
                            + " a popular and iconic game enjoyed by "
                            + "millions of players worldwide.",
                    myAboutString, JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
