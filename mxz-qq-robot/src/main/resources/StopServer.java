import cn.mxz.base.server.Server;
import cn.mxz.base.world.WorldFactory;

public class StopServer {

	public String run() {
		return "RUN_FOUNCTION";
	}

	/**
	 * 停服
	 * 
	 * @param server
	 */
	void stopServer() {
		Server s = WorldFactory.getWorld().getServer();
		s.stop();
	}
}