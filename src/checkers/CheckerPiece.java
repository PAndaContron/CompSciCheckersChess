package checkers;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import general.Piece;
import general.Side;
import general.Utils;

public class CheckerPiece extends Piece 
{
	private final static List<int[]> diagonals = new ArrayList<>();
	private final static List<int[]> kingDiagonals = new ArrayList<>();
	
	static
	{
		diagonals.add(new int[] {1, 1});
		diagonals.add(new int[] {1, -1});
		
		kingDiagonals.addAll(diagonals);
		kingDiagonals.add(new int[] {-1, 1});
		kingDiagonals.add(new int[] {-1, -1});
	}
	
	private boolean isKing;
	private List<int[]> curDiagonals = diagonals;
	
	public CheckerPiece(Color c, Side s)
	{
		super(s, c);
	}
	
	public void king()
	{
		isKing = true;
		curDiagonals = kingDiagonals;
	}
	
	public List<int[]> getMoves(Piece[][] board, int[] current) 
	{
		if(board[current[0]][current[1]] != this)
				throw new IllegalArgumentException();
		
		List<int[]> captures = getCaptures(board, current);
		if(!captures.isEmpty())
			return captures;

		List<int[]> validMoves = new ArrayList<>();
		for(int[] diag : curDiagonals)
		{
			try
			{
				int[] coords = Utils.add(current, diag);
				if(board[coords[0]][coords[1]] == null)
					validMoves.add(coords);
			}
			catch(ArrayIndexOutOfBoundsException e)
			{
				//Continue on with the loop
			}
		}
		
		return validMoves;
	}
	
	private List<int[]> getCaptures(Piece[][] board, int[] current)
	{
		List<int[]> captures = new ArrayList<>();
		
		for(int[] diag : curDiagonals)
		{
			try
			{
				int[] coords = Utils.add(current, diag);
				int[] nextCoords = Utils.add(coords, diag);
				Piece p = board[coords[0]][coords[1]];
				Piece next = board[nextCoords[0]][nextCoords[1]];
				if(p != null && !p.getColor().equals(getColor()) && next == null)
				{
					board[coords[0]][coords[1]] = null;
					List<int[]> doubles = getCaptures(board, nextCoords);
					if(doubles.isEmpty())
						captures.add(nextCoords);
					else
					{
						for(int i=0; i<doubles.size(); i++)
							doubles.set(i, Utils.append(nextCoords, doubles.get(i)));
						captures.addAll(doubles);
					}
				}
			}
			catch(ArrayIndexOutOfBoundsException e)
			{
				//Continue on with the loop
			}
		}
		
		return captures;
	}
}
