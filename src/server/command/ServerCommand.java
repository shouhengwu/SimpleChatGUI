package server.command;

import server.*;

/**
 *  This abstract class specifies a framework for any command from the server
 *  user interface to the server. Any such command must be implemented with
 *  a subclass of this class with a classname identical to the command
 *  (stripped of the '#'). An instance of the command class will be formed
 *  from the name using reflection, then its doCommand method will be
 *  executed.
 *
 * @author Chris Nevison
 * @version February 2012
 */
public abstract class ServerCommand
{
  private String myString;
  private Chat4Server myServer;

  public ServerCommand(String str, Chat4Server server)
  {
    myString = str;
    myServer = server;
  }

  /**
   *  Provides the concrete class with access to the server
   *
   * @return the server
   */
  protected Chat4Server getServer()
  {
    return myServer;
  }

  /**
   *  Provides the concrete class with access to the String associated
   *  with this command, after the command has been stripped. May be
   *  an empty String.
   *
   * @return message String
   */
  protected String getStr()
  {
    return myString;
  }

  /**
   * This method provides the slot that any command from the server UI to the server must fill by
   * implementing this method in the subclass that defines the command.
   */
  abstract public void doCommand();
}
