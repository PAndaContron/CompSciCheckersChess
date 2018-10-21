package general;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * Represents a single square on a {@link Board}, with a background {@link Color} and a {@link Piece}.
 */
public class BoardSquare extends JPanel
{
	/** This class is probably never going to be serialized, but Eclipse made me add this anyway. */
	private static final long serialVersionUID = -1840139402010540234L;
	
	/** The piece currently sitting in this square. */
	private Piece piece;

	/**
	 * Creates a new BoardSquare.
	 * 
	 * @param c The background color.
	 */
	public BoardSquare(Color c)
	{
		setBackground(c);
	}
	
	/**
	 * Sets the {@link Piece} to be displayed in this square.
	 * 
	 * @param p The piece in this square.
	 */
	public void setPiece(Piece p)
	{
		piece = p;
	}
	
	/**
	 * Draws this panel, then draws the {@link Piece} on top of it, scaled to fit exactly in this square.
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if(piece != null)
		{
			piece.draw(this, g, 0, 0, getWidth(), getHeight());
		}
	}
}
