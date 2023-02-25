
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

import static java.lang.System.*;


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
     *
     */
    private final String myAboutString = "About Tetris";

    /**
     *
     */
    private final String myExitString = "Exit";
    /**
     * I made this JMenuItem a field because the variable is used in multiple methods.
     */
    private final JMenuItem myAboutItem = new JMenuItem(myAboutString);
    private final JMenuItem myAboutUsItem = new JMenuItem("About Us");
    private final JMenuItem myHowToPlayItem = new JMenuItem("How to Play");
    private final JMenuItem myFAQItem = new JMenuItem("FAQs");
    private final JMenuItem myExitItem = new JMenuItem(myExitString);
    private final JMenuItem newGameItem = new JMenuItem("New Game");
    private final JMenuItem saveItem = new JMenuItem("Save");
    private final JMenuItem loadItem = new JMenuItem("Load");
    private final JMenuItem deleteItem = new JMenuItem("Delete");
    private final JMenuItem layout1 = new JMenuItem("Layout 1");
    private final JMenuItem layout2 = new JMenuItem("Layout 2");
    //    private final JMenu fileMenu = new JMenu("File");
//    private final JMenu aboutMenu = new JMenu("About ");
//    private final JMenu helpMenu = new JMenu("Help");
//    private final JMenu exitMenu = new JMenu("Exit");
//    private final JMenu optionsMenu = new JMenu("Options");
    private GameBoardGUI frame;

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
        //GUI window using BorderLayoutPractice
        frame = new GameBoardGUI();

        // Creating GUI window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(DIMENSION_500, DIMENSION_500);
        frame.setLayout(new BorderLayout(DIMENSION_10, DIMENSION_10));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        // Adding icon and title
        final ImageIcon icon = new ImageIcon("TetrisIcon.PNG");
        frame.setIconImage(icon.getImage());
        frame.setTitle("Tetris");

//        final JMenuItem myHowToPlayItem = new JMenuItem("How to Play");
//        final JMenuItem myFAQItem = new JMenuItem("FAQs");
//        final JMenuItem myExitItem = new JMenuItem("Exit");

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

        // Creating Menu Items for File Menu
//        final JMenuItem newGameItem = new JMenuItem("New Game");
//        final JMenuItem saveItem = new JMenuItem("Save");
//        final JMenuItem loadItem = new JMenuItem("Load");
//        final JMenuItem deleteItem = new JMenuItem("Delete");

        // Creating Menu Items for About Menu
//        final JMenuItem aboutUsItem = new JMenuItem("About Us");
//        final JMenuItem aboutItem = new JMenuItem(myAboutTetrisString);

        // Creating Menu Items for Options Menu
//        JMenuItem layout1 = new JMenuItem("Layout 1");
//        JMenuItem layout2 = new JMenuItem("Layout 2");

        // adding Items to options
        optionsMenu.add(layout1);
        optionsMenu.add(layout2);

        // Set ShortKey to File Menu Items
        newGameItem.setMnemonic(KeyEvent.VK_N);
        saveItem.setMnemonic(KeyEvent.VK_S);
        loadItem.setMnemonic(KeyEvent.VK_L);
        deleteItem.setMnemonic(KeyEvent.VK_D);

        // Creating performance for these layouts
        layout1.addActionListener(this);
        layout2.addActionListener(this);

        // Adding Items to Help Menu
        helpMenu.add(myHowToPlayItem);
        helpMenu.add(myFAQItem);

        //Adding Items to Exit Menu
        exitMenu.add(myExitItem);

        // Creating performances of file menu items
        newGameItem.addActionListener(this);
        saveItem.addActionListener(this);
        loadItem.addActionListener(this);
        deleteItem.addActionListener(this);

        // Adding Items to file menu
        fileMenu.add(newGameItem);
        fileMenu.addSeparator();
        fileMenu.add(saveItem);
        fileMenu.add(loadItem);
        fileMenu.add(deleteItem);

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
        frame.createLayout();
        // --------------------------------------------------------------------

        // Set Frame menu bar
        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
    }

    /**
     * This method pulls up the image in the class AboutUs when
     * the "About Tetris" button is clicked.
     *
     * @param theEvent the event to be processed
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        if (theEvent.getSource() == myAboutItem) {
            JOptionPane.showMessageDialog(null,
                    "<html>"
                            + "Tetris is a classic puzzle video game where players manipulate different shaped blocks"
                            + "<br><br>"
                            + " to create complete horizontal lines The game was created in 1984 and has since become"
                            + "<br><br>"
                            + " a popular and iconic game enjoyed by millions of players worldwide.",
                    myAboutString, JOptionPane.INFORMATION_MESSAGE);
        }
        if (theEvent.getSource() == saveItem) {
            System.out.println("Game Saved");
        }
        if (theEvent.getSource() == loadItem) {
            out.println("Game Loaded");
        }
        if (theEvent.getSource() == deleteItem) {
            out.println("Game Deleted");
        }
        if (theEvent.getSource() == newGameItem) {
            out.println("New Game!");
        }
        if (theEvent.getSource() == myAboutUsItem) {
            new AboutUs();
        }
        if (theEvent.getSource() == layout1) {
            frame.createLayout();
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
}
