/*
 * MetaAgent class defines what all MetaAgent do, such as Portals and UserAgents
 * Each agent runs on it's own thread and has it's own queue
 */
package Agents;

import Messages.Message;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Group H
 */
public abstract class MetaAgent 
{
    /**
     * Stores the parent agent of the agent, which is a portal
     * Called parentAgent as the name "portal" was confusing to work with
     */
    protected MetaAgent parentAgent = null;

    /**
     * Creates a new thread for each agent
     */
    private Thread messageThread;

    /**
     * The scope of each MetaAgent, scoping each MetaAgent
     */
    private MetaAgent scope;

    /**
     * The name of the agent
     */
    private final String agentName;

    /**
     * A new queue for the agent to distribute messages on The linked blocking
     * queue has a built in wait state
     */
    private final LinkedBlockingQueue<Message> messageQueue = new LinkedBlockingQueue<>();

    /**
     * Constructor - MetaAgent with a name and starts a new thread for that
     * agent
     *
     * @param agentName - name of the agent
     */
    public MetaAgent(String agentName)
    {
        this.agentName = agentName;
        startThread();
    }

    /**
     * Constructor - MetaAgent with a name, scope and starts a new thread.
     *
     * @param agentName - name of the agent
     * @param scope - the scope, area in which it is defined
     */
    public MetaAgent(String agentName, MetaAgent scope)
    {
        this.agentName = agentName;
        this.scope = scope;
        startThread();
    }

    /**
     * Offers a message to the agents tail of the agents queue
     *
     * @param message - The message that is to be offered to the queue
     */
    protected void offerToQueue(Message message)
    {

        try
        {
            messageQueue.offer(message, 10, TimeUnit.SECONDS);
        } catch (InterruptedException ex)
        {
            System.out.println("Unable to add to queue");
            Logger.getLogger(MetaAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Method returns the scope of the agent
     *
     * @return - scope
     */
    protected MetaAgent getScope()
    {
        return scope;
    }

    /**
     * Method returns the scope by the name of the agent
     *
     * @param name - name of the agent
     * @return - scope
     */
    protected MetaAgent getScope(String name)
    {
        return scope;
    }

    /**
     * Method returns the name of the object
     *
     * @return
     */
    @Override
    public String toString()
    {
        return agentName;
    }

    /**
     * Start the agents thread
     */
    private void startThread()
    {
        messageThread = new Thread(new Runnable()
        {

            @Override
            public void run()
            {
                while (true)
                {
                    try
                    {
                        messageHandler(messageQueue.take());
                    } catch (InterruptedException ex)
                    {
                        System.out.println("An Unexpected Error Has Occured");
                        Logger.getLogger(MetaAgent.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        messageThread.start();
    }

    /**
     * An abstract method that takes the message and handles where the message
     * goes
     *
     * @param message the message object to be handled
     */
    protected abstract void messageHandler(Message message);
}
