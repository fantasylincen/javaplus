package cn.mxz.testbase;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

import cn.mxz.util.debuger.Debuger;

import com.lemon.commons.socket.ISocket;

public class TestSocket implements ISocket {

	private static AtomicLong ids = new AtomicLong();

	private long id;

	public TestSocket() {
		id = ids.addAndGet(1);
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public String getIP() {
		return "127.0.0.1";
	}

	@Override
	public void write(final ByteBuffer data) throws IOException {
		Debuger.debug("测试套接字发送数据:" + Arrays.toString(data.array()));
	}

	@Override
	public void close() {
		Debuger.debug("测试套接字关闭了!");
	}

	@Override
	public String toString() {
		return "TestSocket:" + getId();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TestSocket other = (TestSocket) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
