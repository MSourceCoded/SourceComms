package sourcecoded.comms.network.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Pkt0x00Ping implements ISourceCommsPacket {

	public long OnEncode, OnDecode, diff;
	
	public Pkt0x00Ping() {
		OnEncode = System.currentTimeMillis();
	}
	
	@Override
	public void encode(DataOutputStream data) throws IOException {
		data.writeLong(OnEncode);
	}

	@Override
	public void decode(DataInputStream data) throws IOException {
		OnEncode = data.readLong();
		OnDecode = System.currentTimeMillis();
	}

	@Override
	public void executeAfter() {
		diff = OnDecode - OnEncode;
		System.err.println("Delay = " + diff + "ms");
	}

}
