public class State {

    static final int X = 1;     //User (used in Main and switch case)
    static final int O = -1;    //Computer (used in Main and switch case)
    int EMPTY = 0;              //Blank space
    //We need to know the player that made the last move
    GamePlay lastMove;
    int lastLetterPlayed;
    int winner;
    int [][] gameBoard;
    String winningMethod;       //Winner by [row, column, diagonal]
    //------------

    public State() {
        lastMove = new GamePlay();
        lastLetterPlayed = O; //The user starts first
        winner = 0;
        gameBoard = new int[6][7]; //creates the board
        for(int i=0; i<6; i++) {
            for(int j=0; j<7; j++) {
                gameBoard[i][j] = EMPTY;
            }
        }
    }
//checks if there is a win, if answer is true it will check who and how
        public boolean checkIfWin(){
            int tcount = 0;
            //שורות
            for (int i = 0; i < gameBoard.length-1; i++) {
                for (int j = 0; j < gameBoard[i].length; j++) {
                    if (gameBoard[i][j] != 0 && gameBoard[i][j] == gameBoard[i][j + 1])
                        tcount++;
                    else
                        tcount = 0;
                    if (tcount == 4){
                      setWinner(gameBoard[i][j]);
                      setWinnerMethod("Winner by Row!");
                      return true;
                   }
                }
                tcount = 0;
            }
            //עמודות
            tcount = 0;
            for (int i = 0; i < gameBoard.length; i++) {
                for (int j = 0; j < gameBoard[i].length - 1; j++) {
                    if (gameBoard[j][i] != 0 && gameBoard[j + 1][i] == gameBoard[j][i])
                        tcount++;
                    else
                        tcount = 0;
                    if (tcount == 4){
                      setWinner(gameBoard[j][i]);
                      setWinnerMethod("Winner by Collum!");
                      return true;
                      }
                }
                tcount = 0;
            }
            //אלכסונים
            for (int i = 5; i < 2; i--) {
                for (int j = 0; j < 4; j++) {
                    if (checkRight(gameBoard, i, j) == 1){
                      setWinner(gameBoard[i][j]);
                      setWinnerMethod("Winner by Diagonal!");
                     return true;
                   }
                }
            }

            for (int i = 5; i < 2; i--) {
                for (int j = 6; j > 2; j--) {
                    if (checkLeft(gameBoard, i, j) == 1){
                      setWinner(gameBoard[i][j]);
                      setWinnerMethod("Winner by Diagonal!");
                     return true;
                    }
                }
            }
            setWinner(0);
            return false;
//no winner

        }
//end of checkIfWin

//side functions for checkIfWin (4 in a row)
    private int checkRight(int[][] Board,int row,int coll){
        for(int i =0;i<4;i++){
            if(Board[row][coll]!=Board[row-1][coll+1])return 0;
            row--;
            coll++;
        }
        return 1;
    }
    private int checkLeft(int[][] Board,int row,int coll){
        for(int i =0;i<4;i++){
            if(Board[row][coll]!=Board[row-1][coll-1])return 0;
            row--;
            coll--;
        }
        return 1;
    }
//end of side functions

    //place holders for who won and how:
        public void setWinner (int winner){
            this.winner = winner;

        }
        public void setWinnerMethod(String w){
          this.winningMethod=w;
        }
//end of place holders

//returns the place in the collum that is free, else  returns -1
        public int isCollumFree(int c){
          for(int i = 5;i>=0;i--){
            if(gameBoard[i][c]== EMPTY)return i;
          }
          return -1;
        }
//end of collum checks

//checks if game is over
        public boolean checkGameOver(){
          if(checkIfWin)return true;
          for(int i =0;i<6;i++){
            for(int j = 0;j<7;j++){
              if(gameBoard[i][j]== EMPTY) return false;
            }
          }
          return true;
        }
//end checkGameOver

//check if there are 3 in a row
      public int check3In(int p){
        int  times = 0;
        int tcount = 0;
        //שורות
        for (int i = 0; i < gameBoard.length-1; i++) {
          tcount = 0;
            for (int j = 0; j < gameBoard[i].length; j++) {
                if (gameBoard[i][j] == p && gameBoard[i][j] == gameBoard[i][j + 1])
                    tcount++;
                else
                    tcount = 0;
                  }
                if (tcount == 3){
                  times++;
               }

        }
        //עמודות
        tcount = 0;
        for (int i = 0; i < gameBoard.length; i++) {
          tcount = 0;
            for (int j = 0; j < gameBoard[i].length - 1; j++) {
                if (gameBoard[j][i] == p && gameBoard[j + 1][i] == gameBoard[j][i])
                    tcount++;
                else
                    tcount = 0;
                  }
                if (tcount == 3){
                  times++;
                  }

        }
        //אלכסונים
        for (int i = 5; i < 1; i--) {
            for (int j = 0; j < 5; j++) {
                if (checkRight3(gameBoard, i, j,p) == 1){
                  times++;
               }
            }
        }

        for (int i = 5; i < 1; i--) {
            for (int j = 6; j > 1; j--) {
                if (checkLeft3(gameBoard, i, j,p) == 1){
                  times++;
                }
            }
        }
        return times;
      }
      //end of check3Ln


      //side functions for check3Ln (3 in a row)
          private int checkRight3(int[][] Board,int row,int coll,int p){
              for(int i =0;i<3;i++){
                  if(Board[row][coll]!=p || Board[row][coll]!=Board[row-1][coll+1])return 0;
                  row--;
                  coll++;
              }
              return 1;
          }
          private int checkLeft3(int[][] Board,int row,int coll,int p){
              for(int i =0;i<3;i++){
                  if(Board[row][coll]!=p || Board[row][coll]!=Board[row-1][coll-1])return 0;
                  row--;
                  coll--;
              }
              return 1;
          }
          //end of side functions



//check if there are 2 in a row
          public int chec2In(int p){
            int times  = 0;
            int tcount = 0;
            //שורות
            for (int i = 0; i < gameBoard.length-1; i++) {
              tcount = 0;
                for (int j = 0; j < gameBoard[i].length; j++) {
                    if (gameBoard[i][j] == p && gameBoard[i][j] == gameBoard[i][j + 1])
                        tcount++;
                    else
                        tcount = 0;
                      }
                    if (tcount == 2){
                      times++;
                   }

            }
            //עמודות
            tcount = 0;
            for (int i = 0; i < gameBoard.length; i++) {
              tcount = 0;
                for (int j = 0; j < gameBoard[i].length - 1; j++) {
                    if (gameBoard[j][i] == p && gameBoard[j + 1][i] == gameBoard[j][i])
                        tcount++;
                    else
                        tcount = 0;
                      }
                    if (tcount == 2){
                      times++;
                      }

            }
            //אלכסונים
            for (int i = 5; i < 0; i--) {
                for (int j = 0; j < 6; j++) {
                    if (checkRight2(gameBoard, i, j,p) == 1){
                      times++;
                   }
                }
            }

            for (int i = 5; i < 0; i--) {
                for (int j = 6; j > 0; j--) {
                    if (checkLeft2(gameBoard, i, j,p) == 1){
                      times++;
                    }
                }
            }
            return times;
          }
          //end of check2Ln


          //side functions for check2Ln (2 in a row)
              private int checkRight2(int[][] Board,int row,int coll,int p){
                  for(int i =0;i<2;i++){
                      if(Board[row][coll]!=p || Board[row][coll]!=Board[row-1][coll+1])return 0;
                      row--;
                      coll++;
                  }
                  return 1;
              }
              private int checkLeft2(int[][] Board,int row,int coll,int p){
                  for(int i =0;i<2;i++){
                      if(Board[row][coll]!=p || Board[row][coll]!=Board[row-1][coll-1])return 0;
                      row--;
                      coll--;
                  }
                  return 1;
              }
              //end of side functions

              public int pointsFunction(){
                //Computer players 'O'
                // if computer wins: +90
                //if computer has 3 'O' in a row = +10. if 3 'X' in a row = -5.
                //if computer has 2 'O' in a row = +4. if 2 'X' in a row = -1.
                int Xpoints = 0;
                int Opoints = 0;
                if(checkIfWin){
                  if(winner==-1){
                    Opoints+=90;
                  }
                  else{
                    Xpoints+=90;
                  }
                }
                Xlines+= check3In(-1)*10 + check2In(-1)*4;
                Olines+= check3In(1)*5 + check2In(1);
                return Olines-Xlines;


              }


              //Print the board
              public void printBoard() {
                  System.out.println("| 1 | 2 | 3 | 4 | 5 | 6 | 7 |");
                  System.out.println();
                  for (int i=0; i<6; i++) {
                      for (int j=0; j<7; j++) {
                              if (gameBoard[i][j] == 1) {
                                  System.out.print("| " + "X "); //Blue for user
                              } else if (gameBoard[i][j] == -1) {
                                  System.out.print("| " + "O "); //Red for computer
                              } else {
                                  System.out.print("| " + "-" + " ");
                              }
                      }
                      System.out.println("|"); //End of each row
                  }
              }//end printBoard







    }
