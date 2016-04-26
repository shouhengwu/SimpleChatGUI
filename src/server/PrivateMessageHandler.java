package server;

import server.*;
import ocsf.server.*;
import java.io.*;
import java.util.Set;

/**
 *  This class defines a message handler to send a message to a single client
 *  rather than broadcast it.
 */
public class PrivateMessageHandler extends ServerNonLoginHandler
{
  private String receiveId;
  private String myMessage;

  public PrivateMessageHandler(String str, Chat4Server server, ConnectionToClient client)
  {
    super(str, server, client);
    String[] Messagesplit;
    Messagesplit = getMessage().split(" ", 2);
    receiveId = Messagesplit[0];
    try{
      myMessage = Messagesplit[1];
    }
    catch(IndexOutOfBoundsException ex){
      myMessage = "";
    }
  }

  protected String getReceiveId()
  {
    return receiveId;
  }

  protected String getMyMessage()
  {
    return myMessage;
  }

  /**
   *  This method send the message only to the selected client, if not b;ocking the sender.
   *  Message is also displayed on the server console.
   */
  public void handleMess()
  {
    //find client to send to...
    ConnectionToClient clientConnect = findClient(getReceiveId());
    try
    {
      if (clientConnect!= null)
      {
        Set<String> blocked = (Set<String>) clientConnect.getInfo("iblock");
        if (! blocked.contains(getClient().getInfo("id")))
        {
          clientConnect.sendToClient("whisper from " + getClient().getInfo("id") + " : " + getMyMessage());   // send message
          getServer().getConsole().display(getClient().getInfo("id") + " whispers \"" + getMyMessage() + "\" to " + getReceiveId());
        }
        else
        {
          getClient().sendToClient(getReceiveId() + " has blocked you");
        }
      }
      else
      {
        getClient().sendToClient("Username '" +  getReceiveId() + "' is not connected or does not exist!");
      }
    }
    catch(IOException ex)
    {
      try{
        getClient().sendToClient("error in whisper");
      }
      catch(IOException e)
      {
        System.out.println("error in whisper and error handles");
      }
    }

  }

  private ConnectionToClient findClient(String id)
  {
    Thread[] clientList = getServer().getClientConnections();
    ConnectionToClient clientConnect = null;

    for (int i = 0; i<clientList.length; i++)
    {
      clientConnect = (ConnectionToClient)clientList[i];
      if ((clientConnect.getInfo("id")).equals(id))
      {
        return clientConnect;
      }
    }
    return clientConnect;
  }

}

