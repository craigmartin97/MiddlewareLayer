/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;

import Agents.Portal;
import Agents.UserAgent;
import FileUtility.FileUtility;
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
public class PortalToPortalLinks
{

    @Test
    public void testPortalToPortalLinks() throws InterruptedException
    {
        FileUtility.resetFile(new File("storedAddresses.txt"));
        Portal portalOne = new Portal("One");
        Portal portalTwo = new Portal("Two", portalOne);

        portalOne.showAddresses();

        final String actualOutput = FileUtility.readFile(new File("storedAddresses.txt"));
        final String expectedOutput = FileUtility.readFile(new File("expected-storedAddresses.txt"));
        assertTrue(expectedOutput.contains(actualOutput));
        System.out.println("-- Portal To Portal Links Are Correct --");
        System.out.println("");
    }

}
