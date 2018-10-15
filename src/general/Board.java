package general;

import java.awt.Color;

public abstract class Board 
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
				if(p!=null && p.getColor().equals(c))
					i++;
		return i;
	}
	
	public Piece getPiece(int row, int col)
	{
		return board[row][col];
	}
	
	public String toString()
	{
		String out = " ";
		for(int i=0; i<board.length; i++)
			out += Utils.ALPHABET[i];
		out += "\n";
		for(int i=0; i<board.length; i++)
		{
			out += (i+1);
			Piece[] row = board[i];
			for(int j=0; j<row.length; j++)
			{
				Piece p = row[j];
				if(p != null)
					out += p;
				else
					if((i+j)%2 == 0)
						out += " ";
					else
						out += "\u25A1";
			}
			out += "\n";
		}
		return out;
	}
}
