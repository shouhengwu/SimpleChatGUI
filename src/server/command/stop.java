package server.command;

import server.*;

/**
 *  Implements server command to stop listening.
 *
 * @author Chris Nevison
 * @version February 2012
 */
public class stop extends ServerCommand
{
  public stop(String str, Chat4Server server)
  {
    super(str, server);
  }

  public void doCommand()
  {
    if(getServer().isListening())
    {
      getServer().stopListening();
    }
    else
    {
      getServer().getConsole().display("Server already not listening. No action taken.");
    }
  }
}


