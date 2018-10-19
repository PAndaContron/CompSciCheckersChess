package checkers;

import javax.swing.JFrame;

import general.Board;

/**
 * Runs the game using a GUI
 */
public class GraphicsInterface
{
	private static JFrame game = new JFrame("Checkers");
	private static Board board = new CheckerBoard();

	public static void main(String[] args)
	{
		
		
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.getContentPane().add(board.getPanel());
		game.pack();
		
		game.setVisible(true);
	}

}
