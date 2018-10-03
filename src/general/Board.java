package general;

import java.awt.Color;
import java.util.List;

public class Board 
{
	private Piece[][] board;
	
	public void move(int[] from, int[] to)
	{
		Piece p = board[from[0]][from[1]];
		List<int[]> valid = p.getMoves(board, from);
		if(Utils.containsArray(valid, to))
		{
			
		}
	}
	
	public int countPieces(Color c)
	{
		int i = 0;
		for(Piece[] row : board)
			for(Piece p : row)
				if(p.getColor().equals(c))
					i++;
		return i;
	}
}
