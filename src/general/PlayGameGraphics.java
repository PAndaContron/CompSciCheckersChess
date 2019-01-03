package general;

import java.util.Set;

public class PlayGameGraphics
{

	public static void main(String[] args)
	{
		Set<Class<? extends Object>> games = Utils.getClassesWith(Game.class);
		System.out.println(games);
	}

}
