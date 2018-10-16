package general;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class BoardSquare extends JPanel
{
	private static final long serialVersionUID = -1840139402010540234L;
	
	private Piece piece;

	public BoardSquare(Color c)
	{
		setBackground(c);
	}
	
	public void setPiece(Piece p)
	{
		piece = p;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if(piece != null)
		{
			piece.draw(this, g, getX(), getY(), getWidth(), getHeight());
		}
		System.out.printf("(%3d, %3d)\t%3d x %3d\t%s\n", getX(), getY(), getWidth(), getHeight(), piece);
	}
}
