package general;

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
}
