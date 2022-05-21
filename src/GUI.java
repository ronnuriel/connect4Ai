//package connect4Ai.src.State;

//import com.company.Controller.Connect4Game;

import java.awt.*;
import java.net.URL;
import javax.swing.*;

/** Test setting Swing's JComponents properties and appearances */
@SuppressWarnings("serial")
public class GUI extends JFrame {
    private final Container cp;

    State game = new State();

    // TODO: Get it from the board
    int rows;
    int columns;

    // TODO: Calculate them instead
    int windowWidth = 750;
    int windowHeight = 650;

    // Prepare ImageIcons to be used with JComponents
    private ImageIcon iconEmpty = null;
    private ImageIcon iconRed = null;
    private ImageIcon iconYellow = null;

    private final String title = "Connect 4 - ";
    int count = 1;

    private void worldsBestUpdater(JButton button) {
        int row10plusCol = Integer.parseInt(button.getName());
        int col = row10plusCol % 10;

        //boolean player1turn = game.isIs1playing();
        if(count==-1) setTitle(title + "Yellow");
        else setTitle(title + "Red");

        int addedRow = game.isCollumFree(row10plusCol);

        if(addedRow != -1) {

            if(count==1){
                JButton buttonToUpdate = ((JButton)(cp.getComponent(columns * addedRow + col)));
                buttonToUpdate.setIcon(iconYellow);
                game.makeMove((row10plusCol+1),1);
            }
            else{
                JButton buttonToUpdate = ((JButton)(cp.getComponent(columns * addedRow + col)));
                buttonToUpdate.setIcon(iconRed);
                game.makeMove((row10plusCol+1),-1);
            }
            count*=-1;
            game.printBoard(); // remove later
            // check for winner
            if(game.checkGameOver()) {
                if(game.checkForWinnerInGUI(count)) {
                    JOptionPane.showMessageDialog(null, "You have won!");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Its a tie!");
                }
                int reply = JOptionPane.showConfirmDialog(null, "Would you like to play again?", null, JOptionPane.YES_NO_OPTION);
                if(reply == JOptionPane.YES_OPTION) {
                    System.out.println("Trying to play again...");
                    game.reset();
                    resetBoard();
                } else {
                    System.exit(0);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a valid position.");
        }
    }

    public void resetBoard() {
        for(int row = 0; row < rows; row++)
            for (int col = 0; col < columns; col++)
                ((JButton)(cp.getComponent(columns * row + col))).setIcon(iconEmpty);
    }

    /** Constructor to setup the GUI */
    public GUI(boolean player1turn, State game, int rows, int columns) {
        this.game = game;
        this.rows = rows;
        this.columns = columns;

        // Prepare Icons
        // Image path relative to the project root (i.e., bin)
        String imgEmptyFilename = "images/empty.png";
        URL imgURL = getClass().getClassLoader().getResource(imgEmptyFilename);
        if (imgURL != null) iconEmpty = new ImageIcon(imgURL);
        else System.err.println("Couldn't find file: " + imgEmptyFilename);

        String imgRedFilename = "images/red.png";
        imgURL = getClass().getClassLoader().getResource(imgRedFilename);
        if (imgURL != null) iconRed = new ImageIcon(imgURL);
        else System.err.println("Couldn't find file: " + imgRedFilename);


        String imgYellowFilename = "images/yellow.png";
        imgURL = getClass().getClassLoader().getResource(imgYellowFilename);
        if (imgURL != null) iconYellow = new ImageIcon(imgURL);
        else System.err.println("Couldn't find file: " + imgYellowFilename);

        cp = getContentPane();
        cp.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        for(int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                JButton button = new JButton(); // use setter to set text and icon
                button.setIcon(iconEmpty);
                button.setPreferredSize(new Dimension(100, 100));
                // row * 10 + col
                button.setName(Integer.toString((row * 10 + col)));

                button.addActionListener(actionEvent -> worldsBestUpdater(((JButton) (actionEvent.getSource()))));
                cp.add(button);
            }
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if(count==1) setTitle(title + "Yellow");
        else setTitle(title + "Red");
        setLocationRelativeTo(null); // center window on the screen
        setSize(windowWidth, windowHeight);
        setVisible(true);
    }
}