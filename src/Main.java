import java.net.StandardSocketOptions;

public class Main {
    //THE HOST

    public static void main (String[] main){
//Must check if there is a winner each round, before checking if there is 3 in a row, or 2.
      State test = new State();
      test.printBoard();
      test.makeMove(1,2);
      /*
      test.makeMove(2,1);
        test.makeMove(2,2);
        test.makeMove(3,1);
        test.makeMove(3,1);
        test.makeMove(3,1);
        test.makeMove(4,1);
        test.makeMove(4,1);
        test.makeMove(4,1);
        test.makeMove(4,2);

*/

      test.printBoard();
      System.out.print(test.checkIfWin());
    }

}
