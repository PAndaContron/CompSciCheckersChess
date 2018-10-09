package general;

import java.awt.Color;

public class Board 
{
	protected Piece[][] board;
	
	public Board(int size)
	{
		board = new Piece[size][size];
	}
	
	public void move(int[] from, int[] to)
	{
		board[to[to.length-2]][to[to.length-1]] = board[from[0]][from[1]];
		board[from[0]][from[1]] = null;
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
	
	public String toString()
	{
		String out = "";
		for(Piece[] row : board)
		{
			for(Piece p : row)
			{
				if(p != null)
					out += p;
				else
					out += "▢";
			}
			out += "\n";
		}
		return out;
	}
}
