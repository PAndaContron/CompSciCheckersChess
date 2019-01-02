package general;

import java.awt.Color;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import checkers.GraphicsInterface;

public class PlayGame
{

	public static void main(String[] args)
	{
//		Set<Class<? extends Object>> games = Utils.getClassesWith(Game.class);
		Class<GraphicsInterface> gameClass = GraphicsInterface.class;
		Game gameAnnotation = (Game) gameClass.getAnnotation(Game.class);
		
		Method[] gameMethods = gameClass.getMethods();
		String runName = gameAnnotation.method();
		Method runMethod = null;
		for(Method m : gameMethods)
		{
			if(m.getName().equals(runName))
			{
				runMethod = m;
				break;
			}
		}
		
		Class<? extends Object>[] paramClasses = runMethod.getParameterTypes();
		Object[] params = new Object[paramClasses.length];
		params[0] = new GraphicsPlayer("Player 1", Color.BLACK);
		params[1] = new GraphicsPlayer("Player 2", Color.RED);
		
		Constructor<GraphicsInterface> c = null;;
		try
		{
			c = gameClass.getConstructor();
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
		}
		
		GraphicsInterface instance = null;
		try
		{
			instance = c.newInstance();
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			runMethod.invoke(instance, params);
		}
		catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
	}

}
