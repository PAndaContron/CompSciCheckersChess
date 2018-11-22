package vbInterface;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class ReflectionManager 
{

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException, ClassNotFoundException 
	{
		Class<Test> testClass = Test.class;
		
		Method staticPrint = testClass.getMethod("staticPrint", (Class<String>[]) null);
		Method staticPrintParam = testClass.getMethod("staticPrint", new Class[] {String.class});
		Method staticEcho = testClass.getMethod("staticEcho", new Class[] {String.class});
		Method print = testClass.getMethod("print", (Class<String>[]) null);
		Method printParam = testClass.getMethod("print", new Class[] {String.class});
		Method echo = testClass.getMethod("echo", new Class[] {String.class});
		
		Constructor<Test> constructor = testClass.getConstructor(new Class[] {String.class});
		
		Test testObj = (Test) constructor.newInstance(new Object[] {"String property"});
		
		String ser = serialize(testObj);
		String ser2 = serialize(new int[] {1, 2, 3, 4});
		System.out.println(ser);
		System.out.println(ser2);
		testObj = (Test) deserialize(ser);
		System.out.println(Arrays.toString((int[]) deserialize(ser2)));
		
		staticPrint.invoke(null);
		staticPrintParam.invoke(null, "Static Parameter");
		if ("Hi!".equals((String) staticEcho.invoke(null, "Hi!")))
		{
			System.out.println("Static echo works!");
		}
		
		print.invoke(testObj);
		printParam.invoke(testObj, "Class Parameter");
		if ("Hi!".equals((String) echo.invoke(testObj, "Hi!")))
		{
			System.out.println("Class echo works!");
		}
		
		System.out.println("invoke method testing!!");
		
		invoke(null, Test.class, "staticPrint", new Object[0]);
		System.out.println(invoke(null, Test.class, "staticEcho", new Object[] {"Hello!"}).equals("Hello!"));
		
		invoke(ser, Test.class, "print", new Object[0]);
		invoke(ser, Test.class, "print", new Object[] {"Hello!"});
		System.out.println(invoke(ser, Test.class, "echo", new Object[] {"Hello!"}).equals("Hello!"));
	}
	
	public static String serialize(Serializable obj)
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		String ser = "";
		try
		{
			ObjectOutputStream out = new ObjectOutputStream(bos);
			out.writeObject(obj);
			ser = bos.toString();
			out.close();
			bos.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return ser;
	}
	
	public static String serializeJSON(Serializable obj)
	{
		Class<? extends Object> clazz = obj.getClass();
		String ser = serialize(obj);
		return String.format("{\"class\":\"%s\",\"obj\":\"%s\"}", clazz, ser);
	}
	
	public static Object deserialize(String s)
	{
		ByteArrayInputStream bis = new ByteArrayInputStream(s.getBytes());
		Object o = null;
		try
		{
			ObjectInputStream in = new ObjectInputStream(bis);
			o = in.readObject();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return o;
	}
	
	public static Object deserializeJSON(String s)
	{
		return null;
	}
	
	public static <T> Object invoke(String obj, Class<T> objClass, String method, List<Object> params)
	{
		return invoke(obj, objClass, method, params.toArray());
	}
	
	public static <T> Object invoke(String obj, Class<T> objClass, String method, Object[] params)
	{
		Object object = null;
		if(obj != null && !obj.equals(""))
			object = deserialize(obj);
		
		Class<? extends Object>[] paramClasses = new Class<?>[params.length];
		for(int i=0; i<params.length; i++)
			paramClasses[i] = params[i].getClass();
		
		Method m;
		try
		{
			m = objClass.getMethod(method, paramClasses);
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			return null;
		}
		
		try
		{
			return m.invoke(object, params);
		}
		catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			return null;
		}
	}

}
