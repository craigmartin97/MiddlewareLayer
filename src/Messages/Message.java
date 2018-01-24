/*
 * Class that defines what a message contains the type of message,
 * Who the message is to, who it's from and the data and time it was sent
 */
package Messages;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * A generic class with a type parameter called M Enables stronger type checks
 * and no casting needed The T is a standard naming convention meaning type
 *
 * @author Group H
 * @param <T>
 */
public class Message<T>
{

    /**
     * object that is being sent out
     */
    private final T messageObject;

    /**
     * message sender
     */
    private final String from;

    /**
     * message going to
     */
    private final String to;

    /**
     * type of message, i.e. error etc
     */
    private final MessageType type;

    /**
     * date the message was sent
     */
    private final LocalDate dateSent;

    /**
     * time the message was sent
     */
    private final LocalTime timeSent;

    /**
     * Constructor - creates a new instance of a message Date and Time are set
     * to the current time of that location
     *
     * @param type - type of message
     * @param from - sender
     * @param to - recipient
     * @param messageObject - message object
     */
    public Message(MessageType type, String from, String to, T messageObject)
    {
        this.from = from;
        this.to = to;
        this.type = type;
        this.messageObject = messageObject;
        dateSent = LocalDate.now();
        timeSent = LocalTime.now();
    }

    /**
     * Method returns message item
     *
     * @return - messageObject
     */
    public T getMessageItem()
    {
        return messageObject;
    }

    /**
     * Method returns the type of message being sent
     *
     * @return - type
     */
    public MessageType getType()
    {
        return type;
    }

    /**
     * Method returns who the message was sent from
     *
     * @return - from
     */
    public String getFrom()
    {
        return from;
    }

    /**
     * Method returns who the message is being sent to
     *
     * @return - to
     */
    public String getTo()
    {
        return to;
    }

    /**
     * Method returns the date the message was sent
     *
     * @return - dateSent
     */
    public LocalDate getDate()
    {
        return dateSent;
    }

    /**
     * Method returns the time the message was sent
     *
     * @return - timeSent
     */
    public LocalTime getTimeSent()
    {
        return timeSent;
    }
}
