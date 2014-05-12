package sourcecoded.comms.network.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import sourcecoded.comms.network.SCSide;

public class Pkt1x03NBTCancel implements ISourceCommsPacket {

	public Pkt1x03NBTCancel() {
	}
	
	@Override
	public void encode(DataOutputStream data) throws IOException {
	}

	@Override
	public void decode(DataInputStream data) throws IOException {
	}

	@Override
	public void executeAfter(SCSide side) {
	}

}
