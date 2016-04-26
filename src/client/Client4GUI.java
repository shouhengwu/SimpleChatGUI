

package client;

/* TextDemo.java requires no other files. */

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

import ocsf.client.ObservableClient;
import common.ChatIF;

public class Client4GUI{
	

    /**
     * The default port to connect on.
     */
    private final static int DEFAULT_PORT = 5555;
	
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI(String host, int port) {
        //Create and set up the window.
        Client4GUIFrame frame = new Client4GUIFrame("SimpleChat", host, port);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
    	
    	String hostStr = "";
        String portStr = "";  //The port number

        try {
            hostStr = args[2];
        } catch (ArrayIndexOutOfBoundsException e) {
            hostStr = "localhost";
        }

        try {
            portStr = args[3];
        } catch (ArrayIndexOutOfBoundsException e) {
            portStr = "" + DEFAULT_PORT;
        }

        final String host = hostStr;
        final int port = Integer.parseInt(portStr);

        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() { 
            	createAndShowGUI(host, port);} 
            });
    }
}

