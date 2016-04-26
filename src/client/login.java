package client;

import java.io.IOException;

/**
 *  Implements client command to log in to the current host.
 *
 * @author Chris Nevison
 * @version March 2016
 */
public class login extends NotConnectedClientCommand
{
  public login(String str, Chat4ClientCommandProcessor client){
    super(str, client);
  }
  public login(String id, String password, Chat4ClientCommandProcessor client)
  {
    super(id + ' ' + password, client);
  }


  public void doCmd()
  {
    try
    {
      getClient().OC().openConnection();
      getClient().clientUI().display("Connection to " + getClient().OC().getHost() + " opened.");
      String handlerMessage = "ServerLoginHandler " + getStr();
      getClient().OC().sendToServer(handlerMessage);
    }
    catch(IOException ex)
    {
      getClient().clientUI().display(ex + "\nConnection to " + getClient().OC().getHost() + " failed.");
    }
  }

}

