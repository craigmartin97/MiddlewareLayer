/*
 * Class that defines what type of message that will be sent
 */
package Messages;

import Agents.MetaAgent;
import java.util.HashMap;

/**
 *
 * @author Group H
 */
public enum MessageType
{
    /**
     * Message type sending a message
     */
    SEND_MESSAGE(String.class, "Message Is Attempting To Send"),
    /**
     * Adding a node
     */
    ADD_NODE(MetaAgent.class, "A Node Is Being Added"),
    /**
     * Update the addresses
     */
    UPDATE_ADDRESSES(HashMap.class, "Updating All Addresses In The System"),
    /**
     * Failed to send message
     */
    FAILED(Message.class, "Failed To Deliver"),
    /**
     * Add a node monitor message
     */
    ADD_NODE_MONITOR(MetaAgent.class, "A New Node Monitor Is Being Added");

    /**
     * The type that is required
     */
    Class type;

    /**
     * The description of the message
     */
    String context;

    /**
     * Constructor - creates a new message type
     *
     * @param m
     * @param context
     */
    private MessageType(Class m, String context)
    {
        type = m;
        this.context = context;
    }

    /**
     * Returns the message description, context being sent
     *
     * @return - context
     */
    @Override
    public String toString()
    {
        return context;
    }

    /**
     * Method returns the type of class
     *
     * @return - type
     */
    public Class getType()
    {
        return type;
    }
}
