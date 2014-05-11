package sourcecoded.comms.network.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Pkt0x00Ping implements ISourceCommsPacket {

	public long OnEncode, OnDecode, diff, diffMS;
	
	public Pkt0x00Ping() {
	}
	
	@Override
	public void encode(DataOutputStream data) throws IOException {
		OnEncode = System.nanoTime();
		data.writeLong(OnEncode);
	}

	@Override
	public void decode(DataInputStream data) throws IOException {
		OnEncode = data.readLong();
		OnDecode = System.nanoTime();
	}

	@Override
	public void executeAfter() {
		diff = OnDecode - OnEncode;
		diffMS = diff / 1000000;
		System.err.println("Delay = " + diff + "ns");
	}

}
