package server.command;

import server.*;

/**
 *  Implements server command to set the port for the server
 *  Can only be successful if server is closed. Port is
 *  given by message string.
 *
 * @author Chris Nevison
 * @version February 2012
 */
public class setport extends ServerCommand
{
  public setport(String str, Chat4Server server)
  {
    super(str, server);
  }

  public void doCommand()
  {
    try
    {
      int port = Integer.parseInt(getStr());
      setP(port);
    }
    catch(Exception ex)
    {
      getServer().getConsole().display("Exception converting string to port. No action taken.");
    }
  }

  private void setP(int port)
  {
    if(getServer().isClosed())
    {
      getServer().setPort(port);
    }
    else
    {
      getServer().getConsole().display("Server not closed. Cannot set port.");
    }
  }
}


