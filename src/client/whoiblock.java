package client;

import java.io.IOException;

/**
 *  Implements client command to list users that I block
 *
 * @author Chris Nevison
 * @version February 2012
 */
public class whoiblock extends ClientCommand
{
  public whoiblock(String str, Chat4ClientCommandProcessor client){
    super(str, client);
  }

  public void doCommand()
  {
    try
    {
      String handlerMessage = "WhoIBlockHandler";
      getClient().OC().sendToServer(handlerMessage);
    }
    catch(IOException ex)
    {
      getClient().clientUI().display(ex + "\n whoiblock failed");
    }
  }

}

