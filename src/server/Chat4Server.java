package server;

import java.io.*;

import ocsf.server.*;
import server.command.*;
import common.ChatIF;
import password.*;

import java.util.HashSet;


/**
 * This class modifies the EchoServer from the book
 *
 * Modified to complete E49, E50 and E51
 * Uses reflection to create subclasses of the ServerCommand and
 * ServerMessageHandler classes.
 *
 * It uses messages from Client to Server that names of the appropriate
 * ServerMessageHandler subclass and delegates responsibility for handling
 * a message to the instance of that subclass created by reflection.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @author Chris Nevison
 * @version March 2012
 */
public class Chat4Server extends AbstractServer
{
  //Class variables *************************************************

  /**
   * The default port to listen on.
   */
  final public static int DEFAULT_PORT = 5555;

  ChatIF myConsole;
  PasswordManager passwordChecker;
  ChannelManager myChannels;

  //Constructors ****************************************************

  /**
   * Constructs an instance of the echo server.
   *
   * @param port The port number to connect on.
   */
  public Chat4Server(int port, ChatIF console)
  {
    super(port);
    myConsole = console;
    passwordChecker = new PasswordManager();
    myChannels = new ChannelManager();
    getChannelManager().createChannel("global");
  }

  public ChatIF getConsole()
  {
    return myConsole;
  }

  public PasswordManager getPasswordManager()
  {
    return passwordChecker;
  }

  public ChannelManager getChannelManager()
  {
    return myChannels;
  }

  //Instance methods ************************************************

  /**
   * This method handles any messages received from the client.
   *
   * @param msg The message received, an instance of a subclass of ServerMessageHandler
   * @param client The connection from which the message originated.
   */
  public void handleMessageFromClient (Object msg, ConnectionToClient client)
  {
// getConsole().display("start handleMessageFromClient");
    String handlerStr;
    String message = (String)msg;
    int indexBlank = message.indexOf(' ');
    if(indexBlank == -1)
    {
      handlerStr = "server." + message;
      message = "";
    }
    else
    {
      handlerStr = "server." + message.substring(0, indexBlank);
      message = message.substring(indexBlank+1);
    }

    try
    {
      @SuppressWarnings("rawtypes")
	  Class[] param = {String.class, Chat4Server.class, ConnectionToClient.class};
      ServerMessageHandler handler = (ServerMessageHandler)Class.forName(handlerStr).getConstructor(param).newInstance(message, this, client);
      handler.handleMessage();
    }
    catch(Exception ex)
    {
      getConsole().display("Bad handler " + handlerStr + " sent from " + client.getInfo("id") + "\nNo action taken.");
      try
      {
        client.sendToClient("No such message handler " + handlerStr + ". No action taken.");
      }
      catch(IOException ioex)
      {
        getConsole().display(ioex + "\n Exception sending to client " + client.getInfo("id"));
      }
    }
  }

  public void handleMessageFromServerUI(String message)
  {
    if(message.charAt(0) != '#')
    {
      handleUIString(message);
    }
    else
    {
      message = message.substring(1);
      createAndDoCommand(message);
    }

  }

  /**
   * This method handles a simple string message, not a command
   *
   * @param message The message from the UI
   */
  private void handleUIString(String message)
  {
    message = "SERVER MSG> " + message;

    getConsole().display(message);
    sendToAllClients(message);
  }


  /**
   * This method handles a command message after the '#' has been stripped
   * It uses reflection to create an instance of a subclass of ServerCommand whose name
   * is given by the first token in the message
   *
   * @param message The command string (after '#' is stripped)
   */
  private void createAndDoCommand(String message)
  {
    String commandStr;
    int indexBlank = message.indexOf(' ');
    if(indexBlank == -1)
    {
      commandStr = "server.command." + message;
      message = "";
    }
    else
    {
      commandStr = "server.command." + message.substring(0, indexBlank);
      message = message.substring(indexBlank+1);
    }

    try
    {
      @SuppressWarnings("rawtypes")
	  Class[] param = {String.class, Chat4Server.class};
      ServerCommand cmd = (ServerCommand)Class.forName(commandStr).getConstructor(param).newInstance(message, this);
      cmd.doCommand();
    }
    catch(Exception ex)
    {
      getConsole().display("\nNo such command " + commandStr + "\nNo action taken.");
    }
  }

  /**
   * This method overrides the one in the superclass.  Called
   * when the server starts listening for connections.
   */
  protected void serverStarted()
  {
    getConsole().display
      ("Server listening for connections on port " + getPort());
  }

  /**
   * This method overrides the one in the superclass.  Called
   * when the server stops listening for connections.
   */
  protected void serverStopped()
  {
    getConsole().display
      ("Server has stopped listening for connections.");
  }

  protected void serverClosed()
  {
    getConsole().display("Server is closed and not listening.");
  }

  synchronized protected void clientException(
      ConnectionToClient client, Throwable exception)
  {
    String message = client.getInfo("id") + " has disconnected.";
    getConsole().display(message);
    sendToAllClients("SERVER MSG> " + message);
  }

  //this sends to all clients in a given channel
  public void sendToAllClients(String msg, String channel, String sender)
  {
    Thread[] clientThreadList = getClientConnections();
    String name;
    String id;
    HashSet<String> blocked;
    for (int i=0; i<clientThreadList.length; i++)
    {
      try
      {
        name =(String) ((ConnectionToClient)clientThreadList[i]).getInfo("channel");
        id =(String) ((ConnectionToClient)clientThreadList[i]).getInfo("id");
        blocked = (HashSet<String>) ((ConnectionToClient)clientThreadList[i]).getInfo("iblock");
        if (name.equals(channel) && !blocked.contains(sender)){
          ((ConnectionToClient)clientThreadList[i]).sendToClient(id + ">" + msg);
        }
      }
      catch (Exception ex) {}
    }
  }


}

