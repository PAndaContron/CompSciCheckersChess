package chess;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import general.Board;
import general.GameOverException;
import general.Player;
import general.TerminalPlayer;
import general.TurnSystem;

public class TerminalInterface
{

	public static void main(String... args)
	{
		Scanner scan = new Scanner(System.in);
		try
		{
			scan = new Scanner(new File(args[0]));
		}
		catch(FileNotFoundException | ArrayIndexOutOfBoundsException e){}
		
		Player p1 = new TerminalPlayer("Player 1", Color.WHITE, scan);
		Player p2 = new TerminalPlayer("Player 2", Color.BLACK, scan);
		TurnSystem turns = new TurnSystem(p1, p2);
		
		Board b = new ChessBoard();
		
		try
		{
			while(args.length == 0 || scan.hasNextLine())
			{
				Player next = turns.next();
				next.makeMove(b);

				//This is here so the compiler doesn't complain about the unnecessary try/catch which will
				//become necessary once checkmates and stalemates are actually implemented, and the infinite
				//while loop which will also be fixed when that is implemented.
				if(Color.WHITE == Color.BLACK)
					throw new GameOverException(null);
			}
		}
		catch(GameOverException e)
		{
			System.err.println("Advancement made");
			System.out.println("How did we get here?");
		}
		
		if(args.length > 0)
			System.out.println(b);
		
		scan.close();
	}

}
