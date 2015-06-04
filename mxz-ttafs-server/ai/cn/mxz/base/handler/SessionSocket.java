package cn.mxz.base.handler;

import java.io.IOException;
import java.nio.ByteBuffer;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import com.lemon.commons.socket.ISocket;

class SessionSocket implements ISocket {

	private IoSession	session;

	SessionSocket(IoSession session) {

		this.session = session;
	}

	@Override
	public long getId() {
		return session.getId();
	}

	@Override
	public String getIP() {

		String ip = session.getRemoteAddress() + "";

		return ip.replaceAll("/", "").replaceAll(":.*", "");
	}

	@Override
	public void write(ByteBuffer data) throws IOException {

		IoBuffer b = IoBuffer.allocate(data.limit());

		b.put(data);

		b.flip();

		session.write(b);
	}

	@Override
	public void close() {

		boolean immediately = true;

		session.close(immediately );
	}

	@Override
	public int hashCode() {

		Long v = session.getId();

		return v.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SessionSocket other = (SessionSocket) obj;
		if (session == null) {
			if (other.session != null)
				return false;
		} else if (session.getId() != other.session.getId())
			return false;
		return true;
	}

}
