package cn.javaplus.crazy.main;

import cn.javaplus.crazy.Protocols.OtherP;
import cn.javaplus.crazy.Protocols.PlayerP;

public class OtherPAdaptor implements PlayerInfo {

	private PlayerP data;

	public OtherPAdaptor(OtherP left) {
		data = left.getPlayer();
	}

	public String getNick() {
		return data.getNick();
	}

}
