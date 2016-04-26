

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

public class Client4GUIFrame extends JFrame{
    
    private Client4LoginPanel loginGUI;
    private Client4ChatPanel chatGUI;

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    
    public Client4GUIFrame(String name, String host, int port){
    	super(name);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    	loginGUI = new Client4LoginPanel(host, port, this);
    	
        //Add contents to the window.
        add(loginGUI);


        //Display the window.
        pack();
        setVisible(true);
    }
    
    //returns true if login succeeds
    //returns false otherwise
    protected void loginSucceeded(String username, Chat4ClientCommandProcessor client){
    	this.remove(loginGUI);
    	
    	chatGUI = new Client4ChatPanel(username, client);
    	this.add(chatGUI);
    	
    	pack();
    	
    }
    
}

