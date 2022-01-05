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
        public boolean makeMove(int col, int player){
        if(col-1<0||col-1>7){
            System.out.println("Illegal Collum choice, please pick agian:");
            return false;
        }
        if(isCollumFree(col-1)==-1){
             System.out.println("This collum is full, please pick a diffrent one:");
                return false;
         }
            if(player%2==1){
            gameBoard[isCollumFree(col-1)][col-1]=1;
            }
            else{
            gameBoard[isCollumFree(col-1)][col-1]=-1;
            }
            return true;
    }

    //place holders for who won and how:
    public void setWinner (int winner){this.winner = winner;}
    public void setWinnerMethod(String w){
        this.winningMethod=w;
    }
    public int getWinner(){return winner;}
    public String getWinnerMethod(){return winningMethod;}
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
        if(checkIfWin())return true;
        for(int i =0;i<6;i++){
            for(int j = 0;j<7;j++){
                if(gameBoard[i][j]== EMPTY) return false;
            }
        }
        return true;
    }
    //end checkGameOver

    //checks if given position is legal (in the board)
    public boolean isLegal(int row,int col){
        if ((row <= -1) || (col <= -1) || (row > 5) || (col > 6)) {
            return false;
        }
        return true;
    }
    //end isLegal


//checks if there is a win, if answer is true it will check who and how
        public boolean checkIfWin(){
            //שורות
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {
                    if (isLegal(i,j+3)){
                        if (gameBoard[i][j] != EMPTY && gameBoard[i][j] == gameBoard[i][j + 1]&&
                        gameBoard[i][j+1]==gameBoard[i][j+2]&&gameBoard[i][j+2]==gameBoard[i][j+3]){
                            setWinner(gameBoard[i][j]);
                            setWinnerMethod("Winner by Row!");
                            return true;
                        }
                    }

                }
            }
            //עמודות
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {
                    if (isLegal(i + 3, j)) {
                        if (gameBoard[i][j] != EMPTY && gameBoard[i + 1][j] == gameBoard[i][j]
                                && gameBoard[i + 1][j] == gameBoard[i + 2][j] && gameBoard[i + 3][j] == gameBoard[i + 2][j]) {
                            setWinner(gameBoard[i][j]);
                            setWinnerMethod("Winner by Collum!");
                            return true;
                        }
                    }
                }
            }
            //אלכסונים

            for (int i =0;i<6;i++) {
                for (int j = 0; j < 7; j++) {
                    if(isLegal(i-3,j+3)){
                    if (gameBoard[i][j]==gameBoard[i-1][j+1]&&gameBoard[i-1][j+1]==gameBoard[i-2][j+2]&&
                    gameBoard[i-2][j+2]==gameBoard[i-3][j+3]&&gameBoard[i][j]!= EMPTY) { //checks if equal
                        setWinner(gameBoard[i][j]);
                        setWinnerMethod("Winner by Diagonal!");
                        return true;
                    }
                }

                }
            }
            for (int i =0;i<6;i++) {
                for (int j = 0; j < 7; j++) {
                    if(isLegal(i-3,j-3)){
                        if (gameBoard[i][j]==gameBoard[i-1][j-1]&&gameBoard[i-1][j-1]==gameBoard[i-2][j-2]&&
                                gameBoard[i-2][j-2]==gameBoard[i-3][j-3]&&gameBoard[i][j]!= EMPTY) { //checks if equal
                            setWinner(gameBoard[i][j]);
                            setWinnerMethod("Winner by Diagonal!");
                            return true;
                        }
                    }

                }
            }
            setWinner(0);
            return false;
//no winner

        }
//end of checkIfWin


//check if there are 3 in a row
      public int check3In(int p){
        int  times = 0;
          //שורות
          for (int i = 0; i < 6; i++) {
              for (int j = 0; j < 7; j++) {
                  if (isLegal(i,j+2)){
                      if (gameBoard[i][j]==p && gameBoard[i][j] != EMPTY && gameBoard[i][j] == gameBoard[i][j + 1]&&
                              gameBoard[i][j+1]==gameBoard[i][j+2]){
                          times++;
                      }
                  }

              }
          }
          //עמודות
          for (int i = 0; i < 6; i++) {
              for (int j = 0; j < 7; j++) {
                  if (isLegal(i + 2, j)) {
                      if (gameBoard[i][j]==p && gameBoard[i][j] != EMPTY && gameBoard[i + 1][j] == gameBoard[i][j]
                              && gameBoard[i + 1][j] == gameBoard[i + 2][j]) {
                          times++;
                      }
                  }
              }
          }
          //אלכסונים

          for (int i =0;i<6;i++) {
              for (int j = 0; j < 7; j++) {
                  if(isLegal(i-2,j+2)){
                      if (gameBoard[i][j]==p && gameBoard[i][j]==gameBoard[i-1][j+1]&&gameBoard[i-1][j+1]==gameBoard[i-2][j+2]&&
                              gameBoard[i][j]!= EMPTY) {
                          times++;
                      }
                  }

              }
          }
          for (int i =0;i<6;i++) {
              for (int j = 0; j < 7; j++) {
                  if(isLegal(i-2,j-2)){
                      if (gameBoard[i][j]==p && gameBoard[i][j]==gameBoard[i-1][j-1]&&gameBoard[i-1][j-1]==gameBoard[i-2][j-2]&&
                              gameBoard[i][j]!= EMPTY) {
                          times++;
                      }
                  }

              }
          }
        return times;
      }
      //end of check3Ln

//check if there are 2 in a row
          public int check2In(int p){
              int  times = 0;
              //שורות
              for (int i = 0; i < 6; i++) {
                  for (int j = 0; j < 7; j++) {
                      if (isLegal(i,j+1)){
                          if (gameBoard[i][j]==p && gameBoard[i][j] != EMPTY && gameBoard[i][j] == gameBoard[i][j + 1]){
                              times++;
                          }
                      }

                  }
              }
              //עמודות
              for (int i = 0; i < 6; i++) {
                  for (int j = 0; j < 7; j++) {
                      if (isLegal(i + 1, j)) {
                          if (gameBoard[i][j]==p && gameBoard[i][j] != EMPTY && gameBoard[i + 1][j] == gameBoard[i][j]) {
                              times++;
                          }
                      }
                  }
              }
              //אלכסונים

              for (int i =0;i<6;i++) {
                  for (int j = 0; j < 7; j++) {
                      if(isLegal(i-1,j+1)){
                          if (gameBoard[i][j]==p && gameBoard[i][j]==gameBoard[i-1][j+1]&&
                                  gameBoard[i][j]!= EMPTY) {
                              times++;
                          }
                      }

                  }
              }
              for (int i =0;i<6;i++) {
                  for (int j = 0; j < 7; j++) {
                      if(isLegal(i-1,j-1)){
                          if (gameBoard[i][j]==p && gameBoard[i][j]==gameBoard[i-1][j-1]&&
                                  gameBoard[i][j]!= EMPTY) {
                              times++;
                          }
                      }

                  }
              }
              return times;
          }

              //end of side functions

              public int pointsFunction(){
                //Computer players 'O'
                // if computer wins: +90
                //if computer has 3 'O' in a row = +10. if 3 'X' in a row = -5.
                //if computer has 2 'O' in a row = +4. if 2 'X' in a row = -1.
                int Xpoints = 0;
                int Opoints = 0;
                if(checkIfWin()){
                  if(winner==-1){
                    Opoints+=90;
                  }
                  else{
                    Xpoints+=90;
                  }
                }
                Xpoints+= check3In(-1)*10 + check2In(-1)*4;
                Opoints+= check3In(1)*5 + check2In(1);
                return Opoints-Xpoints;


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
