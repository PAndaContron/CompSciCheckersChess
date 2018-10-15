package checkers;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import general.Board;
import general.Piece;
import general.Side;
import general.Utils;

public class CheckerBoard extends Board 
{

	public CheckerBoard()
	{
		super(8);

		for (int x = 0; x < 8; x++)
		{
			for (int y = 0; y < 8; y++)
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
		List<int[]> capturePieces = new ArrayList<>();
		for(int i=0; i<board.length; i++)
			for(int j=0; j<board.length; j++)
			{
				Piece current = board[i][j];
				if(current!=null
						&& current.getColor().equals(p.getColor())
						&& ((CheckerPiece) current).hasCaptures(board, new int[] {i, j}))
					capturePieces.add(new int[] {i, j});
			}
		if(!capturePieces.isEmpty() && !Utils.containsArray(capturePieces, from))
			throw new IllegalArgumentException("Invalid move");
		List<int[]> valid = p.getMoves(board, from);
		if(Utils.containsArray(valid, to))
		{
			List<int[]> captures = Utils.seqAverage(Utils.split(Utils.append(from, to)));
			for(int[] pos : captures)
			{
				if(board[pos[0]][pos[1]]!=null && !board[pos[0]][pos[1]].getColor().equals(p.getColor()))
				{
					board[pos[0]][pos[1]] = null;
				}
			}
			
			super.move(from, to);
			Side s = Side.getSide(to, board.length);
			if(s!=null && s.getOpposite().equals(p.getSide()))
				((CheckerPiece) p).king();
		}
		else
		{
			throw new IllegalArgumentException("Invalid move");
		}
	}
	
}
