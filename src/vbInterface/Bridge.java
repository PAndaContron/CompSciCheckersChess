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

public class Bridge 
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
		System.out.println(ser);
		testObj = deserialize(ser, testObj.getClass());
		
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
	
	public static <T> T deserialize(String s, Class<T> clazz)
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
		return clazz.cast(o);
	}

}
