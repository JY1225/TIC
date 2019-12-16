package com.sweii.server;
import org.apache.mina.common.ByteBuffer;
import org.apache.mina.common.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
/**
 * 
 * @author Administrator
 *
 */
public class ServerProtocolEncoder extends ProtocolEncoderAdapter {
    // private final Charset charset;
    public ServerProtocolEncoder() {
    }
    // �ڴ˴�ʵ�ֶ�MyProtocalPack���ı��빤����������д���������
    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
	String d=(String)message;
	d=d.replaceAll("o", "0");
	byte[] data = UdpUtil.hex2byte((d).getBytes());
	ByteBuffer buf = ByteBuffer.allocate(data.length);
	buf.setAutoExpand(true);
	buf.put(data);
	buf.flip();
	out.write(buf);
	out.flush();
    }
    public void dispose() throws Exception {
    }
}
