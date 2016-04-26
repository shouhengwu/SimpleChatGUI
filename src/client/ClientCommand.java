package client;

/**
 *  This abstract class specifies a framework for any command from the client
 *  user interface to the client. Any such command must be implemented with
 *  a subclass of this class with a classname identical to the command
 *  (stripped of the '#'). An instance of the command class will be formed
 *  from the name using reflection, then its doCommand method will be
 *  executed.
 *
 * @author Chris Nevison
 * @version February 2012
 */
public abstract class ClientCommand
{
  private String myString;
  private Chat4ClientCommandProcessor myClient;

  public ClientCommand(String str, Chat4ClientCommandProcessor client)
  {
    myString = str;
    myClient = client;
  }

  /**
   * This method provides the command access to the client
   *
   * @return the client
   */
  protected Chat4ClientCommandProcessor getClient()
  {
    return myClient;
  }

  /**
   * This method provides the command access to the command String (stripped of the command)
   *
   * @return command String
   */
  protected String getStr()
  {
    return myString;
  }

  /**
   * This method provides the slot that any command from the client UI to the client must fill by
   * implementing this method in the subclass that defines the command.
   */
  abstract public void doCommand();
}
