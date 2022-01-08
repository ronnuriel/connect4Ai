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
public int pickBestMove(State cBoard,int player){
    State nBoard = new State(cBoard);
    int[] Scores = new int[7];
    for(int i =0;i<7;i++)  Scores[i] = -100000000;
    for(int i =0;i<7;i++){
        if(nBoard.isCollumFree(i)!=-1){
            nBoard.makeMove(i+1,player);
            Scores[i]=nBoard.pointsFunction();
            nBoard = new State(cBoard);
        }
    }
    int bCol = -1;
    int mPoints = -1000000;
    for(int i =0;i<7;i++){
        System.out.println(Scores[i]);
        if(Scores[i]>mPoints){
            mPoints=Scores[i];
            bCol=i;
        }
    }
    return bCol+1;

}


}
