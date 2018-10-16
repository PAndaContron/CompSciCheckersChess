package general;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.util.Scanner;

import javax.swing.JPanel;

public abstract class Board 
{
	protected Piece[][] board;
	private JPanel mainPanel;
	private BoardSquare[][] boardPanels;
	
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
	
	public void move(int[] from, int[] to)
	{
		board[to[to.length-2]][to[to.length-1]] = board[from[0]][from[1]];
		board[from[0]][from[1]] = null;
		updatePanels();
	}
	
	public int countPieces(Color c)
	{
		int i = 0;
		for(Piece[] row : board)
			for(Piece p : row)
				if(p!=null && p.getColor().equals(c))
					i++;
		return i;
	}
	
	public Piece getPiece(int row, int col)
	{
		return board[row][col];
	}
	
	public JPanel getPanel()
	{
		return mainPanel;
	}
	
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
