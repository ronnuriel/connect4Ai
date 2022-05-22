public class MinMax {
   /* //Implement min max func

//    function  minimax(node, depth, maximizingPlayer) is
//    if depth = 0 or node is a terminal node then
//        return the heuristic value of node
//    if maximizingPlayer then
//    value := −∞
//            for each child of node do
//    value := max(value, minimax(child, depth − 1, FALSE))
//            return value
//    else (* minimizing player *)
//    value := +∞
//            for each child of node do
//    value := min(value, minimax(child, depth − 1, TRUE))
//            return value


public int minimax(node, int depth,char maximizingPlayer) {
    if depth == 0 or :
    if is_terminal:
    if winning_move(board, AI_PIECE):
    return (None, 100000000000000)
    elif winning_move(board, PLAYER_PIECE):
    return (None, -10000000000000)
			else: # Game is over, no more valid moves
    return (None, 0)
		else: # Depth is zero
    return (None, score_position(board, AI_PIECE))
}
*/
   private int col;
    public void setCollumn(int i){
        col=i;
    }
    public int getCollumn(){
        return col;
    }
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
                new_score = miniMax(nBoard, depth - 1, maximizingPlayer);
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
/*
public int[] miniMax(State cBoard,int depth, boolean maximizingPlayer) {
    int[] ret = new int[2]; ret[0]=-1; ret[1]=0; //0 = location, 1 = points
    int value = 0;
    int[] new_score = new int[2]; new_score[0] = 0; new_score[1]=-1;
    for(int i = 0;i<7;i++){
        if(cBoard.isCollumFree(i)!=-1 && new_score[0] == -1)
            new_score[0] = i;
    }
    State nBoard;
    if (depth == 0 || cBoard.checkGameOver()) {
        if (cBoard.getWinner() == -1){
            ret[1]=1000;
            return ret;
        }
        if (cBoard.getWinner() == 1){
            ret[1]=-1000;
            return ret;
        }
        if (depth == 0){
            ret[1]= cBoard.pointsFunction();
            return ret;
        }
        return ret; //game is over, no valid moves.
    }
    if (maximizingPlayer) {
        value = -1000000000;
        for (int i = 0; i < 7; i++) {
            nBoard = new State(cBoard);
            if (nBoard.isCollumFree(i) != -1) {
                nBoard.makeMove(i + 1, -1);
                new_score = miniMax(nBoard, depth - 1, false);
                if(new_score[1]>value){
                    value=new_score[1];
                    new_score[0]=i;
                }

            }
        }
        new_score[1]=value;
        return new_score;
    } else { //Minimizing player
        value = 1000000000;
        for (int i = 0; i < 7; i++) {
            nBoard = new State(cBoard);
            if (nBoard.isCollumFree(i) != -1) {
                nBoard.makeMove(i + 1, 1);
                new_score = miniMax(nBoard, depth - 1, true);
                if(new_score[1]<value){
                    value=new_score[1];
                    new_score[0]=i;
                }

            }
            }
        new_score[1]=value;
        return new_score;
        }

    }
 */


