package cn.mxz.pvp;

import cn.mxz.log.Log;
import cn.mxz.protocols.user.log.LogsP.LogPro;

class LogBuilder {

	public LogPro build(Log log) {
		LogPro.Builder b = LogPro.newBuilder();
		b.setId(log.getId());
		b.setLog(log.getLog());
		return b.build();
	}

}
