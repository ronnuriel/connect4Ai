public class MinMax {

   private int col;
    public void setCollumn(int i){
        col=i;
    }
    public int getCollumn(){
        return col;
    }

    /*
    public int pickBestMove(State cBoard,int player){
    State nBoard = new State(cBoard);
    int[] Scores = new int[7];
    for(int i =0;i<7;i++)  Scores[i] = -100000000;
    for(int i =0;i<7;i++){
        if(nBoard.isCollumFree(i)!=-1){
            nBoard.makeMove(i+1,player);
            Scores[i]=nBoard.pointsFunction();
            if(i==3)Scores[i]+=4;
            nBoard = new State(cBoard);
        }
    }
    int bCol = -1;
    int mPoints = -1000000;
    for(int i =0;i<7;i++){
        if(Scores[i]>mPoints){
            mPoints=Scores[i];
            bCol=i;
        }
    }
    return bCol+1;

}
*/
public int miniMax(State cBoard,int depth, boolean maximizingPlayer) {
    int value = 0;
    State nBoard;
    if (depth == 0 || cBoard.checkGameOver()) {
        if (cBoard.getWinner() == -1){
            return 1000000000;
        }
        if (cBoard.getWinner() == 1){
            return -1000000000;
        }
        if (depth == 0){
            return cBoard.pointsFunction();
        }
        return 0; //game is over, no valid moves.
    }
    if (maximizingPlayer) {
        int new_score;
        value = -1000000000;
        for (int i = 0; i < 7; i++) {
            nBoard = new State(cBoard);
            if (nBoard.isCollumFree(i) != -1) {
                nBoard.makeMove(i + 1, -1);
                new_score = miniMax(nBoard, depth - 1, false);
                if(new_score>value){
                    value=new_score;
                    setCollumn(i);
                }

            }
            //return value;
        }

    } else { //Minimizing player
        int new_score;
        value = 1000000000;
        for (int i = 0; i < 7; i++) {
            nBoard = new State(cBoard);
            if (nBoard.isCollumFree(i) != -1) {
                nBoard.makeMove(i + 1, 1);
                new_score = miniMax(nBoard, depth - 1, true);
                if(new_score<value){
                    value=new_score;
                    setCollumn(i);
                }

            }
            //return value;
            }

        }
    return value;
    }
}



