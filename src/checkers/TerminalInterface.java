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
		
		try
		{
			while(true)
			{
				Player next = turns.next();
				next.makeMove(b);
				
				if(b.countPieces(Color.BLACK) == 0 || !b.hasMoves(Color.BLACK))
					throw new GameOverException(p2.toString());
				if(b.countPieces(Color.RED) == 0 || !b.hasMoves(Color.RED))
					throw new GameOverException(p1.toString());
			}
		}
		catch(GameOverException e)
		{
			System.out.println(b);
			String s = e.getMessage();
			if(s.equals(p1.toString()))
				System.out.println(p1 + " wins!");
			else if(s.equals(p2.toString()))
				System.out.println(p2 + " wins!");
		}
		
		scan.close();
	}

}
