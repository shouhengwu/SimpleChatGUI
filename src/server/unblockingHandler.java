package server;

import server.*;
import ocsf.server.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *  This class defines a message handler to put a block on the user specified by string
 */
public class unblockingHandler extends ServerNonLoginHandler{

  String whoToUnblock;
  public unblockingHandler(String str, Chat4Server server, ConnectionToClient client)
  {
    super(str, server, client);
    whoToUnblock = str;
  }

  /**
   *  This method removes a block on a user
   */
  public void handleMess()
  {
    //find client to block
    ConnectionToClient clientToUnblock = findClient(whoToUnblock);

    if(clientToUnblock == null)
    {
      try
      {
        getClient().sendToClient(whoToUnblock + " is no longer connected. Removed from your who-I-block set.");
      }
      catch(IOException ex)
      {
        getServer().getConsole().display(ex + "\nError sending message to client " + getClient().getInfo("id"));
      }
      finally{
        ((HashSet<String>)getClient().getInfo("iblock")).remove(whoToUnblock);
      }
      return;
    }

    ((HashSet<String>)getClient().getInfo("iblock")).remove(whoToUnblock);
    ((HashSet<String>)clientToUnblock.getInfo("blocksme")).remove(getClient().getInfo("id"));

    try
    {
      getClient().sendToClient(whoToUnblock + " is no longer blocked");   // send message
      getServer().getConsole().display(getClient().getInfo("id") + " no longer blocks " + whoToUnblock);
    }
    catch(IOException ex)
    {
      try
      {
        getClient().sendToClient(ex + "\nError in unblock messages.");
      }
      catch(IOException e)
      {
        getServer().getConsole().display(e + "\nError in unblock error message.");
      }
    }

  }

  private ConnectionToClient findClient(String who)
  {
    Thread[] clientList = getServer().getClientConnections();
    for (int i = 0; i<clientList.length; i++)
    {
      if ((((ConnectionToClient)clientList[i]).getInfo("id")).equals(who))
      {
        return (ConnectionToClient)clientList[i];
      }
    }
    return null;
  }

}
