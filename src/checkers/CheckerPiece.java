package checkers;

import java.awt.Color;

import general.Piece;
import general.Side;

public class CheckerPiece extends Piece 
{
	private boolean isKing;
	
	public CheckerPiece(Color c, Side s)
	{
		super(s, c);
		
	}
	
	public void king()
	{
		isKing = true;
	}
	
	public boolean canMove(Piece[][] board, int[] current, int[] moveTo) 
	{
		try
		{
			if(board[current[0]][current[1]] != this)
				throw new IllegalArgumentException();
			
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			return false;
		}
	}
}
