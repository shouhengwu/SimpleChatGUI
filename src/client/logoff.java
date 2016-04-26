package client;

import java.io.IOException;

/**
 *  Implements client command to log off from the current host.
 *  The clients connection to the host will be closed.
 *
 * @author Chris Nevison
 * @version March 2016
 */
public class logoff extends ClientCommand
{
  public logoff(String str, Chat4ClientCommandProcessor client)
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
        getClient().clientUI().display("Logged off, but still waiting for commands");
      }
      else
      {
        getClient().clientUI().display("Not connected, so no log-off. \nWaiting for commands.");
      }

    }
    catch(IOException ex)
    {
      getClient().clientUI().display(ex + ", \nExiting.");
      System.exit(-1);
    }
  }
}



