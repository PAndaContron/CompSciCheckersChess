package general;

import java.awt.Color;

public abstract class Piece 
{
	private char icon;
	private Color color;
	private Side side;
	
	public Piece()
	{
		this(Side.TOP, Color.WHITE);
	}
	
	public Piece(Side s, Color c)
	{
		this(s, c, ' ');
	}
	
	public Piece(Side s, Color c, char ic)
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
	
	abstract public boolean canMove(Piece[][] board, int[] current, int[] moveTo);
}
