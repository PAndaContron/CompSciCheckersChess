package chess;

import java.awt.Color;
import java.util.List;

import general.Piece;
import general.Side;

public class King extends ChessPiece
{
	private static final long serialVersionUID = 1L;

	public King(Side s, Color c)
	{
		super(s, c);
		if(c.equals(Color.WHITE))
		{
			setIcon("k");
		}
		else if(c.equals(Color.BLACK))
		{
			setIcon("K");
		}
	}

	public List<int[]> getMovesNoCheck(Piece[][] board, int[] current)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
