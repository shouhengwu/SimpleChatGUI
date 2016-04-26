package client;

import java.io.IOException;

/**
 *  Implements client command to list current clients in one's channels
 *
 */
public class users extends ClientCommand
{
  public users(String str, Chat4ClientCommandProcessor client)
  {
    super(str, client);
  }


  public void doCommand()
  {
    try
    {
      String handlerMessage = "UserListChannelHandler " + getStr();
      getClient().OC().sendToServer(handlerMessage);
    }
    catch(IOException ex)
    {
      getClient().clientUI().display(ex + "\nUnable to list users in channel " + getStr());
    }
  }
  }


