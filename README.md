SourceComms
===========

The main communication library for my cross-platform applications

_____________
Documentation
-------------

### Packets
  By default SourceComms comes with a few packets. You can create a new packet by calling ```new PacketName();```. To send a packet from the client to the server, call ```SourceCommsClient.instance().sendPacketToServer();```. To send a packet from the server to the client, call ```SourceCommsServer.instance().sendPacketToClient();```
  
  **NOTE:** I would recommed waiting for the EventServerReady or EventClientReady events before sending these packets, it will make the program a lot more efficient and eliminate the small chance of packet-sending failure
  
  To Create your own packet, create a new class and make it implement ISourceCommsPacket. Use reference from the stock packets to get an idea of how to handle encodes, decodes, etc. Next open SourceCommsPacketHandler and go to the ```SourceCommsPacketCodec``` subclass. In the constructor, call ```addDiscriminator(id, packetclass)``` and that should be all you need to do.

### Events
  To ensure this Comms library can communicate cross-platform, packet handling is done via an Event System. These are the events that come with SourceComms
  
  * **EventPacketHandled**
    * This is called once a packet has been decoded. Call ```event.getPacket();``` to retrieve the packet.
  
  * **EventClientClosed / EventServerClosed**
    * These events are sent whenever a server/client is closed, usually with an error. Each of these has different variables, I recommend you check the class for more details.

  In order to listen for an event, simply apply the @SourceCommsEvent annotation to any method with a single parameter of the Event.
  e.g.
    ```java
        @SourceCommsEvent
        public void listen(EventPacketHandled e) {
          //Packet received
        }

### Other
  That should be just about all you need to know before you dive into the SourceComms library. A standard init procedure is listed below. For extra info, I recommend you read the SRC. If you need help with anything in particular, contact me on IRC. #SourceCoded on irc.esper.net. More details about me and my work can be found here: http://www.mrsourcecoded.com/
  
### Initialization Procedure
  * **1: Rename Packages**
    * Replace SourceCoded in the package names to something else. This ensures nothing conflicts
    
  * **2: Initialize Comms**
    * Call ```SourceComms.init();``` to initialize everything the CommsLibrary needs on startup. Call this **BEFORE** anything else
  
  * **3: Register your handlers**
    * Call ```EventBus.Registry.register(yourEventHandlerClass)```  to register a class holding a SourceCommsEvent annotation to register it as an event handler
  
  * **4: Server**
    * Here is where the action takes place. Call ```SourceCommsServer.instance()``` to gain access to the server instance. Here you can trigger methods relating to server operation.
    
  * **5: Client**
    * Call ```SourceCommsClient.instance()``` to gain access to the client instance. Here you can trigger methods relating to client operation.
