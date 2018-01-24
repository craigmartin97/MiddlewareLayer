/*
 * UserAgent class that defines what each agent does.
 * Agents can be such things as trams, planes etc
 * Contains all functionality of a UserAgent. That can send and recieve messages
 */
package Agents;

import FileUtility.FileUtility;
import Messages.Message;
import Messages.MessageType;
import java.io.File;

/**
 *
 * @author Group H
 */
public class UserAgent extends MetaAgent
{
    /**
     * Constructor - Sets the user agent's name, it's parent and the scope in
     * which it is defined
     *
     * @param name - Name of the agent
     * @param parentAgent - The agents parent
     * @param scope - The scope which the agent is avaliable
     */
    public UserAgent(String name, MetaAgent parentAgent, MetaAgent scope)
    {
        super(name, scope);
        this.parentAgent = parentAgent;
        updateParentAgent();
    }

    /**
     * A message will be added to the parent agent so it's address table can be
     * updated
     */
    private void updateParentAgent()
    {
        if (parentAgent == null)
        {
            return;
        } else
        {
            parentAgent.offerToQueue(new Message(MessageType.ADD_NODE, this.toString(), parentAgent.toString(), this));
        }
    }

    /**
     * Sends a message to an agent by adding the message to it's parent agent
     *
     * @param to - Who the message is being sent to
     * @param message - The message that is being sent
     */
    public void sendMessage(String to, String message)
    {
        parentAgent.offerToQueue(new Message(MessageType.SEND_MESSAGE, this.toString(), to, message));
    }

    /**
     * This method deals with the received receivedMessage. It gets the
     * receivedMessage and updates all of the active nodes watching, and deal
     * with it dependant upon the type of receivedMessage being sent
     *
     * @param receivedMessage - The receivedMessage that has been received that
     * needs to be handled
     */
    @Override
    public void messageHandler(Message receivedMessage)
    {
        /**
         * This method contains System Outputs, We understand they are not needed as
         * the node monitor will display if the message has successfully sent
         * we have left them in commented out incase we need to check if the 
         * message has successfully sent easier in later tests or development
         */
        
        // Get the message type
        switch (receivedMessage.getType())
        {
            case SEND_MESSAGE:
                // Create a new variable to store the received message
                String message = (String) receivedMessage.getMessageItem();
//                System.out.println(this.toString() + " Message Recieved By The UserAgent and Was Sent Successfully: " + message + "\n"
//                + "This Message Was Successfully Sent On: " + receivedMessage.getDate() + " " + receivedMessage.getTimeSent());

                // Write the receivedMessage to a file, used in later testing
                FileUtility.writeFile(new File("log.txt"), message + "\n");
                break;
            case FAILED:
                String error = "The Message Was Unable To Be Sent. \n";
//                System.out.println(error);
                String from = "Message was from: " + ((Message) receivedMessage.getMessageItem()).getFrom() + "\n";
//                System.out.println(from);
                String to = "Message was to " + ((Message) receivedMessage.getMessageItem()).getTo() + "\n";
//                System.out.println(to);
//                String dateTime = "The message was sent on " + ((Message) receivedMessage.getMessageItem()).getDate() + " " + ((Message) receivedMessage.getMessageItem()).getTimeSent() + "\n";
//                System.out.println(dateTime);

                String newMessage = error + from + to;
                FileUtility.writeFile(new File("errorLog.txt"), newMessage + "\n");
                break;
        }
    }
}
