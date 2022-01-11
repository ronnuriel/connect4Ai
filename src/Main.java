import java.net.StandardSocketOptions;
import java.util.*;
public class Main {
    //THE HOST

    public static void main (String[] main){
        Scanner input = new Scanner(System.in);
      State game = new State();
        int count = 1;
      /* <--- two player game
      while(!game.checkGameOver()){
          game.printBoard();
          if(count==1){
              System.out.println("Player 1's Turn!");
          }
          else{
              System.out.println("Player 2's Turn!");
          }
          System.out.println("Please Pick your column:");
          int choice = input.nextInt();
          while(!game.makeMove(choice,count))
              choice=input.nextInt();
          count = count*-1;
      }
      game.printBoard();
      if(game.getWinner()==1){
          System.out.print("Player one: " + game.getWinnerMethod());
      }
      else{
          if(game.getWinner()==-1){
              System.out.print("Player two: " + game.getWinnerMethod());
          }
          else{
              System.out.print("Its a tie!");
          }
      }*/
        // AI VS Player game

        MinMax AI = new MinMax();

        while(!game.checkGameOver()){
            game.printBoard();
            if(count==1){
                System.out.println("Player 1's Turn!");
                System.out.println("Please Pick your column:");
                int choice = input.nextInt();
                while(!game.makeMove(choice,count))
                    choice=input.nextInt();
            }
            else{
                System.out.println("AI's Turn!");
                AI.miniMax(game,7,false);
                game.makeMove(AI.getCollumn()+1,-1);
            }
            count = count*-1;
        }
        game.printBoard();
        if(game.getWinner()==1){
            System.out.print("Player one: " + game.getWinnerMethod());
        }
        else{
            if(game.getWinner()==-1){
                System.out.print("AI: " + game.getWinnerMethod());
            }
            else{
                System.out.print("Its a tie!");
            }
        }

    }

}
