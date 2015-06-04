package cn.javaplus.commons.socket;

import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

public interface MySocket extends ISocket{

	public abstract long getLastReceivingTime();

	public abstract int getPort();

	public abstract ConcurrentLinkedQueue<byte[]> getBufferList();

	public abstract void write(String message) throws IOException;

}