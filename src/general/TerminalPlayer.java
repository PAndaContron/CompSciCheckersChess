package general;

import java.awt.Color;
import java.util.Arrays;
import java.util.Scanner;

public class TerminalPlayer extends Player
{
	private Scanner scan;
	
	public TerminalPlayer(String name, Color c, Scanner scan)
	{
		super(name, c);
		this.scan = scan;
	}
	
	public void makeMove(Board b) 
	{
		System.out.println(this+"\'s turn!");
		System.out.println(b);
		System.out.println("Enter your move:");
		
		while(true)
		{
			try
			{
				String s = scan.nextLine();
				int[] move = Utils.parseCoords(s);
				if(b.getPiece(move[0], move[1]).getColor().equals(getColor()))
					b.move(Arrays.copyOf(move, 2), Arrays.copyOfRange(move, 2, move.length));
				else
					throw new IllegalArgumentException("Wrong color");
				break;
			}
			catch(Exception e)
			{
				System.out.println("That's not a valid move!");
				e.printStackTrace();
			}
		}
	}
}
