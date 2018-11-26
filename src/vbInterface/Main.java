package vbInterface;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import json.JsonObject;

public class Main
{

	public static void main(String[] args)
	{
		String callerJson = args[0].equals("null") ? null : args[0];
		String methodStr = args[1];
		String[] paramsJson = {};
		if(args.length > 2)
			paramsJson = Arrays.copyOfRange(args, 2, args.length);
		
		JsonObject caller = JsonObject.parse(callerJson);
		JsonObject method = new JsonObject(methodStr);
		JsonObject[] params = new JsonObject[paramsJson.length];
		for(int i=0; i<paramsJson.length; i++)
		{
			params[i] = JsonObject.parse(paramsJson[i]);
		}
		
		Map<String, JsonObject> requestMap = new HashMap<>();
		requestMap.put("caller", caller);
		requestMap.put("method", method);
		requestMap.put("parameters", new JsonObject(params));
		
		try
		{
			String response = ReflectionManager.invoke(new JsonObject(requestMap).toString());
			System.out.print(response);
		}
		catch (Throwable e)
		{
			e.printStackTrace();
		}
	}

}
