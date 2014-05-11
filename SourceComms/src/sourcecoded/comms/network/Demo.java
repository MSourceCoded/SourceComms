package sourcecoded.comms.network;

import sourcecoded.comms.eventsystem.EventBus;
import sourcecoded.comms.eventsystem.SourceCommsEvent;
import sourcecoded.comms.eventsystem.event.EventPacketHandled;
import sourcecoded.comms.network.packets.Pkt0x00PingRequest;
import sourcecoded.comms.network.packets.Pkt0x01PingReply;
import sourcecoded.comms.socket.SourceCommsClient;
import sourcecoded.comms.socket.SourceCommsServer;

public class Demo {
	@SuppressWarnings({ "rawtypes" })
	public static void main(String[] args) throws InterruptedException {
		EventBus.Registry.register(Demo.class);
		
		SourceCommsServer.instance().setData(1338);
		SourceCommsServer.instance().open();
		SourceCommsServer.instance().setListeningState(true);
		SourceCommsServer.instance().listen();
		
		SourceCommsClient.instance().setData("localhost", 1338);
		SourceCommsClient.instance().connect();
		SourceCommsClient.instance().setListeningState(true);
		SourceCommsClient.instance().listen();
		
		Thread.sleep(1000);
		Pkt0x00PingRequest packetC = new Pkt0x00PingRequest();
		SourceCommsServer.instance().sendToClient(packetC);
		Thread.sleep(1000);
		Pkt0x00PingRequest packetS = new Pkt0x00PingRequest();
		SourceCommsClient.instance().sendToServer(packetS);
	}
	
	@SourceCommsEvent
	public void onPacketHandled(EventPacketHandled e) {
		System.err.println("Pkt Received");
		if (e.getPacket() instanceof Pkt0x01PingReply) {
			Pkt0x01PingReply pi = (Pkt0x01PingReply) e.getPacket();
			System.err.println(pi.diff);
		}
	}
}
