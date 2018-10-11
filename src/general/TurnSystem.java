package general;

import java.util.ArrayList;
import java.util.List;

public class TurnSystem
{
	private List<Player> players = new ArrayList<>();
	private int index = 0;
	
	public TurnSystem(Player... players)
	{
		for(Player p : players)
			this.players.add(p);
	}
	
	public void add(Player p)
	{
		players.add(index, p);
	}
	
	public Player next()
	{
		Player next = players.get(index);
		index = (index + 1) % players.size();
		return next;
	}
}
