/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Agents;

import GUI.MessageGUI;
import Messages.Message;
import Messages.MessageType;
import java.util.ArrayList;

/**
 * This class allows agents to display messages on a GUI application
 * 
 * PLEASE NOTE THIS CLASS IS NO LONGER IN USE BUT HAS STILL BEEN INCLUDED 
 * IN THE SOFTWARE/SUBMISSION FOR VIEWING PURPOSES
 * THIS CLASS CREATES A NEW NODEMONITOR THAT WILL BE DISPLAYED ON A GUI
 * THE USER WOULD HAVE TO MANUALLY CREATE A NEW NODEMONITOR FOR EACH PORTAL
 * BUT NOW IT IS AUTOMATICALLY DONE
 * 
 *
 * @author Group H
 */
public class NodeMonitorOld extends MetaAgent
{

    /**
     * THIS WOULD PREVIOUSLY BE UNCOMMENTED
     */
    //private final MessageGUI gui;

    // List of all agents being watched by this monitor
    ArrayList<MetaAgent> activeAgents = new ArrayList<>();

    public NodeMonitorOld(String monitorName)
    {
        super(monitorName);
        /**
         * THESE WOULD PREVIOSULY BE UNCOMMENTED
         */
        //gui = new MessageGUI(this);
        //gui.setVisible(true);

    }

    private void displayMessage(Message message)
    {
        String obj = "";
        String type = "";
        String to;
        String from;
        String date;
        String time;

        switch (message.getType())
        {
            case SEND_MESSAGE:
                obj = (String) message.getMessageItem();
                type = "String";
                break;
            case ADD_NODE:
                MetaAgent agent = (MetaAgent) message.getMessageItem();
                obj = agent.toString() + " Agent Added With Scope: " + agent.getScope();
                type = "MetaAgent";
                break;
            case UPDATE_ADDRESSES:
                obj = "Address Updated";
                type = "HashMap";
                break;
            case FAILED:
                obj = (String) message.getMessageItem();
                type = "Message";
                break;
            case ADD_NODE_MONITOR:
                agent = (MetaAgent) message.getMessageItem();
                agent.offerToQueue(new Message(MessageType.ADD_NODE_MONITOR,
                        this.toString(),
                        agent.toString(),
                        this));
                activeAgents.add(agent);
                break;
            default:
                obj = "";
                type = "";
        }

        to = message.getTo();
        from = message.getFrom();
        date = message.getDate().toString();
        time = message.getTimeSent().toString();

        /**
         * WOULD PREVIOUSLY BE UNCOMMENTED
         */
        //gui.messageReceived(obj, type, date, time, to, from);

    }

    /**
     * Extract the message details
     *
     * @param message - Message being passed
     */
    @Override
    public void messageHandler(Message message)
    {
        displayMessage(message);
    }
}
