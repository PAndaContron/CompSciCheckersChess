package vbInterface;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import general.Utils;
import json.JsonObject;

public class ReflectionManager 
{

	public static void main(String[] args) throws Throwable
	{
//		Test test = new Test("Instance variable");
		JsonObject caller = JsonObject.parse(serializeJSON(Test.class, null));
		
		Map<String, JsonObject> requestMap = new HashMap<>();
		requestMap.put("caller", caller);
		requestMap.put("method", new JsonObject("staticEcho"));
		requestMap.put("parameters", new JsonObject(new JsonObject[]
		{
			JsonObject.parse(serializeJSON("JSON String"))	
		}));
		String requestStr = new JsonObject(requestMap).toString();
		System.out.println(requestStr);
		
		JsonObject responseJson = JsonObject.parse(invoke(requestStr));
		
		System.out.println(responseJson);
		System.out.println(deserialize(responseJson.getValue("return").getValue("object").getString()));
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
		return serializeJSON(obj.getClass(), obj);
	}
	
	public static String serializeJSON(Class<? extends Serializable> clazz, Serializable obj)
	{
		JsonObject classObj = new JsonObject(clazz.getCanonicalName());
		JsonObject serObj = new JsonObject(obj==null ? null : serialize(obj));
		Map<String, JsonObject> map = new HashMap<>();
		map.put("class", classObj);
		map.put("object", serObj);
		return new JsonObject(map).toString();
	}
	
	public static Serializable deserialize(String s)
	{
		ByteArrayInputStream bis = new ByteArrayInputStream(s.getBytes());
		Serializable o = null;
		try
		{
			ObjectInputStream in = new ObjectInputStream(bis);
			o = (Serializable) in.readObject();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return o;
	}
	
	public static Object invoke(String obj, Class<? extends Object> objClass, String method, List<Object> params)
	{
		return invoke(obj, objClass, method, params.toArray());
	}
	
	public static Object invoke(String obj, Class<? extends Object> objClass, String method, Object... params)
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
	
	@SuppressWarnings("unchecked")
	public static String invoke(String json) throws Throwable
	{
		PrintStream realout = System.out;
		PrintStream realerr = System.err;
		System.setOut(new PrintStream(new FileOutputStream(String.format(
				"logs/[%s] out.log", Utils.getDateTime()))));
		System.setErr(new PrintStream(new FileOutputStream(String.format(
				"logs/[%s] err.log", Utils.getDateTime()))));
		
		JsonObject info = JsonObject.parse(json);
		
		JsonObject objJson = info.getValue("caller");
		Class<? extends Serializable> clazz = null;
		try
		{
			clazz = (Class<? extends Serializable>) ReflectionManager.class.getClassLoader().loadClass(objJson.getValue("class").getString());
		}
		catch(ClassNotFoundException | ClassCastException e)
		{
			e.printStackTrace();
			
			System.setOut(realout);
			System.setErr(realerr);
			
			return null;
		}
		
		String serObj = objJson.getValue("object").getString();
		Serializable obj = serObj==null ? null : deserialize(serObj);
		
		JsonObject[] paramsJson = info.getValue("parameters").getArray();
		Class<? extends Serializable>[] paramClasses = (Class<? extends Serializable>[]) new Class<?>[paramsJson.length];
		Serializable[] params = new Serializable[paramsJson.length];
		for(int i=0; i<paramsJson.length; i++)
		{
			try
			{
				paramClasses[i] = (Class<? extends Serializable>) ReflectionManager.class.getClassLoader().loadClass(paramsJson[i].getValue("class").getString());
			}
			catch(ClassNotFoundException | NullPointerException | ClassCastException e)
			{
				e.printStackTrace();
				
				System.setOut(realout);
				System.setErr(realerr);
				
				return null;
			}
			
			serObj = paramsJson[i].getValue("object").getString();
			params[i] = serObj==null ? null : deserialize(serObj);
		}
		
		Method m = null;
		try
		{
			m = clazz.getMethod(info.getValue("method").getString(), paramClasses);
		}
		catch(NoSuchMethodException e)
		{
			e.printStackTrace();
			
			System.setOut(realout);
			System.setErr(realerr);
			
			return null;
		}
		
		Serializable returnObj = null;
		try
		{
			returnObj = (Serializable) m.invoke(obj, (Object[]) params);
		}
		catch(IllegalArgumentException | IllegalAccessException | NullPointerException
				| ClassCastException e)
		{
			e.printStackTrace();
			
			System.setOut(realout);
			System.setErr(realerr);
			
			return null;
		}
		catch(InvocationTargetException e)
		{
			System.setOut(realout);
			System.setErr(realerr);
			throw e.getCause();
		}
		
		Class<? extends Serializable> returnClass = (Class<? extends Serializable>) m.getReturnType();
		
		Map<String, JsonObject> responseMap = new HashMap<>();
		responseMap.put("caller", JsonObject.parse(serializeJSON(clazz, obj)));
		responseMap.put("return", JsonObject.parse(serializeJSON(returnClass, returnObj)));
		
		for(int i=0; i<params.length; i++)
		{
			paramsJson[i] = JsonObject.parse(serializeJSON(paramClasses[i], params[i]));
		}
		responseMap.put("parameters", new JsonObject(paramsJson));
		
		System.setOut(realout);
		System.setErr(realerr);
		
		return new JsonObject(responseMap).toString();
	}

}
