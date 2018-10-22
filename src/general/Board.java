package general;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

/**
 * Represents a square board with {@link BoardSquares}s of alternating colors. Each space can hold one {@link Piece}.
 */
public abstract class Board 
{
	/** Holds all of the {@link Piece}s on the board. */
	protected Piece[][] board;
	/** {@link JPanel} which can be used by another program for a GUI display. */
	private JPanel mainPanel;
	/** Holds all of the {@link BoardSquare}s that make up the board. */
	private BoardSquare[][] boardPanels;
	
	/**
	 * Creates a new board with all of its {@link BoardSquares}s.
	 * 
	 * @param size The height and width of this board in squares.
	 * @param c1 The color used on all {@link BoardSquare} where the sum of their coordinates is even.
	 * @param c2 The color used on all {@link BoardSquare} where the sum of their coordinates is odd.
	 */
	public Board(int size, Color c1, Color c2)
	{
		board = new Piece[size][size];
		mainPanel = new JPanel(new GridLayout(size, size));
		mainPanel.setPreferredSize(new Dimension(size*100, size*100));
		boardPanels = new BoardSquare[size][size];
		
		for(int i=0; i<size; i++)
		{
			for(int j=0; j<size; j++)
			{
				if((i+j) % 2 == 0)
					boardPanels[i][j] = new BoardSquare(c1);
				else
					boardPanels[i][j] = new BoardSquare(c2);
				mainPanel.add(boardPanels[i][j]);
			}
		}
	}
	
	/**
	 * Sets the {@link Piece} for each {@link BoardSquare} to the one at its position.
	 */
	public void updatePanels()
	{
		for(int i=0; i<board.length; i++)
		{
			for(int j=0; j<board.length; j++)
			{
				boardPanels[i][j].setPiece(board[i][j]);
			}
		}
	}
	
	/**
	 * Moves a {@link Piece}, without any checks for validity, and updates the {@link BoardSquare}s appropriately.
	 * 
	 * @param from The position of the {@link Piece} to move.
	 * @param to The final position of the {@link Piece}.
	 * <br> If the array is longer than 2 (in the case of multiple jumps), the last 2 values are used.
	 */
	public void move(int[] from, int[] to)
	{
		board[to[to.length-2]][to[to.length-1]] = board[from[0]][from[1]];
		board[from[0]][from[1]] = null;
		updatePanels();
	}
	
	/**
	 * Finds whether there are any pieces on the board of a certain color which can make a valid move.
	 * This can be used to check a victory condition for some games.
	 * 
	 * @param c The color to look for.
	 * 
	 * @return Whether or not any pieces with that color can move.
	 */
	public boolean hasMoves(Color c)
	{
		for(int i=0; i<board.length; i++)
			for(int j=0; j<board.length; j++)
				if(board[i][j]!=null && board[i][j].getColor().equals(c) && board[i][j].hasMoves(board, new int[] {i, j}))
				{
//					System.out.printf("Piece at %d, %d has a move%n", i, j);
					return true;
				}
		return false;
	}
	
	/**
	 * Counts the number of pieces with the specified {@link Color}.
	 * This can be used to check a victory condition for some games.
	 * 
	 * @param c The color to look for.
	 * 
	 * @return The number of pieces with that color.
	 */
	public int countPieces(Color c)
	{
		int i = 0;
		for(Piece[] row : board)
			for(Piece p : row)
				if(p!=null && p.getColor().equals(c))
					i++;
		return i;
	}
	
	/**
	 * Gets the {@link Piece} at a specified position.
	 * 
	 * @param row The row of the board, starting at 0 for the top.
	 * @param col The column of the board, starting at 0 for the side.
	 * 
	 * @return The {@link Piece} at <b>row</b>, <b>col</b>.
	 */
	public Piece getPiece(int row, int col)
	{
		return board[row][col];
	}
	
	/**
	 * Gets a {@link JPanel} which can be used in a GUI to display the current state of the board.
	 * 
	 * @return This board;s panel.
	 */
	public JPanel getPanel()
	{
		return mainPanel;
	}
	
	/**
	 * Creates a {@link String} with a picture of the board using each {@link Piece}'s icon. Includes numerical labels for rows and alphabetical labels for columns.
	 * 
	 * @return The picture of the board.
	 */
	public String toString()
	{
		String out = " ";
		for(int i=0; i<board.length; i++)
			out += Utils.ALPHABET[i];
		out += "\n";
		for(int i=0; i<board.length; i++)
		{
			out += (i+1);
			Piece[] row = board[i];
			for(int j=0; j<row.length; j++)
			{
				Piece p = row[j];
				if(p != null)
					out += p;
				else
					if((i+j)%2 == 0)
						out += " ";
					else
						out += "\u25A1";
			}
			out += "\n";
		}
		return out;
	}
}
