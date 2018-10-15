package general;

import java.util.Arrays;
import java.util.Scanner;

public class TerminalPlayer extends Player
{
	private Scanner scan;
	
	public TerminalPlayer(String name, Scanner scan)
	{
		super(name);
		this.scan = scan;
	}
	
	public void makeMove(Board b) 
	{
		System.out.println(name+"\'s turn!");
		System.out.println(b);
		System.out.println("Enter your move:");
		
		while(true)
		{
			try
			{
				String s = scan.nextLine();
				int[] move = Utils.parseCoords(s);
				b.move(Arrays.copyOf(move, 2), Arrays.copyOfRange(move, 2, move.length));
			}
			catch(Exception e)
			{
				System.out.println("That's not a valid move!");
			}
		}
	}
}
