package server;

import server.*;
import ocsf.server.*;
import java.io.*;

/**
 *  This abstract class specifies requests that can be sent from a client to
 *  the server. Rather than Strings being sent, an request object, an instance of a
 *  subclass of this class, is sent.
 *  Any request from client to server, including just echoing a String
 *  must extend this class.
 *
 * @author Chris Nevison
 * @version February 2012
 */

public abstract class ServerMessageHandler implements Serializable
{

	private static final long serialVersionUID = 8914728776829666970L;
  private String message;
  protected Chat4Server myServer;
  private ConnectionToClient myClient;

  /**
   * Creates the message handler
   *
   * @param msg The remainder string after handler has been stripped
   * @param server The server processing this handler
   * @param client The Clientconnectionestablished for the client sending this message
   */
  public ServerMessageHandler(String msg, Chat4Server server, ConnectionToClient client)
  {
    message = msg;
    myServer = server;
    myClient = client;
  }

  /**
   * Allows subclasses access to the ConnectionToClient
   *
   * @return the ConnectionToClient for this client
   */
  protected ConnectionToClient getClient()
  {
    return myClient;
  }

  /**
   * Allows subclasses access to the ConnectionToClient
   *
   * @return the ConnectionToClient for this client
   */
  protected Chat4Server getServer()
  {
    return myServer;
  }

  protected String getMessage()
  {
    return message;
  }

  /**
   * This method provides the slot that any command from the client sent to the server must fill by
   * implementing this method in the subclass that defines the command.
   */
  public abstract void handleMessage();

}
