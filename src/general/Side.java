package general;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Side 
{
	LEFT,
	RIGHT,
	TOP,
	BOTTOM;
	
	private static Map<Side, List<int[]>> diagonals = new HashMap<>();
	private static List<int[]> allDiagonals = new ArrayList<>();
	
	static
	{
		List<int[]> left = new ArrayList<>(), right = new ArrayList<>(), top = new ArrayList<>(), bottom = new ArrayList<>();
		
		left.add(new int[] {1, 1});
		left.add(new int[] {-1, 1});
		
		right.add(new int[] {1, -1});
		right.add(new int[] {-1, -1});
		
		top.add(new int[] {1, 1});
		top.add(new int[] {1, -1});
		
		bottom.add(new int[] {-1, 1});
		bottom.add(new int[] {-1, -1});
		
		diagonals.put(LEFT, left);
		diagonals.put(RIGHT, right);
		diagonals.put(TOP, top);
		diagonals.put(BOTTOM, bottom);
		
		allDiagonals.addAll(left);
		allDiagonals.addAll(right);
	}
	
	public Side getOpposite(Side s)
	{
		switch(s)
		{
			case LEFT:
				return RIGHT;
			case RIGHT:
				return LEFT;
			case TOP:
				return BOTTOM;
			case BOTTOM:
				return TOP;
		}
		throw new IllegalArgumentException();
	}
	
	public List<int[]> getDiagonals(boolean all)
	{
		if(all)
			return allDiagonals;
		return diagonals.get(this);
	}
}
