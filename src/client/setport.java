package client;



/**
 *  Implements client command to set the port.
 *  Can only be executed if not connected.
 *
 * @author Chris Nevison
 * @version February 2012
 */
public class setport extends NotConnectedClientCommand
{
  public setport(String str, Chat4ClientCommandProcessor client)
  {
    super(str, client);
  }

  public void doCmd()
  {
    getClient().OC().setPort(Integer.parseInt(getStr()));
  }

}