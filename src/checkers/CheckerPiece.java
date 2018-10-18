package checkers;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;

import general.Piece;
import general.Side;
import general.Utils;

/**
 *	Represents a checker, with a {@link Side} of the board and a piece {@link Color}.
 */
public class CheckerPiece extends Piece 
{
	/** Stores whether or not this piece is a King. */
	private boolean isKing;
	
	/**
	 * Creates a new Checker and sets the icon and {@link ImageIcon} appropriately.
	 * 
	 * @param s The {@link Side} of the board this piece is on.
	 * @param c The {@link Color} of the piece. Currently supported colors are: <ul>
	 * 	<li>Black</li>
	 * 	<li>Red</li>
	 * </ul>
	 */
	public CheckerPiece(Side s, Color c)
	{
		super(s, c);
		if(c.equals(Color.RED))
		{
//			setIcon("\u25E6");
			setIcon("O");
			setImageIcon("checkers/redPiece.png");
		}
		else if(c.equals(Color.BLACK))
		{
			setIcon("\u2022");
			setImageIcon("checkers/blackPiece.png");
		}
	}
	
	
	/**
	 * Changes this piece to a king, updating the icon and {@link ImageIcon} appropriately.
	 */
	public void king()
	{
		isKing = true;
		if(getColor().equals(Color.RED))
		{
//			setIcon("\uD835\uDD42");
			setIcon("R");
			setImageIcon("checkers/redKing.png");
		}
		else if(getColor().equals(Color.BLACK))
		{
			setIcon("K");
			setImageIcon("checkers/blackKing.png");
		}
	}
	
	/**
	 * Finds all valid moves for this piece on <b>board</b>.
	 * <br>
	 * A checker can move diagonally forward (away from the {@link Side} it started out on) if there is nothing blocking its path.
	 * If it is a king, it can move diagonally in all directions. However, if there is a piece of the opposite {@link Color} blocking its path in any direction,
	 * then it must be jumped over and captured. If any more pieces can be jumped over from there, they must be jumped over as well.
	 * 
	 * @param board The board the move should work on.
	 * @param current The position of this piece, to save time that would be used searching.
	 * 
	 * @return A list of all valid moves as coordinates. If there are multiple jumps, each coordinate is appended to the end of the list in order.
	 * 
	 * @throws IllegalArgumentException if this piece is not at <b>current</b>
	 */
	public List<int[]> getMoves(Piece[][] board, int[] current) 
	{
		if(board[current[0]][current[1]] != this)
				throw new IllegalArgumentException();
		
		List<int[]> curDiagonals = getSide().getDiagonals(isKing);
		
		List<int[]> captures = getCaptures(board, current);
		if(!captures.isEmpty())
			return captures;

		List<int[]> validMoves = new ArrayList<>();
		for(int[] diag : curDiagonals)
		{
			try
			{
				int[] coords = Utils.add(current, diag);
				if(board[coords[0]][coords[1]] == null)
					validMoves.add(coords);
			}
			catch(ArrayIndexOutOfBoundsException e)
			{
				//Continue on with the loop
			}
		}
		
		System.out.println("Valid Moves:");
		for(int[] move : validMoves)
		{
			System.out.println(Arrays.toString(move));
		}
		return validMoves;
	}
	
	/**
	 * Checks if this piece can capture any pieces.
	 * 
	 * @param board The board the move should work on.
	 * @param current The position of this piece, to save time that would be used searching.
	 * 
	 * @return Whether or not this piece can make any captures.
	 */
	public boolean hasCaptures(Piece[][] board, int[] current)
	{
		return !getCaptures(board, current).isEmpty();
	}
	
	/**
	 * 
	 * @param board The board the move should work on.
	 * @param current The position of the piece to be tested.
	 * @return
	 */
	private List<int[]> getCaptures(Piece[][] board, int[] current)
	{
		List<int[]> captures = new ArrayList<>();
		
		List<int[]> curDiagonals = getSide().getDiagonals(isKing);
		
		for(int[] diag : curDiagonals)
		{
			try
			{
				int[] coords = Utils.add(current, diag);
				int[] nextCoords = Utils.add(coords, diag);
				Piece p = board[coords[0]][coords[1]];
				Piece next = board[nextCoords[0]][nextCoords[1]];
				if(p != null && !p.getColor().equals(getColor()) && next == null)
				{
					board[coords[0]][coords[1]] = null;
					List<int[]> doubles = getCaptures(board, nextCoords);
					board[coords[0]][coords[1]] = p;
					if(doubles.isEmpty())
						captures.add(nextCoords);
					else
					{
						for(int i=0; i<doubles.size(); i++)
							doubles.set(i, Utils.append(nextCoords, doubles.get(i)));
						captures.addAll(doubles);
					}
				}
			}
			catch(ArrayIndexOutOfBoundsException e)
			{
				//Continue on with the loop
			}
		}
		
		return captures;
	}
}
