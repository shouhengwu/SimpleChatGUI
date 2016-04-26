package client;

import java.io.IOException;

/**
 *  Implements client command to send a private message to one other user
 *
 * @author Chris Nevison
 * @version February 2012
 */
public class whisper extends ClientCommand
{
  public whisper(String str, Chat4ClientCommandProcessor client)
  {
  super(str,client);
  }

  private String getPrivateId()
  {
    String privateMess = getStr();
    int indexBlank = privateMess.indexOf(' ');
    if(indexBlank == -1)
    {
      return privateMess;
    }
    else
    {
      return privateMess.substring(0, indexBlank);
    }
  }

  public void doCommand()
  {
    try
    {
      String handlerMessage = "PrivateMessageHandler " + getStr();
      getClient().OC().sendToServer(handlerMessage);
    }
    catch(IOException ex)
    {
      getClient().clientUI().display(ex + "\nWhisper to " + getPrivateId() + " failed.");
    }

  }



}