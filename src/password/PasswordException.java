package password;

/**
 * This class defines password exceptions called by PasswordManager methods
 *
 * @author Chris Nevison
 * @version March 2012
 */
public class PasswordException extends Exception
{
	private static final long serialVersionUID = 603663038383031673L;

public PasswordException(String issue)
  {
    super("PasswordException-- " + issue);
  }
}