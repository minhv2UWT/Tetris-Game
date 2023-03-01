/*
 * TCSS 305 – Winter 2023
 * Tetris Project – Sprint 1
 */
package view;

import model.Board;
import model.CreateBoard;

import static java.lang.System.out;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.Serial;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

/**
 * This class creates the MenuGUI, and combines it with
 * a GameBoardGUI object.
 */

/*
 * @author Simran Narwal
 * @author Jack Chen
 * @author Koji Yoshiyama
 * @author Minh Vu
 * @version Winter 2023
 */
public class MenuGUI extends JFrame implements ActionListener {
    /**
     * This constant is made to avoid magic numbers.
     */
    private static final int BOUNDS_SIZE = 600;

    /**
     * This constant is made to avoid magic numbers.
     */
    private static final int SIZE_WIDTH = 500;

    /**
     * This constant is made to avoid magic numbers.
     */
    private static final int SIZE_HEIGHT = 450;
    /**
     * This constant is the dimension for the window.
     */
    private static final int DIMENSION_510 = 510;
    /**
     * This constant sets the size of the gameBoardGUI.
     */
    private static final int DIMENSION_500 = 500;
    /**
     * This constant sets the size of the gameBoardGUI.
     */
    private static final int DIMENSION_150 = 150;
    /**
     * This constant was made to avoid magic numbers.
     */
    private static final int MAGIC_NUMBER = 400;

    /**
     * This constant helps set the gameBoardGUI layout.
     */
    private static final int DIMENSION_10 = 10;
    /**
     * This constant is the most used dimension value for panels.
     */
    private static final int DIMENSION_100 = 100;

    /**
     * This constant is a defining dimension value.
     */
    private static final int DIMENSION_175 = 175;

    /**
     * This constant is another defining dimension value.
     */
    private static final int DIMENSION_20 = 20;

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
     * Creating string holding breaklines commands.
     */
    private static final String BR_BR = "<br><br>";
    /**
     * Creating string holding breaklines commands.
     */
    private static final String HTML = "<html>";
    private CreateBoard board;

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
    private JFrame myFrame;


    /**
     * This method is a constructor that calls the createMenuGUI() method.
     */
    public MenuGUI() {
        super();
        gameBoardGUISetUp();
        createLayout();
        createMenuGUI();
        board = new Board();
    }

    /**
     * This method creates the MenuGUI and inserts
     * it into a GameBoardGUI object.
     */
    private void createMenuGUI() {

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
        myLayout1.addActionListener(e -> out.println("layout 1 generated"));
        myLayout2.addActionListener(e -> out.println("layout 2 generated"));

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
        myAboutUsItem.addActionListener(e -> createAboutUs());
        myAboutItem.addActionListener(e -> JOptionPane.showMessageDialog(null,
                HTML
                        + "Tetris is a classic puzzle video game where "
                        + "players manipulate different shaped blocks"
                        + BR_BR
                        + " to create complete horizontal lines The "
                        + "game was created in 1984 and has since become"
                        + BR_BR
                        + " a popular and iconic game enjoyed by "
                        + "millions of players worldwide.",
                ABOUT_US, JOptionPane.INFORMATION_MESSAGE));
        myHowToPlayItem.addActionListener(e -> createHowToPlay());
        myFAQItem.addActionListener(e -> createFrequentlyAskedQuestions());
        myExitItem.addActionListener(e -> createExitOption());

        // Adding options to menu bar
        menuBar.add(fileMenu);
        menuBar.add(aboutMenu);
        menuBar.add(optionsMenu);
        menuBar.add(helpMenu);
        menuBar.add(exitMenu);

        // Set Frame menu bar
        myFrame.setJMenuBar(menuBar);
        myFrame.setVisible(true);
    }

    private void createLayout() {
        // create panels
        final JPanel panel1 = createPanel(Color.gray,
                new Dimension(DIMENSION_175, DIMENSION_100));
        final JPanel panel2 = createPanel(Color.gray,
                new Dimension(DIMENSION_100, DIMENSION_100));
        final JPanel panel3 = createPanel(Color.gray,
                new Dimension(DIMENSION_100, DIMENSION_20));
        final JPanel panel4 = createPanel(Color.red,
                new Dimension(DIMENSION_100, DIMENSION_100));
        final JPanel panel5 = createPanel(Color.blue,
                new Dimension(DIMENSION_100, DIMENSION_100));
        final JPanel panel6 = createPanel(Color.green,
                new Dimension(DIMENSION_100, DIMENSION_100));

        // add labels to panels
        panel2.add(createLabel("TETRIS"));
        panel3.add(createLabel("Footer"));
        panel4.add(createLabel("Game Panel"));
        panel5.add(createLabel("Next Piece Panel"));
        panel6.add(createLabel("Game Controls"));

        // create nested panel for next piece/controls
        panel1.setLayout(new GridLayout(2, 1));
        panel1.add(panel5);
        panel1.add(panel6);

        // add panels to frame
        myFrame.add(panel1, BorderLayout.EAST);
        myFrame.add(panel2, BorderLayout.NORTH);
        myFrame.add(panel3, BorderLayout.SOUTH);
        myFrame.add(panel4, BorderLayout.CENTER);
    }

    private JPanel createPanel(final Color theColor, final Dimension theSize) {
        final JPanel panel = new JPanel();
        panel.setBackground(theColor);
        panel.setPreferredSize(theSize);
        return panel;
    }

    private JLabel createLabel(final String theText) {
        return new JLabel(theText);
    }

    /**
     * This is a helper method for createMenuGUI that sets up te gameBoardGUI.
     */
    private void gameBoardGUISetUp() {
        //GUI window using BorderLayoutPractice
        myFrame = new JFrame();

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
     * This method creates the image window that displays when
     * "About Tetris" is clicked in the menu.
     */
    private void createAboutUs() {
        final ImageIcon image = new ImageIcon("Tetris.PNG");
        final JLabel label = new JLabel(HTML + "Developers:"
                + BR_BR + "1. Minh Vu"
                + BR_BR + "2. Simran Narwal" + BR_BR + "3. Koji Yoshiyama"
                + BR_BR + "4. Jack Chen");
        label.setIcon(image);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        this.setSize(DIMENSION_510, DIMENSION_500);
        this.setVisible(true);
        this.add(label);
        this.setIconImage(image.getImage());
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle(ABOUT_US);

    }

    /**
     * This method pulls up the image in the class AboutUs when
     * the "About Tetris" button is clicked.
     *
     * @param theEvent the event to be processed
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
//aaa
    }

    /**
     * This method creates a window of text, displaying the instructions on
     * how to play Tetris.
     */
    private void createHowToPlay() {
        final JFrame frame = new JFrame("Instructions to Play!");
        final JTextArea text = new JTextArea("""
                Instructions to Play!
                1. The pieces will automatically come down
                2. To bring piece down faster, click the down key on your keyboard
                3. Fit the pieces so they all fill the bottom row
                4. Once the bottom row is filled, your score will increase!
                """);
        text.setBounds(0, 0, MAGIC_NUMBER, MAGIC_NUMBER);
        frame.add(text);
        frame.setSize(MAGIC_NUMBER, DIMENSION_150);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * This method creates a window to answer a series of FAQs.
     */
    private void createFrequentlyAskedQuestions() {
        final JTextArea text = new JTextArea("""
                  Frequently Asked Questions
                  1. Question: Why play?
                  Answer: IT'S THE BEST GAME EVER!
                  2. Question: When was the first game of Tetris created?
                  Answer: 1984!
                  3. Question: My pieces won't fall?
                  Answer: There may be a chance there is a lag in the system
                  Try restarting!
                  4. Question: Can I change the background color of my screen?
                  Answer: Unfortunately, we are unable to support that request at this time!
                  5. Question: Can I play with other players?
                  Answer: Unfortunately, this game of Tetris is to be played individually!
                  Is your question not here?
                 `Try searching the web. Good Luck :)
                """);
        text.setBounds(0, 0, BOUNDS_SIZE, BOUNDS_SIZE);

        final JFrame frame = new JFrame();
        frame.add(text);
        frame.setSize(SIZE_WIDTH, SIZE_HEIGHT);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void createExitOption() {
        final int selection = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to "
                        + "exit?", "Confirm", JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (selection == JOptionPane.YES_OPTION) {
            final Window[] windows = Window.getWindows();
            for (final Window window : windows) {
                window.dispose(); // close all windows
            }
        }
    }

}
