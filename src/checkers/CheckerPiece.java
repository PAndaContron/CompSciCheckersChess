package checkers;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import general.Piece;
import general.Side;
import general.Utils;

public class CheckerPiece extends Piece 
{
	private boolean isKing;
	
	public CheckerPiece(Side s, Color c)
	{
		super(s, c);
		if(c.equals(Color.RED))
			setIcon("\u25E6");
		else if(c.equals(Color.BLACK))
			setIcon("\u2022");
	}
	
	public void king()
	{
		isKing = true;
		if(getColor().equals(Color.RED))
			setIcon("\uD835\uDD42");
		else if(getColor().equals(Color.BLACK))
			setIcon("K");
	}
	
	public List<int[]> getMoves(Piece[][] board, int[] current) 
	{
		if(board[current[0]][current[1]] != this)
				throw new IllegalArgumentException();
		
		List<int[]> curDiagonals = getSide().getDiagonals(isKing);
		
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
		
		System.out.println("Valid Moves:");
		for(int[] move : validMoves)
		{
			System.out.println(Arrays.toString(move));
		}
		return validMoves;
	}
	
	public boolean hasCaptures(Piece[][] board, int[] current)
	{
		return !getCaptures(board, current).isEmpty();
	}
	
	private List<int[]> getCaptures(Piece[][] board, int[] current)
	{
		List<int[]> captures = new ArrayList<>();
		
		List<int[]> curDiagonals = getSide().getDiagonals(isKing);
		
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
					board[coords[0]][coords[1]] = p;
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
