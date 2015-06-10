package experiment.xoevent;

import java.util.EventListener;

/**
 * 监听器接口定义
 */
public interface XOListener extends EventListener{
	public void eventHandle(XOEvent e);
}
