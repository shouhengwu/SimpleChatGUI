package server;

import server.*;
import ocsf.server.*;

import java.io.*;
import java.util.Set;

public class ListChannelHandler extends ServerNonLoginHandler{
/**
 * This Class will list all available channels for a user
 *
 */

  public ListChannelHandler(String str, Chat4Server server, ConnectionToClient client)
    {
      super(str, server, client);

    }

  @Override
  public void handleMess()
  {
    try
    {
      Set<String> channelNameSet = getServer().getChannelManager().getChannelNames();
      for (String channelName : channelNameSet)
      {
        getClient().sendToClient(channelName);// send each to client
      }
    }
    catch(IOException e)
    {
      getServer().getConsole().display(e + "\nError sending channel names in list command.");
    }
  }
}
