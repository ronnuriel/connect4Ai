import java.net.StandardSocketOptions;
import java.util.*;
public class Main {
    //THE HOST

    public static void main (String[] main){
        Scanner input = new Scanner(System.in);
//Must check if there is a winner each round, before checking if there is 3 in a row, or 2.
      State game = new State();
      int count = 1;
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
      }
    }

}
