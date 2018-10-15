package checkers;

import java.awt.Color;
import java.util.List;

import general.Board;
import general.Piece;
import general.Side;
import general.Utils;

public class CheckerBoard extends Board 
{

	public CheckerBoard(int size)
	{
		super(size);

		for (int x = 0; x < size; x++)
		{
			for (int y = 0; y < size; y++)
			{
				if ((x+y) % 2 == 0)
				{
					if (x <= 2)
						board[x][y] = new CheckerPiece(Side.TOP, Color.BLACK);
					else if (x >= 5)
						board[x][y] = new CheckerPiece(Side.BOTTOM, Color.RED);
				}
			}
		}
	}
	
	public void move(int[] from, int[] to)
	{
		Piece p = board[from[0]][from[1]];
		List<int[]> valid = p.getMoves(board, from);
		if(Utils.containsArray(valid, to))
		{
			List<int[]> captures = Utils.seqAverage(Utils.split(to));
			for(int[] pos : captures)
			{
				if(!board[pos[0]][pos[1]].getColor().equals(p.getColor()))
				{
					board[pos[0]][pos[1]] = null;
				}
			}
			
			super.move(from, to);
		}
		else
		{
			throw new IllegalArgumentException("Invalid move");
		}
	}
	
}
