package server;

import server.*;
import ocsf.server.*;

import java.io.*;

  /**
   *  This class defines a message handler to  allow the user to switch channels
   */
  public class JoinChannel extends ServerNonLoginHandler
  {
    private String channelName;

    public JoinChannel(String str, Chat4Server server, ConnectionToClient client)
    {
      super(str, server, client);
      channelName = getMessage();
      if (channelName.trim().length() == 0)
      {
        channelName = "global";
      }
    }

    /**
     *  This method will switch the user's channel. Additionally, it will delete a channel after everyone has left
     *  and it will create new channels if one requests to move to a channel that does not exist.
     */
    public void handleMess()
    {
      removeUserFromCurrentChannel();
      createNewChannelIfNeeded();
      addUserToChannel();
    }

    /**
     * remove user from current channel and erase that channel if it becomes empty
     */
    private void removeUserFromCurrentChannel()
    {
      String oldChannelName = (String) getClient().getInfo("channel");
      if(oldChannelName != null){
        getServer().getChannelManager().removeFromChannel(oldChannelName, getClient());
        if (getServer().getChannelManager().isEmptyChannel(oldChannelName))
        {
          getServer().getChannelManager().removeChannel(oldChannelName);// erase channel
        }
      }
    }

    /**
     * create a new channel if none by the given name exists
     */
    private void createNewChannelIfNeeded()
    {
      if(!getServer().getChannelManager().channelExists(channelName))
      {
        getServer().getChannelManager().createChannel(channelName);
        try
        {
          getClient().sendToClient("you have created the channel " + channelName);
        }
        catch (IOException e)
        {
          getServer().getConsole().display(e + "\nAn error has occured when sending channel create message to client");
        }
      }
    }

    /**
     * add user to the channel
     */
    private void addUserToChannel()
    {
      getServer().getChannelManager().joinChannel(channelName, getClient());
      getClient().setInfo("channel", channelName);
      try
      {
        getClient().sendToClient("you have joined the channel " + channelName);
      }
      catch (IOException e)
      {
        getServer().getConsole().display(e + "\nAn error has occured when sending channel join message to client");
      }
    }

  }


