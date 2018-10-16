package general;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.util.List;

import javax.swing.ImageIcon;

public abstract class Piece 
{
	private String icon;
	private String imageIconPath;
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
	
	protected void setImageIcon(String s)
	{
		imageIconPath = s;
	}
	
	public Color getColor()
	{
		return color;
	}
	
	public Side getSide()
	{
		return side;
	}
	
	public void draw(Component c, Graphics g, int x, int y, int width, int height)
	{
		ImageIcon image = Utils.scale(ImageSystem.getIcon(imageIconPath), width, height);
		image.paintIcon(c, g, x, y);
	}
	
	public String toString()
	{
		return icon;
	}
	
	abstract public List<int[]> getMoves(Piece[][] board, int[] current);
}
