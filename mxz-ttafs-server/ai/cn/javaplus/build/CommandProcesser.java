package cn.javaplus.build;

import java.io.InputStream;
import java.util.List;

import cn.javaplus.io.LinesReader;
import cn.javaplus.log.Log;
import cn.javaplus.util.Closer;
import cn.javaplus.util.Util;
import cn.mxz.events.EventDispatcher;
import cn.mxz.events.Listener;

import com.google.common.collect.Lists;

/**
 * 命令行工具, 用于构建项目
 * 
 * @author 林岑
 * 
 */
public class CommandProcesser implements Runnable {

	public static class EventsManager {
	
		private static EventsManager instance;
	
		private EventDispatcher dispatcher = new EventDispatcher();
	
		private EventsManager() {
		}
	
		/**
		 * 获得事件分发器实例 如果你想增加lisenter 那么请加载init方法里面, 外部是不允许随意增加监听器的
		 * 
		 * @return
		 */
		public static final EventsManager getInstance() {
			if (instance == null) {
				instance = new EventsManager();
			}
			return instance;
		}
	
		public void dispatchEvent(Object e) {
			dispatcher.dispatch(e);
		}
	
		<T> void addListener(Class<T> c, Listener<T> listener) {
			dispatcher.addListener(c, listener);
		}
	}

	private List<Command> commands;

	public CommandProcesser() {
		commands = Lists.newArrayList();
		addListeners();
	}

	public EventsManager getEventsManager() {
		return EventsManager.getInstance();
	}

	private static void addListeners() {
		EventsManager e = EventsManager.getInstance();
		e.addListener(ErrorEvent.class, new ThrowException());
		e.addListener(InfoEvent.class, new PrintData());
	}

	public void start() {
		new Thread(this).start();
	}

	public void append(String cmd, String path) {
		commands.add(new Command(cmd, path));
	}

	@Override
	public void run() {

		for (int i = 0; i < commands.size() - 1; i++) {
			Command c = commands.get(i);
			c.setCommandOverCommand(commands.get(i + 1));
		}

		commands.get(0).process();
	}

}

abstract class AbstractReader {

	protected Process exec;

	public AbstractReader(Process exec) {
		this.exec = exec;
	}

	public void run() {
		InputStream is = getStream();

		LinesReader reader = null;

		List<String> ls;
		try {
			reader = new LinesReader(is);
			ls = Lists.newArrayList();
			for (String line : reader) {

				ls.add(line);
			}
		} catch (Exception e) {
			throw Util.Exception.toRuntimeException(e);
		} finally {
			Closer.close(reader);
		}

		dispatchEvent(ls);
	}

	protected abstract InputStream getStream();

	protected abstract void dispatchEvent(List<String> ls);
}

class PrintData implements Listener<InfoEvent> {

	@Override
	public void onEvent(InfoEvent e) {
		List<String> infos = e.getInfos();
		for (String info : infos) {
			Log.d(info);
		}
	}

}
