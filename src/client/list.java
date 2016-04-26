package client;

import java.io.IOException;

/**
 *  Implements client command to list available channels.
 *
 * @author Chris Nevison
 * @version March 2016
 */
public class list extends ClientCommand
{
  public list(String str, Chat4ClientCommandProcessor client)
  {
    super(str, client);
  }


  public void doCommand()
  {
    try
    {
    String handlerMessage = "ListChannelHandler ";
    getClient().OC().sendToServer(handlerMessage);
    }
    catch(IOException ex)
    {
      getClient().clientUI().display("Exception " + ex + "\nUnable to list channels ");
    }
  }
  }


