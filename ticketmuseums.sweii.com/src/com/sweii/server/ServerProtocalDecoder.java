package com.sweii.server;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import org.apache.mina.common.ByteBuffer;
import org.apache.mina.common.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
public class ServerProtocalDecoder implements ProtocolDecoder {
    private static final String CONTEXT = ServerProtocalDecoder.class.getName() + ".context";
    private final Charset charset;
    private int maxPackLength = 171520;
    private int count = 0;
    public ServerProtocalDecoder() {
	this(Charset.defaultCharset());
    }
    public ServerProtocalDecoder(Charset charset) {
	this.charset = charset;
    }
    public int getMaxLineLength() {
	return maxPackLength;
    }
    public void setMaxLineLength(int maxLineLength) {
	if (maxLineLength <= 0) {
	    throw new IllegalArgumentException("maxLineLength: " + maxLineLength);
	}
	this.maxPackLength = maxLineLength;
    }
    private Context getContext(IoSession session) {
	Context ctx;
	ctx = (Context) session.getAttribute(CONTEXT);
	if (ctx == null) {
	    ctx = new Context();
	    session.setAttribute(CONTEXT, ctx);
	}
	return ctx;
    }
    public void decode(IoSession session, ByteBuffer in, ProtocolDecoderOutput out) throws Exception {
	final int packHeadLength = 11;
	//�Ȼ�ȡ�ϴεĴ��������ģ����п�����δ�����������      
	Context ctx = getContext(session);
	// �Ȱѵ�ǰbuffer�е�����׷�ӵ�Context��buffer����       
	ctx.append(in);
	//��positionָ��0λ�ã���limitָ��ԭ����positionλ��      
	ByteBuffer buf = ctx.getBuf();
	buf.flip(); //�˷�������: limit=position,position=0,mark=-1
	int totalSize = buf.remaining();// Ȼ�����ݰ���Э����ж�ȡ  
	Server server = (Server) session.getAttribute("server");
	synchronized (server) {
	    while (totalSize >= packHeadLength) {
			buf.mark(); //�˷�������:mark=position,��:mark=0;
			byte[] header = new byte[8];// ��ȡ��Ϣͷ���� 
			buf.get(header);//ȡ8���ֽ�
			totalSize = totalSize - 8;
			String head = UdpUtil.byte2hex(header).toLowerCase();
			if (head.equals("efefefefeeeeeeee")) {//��Ϣͷ
			    byte[] s = new byte[3];
			    buf.get(s);
			    totalSize = totalSize - 3;
			    String size = UdpUtil.byte2hex(s).toLowerCase();
			    size=size.toString().substring(4,6)+size.toString().substring(2,4);		    
			    int dataSize = Long.valueOf(size, 16).intValue();//���ݳ���
			    if ((totalSize >= dataSize-11)&&(dataSize-11)>0) {
					byte[] data = new byte[dataSize - 11];
					buf.get(data);
					totalSize = totalSize - dataSize;
					String message = UdpUtil.byte2hex(data).toLowerCase();
					out.write(message);
					out.flush();
					totalSize = buf.remaining();// Ȼ�����ݰ���Э����ж�ȡ
			    } else {
				   buf.reset(); //δ�������
			    }
			} else {//ͷ�����󣬽���������� 
			    buf.clear();
			}
	    }
	    if (buf.hasRemaining()) {//      
		// �������Ƶ�buffer����ǰ��       
		ByteBuffer temp = ByteBuffer.allocate(maxPackLength).setAutoExpand(true);
		temp.put(buf);
		temp.flip();
		buf.clear();
		buf.put(temp);
	    } else {// ��������Ѿ�������ϣ��������      
		buf.clear();
	    }
	}
    }
    /**
     * �������ƶ���buffer��ǰ��
     * 
     * @param buf
     */
    private void moveDateToFirst(ByteBuffer buf) {
	ByteBuffer temp = ByteBuffer.allocate(maxPackLength).setAutoExpand(true);
	temp.put(buf);
	temp.flip();
	buf.clear();
	buf.put(temp);
    }
    public void finishDecode(IoSession session, ProtocolDecoderOutput out) throws Exception {
    }
    public void dispose(IoSession session) throws Exception {
	Context ctx = (Context) session.getAttribute(CONTEXT);
	if (ctx != null) {
	    session.removeAttribute(CONTEXT);
	}
    }
    // ��¼�����ģ���Ϊ���ݴ���û�й�ģ���ܿ���ֻ�յ����ݰ���һ��
    // ���ԣ���Ҫ������ƴ�������������Ĵ���
    private class Context {
	private final CharsetDecoder decoder;
	private ByteBuffer buf;
	private int matchCount = 0;
	private int overflowPosition = 0;
	private Context() {
	    decoder = charset.newDecoder();
	    buf = ByteBuffer.allocate(80).setAutoExpand(true);
	}
	public CharsetDecoder getDecoder() {
	    return decoder;
	}
	public int getOverflowPosition() {
	    return overflowPosition;
	}
	public int getMatchCount() {
	    return matchCount;
	}
	public void setMatchCount(int matchCount) {
	    this.matchCount = matchCount;
	}
	public void reset() {
	    overflowPosition = 0;
	    matchCount = 0;
	    decoder.reset();
	}
	public ByteBuffer getBuf() {
	    return buf;
	}
	public void setBuf(ByteBuffer buf) {
	    this.buf = buf;
	}
	public void append(ByteBuffer in) {
	    getBuf().put(in);
	}
    }
}
