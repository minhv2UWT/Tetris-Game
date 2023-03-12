/*
 * TCSS 305 – Winter 2023
 * Tetris Project – Sprint 1
 */
package view;

import model.Board;
import model.CreateBoard;
import model.MovableTetrisPiece;

import static java.lang.System.out;
import static model.CreateBoard.PROPERTY_CURRENT_PIECE;
import static model.CreateBoard.PROPERTY_GAME_OVER;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.Serial;
import javax.sound.sampled.*;
import javax.swing.*;

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
public class CreateTetrisGUI extends JFrame implements PropertyChangeListener {
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
     * About us Window Width.
     */
    private static final int ABOUT_US_WINDOW_WIDTH = 510;
    /**
     * About us Window Height.
     */
    private static final int ABOUT_US_WINDOW_HEIGHT = 500;
    /**
     * This constant sets the size of the gameBoardGUI.
     */
    private static final int WINDOW_WIDTH = 500;
    /**
     * This constant sets the size of the gameBoardGUI.
     */
    private static final int WINDOW_HEIGHT = 750;
    /**
     * This constant sets the size of the gameBoardGUI.
     */
    private static final int DIMENSION_150 = 150;
    /**
     * This constant was made to avoid magic numbers.
     */
    private static final int MAGIC_NUMBER = 400;


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
//    private JFrame this;
    /**
     * Create board inside the JFrame.
     */
    private final CreateBoard myBoard;
    /**
     * 1 second delay.
     */
    private static final int TIMER_DELAY = 500;
    /**
     * Instance of Timer.
     */
    private final Timer myTimer;
    /**
     * Instance of new panels/
     */
    private JPanel myContainerPanel;
    /**
     * Instance of new panels/
     */
    private JPanel myTetrisTitlePanel;
    /**
     * Instance of new panels/
     */
    private ScorePanel myInforPanel;
    private TetrisGameBoard myGameBoard;
    private NextPiece myNextPiecePanel;
    private Clip clip;
    private boolean myGameOver;
    private ControlAdapter myKeys;
    private PauseKey myPauseKey;
    private boolean myPause;

    /**
     * This method is a constructor that calls the createMenuGUI() method.
     */
    public CreateTetrisGUI(CreateBoard theBoard) {
        super();
        createAudio();
        gameBoardGUISetUp();
        createLayout();
        createMenuGUI();
        myBoard = theBoard;
        myKeys = new ControlAdapter();
        myPauseKey = new PauseKey();
        myTimer = new Timer(TIMER_DELAY, new ActionListener() {
            int n = 0;

            @Override
            public void actionPerformed(final ActionEvent theEvent) {

                myBoard.step();
                System.out.println(n++);
            }
        });
    }


    @Override
    public void propertyChange(PropertyChangeEvent theEvent) {
        if (PROPERTY_GAME_OVER.equals(theEvent.getPropertyName())) {
            myGameOver = (boolean) theEvent.getNewValue();
            if (myGameOver) {
                myTimer.stop();
                clip.stop();
                removeKeyListener(myKeys);
                removeKeyListener(myPauseKey);
                JOptionPane.showMessageDialog(null, "GAME OVER YOU SUCK!!!", "GAME OVER LOL", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    class PauseKey extends KeyAdapter {
        @Override
        public void keyPressed(final KeyEvent theEvent) {
            final int keyCode = theEvent.getKeyCode();
            final char keyChar = theEvent.getKeyChar();
            if (keyCode == KeyEvent.VK_P || keyChar == 'p' || keyChar == 'P') {
                System.out.println("Pause");
                myPause = !myPause;
                if (myPause) {
                    if (clip != null) {
                        clip.stop();
                    }
                    myTimer.stop();
                    removeKeyListener(myKeys);
                } else {
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                    clip.start();
                    myTimer.start();
                    addKeyListener(myKeys);
                }
            }
        }
    }

    class ControlAdapter extends KeyAdapter {
        /**
         * When pressing the key, trigger an event:
         * Move Left: left arrow and 'a' and 'A'.
         * Move Right: right arrow and 'd' and 'D'.
         * Rotate: up arrow and 'w' and 'W'.
         * Move Down: down arrow and 's' and 'S'.
         * C TO ROTATE THE BLOCK CLOCKWISE.
         * Drop: space.
         *
         * @param theEvent the event to be processed
         */
        @Override
        public void keyPressed(final KeyEvent theEvent) {
            final int keyCode = theEvent.getKeyCode();
            final char keyChar = theEvent.getKeyChar();
            if (keyCode == KeyEvent.VK_LEFT || keyChar == 'a' || keyChar == 'A') {
                System.out.println("left");
                myBoard.left();
            }
            if (keyCode == KeyEvent.VK_RIGHT || keyChar == 'd' || keyChar == 'D') {
                System.out.println("right");
                myBoard.right();
            }
            if (keyCode == KeyEvent.VK_SPACE) {
                System.out.println("drop");
                myBoard.drop();
            }
            if (keyCode == KeyEvent.VK_DOWN || keyChar == 's' || keyChar == 'S') {
                System.out.println("down");
                myBoard.down();
            }
            if (keyCode == KeyEvent.VK_UP || keyChar == 'e' || keyChar == 'E') {
                System.out.println("rotateCW");
                myBoard.rotateCW();
            }
            if (keyCode == KeyEvent.VK_Q || keyChar == 'q' || keyChar == 'Q') {
                System.out.println("rotateCCW");
                myBoard.rotateCCW();
            }
        }
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
        aboutMenu.setMnemonic(KeyEvent.VK_U);
        helpMenu.setMnemonic(KeyEvent.VK_H);
        exitMenu.setMnemonic(KeyEvent.VK_X);
        optionsMenu.setMnemonic(KeyEvent.VK_O);

        // adding Items to options
        optionsMenu.add(myLayout1);
        optionsMenu.add(myLayout2);

        // Set ShortKey to File Menu Items
        myNewGameItem.setMnemonic(KeyEvent.VK_N);


        // Creating performance for these layouts
        myLayout1.addActionListener(e -> out.println("layout 1 generated"));
        myLayout2.addActionListener(e -> out.println("layout 2 generated"));

        // Adding Items to Help Menu
        helpMenu.add(myHowToPlayItem);
        helpMenu.add(myFAQItem);

        //Adding Items to Exit Menu
        exitMenu.add(myExitItem);

        // Creating performances of file menu items
        myNewGameItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                clip.setMicrosecondPosition(0);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                clip.start();
                myBoard.newGame();
                myTimer.restart();
                myGameOver = false;

                removeKeyListener(myPauseKey);
                addKeyListener(myPauseKey);
                removeKeyListener(myKeys);
                addKeyListener(myKeys);
            }
        });
        // Adding Items to file menu
        fileMenu.add(myNewGameItem);
        fileMenu.addSeparator();


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
        this.setJMenuBar(menuBar);
        this.setVisible(true);
    }

    private void createAudio() {
        try {
            File audioFile = new File("Tetris.wav");
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(audioFile));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createLayout() {
        // create panels
        myContainerPanel = createPanel(Color.gray,
                new Dimension(200, 600));

        myTetrisTitlePanel = createPanel(Color.black,
                new Dimension(500, 120));

        myGameBoard = new TetrisGameBoard();
        myNextPiecePanel = new NextPiece();


        myInforPanel = new ScorePanel();


        final ImageIcon image = new ImageIcon("TetrisBackGround.PNG");
        final JLabel label = new JLabel();
        label.setIcon(image);
        myTetrisTitlePanel.add(label);

        myContainerPanel.setLayout(new GridLayout(2, 1));
        myContainerPanel.add(myNextPiecePanel);
        myContainerPanel.add(myInforPanel);

        // add panels to frame
        this.add(myContainerPanel, BorderLayout.EAST);
        this.add(myTetrisTitlePanel, BorderLayout.NORTH);

        this.add(myGameBoard, BorderLayout.CENTER);
    }

    private JPanel createPanel(final Color theColor, final Dimension theSize) {
        final JPanel panel = new JPanel();
        panel.setBackground(theColor);
        panel.setPreferredSize(theSize);
        return panel;
    }

    /**
     * This is a helper method for createMenuGUI that sets up te gameBoardGUI.
     */
    private void gameBoardGUISetUp() {
        //GUI window using BorderLayoutPractice


        // Creating GUI window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setLayout(new BorderLayout());
        this.setResizable(true);

        this.setLocationRelativeTo(null);

        // Adding icon and title
        final ImageIcon icon = new ImageIcon("TetrisIcon.PNG");
        this.setIconImage(icon.getImage());
        this.setTitle("Tetris");
    }

    /**
     * This method creates the image window that displays when
     * "About Tetris" is clicked in the menu.
     */
    private void createAboutUs() {
        final JFrame frame = new JFrame();
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
        frame.setSize(ABOUT_US_WINDOW_WIDTH, ABOUT_US_WINDOW_HEIGHT);
        frame.setVisible(true);
        frame.add(label);
        frame.setIconImage(image.getImage());
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setTitle(ABOUT_US);

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
                myTimer.stop();
                clip.close();
            }
        }
    }

    public static void main(final String... theArgs) {
        SwingUtilities.invokeLater(CreateTetrisGUI::createAndShowGui);
    }

    public static void createAndShowGui() {
        final Board board = new Board();

        final CreateTetrisGUI panel = new CreateTetrisGUI(board);
        board.addPropertyChangeListener(panel);
        board.addPropertyChangeListener(panel.myGameBoard);
        board.addPropertyChangeListener(panel.myNextPiecePanel);
        board.addPropertyChangeListener(panel.myInforPanel);
    }

}
