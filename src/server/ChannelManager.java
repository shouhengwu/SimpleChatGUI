package server;

import ocsf.server.*;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.NoSuchElementException;


public class ChannelManager
{
  private Map<String, Set<ConnectionToClient> > channelMap;

  public ChannelManager()
  {
    channelMap = new HashMap<String, Set<ConnectionToClient> >();
  }

  public boolean channelExists(String channel)
  {
    return channelMap.containsKey(channel);
  }

  public void createChannel(String channel)
  {
    channelMap.put(channel, new HashSet<ConnectionToClient>());
  }

  public void joinChannel(String channel, ConnectionToClient client)
  {
    if (channelMap.containsKey(channel))
    {
      channelMap.get(channel).add(client);
    }
    else
    {
      throw new NoSuchElementException("No channel " + channel);
    }
  }

  public void removeFromChannel(String channel, ConnectionToClient client)
  {
    if (channelMap.containsKey(channel))
    {
      channelMap.get(channel).remove(client);
    }
    else
    {
      throw new NoSuchElementException("No channel " + channel);
    }
  }

  public boolean isEmptyChannel(String channel)
  {
    return channelMap.get(channel).isEmpty();
  }

  public void removeChannel(String channel)
  {
    channelMap.remove(channel);
  }

  public Set<ConnectionToClient> getChannelClients(String channel)
  {
    return channelMap.get(channel);
  }

  public Set<String> getChannelNames()
  {
    return channelMap.keySet();
  }

}

