/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;

import Agents.NodeMonitor;
import Agents.Portal;
import Agents.UserAgent;
import FileUtility.FileUtility;
import Messages.Message;
import Messages.MessageType;
import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author q5031372
 */
public class PortalMessaging {

    public static UserAgent one;
    public static UserAgent two;
    public static UserAgent three;

    Portal onePortal = new Portal("HeadPortal");

    @BeforeClass
    public static void setUpClass() {
        Portal onePortal = new Portal("HeadPortal");

        one = new UserAgent("one", onePortal, onePortal);
        two = new UserAgent("two", onePortal, onePortal);
        three = new UserAgent("three", onePortal, onePortal);

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Exchanges messages from agent one to two
     * @throws InterruptedException 
     */
    @Test
    public void testMessageExchangingOne() throws InterruptedException {
        System.out.println("");
        FileUtility.resetFile(new File("log.txt"));
        one.sendMessage("two", "I'm UserAgent One and I want to wish UserAgent Two a good day");
        final String actualOutput = FileUtility.readFile(new File("log.txt"));
        final String expectedOutput = FileUtility.readFile(new File("expected-localtest.txt"));
        assertTrue(expectedOutput.contains(actualOutput));
        System.out.println("-- SENDING A MESSAGE FROM AGENT ONE TO AGENT TWO SUCCESSFUL --");
        System.out.println("");

    }

    /**
     * Exchanges messages from agent one to three
     * @throws InterruptedException 
     */
    @Test
    public void testMessageExchangingTwo() throws InterruptedException {
        System.out.println("");
        FileUtility.resetFile(new File("log.txt"));
        System.out.println("Begin Testing");
        one.sendMessage("three", "I'm UserAgent One and I want to wish UserAgent Three a good day");
        final String actualOutput = FileUtility.readFile(new File("log.txt"));
        final String expectedOutput = FileUtility.readFile(new File("expected-localtest.txt"));
        assertTrue(expectedOutput.contains(actualOutput));
        System.out.println("-- SENDING A MESSAGE FROM AGENT ONE TO AGENT THREE SUCCESSFUL --");
        System.out.println("");

    }

    /**
     * Exchanges message from agent two to three
     * @throws InterruptedException 
     */
    @Test
    public void testMessageExchangingThree() throws InterruptedException {
        System.out.println("");
        FileUtility.resetFile(new File("log.txt"));
        two.sendMessage("one", "I'm UserAgent Two and I want to wish UserAgent One a good day");
        final String actualOutput = FileUtility.readFile(new File("log.txt"));
        final String expectedOutput = FileUtility.readFile(new File("expected-localtest.txt"));
        assertTrue(expectedOutput.contains(actualOutput));
        System.out.println("-- SENDING A MESSAGE FROM AGENT TWO TO AGENT ONE SUCCESSFUL --");
        System.out.println("");

    }

    /**
     * Exchanges message from agent two to one
     * @throws InterruptedException 
     */
    @Test
    public void testMessageExchangingFour() throws InterruptedException {
        System.out.println("");
        FileUtility.resetFile(new File("log.txt"));
        two.sendMessage("three", "I'm UserAgent Two and I want to wish UserAgent Three a good day");
        final String actualOutput = FileUtility.readFile(new File("log.txt"));
        final String expectedOutput = FileUtility.readFile(new File("expected-localtest.txt"));
        assertTrue(expectedOutput.contains(actualOutput));
        System.out.println("-- SENDING A MESSAGE FROM AGENT TWO TO AGENT THREE SUCCESSFUL --");
        System.out.println("");
    }

    /**
     * Exchanges message from agent three to one
     * @throws InterruptedException 
     */
    @Test
    public void testMessageExchangingFive() throws InterruptedException {
        System.out.println("");
        FileUtility.resetFile(new File("log.txt"));
        three.sendMessage("one", "I'm UserAgent Three and I want to wish UserAgent One a good day");
        final String actualOutput = FileUtility.readFile(new File("log.txt"));
        final String expectedOutput = FileUtility.readFile(new File("expected-localtest.txt"));
        assertTrue(expectedOutput.contains(actualOutput));
        System.out.println("-- SENDING A MESSAGE FROM AGENT THREE TO AGENT ONE SUCCESSFUL --");
        System.out.println("");
    }

    /**
     * Exchanges message from agent three to two
     * @throws InterruptedException 
     */
    @Test
    public void testMessageExchangingSix() throws InterruptedException {
        System.out.println("");
        FileUtility.resetFile(new File("log.txt"));
        three.sendMessage("two", "I'm UserAgent Three and I want to wish UserAgent Two a good day");
        final String actualOutput = FileUtility.readFile(new File("log.txt"));
        final String expectedOutput = FileUtility.readFile(new File("expected-localtest.txt"));
        assertTrue(expectedOutput.contains(actualOutput));
        System.out.println("-- SENDING A MESSAGE FROM AGENT THREE TO AGENT TWO SUCCESSFUL --");
        System.out.println("");
    }

    /**
     * Test portal to portal message
     */
    @Test
    public void testPortalToPortalMessages() {
        System.out.println("");
        FileUtility.resetFile(new File("log.txt"));

        Portal HeadPortal = new Portal("HeadPortal");

        // tier 1
        Portal one = new Portal("One", HeadPortal);
        Portal two = new Portal("Two", HeadPortal);

        // tier 2
        Portal three = new Portal("Three", one);
        Portal four = new Portal("Four", two);

        // a random floating portal
        Portal P6 = new Portal("6", null);
        Portal P7 = new Portal("7", P6);

        // creating some user agents
        UserAgent UA1 = new UserAgent("UA1", one, HeadPortal);
        UserAgent UA2 = new UserAgent("UA2", two, HeadPortal);
        UserAgent UA3 = new UserAgent("UA3", four, four);
        UserAgent UA4 = new UserAgent("UA4", four, four);

        // User Agent 1 sending a message to a portal
        UA1.sendMessage("UA2", "This Message Has Sent Through Different Portals");
        System.out.println("");
        System.out.println("");

        final String actualOutput = FileUtility.readFile(new File("log.txt"));
        final String expectedOutput = FileUtility.readFile(new File("expected-portalToPortalMessage.txt"));
        assertTrue(expectedOutput.contains(actualOutput));
        System.out.println("-- SENDING BETWEEN DIFFERENT AGENTS WORKING CORRECTLY --");
        System.out.println("");
    }

    /**
     * Test error message is working when recipient doesnt exist
     */
    @Test
    public void sendMessageToUncreatedAgent() {
        System.out.println("");
        FileUtility.resetFile(new File("errorLog.txt"));
        
        Portal P1 = new Portal("P1");
        UserAgent UA1 = new UserAgent("UA1", P1, P1);
        UserAgent UA2 = new UserAgent("UA2", P1, P1);

        UA1.sendMessage("RANDOMAGENT", "THIS WILL PRODUCE AN ERROR MESSAGE");
        
        final String actualOutput = FileUtility.readFile(new File("errorLog.txt"));
        final String expectedOutput = FileUtility.readFile(new File("expected-errorLog.txt"));
        assertTrue(expectedOutput.contains(actualOutput));
        System.out.println("-- SUCCESSFULLY CREATED AN ERROR MESSAGE WHEN AGENT DOESNT EXIST --");
        System.out.println("");
    }
    
    /**
     * Test error message when sending to agent out of scope
     */
    @Test
    public void sendToOutOfScopeAgent()
    {
        System.out.println("");
        FileUtility.resetFile(new File("errorLog.txt"));
        System.out.println("");
        
        Portal P1 = new Portal("P1");
        Portal P2 = new Portal("P2");
        
        UserAgent UA1 = new UserAgent("UA1",P1,P1);
        UserAgent UA2 = new UserAgent("UA2",P2,P2);
        
        UA1.sendMessage("UA2", "UA2 Is Out Of Scope, Won't Send");
        
        final String actualOutput = FileUtility.readFile(new File("errorLog.txt"));
        final String expectedOutput = FileUtility.readFile(new File("expected-errorLogScope.txt"));
        assertTrue(expectedOutput.contains(actualOutput));
        
        System.out.println("-- SUCCESSFULLY DIDNT SEND MESSAGE AS OUT OF SCOPE --");
        System.out.println("");
        
    }
    
    /**
     * Test creation of node monitor
     * @throws InterruptedException 
     */
    @Test
    public void defaultNodeMonitor() throws InterruptedException {
        System.out.println("");
            Portal portalOne = new Portal("portalOne");
            UserAgent agentOne = new UserAgent("agentOne", portalOne, null);
            UserAgent agentTwo = new UserAgent("agentTwo", portalOne, null);
            
            agentOne.sendMessage("agentTwo", "Node monitor test message.");
            System.out.println("-- SUCCESS For The Node Monitor --");    
    }
   
}
