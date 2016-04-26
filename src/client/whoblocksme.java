package client;

import java.io.IOException;

/**
 *  Implements client command to lis who is blocking me
 * @author Chris Nevison
 * @version February 2012
 */
public class whoblocksme extends ClientCommand
{
  public whoblocksme(String str, Chat4ClientCommandProcessor client){
    super(str, client);
  }

  public void doCommand()
  {
    try
    {
      String handlerMessage = "WhoBlocksMeHandler";
      getClient().OC().sendToServer(handlerMessage);
    }
    catch(IOException ex)
    {
      getClient().clientUI().display(ex + "\nwhoblocksme failed");
    }
  }

}

