package client;

import java.io.IOException;

/**
 *  Implements client command to block messages from another client
 *
 * @author Chris Nevison
 * @version March 2016
 */
public class block extends ClientCommand
{
  public block(String str, Chat4ClientCommandProcessor client)
  {
    super(str,client);
  }

  private String getBlockeeId()
  {
    String blockMess = getStr();
    int indexBlank = blockMess.indexOf(' ');
    if(indexBlank == -1)
    {
      return blockMess;
    }
    else
    {
      return blockMess.substring(0, indexBlank);
    }
  }

  public void doCommand()
  {
  try
    {
    String handlerMessage = "blockingHandler " + getStr();
    getClient().OC().sendToServer(handlerMessage);
    }
    catch(IOException ex)
    {
      getClient().clientUI().display(ex + "\nblock command for " + getBlockeeId() + " failed.");
    }
  }

}