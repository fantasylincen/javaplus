import cn.mxz.system.SystemCounter;
import cn.mxz.system.SystemCounterKey;

public class SetNvwaCount {

	public String run() {
		return "RUN_FOUNCTION";
	}

	public String set(String count) {
		int c = new Integer(count);
		SystemCounter.getInstance().set(SystemCounterKey.NVWA_BOUGHT_COUNT, c);
		return "设置成功, 当前购买数量为" + c;
	}
}