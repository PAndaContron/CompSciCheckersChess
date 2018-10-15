package general;

import java.awt.Color;
import java.util.List;

public abstract class Piece 
{
	private String icon;
	private Color color;
	private Side side;
	
	public Piece(Side s, Color c)
	{
		this(s, c, " ");
	}
	
	protected Piece(Side s, Color c, String ic)
	{
		icon = ic;
		color = c;
		side = s;
	}
	
	protected void setIcon(String s)
	{
		icon = s;
	}
	
	public Color getColor()
	{
		return color;
	}
	
	public Side getSide()
	{
		return side;
	}
	
	public String toString()
	{
		return icon;
	}
	
	abstract public List<int[]> getMoves(Piece[][] board, int[] current);
}
