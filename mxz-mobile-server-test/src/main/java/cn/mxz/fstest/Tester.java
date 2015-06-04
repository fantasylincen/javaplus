package cn.mxz.fstest;
import java.net.Socket;

import cn.javaplus.comunication.PacketC2S;
import cn.javaplus.util.Util;

import com.lemon.commons.socket.ISocket;

public class Tester extends Thread {

	private int	index;

	public Tester(int index) {
		this.index = index;
	}

	public static void main(String[] args) {

		ID = "u" + Util.Random.getRandomString(8);

		for (int i = 0; i < 1000; i++) {

			new Tester(i).start();

			System.out.println("Tester.main() ÈËÊý:" + i);

			Util.Thread.sleep(100 + Util.Random.get(0, 40));

		}
	}

	private Socket			socket;
	private static String	ID;

	@Override
	public void run() {
		try {
			 socket = new Socket("localhost", 31510);
//			socket = new Socket("115.182.54.92", 21510);
//			 socket = new Socket("115.182.54.92", 21510);

			// new ReadThread(socket).start();
			String id = ID.toLowerCase() + index;
			access(id, "", 1, "", 1, 1, "");
			Util.Thread.sleep(Util.Random.get(0, 1000) + 1000);
			setNick("nick:" + id);
			Util.Thread.sleep(Util.Random.get(0, 1000) + 1000);
			createUser(300006, "");
			Util.Thread.sleep(Util.Random.get(0, 1000) + 1000);
			while (true) {
				for (int i = 0; i < 5; i++) {
					getData();
					Util.Thread.sleep(Util.Random.get(0, 1000) + 4500);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getData() {
		PacketC2S p = new PacketC2S();
		p.putInt(430000);
		send(p);
	}

	public void access(String id, String token, int adultState, String mac, int clientType, int UnixTime, String otherValue) {

		PacketC2S p = new PacketC2S();
		p.putInt(210000);
		p.putString(id);
		p.putString(token);
		p.putInt(adultState);
		p.putString(mac);
		p.putInt(clientType);
		p.putInt(UnixTime);
		p.putString(otherValue);

		send(p);
	}

	private void send(PacketC2S p) {
		ISocket s = new ISocketImpl2(socket);
		p.send(s);
	}

	public void setNick(String nick) {

		PacketC2S p = new PacketC2S();
		p.putInt(210001);
		p.putString(nick);

		send(p);
	}

	public void create(String invitationCode) {

		PacketC2S p = new PacketC2S();
		p.putInt(210002);
		p.putString(invitationCode);

		send(p);
	}

	public void setUserType(int fighterTypeId) {

		PacketC2S p = new PacketC2S();
		p.putInt(210003);
		p.putInt(fighterTypeId);

		send(p);
	}

	public void createUser(int fighterTypeId, String invitationCode) {

		PacketC2S p = new PacketC2S();
		p.putInt(210004);
		p.putInt(fighterTypeId);
		p.putString(invitationCode);

		send(p);
	}

	public void resetUser() {

		PacketC2S p = new PacketC2S();
		p.putInt(210005);

		send(p);
	}

}
