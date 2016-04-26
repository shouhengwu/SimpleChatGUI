package server;

import ocsf.server.*;

/**
 *  This class defines a message handler to simple request that a String be displayed.
 */
public class ServerStringMessageHandler extends ServerNonLoginHandler
{
	private static final long serialVersionUID = -4389793305302302467L;

  public ServerStringMessageHandler(String str, Chat4Server server, ConnectionToClient client)
  {
    super(str, server, client);
  }

  /**
   *  This method has the message String displayed on the server console and
   *  sent to all clients in the channel who are not blocking the user
   */
  public void handleMess()
  {
    getServer().getConsole().display((String)getClient().getInfo("id") + "> " + getMessage());
    getServer().sendToAllClients(getMessage(), (String) getClient().getInfo("channel"), (String)getClient().getInfo("id"));
  }

}