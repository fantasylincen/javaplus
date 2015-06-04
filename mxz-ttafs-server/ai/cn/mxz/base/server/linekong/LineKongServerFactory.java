package cn.mxz.base.server.linekong;

class LineKongServerFactory {
	
	static LineKongServer getServer() {
		return new LineKongServerImpl();
	}
}
