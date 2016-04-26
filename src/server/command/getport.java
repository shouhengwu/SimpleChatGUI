package server.command;

import server.*;

/**
 *  Implements server command to report the current port.
 *
 * @author Chris Nevison
 * @version February 2012
 */
public class getport extends ServerCommand
{
  public getport(String str, Chat4Server server)
  {
    super(str, server);
  }

  public void doCommand()
  {
    getServer().getConsole().display("Current port is " + getServer().getPort());
  }
}


