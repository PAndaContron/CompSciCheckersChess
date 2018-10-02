package general;

import java.awt.Color;
import java.util.List;

public abstract class Piece 
{
	private char icon;
	private Color color;
	private Side side;
	
	public Piece(Side s, Color c)
	{
		this(s, c, ' ');
	}
	
	protected Piece(Side s, Color c, char ic)
	{
		icon = ic;
		color = c;
		side = s;
	}
	
	protected void setIcon(char c)
	{
		icon = c;
	}
	
	public Color getColor()
	{
		return color;
	}
	
	public Side getSide()
	{
		return side;
	}
	
	abstract public List<int[]> getMoves(Piece[][] board, int[] current);
}
