/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Agents;

import GUI.MessageGUI;
import Messages.Message;
import java.util.ArrayList;

/**
 * This class allows agents to display messages on a GUI application
 *
 * @author Group H
 */
public abstract class NodeMonitor extends MetaAgent
{

    protected MessageGUI gui;

    // List of all agents being watched by this monitor
    protected ArrayList<MetaAgent> activeAgents = new ArrayList<>();

    public NodeMonitor(String monitorName)
    {
        super(monitorName);
        gui = new MessageGUI(this);
        gui.setVisible(true);
    }

    protected abstract void displayMessage(Message message);

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
