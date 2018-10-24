package general;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JPanel;

/**
 * Represents a single square on a {@link Board}, with a background {@link Color} and a {@link Piece}.
 */
public class BoardSquare extends JPanel
{
	/** This class is probably never going to be serialized, but Eclipse made me add this anyway. */
	private static final long serialVersionUID = -1840139402010540234L;
	/** The color used when this square is selected. */
	private static final Color SELECTED_COLOR = Color.YELLOW;
	
	/** The piece currently sitting in this square. */
	private Piece piece;
	/** The color to be used for this piece when it is not selected. */
	private Color backColor;

	/**
	 * Creates a new BoardSquare.
	 * 
	 * @param c The background color.
	 */
	public BoardSquare(Color c)
	{
		backColor = c;
		setBackground(c);
		addComponentListener(new ResizeListener());
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
	 * Changes the background color to indicate that this square is selected.
	 */
	public void select()
	{
		setBackground(SELECTED_COLOR);
	}
	
	/**
	 * Reverts the background color to the original to indicate that this square is no longer selected.
	 */
	public void deselect()
	{
		setBackground(backColor);
	}
	
	/**
	 * Draws this panel, then draws the {@link Piece} on top of it, scaled to fit exactly in this square.
	 */
	public void paintComponent(Graphics g)
	{
//		int s = Math.min(getWidth(), getHeight());
//		setSize(new Dimension(s, s));
		
		super.paintComponent(g);
		if(piece != null)
		{
			piece.draw(this, g, 0, 0, getWidth(), getHeight());
		}
	}
	
//	public Dimension getPreferredSize()
//	{
//		Dimension d = super.getPreferredSize();
//        Container c = getParent();
//        if (c != null)
//        {
//            d = c.getSize();
//        }
//        else
//        {
//            return new Dimension(10, 10);
//        }
//        int w = (int) d.getWidth();
//        int h = (int) d.getHeight();
//        int s = (w < h ? w : h);
//        return new Dimension(s, s);
//	}
	
	private class ResizeListener extends ComponentAdapter
	{
        public void componentResized(ComponentEvent e)
        {
            int s = Math.min(getWidth(), getHeight());
            setSize(new Dimension(s, s));
        }
	}
}
