import cn.mxz.Loader;
import cn.mxz.base.world.World;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.events.Events;
import cn.mxz.events.system.ReloadPropertiesEvent;

public class ReloadWorld {

	public String run() {
		return "RUN_FOUNCTION";
	}

	void reload() {
		Loader.loadAll(); // 重新加载配置表
		Events.getInstance().dispatch(new ReloadPropertiesEvent());
		World world = WorldFactory.getWorld();
		world.reload();
	}
}