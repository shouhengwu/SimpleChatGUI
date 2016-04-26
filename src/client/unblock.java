package client;

import java.io.IOException;

/**
 *  Implements client command to unblock a blocked client.
 *
 * @author Chris Nevison
 * @version February 2012
 */
public class unblock extends ClientCommand
{
  public unblock(String str, Chat4ClientCommandProcessor client)
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
      String handlerMessage = "unblockingHandler " + getBlockeeId();
      getClient().OC().sendToServer(handlerMessage);
    }
    catch(IOException ex)
    {
      getClient().clientUI().display(ex + "\nUnblock of " + getBlockeeId() +" failed.");
    }

  }



}