package client;

import java.io.IOException;

/**
 *  Implements client command to quit, after first closing any connection to the current host.
 *
 * @author Chris Nevison
 * @version February 2012
 */
public class quit extends ClientCommand
{
  public quit(String str, Chat4ClientCommandProcessor client)
  {
    super(str, client);
  }

  public void doCommand()
  {
    try
    {
      if(getClient().OC().isConnected())
      {
        getClient().OC().closeConnection();
        getClient().clientUI().display("Connection closed, exiting.");
      }
      else
      {
        getClient().clientUI().display("Connection already closed, exiting.");
      }
      getClient().quit();
    }
    catch(IOException ex)
    {
      getClient().clientUI().display("Exception: " + ex + "\nExiting.");
      System.exit(-1);
    }
  }
}



