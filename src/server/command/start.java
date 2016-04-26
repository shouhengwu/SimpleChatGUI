package server.command;

import server.*;
import java.io.IOException;

/**
 *  Implements server command to start listening.
 *
 * @author Chris Nevison
 * @version February 2012
 */
public class start extends ServerCommand
{
  public start(String str, Chat4Server server)
  {
    super(str, server);
  }

  public void doCommand()
  {
    if(!getServer().isListening())
    {
      try
      {
        getServer().listen();
      }
      catch(IOException ex)
      {
        getServer().getConsole().display("IOException " + ex + ".\nCannot start listening.");
      }
    }
    else
    {
      getServer().getConsole().display("Server listening. Cannot start again.");
    }
  }
}


