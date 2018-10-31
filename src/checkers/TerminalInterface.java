package checkers;

import java.awt.Color;
import java.util.Scanner;

import general.Board;
import general.GameOverException;
import general.Player;
import general.TerminalPlayer;
import general.TurnSystem;

/**
 * Runs the game using the terminal.
 */
public class TerminalInterface 
{

	public static void main(String[] args) 
	{
		Scanner scan = new Scanner(System.in);
		
		Player p1 = new TerminalPlayer("Player 1", Color.BLACK, scan);
		Player p2 = new TerminalPlayer("Player 2", Color.RED, scan);
		TurnSystem turns = new TurnSystem(p1, p2);
		
		Board b = new CheckerBoard();
		
		int prevPieces = b.countPieces();
		int movesSinceCap = 0;
		boolean stalemate = true;
		
		try
		{
			while(true)
			{
				Player next = turns.next();
				next.makeMove(b);
				
				if(b.countPieces(Color.BLACK) == 0 || !b.hasMoves(Color.BLACK))
					throw new GameOverException(p2);
				if(b.countPieces(Color.RED) == 0 || !b.hasMoves(Color.RED))
					throw new GameOverException(p1);
				
				if(prevPieces != b.countPieces())
				{
					prevPieces = b.countPieces();
					movesSinceCap = 0;
				}
				else
				{
					movesSinceCap++;
				}
				
				if(movesSinceCap > 50 && stalemate)
				{
					System.out.println("This game has been going on for quite some time with no progress.");
					System.out.println("Would you like to declare a stalemate?");
					String s = scan.nextLine();
					if(s.toLowerCase().charAt(0) == 'y')
					{
						throw new GameOverException(null);
					}
					else
					{
						stalemate = false;
					}
				}
			}
		}
		catch(GameOverException e)
		{
			System.out.println(b);
			String s = e.getMessage();
			Player p = e.getWinner();
			if(p == p1)
				System.out.println(p1 + " wins!");
			else if(p == p2)
				System.out.println(p2 + " wins!");
			else
				System.out.println("Stalemate!");
		}
		
		scan.close();
	}

}
