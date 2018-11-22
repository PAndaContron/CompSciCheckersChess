package json;

public class Test 
{

	public static void main(String[] args) 
	{
		System.out.println(JsonObject.parse("{\"value\": \"key\", \"value2\": {\"subvalue\": [38.2, 2.5, 8.3]}}"));
	}

}
