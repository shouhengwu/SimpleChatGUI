package client;

import java.io.IOException;

/**
 *  Implements client command to join another channel
 *
 * @author Chris Nevison
 * @version March 2016
 */
public class join extends ClientCommand
{
  public join(String str, Chat4ClientCommandProcessor client)
  {
    super(str, client);
  }

  public void doCommand()
  {
    try
    {
    String handlerMessage = "JoinChannel " + getStr();
    getClient().OC().sendToServer(handlerMessage);
    }
    catch(IOException ex)
    {
      getClient().clientUI().display(ex + "\nUnable to join " + getStr());
    }
  }


}