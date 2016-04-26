package server.command;

import server.*;
import java.io.IOException;

/**
 *  Implements server command to stop listening and close all connections.
 *
 * @author Chris Nevison
 * @version February 2012
 */
public class close extends ServerCommand
{
  public close(String str, Chat4Server server)
  {
    super(str, server);
  }

  public void doCommand()
  {
    if(getServer().isClosed())
    {
      getServer().getConsole().display("Server already closed. No action taken.");
    }
    else
    {
      try
      {
        getServer().sendToAllClients("SERVER> Server is closing. All connections terminated.");
        getServer().close();
        getServer().getConsole().display("Server closed.");
      }
      catch(IOException ex)
      {
        getServer().getConsole().display("Exception " + ex + ".\nServer terminating.");
        System.exit(-1);
      }
    }
  }
}


