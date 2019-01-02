package general;

import java.util.Set;

public class PlayGame
{

	public static void main(String[] args) throws ClassNotFoundException
	{
		Set<Class<? extends Object>> games = Utils.getClassesWith(Game.class);
		System.out.println(games);
//		System.out.println(Utils.getClassesForPackage("checkers"));
	}

}
