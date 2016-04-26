package password;

import java.util.Map;
import java.util.HashMap;

/**
 * This class supplies password services
 *
 * @author Chris Nevison
 * @version march 2012
 */
public class PasswordManager
{
  private Map<String,String> IDPasswordMap;

  public PasswordManager()
  {
    IDPasswordMap = new HashMap<String, String>();
  }

  public void addIDPasswordPair(String id, String password) throws PasswordException
  {
    if(!IDPasswordMap.containsKey(id))
    {
      IDPasswordMap.put(id, password);
    }
    else
    {
      throw new PasswordException("id " + id + " already used.");
    }
  }

  public boolean validID(String id)
  {
    return IDPasswordMap.containsKey(id);
  }

  public boolean validatePassword(String id, String password) throws PasswordException
  {
    if(IDPasswordMap.containsKey(id))
    {
      return IDPasswordMap.get(id).equals(password);
    }
    else
    {
      throw new PasswordException("Attempt to use invalid id in validatePassword");
    }
  }

  public void removeID(String id) throws PasswordException
  {
    if(IDPasswordMap.containsKey(id))
    {
      IDPasswordMap.remove(id);
    }
    else
    {
      throw new PasswordException("Attempt to remove invalid id");
    }
  }

}

