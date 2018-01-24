/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Agents;

import FileUtility.FileUtility;
import Messages.Message;
import Messages.MessageType;
import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Contains all methods and functionality of the Portals
 *
 * @author Group H
 */
public class Portal extends NodeMonitor
{

    /**
     * The direct links to the agent, the children of the portal
     */
    private final HashSet<MetaAgent> subAgents = new HashSet();

    /**
     * Hash map for all connected agents
     */
    private final ConcurrentHashMap<String, MetaAgent> routingTable = new ConcurrentHashMap<>();

    /**
     * Set to store all of the active monitors of the portal
     */
    private final HashSet<NodeMonitor> activeMonitors = new HashSet<>();

    /**
     * Constructor - Creates a new Portal, sets the name only
     *
     * @param name name of the portal
     */
    public Portal(String name)
    {
        super(name);
    }

    /**
     * Constructor - Creates a new portal which sets the name and parent
     *
     * @param name - name of portal to be set
     * @param parentAgent - parent portal its connected to
     */
    public Portal(String name, MetaAgent parentAgent)
    {
        super(name);
        setParent(parentAgent);
    }

    /**
     * This method assigns the parent to the portal First the portal adds the
     * parentAgent to it's own stored addresses Then the parentAgent adds the
     * portal to it's stored addresses
     *
     * If the the portal has subAgents (Children) then the parentAgents
     * addresses needs to be updated. An updated set of addresses is transfered
     * to the parent agent
     *
     * @param parentAgent - The parent to this portal
     */
    private void setParent(MetaAgent parentAgent)
    {
        this.parentAgent = parentAgent;

        //If the agent has a parentAgent set
        if (parentAgent != null)
        {
            //Put parentAgent in this portals routing table
            routingTable.put(parentAgent.toString(), parentAgent);

            //Add this portal to the parentAgents queue
            parentAgent.offerToQueue(new Message(
                    MessageType.ADD_NODE, this.toString(), this.parentAgent.toString(), this)
            );
        }
    }

    /**
     * Adds new monitor to active monitors list
     *
     * @param monitor - Monitor to be added
     */
    private void addMonitor(NodeMonitor monitor)
    {
        activeMonitors.add(monitor);
    }

    /**
     * Iterates, loops, through the active monitors and adds a message to the
     * queue
     *
     * @param message - The received message
     */
    private void updateMonitors(Message message)
    {
        Iterator<NodeMonitor> nodes = activeMonitors.iterator();
        while (nodes.hasNext())
        {
            nodes.next().offerToQueue(message);
        }
    }

    /**
     * Method that updates the portals sub agents with the new agent. It adds it
     * to their blocking queue
     *
     * @param node - The agent that needs to be added
     * @param address - The address that the portal has for that agent
     */
    private void requestSubAgentsToAddAgent(String agent, MetaAgent address)
    {
        // lamdba expression in forEach loop. a is the MetaAgent element in the subAgents set
        // for each metaAgent element do the following
        subAgents.forEach((a)
                -> 
                {
                    //Checks that we are not sending the request back to a node that sent it to us
                    // if the MetaAgent doesnt equal the MetaAgent passed in and the MetaAgent class equals this class
                    if (!a.equals(address) && a.getClass() == this.getClass())
                    {
                        // offer a new message to that elements queue
                        a.offerToQueue(new Message(MessageType.ADD_NODE, agent, a.toString(), this));
                    }
        });
    }

    /**
     * An agent will be added to the address book If the agent name passed in is
     * the address name passed in it adds it to its children and then add this
     * agent depending upon the scope
     *
     * @param agent - name of the agent that is being added
     * @param address - the MetaAgent object passed in
     */
    private void addAgent(String agent, MetaAgent address)
    {
        routingTable.put(agent, address);

        // if the agent is the addresses name add the address
        if (agent.equals(address.toString()))
        {
            subAgents.add(address);
        }
        // request the children add the agent
        requestSubAgentsToAddAgent(agent, address);
        if (parentAgent != null && address != (parentAgent) && address.getScope(agent) != this)
        {
            // add to portals queue
            parentAgent.offerToQueue(new Message(MessageType.ADD_NODE, agent, parentAgent.toString(), this));
        }
    }

    /**
     * for debugging, showing the addresses that are stored in each
     * parentAgent/agent
     */
    public void showAddresses()
    {
        File storedAddresses = new File("storedAddresses.txt");

        String showAddresses = "Showing All Stored Addresses For " + this.toString() + ":";
        System.out.println(showAddresses);

        FileUtility.writeFile(storedAddresses, showAddresses + "\n");

        Iterator<String> t = routingTable.keySet().iterator();
        while (t.hasNext())
        {
            String next = t.next();
            String nextAddress = next + " : " + routingTable.get(next);
            FileUtility.writeFile(storedAddresses, nextAddress + "\n");
            System.out.println(nextAddress);
        }

        String showChildren = "Showing Sub Addresses For " + this.toString() + ":";
        System.out.println(showChildren);

        FileUtility.writeFile(storedAddresses, showChildren + "\n");

        Iterator<MetaAgent> storedChildren = subAgents.iterator();
        while (storedChildren.hasNext())
        {
            MetaAgent nextChild = storedChildren.next();
            FileUtility.writeFile(storedAddresses, nextChild + "\n");
            System.out.println(nextChild);
        }
    }

    /**
     * Method returns the scope of the agent
     *
     * @param name - Name of the agents scope
     * @return
     */
    @Override
    public MetaAgent getScope(String name)
    {
        // If the name is equal to this node
        if (name.equals(this.toString()))
        {
            // return this agents scope
            return this.getScope();
        } else
        {
            // return the scope of the agent held in 
            return routingTable.get(name).getScope(name);
        }
    }

    /**
     * Method handles the message and produces an output dependant upon the type
     * of message received
     *
     * @param message - The message which has been received
     */
    private void getMessageDetails(Message message)
    {
        switch (message.getType())
        {

            case SEND_MESSAGE:
                String theMessage = (String) message.getMessageItem();
                System.out.println("The Portal: " + this.toString() + " Has Successfully Recieved The Message: " + theMessage);
                break;
            case ADD_NODE:
                addAgent(message.getFrom(), (MetaAgent) message.getMessageItem());
                break;
            case FAILED:
                System.out.println("Message Has Fail To Sent, An Unexpected Error Has Occurred");
                break;
            case ADD_NODE_MONITOR:
                addMonitor((NodeMonitor) message.getMessageItem());
                break;
        }
    }

    /**
     * Method deals with a message that isnt for this portal It will pass the
     * message onto the known address in the table Otherwise, it will produce an
     * error message if it's not found
     *
     * @param message - The message that is being passed on
     */
    private void lookUpAndPassOn(Message message)
    {
        // If the routing table contains the address value
        if (routingTable.containsKey(message.getTo()))
        {
            // pass on to that tables message queue to be dealt with
            routingTable.get(message.getTo()).offerToQueue(message);
        } else
        {
            // produce an error message if the address was not found
            routingTable.get(message.getFrom())
                    .offerToQueue(new Message(MessageType.FAILED,
                            this.toString(),
                            message.getFrom(),
                            message));
        }
    }

    /**
     * Checks the name passed in is the same as this portal
     *
     * @param portalName - name of the MetaAgent
     * @return
     */
    private boolean isForThisPortal(String portalName)
    {
        return portalName.equals(this.toString());
    }

    /**
     * Method handles a message that is being sent to a portal
     *
     * @param message - The message to be handled
     */
    @Override
    public void messageHandler(Message message)
    {
        updateMonitors(message);
        // For this portal
        if (isForThisPortal(message.getTo()))
        {
            getMessageDetails(message);
            // Find the correct address
        } else
        {
            lookUpAndPassOn(message);
        }
        
        displayMessage(message);
    }

    @Override
    public void displayMessage(Message message)
    {
        String obj = "";
        String type = "";

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

        String to = message.getTo();
        String from = message.getFrom();
        String date = message.getDate().toString();
        String time = message.getTimeSent().toString();
        
        gui.messageReceived(obj, type, date, time, to, from);
    }
}
