package server;

import server.*;
import ocsf.server.*;

import java.io.*;
import java.util.Set;

/**
 * Creates a message that will be handled by the server and will display all users in a current chat
 */


public class UserListChannelHandler extends ServerNonLoginHandler
{
  public UserListChannelHandler(String str, Chat4Server server, ConnectionToClient client)
    {
      super(str, server, client);
    }

  @Override
  public void handleMess()
  {
    try
    {
      Set<ConnectionToClient> clientSet = getServer().getChannelManager().getChannelClients((String)getClient().getInfo("channel"));
      for(ConnectionToClient client: clientSet)
      {
        getClient().sendToClient(client.getInfo("id"));// send the name
      }
    }
    catch(IOException e)
    {
      getServer().getConsole().display(e + "\nError sending channel member names");
    }
  }

}


