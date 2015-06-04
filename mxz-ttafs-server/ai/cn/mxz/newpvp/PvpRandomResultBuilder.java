package cn.mxz.newpvp;

import cn.mxz.protocols.pvp.PvpP.PvpRandomResultPro;
import cn.mxz.protocols.pvp.PvpP.PvpUsersPro;

public class PvpRandomResultBuilder {

	public PvpRandomResultPro build(PvpUsersPro build, int remainingSec) {
		PvpRandomResultPro.Builder b = PvpRandomResultPro.newBuilder();
		b.setUsers(build);
		b.setRefreshCd(remainingSec);
		return b.build();
	}

}
