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
	
	public static Side getSide(int[] pos, int size)
	{
		if(pos[0] == 0)
			return TOP;
		if(pos[0] + 1 == size)
			return BOTTOM;
		if(pos[1] == 0)
			return LEFT;
		if(pos[1] + 1 == size)
			return RIGHT;
		return null;
	}
	
	public Side getOpposite()
	{
		switch(this)
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
		throw new IllegalStateException();
	}
	
	public List<int[]> getDiagonals(boolean all)
	{
		if(all)
			return allDiagonals;
		return diagonals.get(this);
	}
}
