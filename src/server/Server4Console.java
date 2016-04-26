package server;

import java.io.*;
import common.*;

/**
 * This class constructs the UI for a chat server.  It implements the
 * chat interface in order to activate the display() method.
 *
 * @author Chris Nevison
 * @version February 2012
 */
public class Server4Console implements ChatIF
{
  //Class variables *************************************************

  /**
   * The default port to connect on.
   */
  final public static int DEFAULT_PORT = 5555;

  //Instance variables **********************************************

  /**
   * The instance of the server served by this console
   */
  private Chat4Server server;

  //Constructors ****************************************************

  /**
   * Constructs an instance of the ServerConsole UI.
   *
   * @param port The port to connect on.
   */
  public Server4Console(int port)
  {
    server= new Chat4Server(port, this);
    try
    {
      server.listen();
    }
    catch(IOException ex)
    {
      display("Exception starting server.");
    }
  }


  //Instance methods ************************************************

  /**
   * This method waits for input from the console.  Once it is
   * received, it sends it to the client's message handler.
   */
  public void accept()
  {
    try
    {
      BufferedReader fromConsole =
        new BufferedReader(new InputStreamReader(System.in));
      String message;

      while (true)
      {
        message = fromConsole.readLine();
        server.handleMessageFromServerUI(message);
      }
    }
    catch (Exception ex)
    {
      display("Unexpected error while reading from console!");
    }
    accept();
  }

  /**
   * This method overrides the method in the ChatIF interface.  It
   * displays a message onto the screen.
   *
   * @param message The string to be displayed.
   */
  public void display(String message)
  {
    System.out.println(message);
  }


  //Class methods ***************************************************

  /**
   * This method is responsible for the creation of the Server UI.
   *
   * @param args[0] The port to connect to.
   */
  public static void main(String[] args)
  {
    int port = 0;  //The port number

    try
    {
      port = Integer.parseInt(args[0]);
    }
    catch(ArrayIndexOutOfBoundsException e)
    {
      port = DEFAULT_PORT;
    }
    Server4Console sc= new Server4Console(DEFAULT_PORT);
    sc.accept();  //Wait for console data
  }
}

