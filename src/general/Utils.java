package general;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils
{
	public static int[] add(int[] a, int[] b)
	{
		if(a.length != b.length)
			throw new IllegalArgumentException();
		int[] c = new int[a.length];
		for(int i=0; i<c.length; i++)
			c[i] = a[i] + b[i];
		return c;
	}
	
	public static int[] append(int[] a, int[] b)
	{
		int[] c = new int[a.length+b.length];
		
		int i=0;
		for(int aI : a)
		{
			c[i] = aI;
			i++;
		}
		for(int bI : b)
		{
			c[i] = bI;
			i++;
		}
		
		return c;
	}
	
	public static List<int[]> split(int[] a)
	{
		List<int[]> out = new ArrayList<>();
		if(a.length == 0)
			return out;
		out.add(Arrays.copyOf(a, 2));
		if(a.length > 2)
			out.addAll(split(Arrays.copyOfRange(a, 2, a.length)));
		return out;
	}
	
	public static boolean containsArray(List<int[]> list, int[] arr)
	{
		for(int[] row : list)
			if(Arrays.equals(row, arr))
				return true;
		return false;
	}
	
	public static int[] average(int[] a, int[] b)
	{
		if(a.length != b.length)
			throw new IllegalArgumentException();
		int[] out = new int[a.length];
		for(int i=0; i<out.length; i++)
		{
			out[i] = (a[i]+b[i])/2;
		}
		return out;
	}
	
	public static List<int[]> seqAverage(List<int[]> list)
	{
		List<int[]> out = new ArrayList<>();
		int[] previous = null;
		for(int[] current : list)
		{
			if(current != null)
				out.add(average(current, previous));
			previous = current;
		}
		return out;
	}
}
