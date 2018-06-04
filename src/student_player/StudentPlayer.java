package student_player;

import java.util.List;

import boardgame.Move;
import boardgame.ServerGUI;
import tablut.TablutBoardState;
import tablut.TablutMove;
import tablut.TablutPlayer;

/** A player file submitted by a student. */
public class StudentPlayer extends TablutPlayer {

    /**
     * You must modify this constructor to return your student number. This is
     * important, because this is what the code that runs the competition uses to
     * associate you with your agent. The constructor should do nothing else.
     */
    public StudentPlayer() {
        super("260654775");
    }

    /**
     * This is the primary method that you need to implement. The ``boardState``
     * object contains the current state of the game, which your agent must use to
     * make decisions.
     */
    public Move chooseMove(TablutBoardState boardState) {
        // You probably will make separate functions in MyTools.
        // For example, maybe you'll need to load some pre-processed best opening
        // strategies...
    	
    	List<TablutMove> options = boardState.getAllLegalMoves();
    	
    	Move currentMove = boardState.getRandomMove();
    	
    	int firstPlayerID = player_id;
    	
    	int opponent = boardState.getOpponent();
    	
    	Node n = new Node(boardState);
    	
    	if(player_id == TablutBoardState.SWEDE)
    	{
    		currentMove = MyTools.alphaBetaTopLevel(n, -10000, 10000, true, 0, firstPlayerID, opponent);		
    	}
    	else
    	{
    		currentMove = MyTools.alphaBetaTopLevel(n, -10000, 10000, true, 0, firstPlayerID, opponent);	
    	}
    	
    	
    	
//        MyTools.getSomething();
//
//        // Is random the best you can do?
//        Move myMove = boardState.getRandomMove();
//
//        // Return your move to be processed by the server.
//    	
    	Move myMove = currentMove;
        return myMove;
    }
}