package general;

import java.awt.Color;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Represents a human player playing the game through a terminal.
 */
public class TerminalPlayer extends Player
{
	/** Used to take in user input. */
	private Scanner scan;
	
	/**
	 * Creates a new TerminalPlayer.
	 * 
	 * @param name The name of this player.
	 * @param c The color of this player's pieces.
	 * @param scan A Scanner used to input moves.
	 */
	public TerminalPlayer(String name, Color c, Scanner scan)
	{
		super(name, c);
		this.scan = scan;
	}
	
	/**
	 * Takes in user input from the provided {@link Scanner} until a valid move is entered using chess coordinates, then makes the move and returns.
	 * If a move has multiple stops, each one should be entered in order after the starting position.
	 */
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
//				if(s.equals("skip"))
//					return;
//				if(s.equals("hasMoves"))
//				{
//					System.out.println(b.hasMoves(getColor()));
//					continue;
//				}
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
			}
		}
	}
}
