/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grouphmiddlewareproject;

import Agents.Portal;
import Agents.UserAgent;

/**
 *
 * @author CGroup H
 */
public class GroupHMiddlewareProject
{

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException
    {
//        Portal HeadPortal = new Portal("HeadPortal");
//
//        // tier 1
//        Portal one = new Portal("PortalOne", HeadPortal);
//        Portal two = new Portal("PortalTwo", HeadPortal);
//
//        // tier 2
//        Portal three = new Portal("PortalThree", one);
//        Portal four = new Portal("PortalFour", two);
//
//        // a random floating portal
//        Portal P6 = new Portal("6", null);
//        // attaching a portal to the floating random one
//        Portal P7 = new Portal("7", P6);
//
//        NodeMonitor nm = new NodeMonitor("NM1");
//        nm.offerToQueue(new Message(MessageType.ADD_NODE_MONITOR, "", "NM1", two));
//
//        // creating some user agents
//        UserAgent UA1 = new UserAgent("UA1", one, HeadPortal);
//        UserAgent UA2 = new UserAgent("UA2", two, HeadPortal);
//        UserAgent UA3 = new UserAgent("UA3", four, four);
//        UserAgent UA4 = new UserAgent("UA4", four, four);
//
//        //UA1.sendMessage("UA2", "I'm the best");
//        HeadPortal.showAddresses();
//        System.out.println("");
//        System.out.println("");
//        one.showAddresses();
//        System.out.println("");
//        System.out.println("");
//        two.showAddresses();
//
//        System.out.println("");
//        System.out.println("");
//        four.showAddresses();
//        System.out.println("");
//        System.out.println("");
//
//        // User Agent 1 sending a message to a UA2
//         UA1.sendMessage("UA2", "I'm UserAgent One and I want to wish UserAgent Two a good day");
//        
//        // Example of UA3 , locally scoped to Portal Four, Sending a message to UA1, Outside of scope
//        // UA3.sendMessage("UA1", "Messae");
//
//        // Example of UA1 unsuccessfully sending a message to UA4 as its locally scope to portal four
//        // UA1.sendMessage("UA4", "This Will Never Work");
        
        
        ///////////////////////////////////////////////////////////////////////////////////////////////
        
        
        
        Portal HeadPortal = new Portal("HeadPortal");

        // tier 1
        Portal one = new Portal("PortalOne", HeadPortal);
        Portal two = new Portal("PortalTwo", HeadPortal);

        // tier 2
        Portal three = new Portal("PortalThree", one);
        Portal four = new Portal("PortalFour", two);

        // a random floating portal
        Portal P6 = new Portal("6", null);
        // attaching a portal to the floating random one
        Portal P7 = new Portal("7", P6);

        //NodeMonitor nm = new NodeMonitor("NM1");
        //nm.offerToQueue(new Message(MessageType.ADD_NODE_MONITOR, "", "NM1", two));

        // creating some user agents
        UserAgent UA1 = new UserAgent("UA1", one, HeadPortal);
        UserAgent UA2 = new UserAgent("UA2", two, HeadPortal);
        UserAgent UA3 = new UserAgent("UA3", four, four);
        UserAgent UA4 = new UserAgent("UA4", four, four);

        //UA1.sendMessage("UA2", "I'm the best");
        HeadPortal.showAddresses();
        System.out.println("");
        System.out.println("");
        one.showAddresses();
        System.out.println("");
        System.out.println("");
        two.showAddresses();

        System.out.println("");
        System.out.println("");
        four.showAddresses();
        System.out.println("");
        System.out.println("");

        /**
         * Sending ten thousand messages around the system
         * Thread sleep added to slow the message speeds down just to
         * be able to see the messages change on the node monitor
         */
        for(int i = 0; i < 100000; i++)
        {
            UA1.sendMessage("UA2", "I'm UserAgent One and I want to wish UserAgent Two a good day " + " Message Number: " + i);
            Thread.sleep(200);
            UA1.sendMessage("UA2", "Hello their this message is important : "  + i);
            Thread.sleep(200);
            UA3.sendMessage("UA4","Hello I'm User Agent 3 Sending To Agent 4 " + i);
            Thread.sleep(200);
            UA3.sendMessage("UA4","UA4 Is Going To Receive This Message!!!! " + i);
        } 
         
        
        // Example of UA3 , locally scoped to Portal Four, Sending a message to UA1, Outside of scope
        // UA3.sendMessage("UA1", "UA3 Is Local To Portal Four But Can Send To Agent UA1");

        // Example of UA1 unsuccessfully sending a message to UA4 as its locally scope to portal four
        // UA1.sendMessage("UA4", "This Will Never Work");
        
        /////////////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////////////////////
        
        
        // ANOTHER TEST TO PROVE IT WORKS
        // evidenceStillWorks.txt view it to see outputs are the same and manually check
        
//        Portal p1 = new Portal("PortalOne");
//        Portal p2 = new Portal("PortalTwo",p1);
//        Portal p3 = new Portal("PortalThree",p1);
//        
//
//        
//        NodeMonitor nm = new NodeMonitor("NM1");
//        nm.offerToQueue(new Message(MessageType.ADD_NODE_MONITOR, "", "NM1", p1));
//        
//        UserAgent UA1 = new UserAgent("UA1", p1, p1);
//        UserAgent UA2 = new UserAgent("UA2", p2, null);
//        
//                p1.showAddresses();
//        System.out.println("");
//        System.out.println("");
//        p2.showAddresses();
//        System.out.println("");
//        System.out.println("");
//        
//        
//        UA1.sendMessage("UA2", "Hey");
        
        
        
    }
}
