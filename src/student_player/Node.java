package student_player;

import java.util.ArrayList;
import java.util.List;

import boardgame.ServerGUI;

import coordinates.Coord;
import coordinates.Coordinates;

import tablut.TablutBoardState;
import tablut.TablutMove;

public class Node {
	public List<Node> children = null;
	public TablutBoardState boardState = null;
	public double abValue;
	public int player;
	public int opponent;
	
	public Node(TablutBoardState boardState)
    {
        this.children = new ArrayList<>();
        this.boardState = boardState;
    }
	
	public void addChild(Node child)
    {
        children.add(child);
    }
	
	public void setPlayer(int playerNumber, int opponentName)
	{
		player = playerNumber;
		opponent = opponentName;
		setABValue();
	}

	private void setABValue()
	{
    	
    	
    	int playerPieces = boardState.getNumberPlayerPieces(player);
    	
    	int numberOfApPieces = boardState.getNumberPlayerPieces(opponent);
    	
    	int netPieces = playerPieces - numberOfApPieces;
    	
    	if(player == 1)
    	{
    		
    		int pieceNextToKing = 0;
    		
    		int kingDistance = 0;
    		
    		int kingThere = 0;
    		
    		try 
    		{
	    		Coord kingPosition = boardState.getKingPosition();
	    		
	    		kingDistance = Coordinates.distanceToClosestCorner(kingPosition);
	    		
	    		
	    		List<Coord> kingNeighbors = Coordinates.getNeighbors(kingPosition);
	    		for(int i = 0; i < kingNeighbors.size(); i++)
	    		{
	    			boolean pieceThere = boardState.isOpponentPieceAt(kingNeighbors.get(i));
	    			if(pieceThere)
	    			{
	    				pieceNextToKing++;
	    			}
	    			if(Coordinates.getSandwichCoord(kingNeighbors.get(i), kingPosition) == null)
	    			{
    					pieceNextToKing--;
    					System.out.println("gothere");
	    			}
	    			
	    		}
	    		
	    		List<Coord> corners = Coordinates.getCorners();
	    		for(int i = 0; i < corners.size(); i++)
	    		{
	    			TablutMove m = new TablutMove(kingPosition, corners.get(i), boardState.getTurnPlayer());
	    			boolean oneMove = boardState.isLegal(m);
	    			if(oneMove) 
	    			{
	    				kingThere++;
	    			}
	    		}
	    		
	    		this.abValue = netPieces - .1*kingDistance - pieceNextToKing;
	    		
	    	}
    		catch(Exception e)
    		{
    			this.abValue = netPieces - 10;
    		}
    		
    		
    		
    		
    		
    	}
    	else
    	{
    		int pieceNextToKing = 0;
    		
    		int kingThere = 0;
    		
    		
    		try 
    		{
	    		Coord kingPosition = boardState.getKingPosition();
	    		
	    		
	    		List<Coord> kingNeighbors = Coordinates.getNeighbors(kingPosition);
	    		for(int i = 0; i < kingNeighbors.size(); i++)
	    		{
	    			boolean pieceThere = boardState.isOpponentPieceAt(kingNeighbors.get(i));
	    			if(pieceThere)
	    			{
	    				pieceNextToKing++;
	    				if(Coordinates.getSandwichCoord(kingNeighbors.get(i), kingPosition) == null)
		    			{
	    					pieceNextToKing--;
		    			}
	    			}
	    			
	    		}
	    		
	    		List<Coord> corners = Coordinates.getCorners();
	    		for(int i = 0; i < corners.size(); i++)
	    		{
	    			TablutMove m = new TablutMove(kingPosition, corners.get(i), boardState.getTurnPlayer());
	    			boolean oneMove = boardState.isLegal(m);
	    			if(oneMove) 
	    			{
	    				kingThere++;
	    			}
	    		}
	    		
	    		this.abValue = playerPieces - numberOfApPieces*1.5 + pieceNextToKing*2 - kingThere*3;
	    		
	    	}
    		catch(Exception e)
    		{
    			this.abValue = playerPieces - numberOfApPieces*1.5 - 5;
    		}
    		
    	}

	}

}