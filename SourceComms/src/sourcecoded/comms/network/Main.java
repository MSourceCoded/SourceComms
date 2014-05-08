package sourcecoded.comms.network;

import sourcecoded.comms.eventsystem.EventBus;
import sourcecoded.comms.eventsystem.SourceCommsEvent;
import sourcecoded.comms.eventsystem.event.EventPacketHandled;
import sourcecoded.comms.exception.ErrorHandler;
import sourcecoded.comms.network.packets.Pkt0x00Ping;
import sourcecoded.comms.network.packets.Pkt1x00Player;
import sourcecoded.comms.socket.SourceCommsClient;
import sourcecoded.comms.socket.SourceCommsServer;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		EventBus.Registry.register(Main.class);
		EventBus.Registry.register(ErrorHandler.class);
		
		SourceCommsServer.instance().setData(1337);
		SourceCommsServer.instance().open();
		SourceCommsServer.instance().setListeningState(true);
		SourceCommsServer.instance().listen();
		
		SourceCommsClient.instance().setData("localhost", 1337);
		SourceCommsClient.instance().connect();
		SourceCommsClient.instance().setListeningState(true);
		SourceCommsClient.instance().listen();
		
		
		Pkt0x00Ping packet = new Pkt0x00Ping();
		SourceCommsServer.instance().sendToClient(packet);
		SourceCommsClient.instance().sendToServer(packet);
		
	}
	
	@SourceCommsEvent
	public void onPacketHandled(EventPacketHandled e) {
		if (e.getPacket() instanceof Pkt1x00Player) {
			System.err.println("This should only trigger on Pkt1x00");
			System.err.println(((Pkt1x00Player)e.getPacket()).target);
		} else {
			System.err.println("Not a player packet");
		}
	}
}
