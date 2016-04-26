package client;


/**
 *  Implements client command to report the current port.
 *
 * @author Chris Nevison
 * @version March 2016
 */
public class getport extends ClientCommand
{
  public getport(String str, Chat4ClientCommandProcessor client)
  {
    super(str, client);
  }

  public void doCommand()
  {
    getClient().clientUI().display("Current port is " + getClient().OC().getPort());
  }

}