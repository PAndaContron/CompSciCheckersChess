package general;

/**
 * Thrown when the game ends to exit the loop. The <b>detailMessage</b> should contain the reason why the game ended.
 */
public class GameOverException extends Exception
{
	/** This class is probably never going to be serialized, but Eclipse made me add this anyway. */
	private static final long serialVersionUID = 1258705957863889299L;
	
	/**
	 * Creates the exception with a reason why the game ended.
	 * 
	 * @param string The reason for the game endign.
	 */
	public GameOverException(String string)
	{
		super(string);
	}
}
