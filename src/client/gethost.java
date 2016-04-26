package client;


/**
 *  Implements client command to report the current host.
 *
 * @author Chris Nevison
 * @version March 2016
 */
public class gethost extends ClientCommand
{
  public gethost(String str, Chat4ClientCommandProcessor client)
  {
    super(str, client);
  }

  public void doCommand()
  {
    getClient().clientUI().display("Current host is " + getClient().OC().getHost());
  }

}