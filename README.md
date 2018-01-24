# MiddlewareLayer
A sub-system that enables applications to rest upon interconnected Meta-Agents. Allows communication between agents



GroupHMiddlewareProject Version 1.1 12/01/2018

The GroupHMiddlwareProject is a messaging system. Middleware is software that sits between an operating system and the applications running on it. 
Essentially, it glues things together to allow message communication. 
The GroupHMiddlewareProject is a sub-system that will enable applications to rest upon interconnected Meta-Agents. These Meta-Agents encapsulate behaviour and provide communication. 

-------------------------------------------------------------------------------------------

Prerequisites
To successfully run the software an integrated development environment is needed (IDE) preferably NetBeans.

-------------------------------------------------------------------------------------------

Installing
To download the GroupHMiddlewareProject, log onto Blackboard and download the zip folder. Then unzip that folder onto your local computer and open in an IDE. 

-------------------------------------------------------------------------------------------

Running The Main Test
1) Press the green button on the top tab to run the code which will start running the main class. 
   Once pressed multiple GUI monitors will appear on the screen, just move them around until you can see them all. 
   They will be one monitor for each portal that has been created.
2) Three GUI monitors will show two messages, being sent one hundred thousand times, from UA1 (UserAgent 1) to UA2 (UserAgent 2).
3) The GUI monitor contains seven fields which show the following
   - Watching Node - A drop-down menu that shows nodes that are being watched
   - Message Object - The message that is being sent
   - Message Type - The type of message that is being sent, e.g., String, Meta-Agent
   - Message Time - The time the message was received
   - Message Date - The date the message was received
   - Message From - The recipient of the message
   - Message To - The sender of the message

-------------------------------------------------------------------------------------------

Running Tests In The Test Packages
1) Navigate to Test Packages/Testing and open both of the files, "PortalMessaging.java" and "PortalToPortalLinks.java."
2) Once they are both opened in the IDE, right click on the code and select "Run File" which will run the tests in the file they are:
   - Sending messages from a single portal
   - Sending messages from portal to another portal
   - Sending to an uncreated agent
   - Sending to an agent out of scope
   - The creation of a node monitor
   - Creating portal to portal links

Message outputs at the bottom and the percentage bar at the bottom display if the tests where successful.
Please note that the main code can't be running while running these tests.

Creating your Portals and UserAgents

Portal HeadPortal = new Portal("HeadPortal"); Is a portal called "HeadPortal" which has no parent

Portal three = new Portal("PortalThree", one); This is an example of how to create a new portal. 
The first string in the parameter is the name of that portal. 
The second parameter is the parent of that portal.
Portals can have parents or no parents; it's up to the user to decide.

UserAgent UA1 = new UserAgent("UA1", one, HeadPortal); 
This is an example of how to create a new User-Agent. The first parameter is the name of the User-Agent. 
The second is the User-Agents parent which is always a portal.
The third parameter is the scope of the agent which will always be a portal.

Open the dist folder and run the GroupHMiddlewareProject.jar file to run the program.

-------------------------------------------------------------------------------------------

Code and Styling
To view, any of the source code select the file from the Source Packages folder, open a sub-folder then the file.

Please note, the file named NodeMonitorOld.java is no longer in use but is still included as a legacy document and
for viewers to be able to how the creation of a node monitor was previously created.
The FileUtility package contains a file called FileUtility.java. This class allows messagges to be 
written and read from files as well as clearing them. This isnt part of the middleware layer itself but
is used for testing purposes to store messages sent and compare them to another file. 

The code styling used is camel case and is java documented throughout.

-------------------------------------------------------------------------------------------

Built With
NetBeans - The IDE used for development

-------------------------------------------------------------------------------------------

Authors of the Software & Email Addresses: 
Craig Martin - Q5031372@tees.ac.uk
Oliver McBurney - S6042911@tees.ac.uk
Matthew Pull - P4031506@tees.ac.uk
Martin Shek - S6281752@tees.ac.uk
Liam Cullen - S6281752@tees.ac.uk

-------------------------------------------------------------------------------------------

Acknowledgments

Thanks, Simon Lynch for the guidance, lectures and source material provided.

