package chess;

import java.awt.Color;
import java.util.List;

import general.Piece;
import general.Side;

public class Queen extends Piece{
	private static final long serialVersionUID = 1L;

	public Queen(Side s, Color c) {
		super(s, c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<int[]> getMoves(Piece[][] board, int[] current) {
		// TODO Auto-generated method stub
		return null;
	}

}
