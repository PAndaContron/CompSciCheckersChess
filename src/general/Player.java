package general;

import java.awt.Color;

public abstract class Player
{
	private String name;
	private Color c;
	
	public Player(String name, Color c)
	{
		this.name = name;
		this.c = c;
	}
	
	public Color getColor()
	{
		return c;
	}
	
	public String toString()
	{
		return name;
	}
	
	abstract public void makeMove(Board b);
}
