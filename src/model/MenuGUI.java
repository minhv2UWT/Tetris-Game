
package model;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuGUI extends JFrame implements ActionListener {
    /**
     *
     */
    private static final long serialVersionUID = -2286457356758598552L;

    JMenuBar menuBar;
    JMenu fileMenu, aboutMenu, helpMenu, exitMenu;
    JMenuItem newGameItem, saveItem, loadItem, deleteItem, aboutUsItem, aboutItem;
    BorderLayoutPractice frame;//Instantiating BorderLayoutPractice Object

    public MenuGUI() {

        //GUI window using BorderLayoutPractice
        frame = new BorderLayoutPractice();

        // Creating GUI window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout(10, 10));
        frame.setResizable(false);

        // Creating Menu Bar
        menuBar = new JMenuBar();

        // Creating Menu Options
        fileMenu = new JMenu("File");
        aboutMenu = new JMenu("About ");
        helpMenu = new JMenu("Help");
        exitMenu = new JMenu("Exit");

        // Set ShortKey to Menu Options
        fileMenu.setMnemonic(KeyEvent.VK_F);
        aboutMenu.setMnemonic(KeyEvent.VK_A);
        helpMenu.setMnemonic(KeyEvent.VK_H);
        exitMenu.setMnemonic(KeyEvent.VK_X);

        // Creating Menu Items for File Menu
        newGameItem = new JMenuItem("New Game");
        saveItem = new JMenuItem("Save");
        loadItem = new JMenuItem("Load");
        deleteItem = new JMenuItem("Delete");

        // Creating Menu Items for About Menu
        aboutUsItem = new JMenuItem("About Us");
        aboutItem = new JMenuItem("About Tetris");

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
        aboutMenu.add(aboutItem);

        // Creating Performance for about Items
        aboutUsItem.addActionListener(this);
        aboutItem.addActionListener(this);

        // Adding options to menu bar
        menuBar.add(fileMenu);
        menuBar.add(aboutMenu);
        menuBar.add(helpMenu);
        menuBar.add(exitMenu);

        // -------------------------------------------------------------------
        //creating layouts using createLayout from BorderLayoutPractice class
        frame.createLayout();
        // ------------------------------------------------------------------------------------------------------

        // Set Frame menu bar
        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == aboutItem) {
            new AboutUs();
        }

    }
}
