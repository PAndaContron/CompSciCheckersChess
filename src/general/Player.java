package general;

public abstract class Player
{
	protected String name;
	
	public Player(String name)
	{
		this.name = name;
	}
	
	public String toString()
	{
		return name;
	}
	
	abstract public void makeMove(Board b);
}
