

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

public class Client4ChatPanel extends JPanel implements ActionListener, ChatIF, Observer {

	protected JTextField textField;
    protected JTextArea textArea;
    private final static String newline = "\n";
    
    /**
     * The instance of the client created by this ClientGUI.
     */
    Chat4ClientCommandProcessor client;

    public Client4ChatPanel(String username, Chat4ClientCommandProcessor client) {
        super(new GridBagLayout());

        textField = new JTextField(20);
        textField.addActionListener(this);

        textArea = new JTextArea(5, 20);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        //Add Components to this panel.
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;

        c.fill = GridBagConstraints.HORIZONTAL;
        add(textField, c);

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        add(scrollPane, c);
        
        
        this.client = client;
        client.OC().addObserver(this);
    }

    public void actionPerformed(ActionEvent evt) {
        String message = textField.getText();
        client.handleMessageFromClientUI(message);
        textField.setText("");
    }
    
    public void update(Observable OC, Object msg)
    {
      if(msg instanceof String)
      	display((String)msg);
      else if(msg instanceof Exception)
      	display("Connection exception " + (Exception)msg);
    }
    
    public void display(String message)
    {
        textArea.append(message + newline);    	
        //Make sure the new text is visible, even if there
        //was a selection in the text area.
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
}

