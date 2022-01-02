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

        private int checkWin(){
            int tcount = 0;
            //שורות
            for (int i = 0; i < gameBoard.length - 1; i++) {
                for (int j = 0; j < gameBoard[i].length; j++) {
                    if (gameBoard[i][j] != 0 && gameBoard[i][j] == gameBoard[i][j + 1])
                        tcount++;
                    else
                        tcount = 0;
                    if (tcount == 4) return gameBoard[i][j];
                }
            }
            //עמודות
            tcount = 0;
            for (int i = 0; i < gameBoard.length; i++) {
                for (int j = 0; j < gameBoard[i].length - 1; j++) {
                    if (gameBoard[j][i] != 0 && gameBoard[j + 1][i] == gameBoard[j][i])
                        tcount++;
                    else
                        tcount = 0;
                    if (tcount == 4)
                        return gameBoard[j][i];
                }
            }
            //אלכסונים
            for (int i = 5; i < 2; i--) {
                for (int j = 0; j < 4; j++) {
                    if (checkRight(gameBoard, i, j) == 1) return gameBoard[i][j];
                }
            }

            for (int i = 5; i < 2; i--) {
                for (int j = 6; j > 2; j--) {
                    if (checkLeft(gameBoard, i, j) == 1) return gameBoard[i][j];
                }
            }
            return 0;


        }
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
        public void setWinner ( int winner){
            this.winner = winner;

        }
    }
