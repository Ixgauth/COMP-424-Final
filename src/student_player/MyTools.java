package student_player;

import java.util.List;

import boardgame.Move;

import tablut.TablutBoardState;
import tablut.TablutMove;

public class MyTools {
    public static double getSomething() {
        return Math.random();
    }
    
    public static Move alphaBetaTopLevel(Node n, double alpha, double beta, boolean minOrMax, int depth, int firstPlayerID, int opponent)
    {
    	double bestValue;
    	Move choice = n.boardState.getRandomMove();
    	if(minOrMax)
    	{
    		bestValue = alpha;
    		
    		List<TablutMove> options = n.boardState.getAllLegalMoves();
    		
    		for (TablutMove move : options)
        	{
        		TablutBoardState cloneBS = (TablutBoardState) n.boardState.clone();
       			cloneBS.processMove(move);
       			
       			if(cloneBS.getWinner() == firstPlayerID)
    	    	{
    	    		bestValue = 1000;
//    	    		System.out.println(bestValue);
//    	    		System.out.println("");
    	    		return move;
    	    	}
       			
       			Node child = new Node(cloneBS);
       			child.setPlayer(firstPlayerID, opponent);
       			n.addChild(child);
       			double childValue = alphaBeta(child, bestValue, beta, false, depth+1, firstPlayerID, opponent);
       			if(childValue > bestValue)
       			{
       				choice = move;
       			}
       			bestValue = Math.max(childValue, bestValue);
       			
       			
       			if(alpha <= bestValue)
       			{
       				alpha = bestValue; 
       			}
       			if(alpha >= beta)
       			{
       				break;
       			}
       			
       		}
    		
    	}
    	else
    	{
    		bestValue = beta;
    		
    		List<TablutMove> options = n.boardState.getAllLegalMoves();
    		
    		
    		for (TablutMove move : options)
        	{
    			
        		TablutBoardState cloneBS = (TablutBoardState) n.boardState.clone();
       			cloneBS.processMove(move);
       			
       			if(cloneBS.getWinner() == firstPlayerID)
    	    	{
    	    		bestValue = 1000;
    	    		return move;
    	    	}
       			
       			Node child = new Node(cloneBS);
       			child.setPlayer(firstPlayerID, opponent);
       			n.addChild(child);
       			
       			double childValue = alphaBeta(child, alpha, bestValue, true, depth+1, firstPlayerID, opponent);
       			if(childValue < bestValue)
       			{
       				choice = move;
       			}
       			bestValue = Math.min(bestValue, childValue);
       			
       			
       			if (bestValue <= beta) 
       			{
       				beta = bestValue;
       			}
       			if(alpha >= beta)
       			{
       				break;
       			}
       		}
    		
    	}
    	return choice;
    }
    
    public static double alphaBeta(Node n, double alpha, double beta, boolean minOrMax, int depth, int firstPlayerID, int opponent)
    {
    	double bestValue;
    	
    	if(n.boardState.getWinner() == firstPlayerID)
    	{
    		bestValue = 1000;
    		return bestValue;
    	}
    	else if(n.boardState.getWinner() == opponent)
    	{
    		bestValue = -1000;
    		return bestValue;
    	}
    	
    	if(depth == 3)
    	{
    		bestValue = n.abValue;
    		return bestValue;
    	}
    	else if(minOrMax)
    	{
    		bestValue = alpha;
    		
    		List<TablutMove> options = n.boardState.getAllLegalMoves();
    		
    		for (TablutMove move : options)
        	{
        		TablutBoardState cloneBS = (TablutBoardState) n.boardState.clone();
       			cloneBS.processMove(move);
       			Node child = new Node(cloneBS);
       			child.setPlayer(firstPlayerID, opponent);
       			n.addChild(child);
       			double childValue = alphaBeta(child, bestValue, beta, false, depth+1, firstPlayerID, opponent);
       			bestValue = Math.max(childValue, bestValue);
       			if(alpha <= bestValue)
       			{
       				alpha = bestValue; 
       			}
       			if(alpha >= beta)
       			{
       				break;
       			}
       			
       		}
    		
    	}
    	else
    	{
    		bestValue = beta;
    		
    		List<TablutMove> options = n.boardState.getAllLegalMoves();
    		
    		
    		for (TablutMove move : options)
        	{
        		TablutBoardState cloneBS = (TablutBoardState) n.boardState.clone();
       			cloneBS.processMove(move);
       			Node child = new Node(cloneBS);
       			child.setPlayer(firstPlayerID, opponent);
       			n.addChild(child);
       			
       			double childValue = alphaBeta(child, alpha, bestValue, true, depth+1, firstPlayerID, opponent);
       			bestValue = Math.min(bestValue, childValue);
       			if (bestValue <= beta) 
       			{
       				beta = bestValue;
       			}
       			if(alpha >= beta)
       			{
       				break;
       			}
       		}
    		
    	}
    	
    	return bestValue;
    }
}