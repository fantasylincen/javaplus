package experiment.xoevent;

import java.util.ArrayList;
import java.util.List;


/**
 * 事件源定义  
 * @author DXF
 *
 */
public class XOEventSource {

	//触发DemoEvent事件。
	public void fireEvent(){
		XOEvent event = new XOEvent(this);
		broadcast(event);
	}
	
	// 监听器列表  
	private List<XOListener> listenerList = new ArrayList<XOListener>();
	
	// 监听器注册方法
	public void addListener(XOListener listener) {
		listenerList.add(listener);
	}
	
	// 监听器注销方法  
	public void removeListener(XOListener listener) {
		listenerList.remove(listener);
	}
	
	// 事件广播方法
	public void broadcast(XOEvent event) {
		for(XOListener listener : listenerList)
			listener.eventHandle(event);
	}
	
}
