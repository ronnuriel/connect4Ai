public class MinMax {
    //Implinet min max func

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



}
