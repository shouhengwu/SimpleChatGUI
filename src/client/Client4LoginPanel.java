

package client;

/* TextDemo.java requires no other files. */

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import ocsf.client.ObservableClient;
import common.ChatIF;

public class Client4LoginPanel extends JPanel implements ActionListener, ChatIF, Observer{

	private String username;
	
	protected JTextField usernameField;
    protected JTextField passwordField;
    protected JTextArea notificationArea;
    private final static String newline = "\n";
    
    private Client4GUIFrame myFrame;
    
    /**
     * The default port to connect on.
     */
    final public static int DEFAULT_PORT = 5555;
    
    final public static String DEFAULT_ID = "";
    final public static String DEFAULT_PASSWORD = "";
    
    /**
     * The instance of the client created by this ClientGUI.
     */
    private Chat4ClientCommandProcessor client;


    public Client4LoginPanel(String host, int port, Client4GUIFrame frame) {
    	
    	super(new GridBagLayout());

    	notificationArea = new JTextArea(5, 2);
    	notificationArea.setEditable(false);
    	
        usernameField = new JTextField(15);

        passwordField = new JTextField(15);
        
        JLabel username_label = new JLabel("Username");
        JLabel password_label = new JLabel("Password");
        
        JButton confirm_button = new JButton("Log in");
        confirm_button.addActionListener(this);

        //Add Components to this panel.
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;

        c.fill = GridBagConstraints.HORIZONTAL;
        add(username_label, c);
        add(usernameField, c);
        add(password_label, c);
        add(passwordField, c);
        add(notificationArea, c);
        add(confirm_button, c);

        myFrame = frame;
        
        
        /*
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        add(passwordField, c);
        */
        
        try
        {
        	client = new Chat4ClientCommandProcessor(DEFAULT_ID, DEFAULT_PASSWORD, new ObservableClient(host, port), this);

        } catch(IOException ex){
        	System.out.println("IOException " + ex + "when connecting, shutting down.");	
        }
        client.OC().addObserver(this);
    }

    public void actionPerformed(ActionEvent evt) {
        username = usernameField.getText();
        String password = passwordField.getText();
        
        
        usernameField.setText("");
        passwordField.setText("");
        
        try
        {
          getClient().OC().openConnection();
          String handlerMessage = "ServerLoginHandler " + username + ' ' + password;
          getClient().OC().sendToServer(handlerMessage);
        }
        catch(IOException ex)
        {
          getClient().clientUI().display(ex + "\nConnection to " + getClient().OC().getHost() + " failed.");
        }
        
    }
    
    public void display(String message)
    {
        notificationArea.append(message + newline);    	
        //Make sure the new text is visible, even if there
        //was a selection in the text area.
        notificationArea.setCaretPosition(notificationArea.getDocument().getLength());
    }
    
    public void update(Observable OC, Object msg) {
        if (msg instanceof String){
        	if(msg.equals("#loginSucceeded")){
        		client.OC().deleteObserver(this);
        		getFrame().loginSucceeded(username, client);
        	}
        	else if(msg.equals("#loginFailed")){
        		display("Invalid username/password.");
        	}
        	
        }
        
    }
    
    public Client4GUIFrame getFrame(){
    	return myFrame;
    }
    
    public Chat4ClientCommandProcessor getClient(){
    	return client;
    }
    
    
    
}

