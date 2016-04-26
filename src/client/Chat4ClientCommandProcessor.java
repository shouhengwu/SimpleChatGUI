// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com

package client;

import ocsf.client.*;
import common.*;

import java.io.*;
import java.util.Observable;
import java.util.Observer;

/**
 * This class overrides some of the methods defined in the abstract
 * superclass in order to give more functionality to the client.
 *
 * Modified to complete exercises E50 and E51
 * Uses reflection to create ClientCommand subclasses for each command
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Chris Nevison
 * @version March 2016
 */
public class Chat4ClientCommandProcessor
{
  //Instance variables **********************************************

  private ObservableClient myOC;
  String myId;
  ChatIF myUI;

  //Constructors ****************************************************

  /**
   * Constructs an instance of the chat client.
   *
   * @param host The server to connect to.
   * @param port The port number to connect on.
   * @param clientUI The interface type variable.
   */

  public Chat4ClientCommandProcessor(String id, String password, ObservableClient oc, ChatIF UI)
    throws IOException
  {
    myUI = UI;
    myOC = oc;
    myId = id;
    myOC.openConnection();
    String handlerMessage = "ServerLoginHandler " + myId + ' ' + password;
    myOC.sendToServer(handlerMessage);
  }

  String getId()
  {
    return myId;
  }
  
  ChatIF clientUI()
  {
	  return myUI;
  }
  
  ObservableClient OC()
  {
	  return myOC;
  }

  //Instance methods ************************************************

  /**
   * This method handles all data coming from the user interfaces
   *
   * @param message The message from the UI.
   */
  public void handleMessageFromClientUI(String message)
  {
    if(message.charAt(0) != '#')
    {
      sendMessageToServer(message);
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
  private void sendMessageToServer(String message)
  {
    if(myOC.isConnected())
    {
      String mess = "ServerStringMessageHandler " + message;
      try
      {
    	  myOC.sendToServer(mess);
      }
      catch(IOException e)
      {
        clientUI().display("IOException " + e + "\nCould not send message to server.  Terminating client.");
        quit();
      }
    }
    else
    {
      clientUI().display("Not connected to a server. Must login before sending a message.");
    }
  }

  /**
   * This method handles a command message after the '#' has been stripped
   * It uses reflection to create an instance of a subclass of ClientCommand whose name
   * is given by the first token in the message string
   *
   * @param message the command string (after '#' is stripped)
   */
  
  protected void setClientUI(ChatIF newUI){
	  myUI = newUI;
  }
  
  private void createAndDoCommand(String message)
  {
    String commandStr;
    int indexBlank = message.indexOf(' ');
    if(indexBlank == -1)
    {
      commandStr = "client." + message;
      message = "";
    }
    else
    {
      commandStr = "client." + message.substring(0, indexBlank);
      message = message.substring(indexBlank+1);
    }

    try
    {
      @SuppressWarnings("rawtypes")
      Class[] param = {String.class, Chat4ClientCommandProcessor.class};
      ClientCommand cmd = (ClientCommand)Class.forName(commandStr).getConstructor(param).newInstance(message, this);
      cmd.doCommand();
    }
    catch(Exception ex)
    {
      clientUI().display("\nNo such command " + commandStr + "\nNo action taken.");
    }
  }

  /**
   * This method terminates the client.
   */
  public void quit()
  {
    try
    {
    	myOC.closeConnection();
    }
    catch(IOException e) {}
    System.exit(0);
  }
}
//End of Chat4Client class
