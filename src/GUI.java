//package connect4Ai.src.State;

//import com.company.Controller.Connect4Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.SQLOutput;
import javax.swing.*;

//@SuppressWarnings("serial")
public class GUI extends JFrame {
    private final Container cp;

    State game = new State();
    MinMax AI = new MinMax();

    int rows;
    int columns;
    int gameType; //PVP or PVC
    int AiLevel;

    int windowWidth = 750;
    int windowHeight = 750;

    // Prepare ImageIcons to be used with JComponents
    private ImageIcon iconEmpty = null;
    private ImageIcon iconRed = null;
    private ImageIcon iconYellow = null;
    private ImageIcon iconHard = null;
    private ImageIcon iconMedium = null;
    private ImageIcon iconEasy = null;
    private ImageIcon iconReset = null;
    private ImageIcon iconHint = null;


    private final String title = "Connect 4 - ";
    int count = 1;

    private void worldsBestUpdater(JButton button) {
        int row10plusCol = Integer.parseInt(button.getName());
        int col = row10plusCol % 10;

        if(count==-1) setTitle(title + "Yellow");
        else setTitle(title + "Red");

        int addedRow = game.isCollumFree(row10plusCol);

        if(addedRow != -1) {
            if (gameType == 1) {
                if (count == 1/*||count==-1*/) {
                    JButton buttonToUpdate = ((JButton) (cp.getComponent(columns * addedRow + col)));
                    buttonToUpdate.setIcon(iconYellow);
                    game.makeMove((row10plusCol + 1), 1);
                } else {
                    JButton buttonToUpdate = ((JButton) (cp.getComponent(columns * addedRow + col)));
                    buttonToUpdate.setIcon(iconRed);
                    game.makeMove((row10plusCol + 1), -1);
                }
                count *= -1;
                //game.printBoard(); // remove later
            }
            else {
                if (count == 1) {
                    JButton buttonToUpdate = ((JButton) (cp.getComponent(columns * addedRow + col)));
                    buttonToUpdate.setIcon(iconYellow);
                    game.makeMove((row10plusCol + 1), 1);
                } else {
                    AI.miniMax(game, AiLevel, false);
                    //System.out.print(AI.getCollumn());
                    //System.out.print(game.isCollumFree(AI.getCollumn()));
                    //System.out.println();
                    addedRow = game.isCollumFree(AI.getCollumn());
                    JButton buttonToUpdate = ((JButton) (cp.getComponent(columns * addedRow + AI.getCollumn())));
                    buttonToUpdate.setIcon(iconRed);
                    game.makeMove(AI.getCollumn() + 1, -1);

                }
                count *= -1;
                //game.printBoard(); // remove later
            }
                // check for winner
                if (game.checkGameOver()) {
                    if (game.checkForWinnerInGUI(count)&&gameType==-1) {
                        if (game.getWinner() == 1) {
                            JOptionPane.showMessageDialog(null, "You have won!");
                        } else {
                            JOptionPane.showMessageDialog(null, "AI Has Won!");
                        }
                    }
                    if (game.checkForWinnerInGUI(count)&&gameType==1) {
                        if (game.getWinner() == 1) {
                            JOptionPane.showMessageDialog(null, "Yellow has won!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Red has Won!");
                        }
                    }
                    else if(!game.checkIfWin()){
                        JOptionPane.showMessageDialog(null, "Its a tie!");
                    }
                    int reply = JOptionPane.showConfirmDialog(null, "Would you like to play again?", null, JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION) {
                        System.out.println("Trying to play again...");
                        game.reset();
                        resetBoard();
                        MinMax AI = new MinMax();
                        count = 1;
                        gameType();
                    } else {
                        System.exit(0);
                    }
                }
            }
        else {
            JOptionPane.showMessageDialog(null, "Please select a valid position.");
        }
    }

    public void resetBoard(){
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

        String imgHardFilename = "images/Hard.png";
        imgURL = getClass().getClassLoader().getResource(imgHardFilename);
        if (imgURL != null) iconHard = new ImageIcon(imgURL);
        else System.err.println("Couldn't find file: " + imgHardFilename);

        String imgEasyFilename = "images/Easy.png";
        imgURL = getClass().getClassLoader().getResource(imgEasyFilename);
        if (imgURL != null) iconEasy = new ImageIcon(imgURL);
        else System.err.println("Couldn't find file: " + imgEasyFilename);

        String imgMediumFilename = "images/Medium.png";
        imgURL = getClass().getClassLoader().getResource(imgMediumFilename);
        if (imgURL != null) iconMedium = new ImageIcon(imgURL);
        else System.err.println("Couldn't find file: " + imgMediumFilename);

        String imgHintFilename = "images/Hint.png";
        imgURL = getClass().getClassLoader().getResource(imgHintFilename);
        if (imgURL != null) iconHint = new ImageIcon(imgURL);
        else System.err.println("Couldn't find file: " + imgHintFilename);

        String imgResetFilename = "images/Reset.png";
        imgURL = getClass().getClassLoader().getResource(imgResetFilename);
        if (imgURL != null) iconReset = new ImageIcon(imgURL);
        else System.err.println("Couldn't find file: " + imgResetFilename);

        cp = getContentPane();
        cp.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        gameType();
        for(int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                JButton button = new JButton(); // use setter to set text and icon
                button.setIcon(iconEmpty);
                button.setPreferredSize(new Dimension(100, 100));
                button.setName(Integer.toString((row * 10 + col)));

                button.addActionListener(actionEvent -> worldsBestUpdater(((JButton) (actionEvent.getSource()))));
                cp.add(button);

            }
        }
        AiLevel = 5;
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(100, 100));
        button.setName(Integer.toString((101)));
        button.setIcon(iconEasy);
        button.addActionListener(actionEvent -> AiLevel=5);
        cp.add(button);


        JButton button1 = new JButton();
        button1.setPreferredSize(new Dimension(100, 100));
        button1.setName(Integer.toString((102)));
        button1.setIcon(iconMedium);
        button1.addActionListener(actionEvent -> AiLevel=6);
        cp.add(button1);

        JButton button2 = new JButton();
        button2.setPreferredSize(new Dimension(100, 100));
        button2.setName(Integer.toString((103)));
        button2.setIcon(iconHard);
        button2.addActionListener(actionEvent -> AiLevel=7);
        cp.add(button2);

        JButton button3 = new JButton();
        button3.setPreferredSize(new Dimension(100, 100));
        button3.setName(Integer.toString((104)));
        button3.setIcon(iconReset);
        button3.addActionListener(actionEvent -> reset());
        cp.add(button3);

        JButton button4 = new JButton();
        button4.setPreferredSize(new Dimension(100, 100));
        button4.setName(Integer.toString((105)));
        button4.setIcon(iconHint);
        button4.addActionListener(actionEvent -> hint());
        cp.add(button4);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if(count==1) setTitle(title + "Yellow");
        else setTitle(title + "Red");
        setLocationRelativeTo(null); // center window on the screen
        setSize(windowWidth, windowHeight);
        setVisible(true);
    }
    public void gameType(){
        int reply = JOptionPane.showConfirmDialog(null, "Would you like to play agianst the Ai?", null, JOptionPane.YES_NO_OPTION);
        if(reply == JOptionPane.NO_OPTION) {
            gameType = 1;
        }
        else {
            gameType=-1;
        }
    }
    public void reset(){
        System.out.println("Trying to play again...");
        game.reset();
        resetBoard();
        MinMax AI = new MinMax();
        count = 1;
        gameType();
    }
    public void hint(){
        AI.miniMax(game,6,true);
        int bestPlace = AI.getCollumn()+1;
        JOptionPane.showMessageDialog(null, "The Ai recommends placing in collunm number: " +
                bestPlace);
    }
}