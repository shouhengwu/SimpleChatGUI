package server.command;

import server.*;
import java.io.IOException;

/**
 *  Implements server command to disconnect and terminate
 *
 * @author Chris Nevison
 * @version February 2012
 */
public class quit extends ServerCommand
{
  public quit(String str, Chat4Server server)
  {
    super(str, server);
  }

  public void doCommand()
  {
    try
    {
      getServer().sendToAllClients("SERVER> Server is closing all connections and terminating.");
      getServer().close();
      System.exit(0);
    }
    catch(IOException ex)
    {
      getServer().getConsole().display("Error in server closing. \n Server exiting.");
      System.exit(-1);
    }
  }
}


