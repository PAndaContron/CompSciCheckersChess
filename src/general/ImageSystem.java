package general;

import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

public class ImageSystem 
{
	private static Map<String, ImageIcon> map = new HashMap<>();
	
	public static ImageIcon getIcon(String filename)
	{
		if(map.containsKey(filename))
			return map.get(filename);
		
		ImageIcon out = new ImageIcon(filename);
		
	}
}
